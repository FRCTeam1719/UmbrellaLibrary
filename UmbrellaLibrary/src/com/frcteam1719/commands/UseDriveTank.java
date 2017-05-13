package com.frcteam1719.commands;

import com.frcteam1719.interfaces.IDrive;
import com.frcteam1719.interfaces.OperatorInterface;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for using the drive in tank mode
 * 
 * @author Kyle
 *
 */
public class UseDriveTank extends Command {

	// If the joy-stick is close to the middle, set it to 0
	protected double joystickDeadzone = 0.1;

	// If the joy-stick values are close to each other, synch them
	protected double synchTolerance = 0.1;
	
	//Raise joy-stick values to a power to give more fine control
	protected int joystickExp = 1;

	IDrive drive;
	OperatorInterface oi;

	public UseDriveTank(OperatorInterface oi, IDrive drive) {
		this.oi = oi;
		this.drive = drive;
	}

	public UseDriveTank(OperatorInterface oi, IDrive drive, double deadzone, double synchTolerance, int joystickExp) {
		this(oi, drive);
		this.joystickDeadzone = deadzone;
		this.synchTolerance = synchTolerance;
		this.joystickExp = joystickExp;
	}

	@Override
	public void initialize() {

	}

	@Override
	public void execute() {
		double rawLeftJoystickVal = oi.getDriverLeftY();
		double rawRightJoystickVal = oi.getDriverRightY();

		double scaledLeft = expJoystickValue(rawLeftJoystickVal, joystickExp);
		double scaledRight = expJoystickValue(rawLeftJoystickVal, joystickExp);

		double[] synched = synchJoystickValues(rawLeftJoystickVal, rawRightJoystickVal, scaledLeft, scaledRight,
				synchTolerance);
		scaledLeft = synched[0];
		scaledRight = synched[1];

		scaledLeft = applyDeadZone(rawLeftJoystickVal, scaledLeft, joystickDeadzone);
		scaledRight = applyDeadZone(rawRightJoystickVal, scaledRight, joystickDeadzone);

		drive.driveTank(scaledLeft, scaledRight);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {

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
	public static double applyDeadZone(double valIn, double valOut, double deadZone) {
		if (Math.abs(valIn) < deadZone) {
			valOut = 0;
		}
		return valOut;
	}

	/**
	 * Set two joystick Values to be equal to each other if they are within
	 * synchTolerance
	 * 
	 * @param leftIn
	 *            The raw left joystick value, used for determining whether to
	 *            synch
	 * @param rightIn
	 *            The raw right joystick value, used for determining whether to
	 *            synch
	 * @param leftOut
	 *            The output left value, which will be averaged if necessary
	 * @param rightOut
	 *            the output right value, which will be averaged if necessary
	 * @param synchTolerance
	 *            If the values are within this value of each other, set them
	 *            both to their average
	 * @return {newLeftValue, newRightValue}
	 */
	public static double[] synchJoystickValues(double leftIn, double rightIn, double leftOut, double rightOut,
			double synchTolerance) {
		double[] newVals = { leftOut, rightOut };
		if (Math.abs(leftIn - rightIn) < synchTolerance) {
			double avg = (leftOut + rightOut) / 2;
			newVals[0] = avg;
			newVals[1] = avg;
		}

		return newVals;
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
	public static double expJoystickValue(double val, double pow) {
		return val * Math.abs(Math.pow(val, pow - 1));
	}

}
