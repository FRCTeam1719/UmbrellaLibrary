package commands;

import interfaces.IDrive;
import interfaces.RobotInterface;

public class UseDriveArcade extends TestableCommand {

	// Speed and Rotate are set to 0 if their absolute values are less than
	// these dead zones
	protected double speedDeadzone = 0.1;
	protected double rotateDeadzone = 0.1;

	// Power to raise joystick values to
	protected int joystickValExp = 1;

	IDrive drive;

	public UseDriveArcade(RobotInterface robot, IDrive system) {
		super(robot, system);
		this.drive = system;
	}

	public UseDriveArcade(RobotInterface robot, IDrive system, double speedDeadzone, double rotateDeadzone, int joystickExp) {
		this(robot, system);
		this.speedDeadzone = speedDeadzone;
		this.rotateDeadzone = rotateDeadzone;
		this.joystickValExp = joystickExp;
	}

	@Override
	public void execute() {
		double rawSpeed = robot.getOi().getDriverLeftY();
		double rawRotate = robot.getOi().getDriverLeftX();

		double scaledSpeed = rawSpeed;
		double scaledRotate = rawRotate;

		scaledSpeed = expJoystickVal(scaledSpeed, joystickValExp);
		scaledRotate = expJoystickVal(scaledRotate, joystickValExp);
		
		scaledSpeed = applyDeadZone(rawSpeed, scaledSpeed, speedDeadzone);
		scaledRotate = applyDeadZone(rawSpeed, scaledSpeed, rotateDeadzone);
		
		drive.driveArcade(scaledSpeed, scaledRotate);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Set the output value to 0 if the input is within a certain range of 0
	 * 
	 * @param valIn
	 *            The value used to determine whether to set the value to 0
	 * @param valOut
	 *            The value to set to 0
	 * @param deadZone
	 *            If valIn is within this value of 0, then set valOut to 0
	 * @return Either 0 or the original valOut
	 */
	double applyDeadZone(double valIn, double valOut, double deadzone) {
		if (Math.abs(valIn) < deadzone) {
			valOut = 0;
		}
		return valOut;
	}

	/**
	 * Raises a joystick value to a power while keeping the sign. Used for
	 * smoother control of the drive
	 * 
	 * @param val
	 *            the value to be scaled
	 * @param pow
	 *            the power that the value should be raised to
	 * @return the scaled value
	 */
	double expJoystickVal(double val, int exp) {
		return val * Math.abs(Math.pow(val, exp - 1));
	}

}
