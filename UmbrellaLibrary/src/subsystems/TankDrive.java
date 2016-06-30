package subsystems;

import customSensors.LoggableEncoder;
import customSensors.NavX;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.Loggable;

public class TankDrive extends Subsystem implements Loggable {
	
	private double maxSpeed = 1;
	
	private LoggableEncoder leftSideEncoder;
	private LoggableEncoder rightSideEncoder;
	
	private NavX navX;
	
	private RobotDrive mainDrive;

	/**
	 * 
	 * @param rightSide Controller for the right side of the drive
	 * @param leftSide Controller for the left side of the drive
	 */
	public TankDrive(SpeedController leftSide, SpeedController rightSide) {
		
		if (rightSide == null) {
			//TODO error handling
		}
		if (leftSide == null) {
			//TODO error handling
		}
		
		mainDrive = new RobotDrive(leftSide, rightSide);
		
	}
	
	public TankDrive(SpeedController leftSide, SpeedController rightSide, LoggableEncoder leftEncoder, LoggableEncoder rightEncoder) {
		
		if (rightSide == null) {
			//TODO error handling
		}
		if (leftSide == null) {
			//TODO error handling
		}
		if (leftEncoder == null) {
			//TODO error handling
		}
		if (rightEncoder == null) {
			//TODO error handling
		}
		
		this.leftSideEncoder = leftEncoder;
		this.rightSideEncoder = rightEncoder;
		mainDrive = new RobotDrive(leftSide, rightSide);
	}
	
	public TankDrive(SpeedController leftSide, SpeedController rightSide, NavX navX) {
		
		if (rightSide == null) {
			//TODO error handling
		}
		if (leftSide == null) {
			//TODO error handling
		}
		if (navX == null) {
			//TODO error handling
		}
		
		this.navX = navX;
		
		mainDrive = new RobotDrive(leftSide, rightSide);
	}
	
	/**
	 * Operates the drive. Sets each side run at the speeds given
	 * @param leftSpeed
	 * @param rightSpeed
	 */
	public void operateDrive(double leftSpeed, double rightSpeed) {
		if (Math.abs(leftSpeed) > maxSpeed) {
			//TODO error handling
		}
		if (Math.abs(rightSpeed) > maxSpeed) {
			//TODO error handling
		}
		
		mainDrive.tankDrive(leftSpeed, rightSpeed);
		
	}
	
	public void setMaxSpeed(double speed) {
		if (speed < 0) {
			//TODO error handling
		}
		if (speed > 1) {
			//TODO error handling
		}
		maxSpeed = speed;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
