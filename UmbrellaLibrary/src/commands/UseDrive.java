package commands;

import interfaces.IDrive;
import interfaces.RobotInterface;

public class UseDrive extends TestableCommand {

	private final double DEADZONE = 0.05D;
	final double SYNCHTOLERANCE = 0.15;
	
	IDrive drive;
	
	public UseDrive(RobotInterface robot, IDrive drive) {
		super(robot, drive);
		this.drive = drive;
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void execute() {
		double leftJoystickVal = robot.getOi().getLeftJoystickReading();
		double rightJoystickVal = robot.getOi().getRightJoystickReading();
		
		//Apply Dead Zone
		leftJoystickVal = applyDeadZone(leftJoystickVal);
		rightJoystickVal = applyDeadZone(rightJoystickVal);
		
		//Sync joystick values if they are close to each other
		double[] newVals = synch(leftJoystickVal, rightJoystickVal);
		leftJoystickVal = newVals[0];
		rightJoystickVal = newVals[1];
		
		//Cube the values
		leftJoystickVal = Math.pow(Math.abs(leftJoystickVal), 2) * leftJoystickVal;
		rightJoystickVal = Math.pow(Math.abs(rightJoystickVal), 2) * rightJoystickVal;
		
		drive.driveTank(leftJoystickVal, rightJoystickVal);		
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
	
	//Only register joystick values above DEADZONE
	private double applyDeadZone(double val) {
		if (Math.abs(val) < DEADZONE) {
			val = 0;
		}
		return val;
	}
	
	//If joystick values are close enough, set them to be equal
	private double[] synch(double left, double right) {
		double[] vals = {left, right};
		if (Math.abs(left - right) < SYNCHTOLERANCE) {
			double newVal = (left + right) / 2;
			vals[0] = newVal;
			vals[1] = newVal;
		}
		
		return vals;
	}

}
