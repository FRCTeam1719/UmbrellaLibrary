package subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import interfaces.IDrive;
import interfaces.IEncoder;
import interfaces.INavX;

public class LogicalDrive implements IDrive {
	
	private double maxSpeed = 1;
	
	private IEncoder leftSideEncoder = null;
	private IEncoder rightSideEncoder = null;
	
	private SpeedController leftMotor;
	private SpeedController rightMotor;
	
	private INavX navX;
	

	/**
	 * 
	 * @param rightSide Controller for the right side of the drive
	 * @param leftSide Controller for the left side of the drive
	 */
	public LogicalDrive(SpeedController leftSide, SpeedController rightSide) {
		this.leftMotor = leftSide;
		this.rightMotor = rightSide;
	}
	
	public LogicalDrive(SpeedController leftSide, SpeedController rightSide, IEncoder leftEncoder, IEncoder rightEncoder) {
		
		this.leftMotor = leftSide;
		this.rightMotor = rightSide;
		
		if (leftEncoder == null) {
			throw new NullPointerException("Null left Encoder provided. Make sure that there are no port conflicts and that the encoder is plugged in correctly");
		}
		else if (rightEncoder == null) {
			throw new NullPointerException("Null left Encoder provided. Make sure that there are no port conflicts and that the encoder is plugged in correctly");
		}
		
		this.leftSideEncoder = leftEncoder;
		this.rightSideEncoder = rightEncoder;
	}
	
	public LogicalDrive(SpeedController leftSide, SpeedController rightSide, INavX navX) {
		
		this.leftMotor = leftSide;
		this.rightMotor = rightSide;
		
		if (navX == null) {
			throw new NullPointerException("Null NavX provided. Make sure that the NavX is securely plugged in to the MXP");
		}
		this.navX = navX;
	}
	
	/**
	 * Operating the drive escapes the bounds of a logical subsystem.
	 * Actually operating the drive needs to exist in TankDrive.java unless we
	 * decide to do away with using a RobotDrive and simply directly control
	 * two SpeedController objects, right now, this method should only be used
	 * when running unit tests.
	 */
	@Deprecated
	public void operateDrive(double leftSpeed, double rightSpeed) {
		leftMotor.set(leftSpeed);
		rightMotor.set(rightSpeed);
	}
	
	/* Doesn't do any error checking because we want to be able to test
	 * if someone passed in a bad value using JUnit
	 * Should only be passed values between 0 and 1.
	 */
	public void setMaxSpeed(double speed) {
		
		maxSpeed = speed;
	}
	
	public double checkAgainstMaxSpeed(double speed) {
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

	@Override
	public double getLeftEncoderDist() {
		return rightSideEncoder.getDistance();
	}

	@Override
	public double getRightEncoderDist() {
		return rightSideEncoder.getDistance();
	}

	@Override
	public double getLeftEncoderRate() {
		return leftSideEncoder.getRate();
	}

	@Override
	public double getRightEncoderRate() {
		return rightSideEncoder.getRate();
		
	}
	
	public double getAngle() {
		return navX.getAngle();
	}
	
	public INavX getNavX() {
		return navX;
	}


}
