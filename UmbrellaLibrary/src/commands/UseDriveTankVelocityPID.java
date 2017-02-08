package commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import interfaces.IDrive;
import interfaces.RobotInterface;

public class UseDriveTankVelocityPID extends TestableCommand {

	// Written to by the pid controllers. This class uses these values to set
	// the speed
	// of the drive
	private volatile double leftMotorOutput = 0;
	private volatile double rightMotorOutput = 0;

	/**
	 * Helper class for setting the left motor values
	 * 
	 * @author Kyle
	 *
	 */
	private class SetLeftMotorOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			leftMotorOutput = output;
		}

	}

	/**
	 * Helper class for setting the right motor values
	 * 
	 * @author Kyle
	 *
	 */
	private class SetRightMotorOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			rightMotorOutput = output;
		}

	}

	// PID Constants, Velocity Control with the PIDController objects don't use
	// an I term
	private double left_kP = 0;
	private double left_kD = 0;
	private double left_kF = 0;

	private double right_kP = 0;
	private double right_kD = 0;
	private double right_kF = 0;

	// Various constants
	private final double MAX_SPEED_LIMIT_SCALING = 1.2; // Multiply max_speed by
														// this to give some
														// leeway in the input
														// range
	private final double SPEED_PERCENT_TOLERANCE = 2;
	private final int BUFFER_SIZE = 20; // Increase to increase stability,
										// decrease to increase reactivity

	// If the joystick is close to the middle, set it to 0
	private final double JOYSTICK_DEADZONE = 0.1;

	// If the joystick values are close to each other, synch them
	private final double SYNCH_TOLERANCE = 0.1;

	/**
	 * Represents the maximum speed of the robot being operated. This variable
	 * is in physical terms, not in terms of the motor value
	 */
	private double maxSpeed;
	private double maxInput;

	private PIDController leftDriveController;
	private PIDController rightDriveController;
	IDrive drive;

	public UseDriveTankVelocityPID(RobotInterface robot, IDrive system, double maxSpeed) {
		super(robot, system);
		drive = system;

		this.maxSpeed = maxSpeed;
		this.maxInput = maxSpeed * MAX_SPEED_LIMIT_SCALING;

		leftDriveController = new PIDController(left_kP, 0, left_kD, left_kF, drive.getLeftEncoder(),
				new SetLeftMotorOutput());

		rightDriveController = new PIDController(right_kP, 0, right_kD, right_kF, drive.getRightEncoder(),
				new SetRightMotorOutput());

		robot.getDashboard().putData("ULIB UseDriveVelocity Left Controller", leftDriveController);
		robot.getDashboard().putData("ULIB UseDriveVelocity Right Cpontroller", rightDriveController);

	}

	@Override
	protected void initialize() {

		// Make sure the sensors output rates
		drive.getLeftEncoder().setPIDSourceType(PIDSourceType.kRate);
		drive.getRightEncoder().setPIDSourceType(PIDSourceType.kRate);

		// Configure PID controllers. Assumes that the drive max speed is the
		// same forwards and backwards
		leftDriveController.setOutputRange(-1, 1);
		leftDriveController.setInputRange(-(maxInput), maxInput);
		leftDriveController.setContinuous(false);
		leftDriveController.setPercentTolerance(SPEED_PERCENT_TOLERANCE);
		leftDriveController.setToleranceBuffer(BUFFER_SIZE);

		rightDriveController.setOutputRange(-1, 1);
		rightDriveController.setInputRange(-(maxInput), maxInput);
		rightDriveController.setContinuous(false);
		rightDriveController.setPercentTolerance(SPEED_PERCENT_TOLERANCE);
		rightDriveController.setToleranceBuffer(BUFFER_SIZE);

	}

	@Override
	protected void execute() {

		// Get the raw values, these will be used as "inputs" in the control
		// scheme to determine whether the joystick is in the dead zone, whether
		// to synch the values, etc.
		double rawLeftJoystickVal = oi.getDriverLeftY();
		double rawRightJoystickVal = oi.getDriverRightY();

		// Scale the values
		double scaledLeft = UseDriveTank.expJoystickValue(rawLeftJoystickVal, 2);
		double scaledRight = UseDriveTank.expJoystickValue(rawRightJoystickVal, 2);

		// Synch the values if they are close to each other
		double[] synched = UseDriveTank.synchJoystickValues(rawLeftJoystickVal, rawRightJoystickVal, scaledLeft,
				scaledRight, SYNCH_TOLERANCE);
		scaledLeft = synched[0];
		scaledRight = synched[1];

		// Set the values to 0 if they are close to 0
		scaledLeft = UseDriveTank.applyDeadZone(rawLeftJoystickVal, scaledLeft, JOYSTICK_DEADZONE);
		scaledRight = UseDriveTank.applyDeadZone(rawRightJoystickVal, scaledRight, JOYSTICK_DEADZONE);

		// These values are scaled to represent the speed that the driver wants
		// the robot to go at
		double desiredleftRate = scaledLeft * maxSpeed;
		double desiredRightRate = scaledRight * maxSpeed;

		// Disable and reset the PID controller if the joysticks are in the dead
		// zone.
		// We don't want to use PID control to make the drive stop,
		if (desiredleftRate == 0) {
			leftMotorOutput = 0;
			leftDriveController.setSetpoint(0);
			leftDriveController.reset();
		} else {
			leftDriveController.setSetpoint(desiredleftRate);
			leftDriveController.enable();
		}

		if (desiredRightRate == 0) {
			rightMotorOutput = 0;
			rightDriveController.setSetpoint(0);
			rightDriveController.reset();
		} else {
			rightDriveController.setSetpoint(desiredRightRate);
			rightDriveController.enable();
		}

		drive.driveTank(leftMotorOutput, rightMotorOutput);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
