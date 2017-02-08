package commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import interfaces.IDrive;
import interfaces.RobotInterface;

/**
 * Command for controlling the robot in tank mode. Uses PID controllers to control the speed of the drive,
 * rather than simply plugging in values to the motors
 * @author Kyle
 *
 */
public class UseDriveTankVelocityPID extends UseDriveTank {

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
	private double speedPercentTolerance = 2;
	private int pidBufferSize = 20; // Increase to increase stability,
										// decrease to increase reactivity


	/**
	 * Represents the maximum speed of the robot being operated. This variable
	 * is in physical terms, not in terms of the motor value
	 */
	private double maxSpeed;
	private double maxInput;

	private PIDController leftDriveController;
	private PIDController rightDriveController;
	IDrive drive;

	/**
	 * 
	 * @param robot Robot to interface with
	 * @param system Drive to interface with
	 * @param maxSpeed The maximum speed at which the drive can move
	 */
	public UseDriveTankVelocityPID(RobotInterface robot, IDrive system, double maxSpeed) {
		super(robot, system);
		drive = system;

		this.maxSpeed = maxSpeed;
		this.maxInput = maxSpeed * MAX_SPEED_LIMIT_SCALING;

		leftDriveController = new PIDController(left_kP, 0, left_kD, left_kF, drive.getLeftEncoder(),
				new SetLeftMotorOutput());

		rightDriveController = new PIDController(right_kP, 0, right_kD, right_kF, drive.getRightEncoder(),
				new SetRightMotorOutput());

		robot.getDashboard().putData("ULIB UseDriveVelocity Left Cont roller", leftDriveController);
		robot.getDashboard().putData("ULIB UseDriveVelocity Right Cpontroller", rightDriveController);

	}
	
	/**
	 * 
	 * @param robot Robot to interface with
	 * @param system Drive to interface with
	 * @param maxSpeed The maximum speed at which the drive can move
	 * @param deadzone The dead zone for the joystick values
	 * @param synchTolerance Tolerance for deciding whether to synch the joystick values
	 */
	public UseDriveTankVelocityPID(RobotInterface robot, IDrive system, double maxSpeed, double deadzone, double synchTolerance) {
		this(robot, system, maxSpeed);
		this.joystickDeadzone = deadzone;
		this.synchTolerance = synchTolerance;
	}
	
	/**
	 * 
	 * @param robot Robot to interface with
	 * @param system Drive to interface with
	 * @param maxSpeed the maximum speed at which the drive can move
	 * @param speedPercentTolerance Tolerance for the PID controllers
	 * @param bufferSize Size of the buffer that the PID controllers will use
	 */
	public UseDriveTankVelocityPID(RobotInterface robot, IDrive system, double maxSpeed, double speedPercentTolerance, int bufferSize) {
		this(robot, system, maxSpeed);
		this.speedPercentTolerance = speedPercentTolerance;
		this.pidBufferSize = bufferSize;
	}
	
	/**
	 * 
	 * @param robot Robot to interface with
	 * @param system Drive to interface with
	 * @param maxSpeed The maximum speed at which the drive can move
	 * @param deadzone The dead zone for the joystick values
	 * @param synchTolerance Tolerance for deciding whether to synch the joystick values
	 * @param speedPercentTolerance Tolerance for the PID controllers
	 * @param bufferSize Size of the buffer that the PID controllers will use
	 */
	public UseDriveTankVelocityPID(RobotInterface robot, IDrive system, double maxSpeed, double deadzone, double synchTolerance, double speedPercentTolerance, int bufferSize) {
		this(robot, system, maxSpeed);
		this.joystickDeadzone = deadzone;
		this.synchTolerance = synchTolerance;
		this.speedPercentTolerance = speedPercentTolerance;
		this.pidBufferSize = bufferSize;
	}
	
	@Override
	public void initialize() {

		// Make sure the sensors output rates
		drive.getLeftEncoder().setPIDSourceType(PIDSourceType.kRate);
		drive.getRightEncoder().setPIDSourceType(PIDSourceType.kRate);

		// Configure PID controllers. Assumes that the drive max speed is the
		// same forwards and backwards
		leftDriveController.setOutputRange(-1, 1);
		leftDriveController.setInputRange(-(maxInput), maxInput);
		leftDriveController.setContinuous(false);
		leftDriveController.setPercentTolerance(speedPercentTolerance);
		leftDriveController.setToleranceBuffer(pidBufferSize);

		rightDriveController.setOutputRange(-1, 1);
		rightDriveController.setInputRange(-(maxInput), maxInput);
		rightDriveController.setContinuous(false);
		rightDriveController.setPercentTolerance(speedPercentTolerance);
		rightDriveController.setToleranceBuffer(pidBufferSize);

	}

	@Override
	public void execute() {

		// Get the raw values, these will be used as "inputs" in the control
		// scheme to determine whether the joystick is in the dead zone, whether
		// to synch the values, etc.
		double rawLeftJoystickVal = oi.getDriverLeftY();
		double rawRightJoystickVal = oi.getDriverRightY();

		// Scale the values
		double scaledLeft = expJoystickValue(rawLeftJoystickVal, 2);
		double scaledRight = expJoystickValue(rawRightJoystickVal, 2);

		// Synch the values if they are close to each other
		double[] synched = synchJoystickValues(rawLeftJoystickVal, rawRightJoystickVal, scaledLeft,
				scaledRight, synchTolerance);
		scaledLeft = synched[0];
		scaledRight = synched[1];

		// Set the values to 0 if they are close to 0
		scaledLeft = applyDeadZone(rawLeftJoystickVal, scaledLeft, joystickDeadzone);
		scaledRight = applyDeadZone(rawRightJoystickVal, scaledRight, joystickDeadzone);

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
