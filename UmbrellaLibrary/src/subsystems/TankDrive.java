package subsystems;

import commands.UseDrive;
import customSensors.LoggableEncoder;
import customSensors.NavX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.IDrive;
import interfaces.Loggable;
import interfaces.RobotInterface;

public class TankDrive extends Subsystem implements IDrive, Loggable {

	LogicalDrive logicDrive;
	RobotInterface robot;
	
	public TankDrive(RobotInterface robot, SpeedController leftSide, SpeedController rightSide,
			LoggableEncoder leftEncoder, LoggableEncoder rightEncoder) {
		
		this.robot = robot;
		logicDrive = new LogicalDrive(leftSide, rightSide, leftEncoder, rightEncoder);
	}

	public TankDrive(RobotInterface robot, SpeedController leftSide, SpeedController rightSide, NavX navX) {
		
		this.robot = robot;
		logicDrive = new LogicalDrive(leftSide, rightSide, navX);
	}

	public TankDrive(RobotInterface robot, SpeedController leftSide, SpeedController rightSide) {
		
		this.robot = robot;
		logicDrive = new LogicalDrive(leftSide, rightSide);
	}

	@Override
	public void operateDrive(double leftVal, double rightVal) {
		logicDrive.operateDrive(leftVal, rightVal);
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
		// TODO Auto-generated method stub
		
	}



}
