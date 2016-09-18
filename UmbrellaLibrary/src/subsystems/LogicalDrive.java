package subsystems;

import customSensors.LoggableEncoder;
import customSensors.NavX;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import interfaces.IDrive;
import interfaces.Loggable;

public class LogicalDrive implements IDrive {
	
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
	public LogicalDrive(SpeedController leftSide, SpeedController rightSide) {
		
		mainDrive = new RobotDrive(leftSide, rightSide);
		
	}
	
	public LogicalDrive(SpeedController leftSide, SpeedController rightSide, LoggableEncoder leftEncoder, LoggableEncoder rightEncoder) {
		
		if (leftEncoder == null) {
			throw new NullPointerException("Null left Encoder provided");
		}
		else if (rightEncoder == null) {
			throw new NullPointerException("Null right Encoder provided");
		}
		
		this.leftSideEncoder = leftEncoder;
		this.rightSideEncoder = rightEncoder;
		mainDrive = new RobotDrive(leftSide, rightSide);
	}
	
	public LogicalDrive(SpeedController leftSide, SpeedController rightSide, NavX navX) {
		
		if (navX == null) {
			throw new NullPointerException("Null NavX provided");
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
		
		leftSpeed = checkAgainstMaxSpeed(leftSpeed);
		rightSpeed = checkAgainstMaxSpeed(rightSpeed);
		
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
	
	private double checkAgainstMaxSpeed(double speed) {
		if (speed < 0) {
			if (speed < -maxSpeed) {
				speed = -maxSpeed;
			}
		}
		else if (speed > 0) {
			if (speed > maxSpeed) {
				speed = maxSpeed;
			}
		}
		
		return speed;
	}
}
