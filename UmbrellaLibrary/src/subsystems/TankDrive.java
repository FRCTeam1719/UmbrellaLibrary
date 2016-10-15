package subsystems;

import commands.UseDrive;
import customSensors.LoggableEncoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.IDrive;
import interfaces.INavX;
import interfaces.Loggable;
import interfaces.RobotInterface;

public class TankDrive extends Subsystem implements IDrive, Loggable {

	LogicalDrive logicDrive;
	RobotInterface robot;
	
	RobotDrive mainDrive;
	
	public TankDrive(RobotInterface robot, SpeedController leftSide, SpeedController rightSide,
			LoggableEncoder leftEncoder, LoggableEncoder rightEncoder) {
		
		mainDrive = new RobotDrive(leftSide, rightSide);
		this.robot = robot;
		logicDrive = new LogicalDrive(leftSide, rightSide, leftEncoder, rightEncoder);
	}

	public TankDrive(RobotInterface robot, SpeedController leftSide, SpeedController rightSide, INavX navX) {
		
		mainDrive = new RobotDrive(leftSide, rightSide);
		this.robot = robot;
		logicDrive = new LogicalDrive(leftSide, rightSide, navX);
	}

	public TankDrive(RobotInterface robot, SpeedController leftSide, SpeedController rightSide) {
		
		mainDrive = new RobotDrive(leftSide, rightSide);
		this.robot = robot;
		logicDrive = new LogicalDrive(leftSide, rightSide);
	}

	@Override
	public void operateDrive(double leftVal, double rightVal) {
		leftVal = logicDrive.checkAgainstMaxSpeed(leftVal);
		rightVal = logicDrive.checkAgainstMaxSpeed(rightVal);
		
		mainDrive.tankDrive(leftVal, rightVal);
	}
	
	public void setMaxSpeed(double speed) {
		logicDrive.setMaxSpeed(speed);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UseDrive(robot, this));
		
	}

	@Override
	public void log() {
		
	}

	@Override
	public double getLeftEncoderDist() {
		return logicDrive.getLeftEncoderDist();
	}

	@Override
	public double getRightEncoderDist() {
		return logicDrive.getRightEncoderDist();
	}

	@Override
	public double getLeftEncoderRate() {
		return logicDrive.getLeftEncoderRate();
	}

	@Override
	public double getRightEncoderRate() {
		return logicDrive.getRightEncoderRate();
	}

	@Override
	public double getAngle() {
		return logicDrive.getAngle();
	}
	
	@Override
	public INavX getNavX() {
		return logicDrive.getNavX();
	}

	@Override
	public double getAvgEncoderDist() {
		return logicDrive.getAvgEncoderDist();
	}

	@Override
	public double getAvgEncoderRate() {
		return logicDrive.getAvgEncoderRate();
	}



}
