package subsystems;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import interfaces.IDrive;
import interfaces.IEncoder;
import interfaces.INavX;

public class DriveLogic implements IDrive, PIDSource {
	
	private PIDSourceType pidSourceType;
	
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
	public DriveLogic(SpeedController leftSide, SpeedController rightSide) {
		pidSourceType = PIDSourceType.kDisplacement;
		this.leftMotor = leftSide;
		this.rightMotor = rightSide;
	}
	
	public DriveLogic(SpeedController leftSide, SpeedController rightSide, IEncoder leftEncoder, IEncoder rightEncoder) {
		this(leftSide, rightSide);
		
		if (leftEncoder == null) {
			throw new NullPointerException("Null left Encoder provided. Make sure that there are no port conflicts and that the encoder is plugged in correctly");
		}
		else if (rightEncoder == null) {
			throw new NullPointerException("Null right Encoder provided. Make sure that there are no port conflicts and that the encoder is plugged in correctly");
		}
		
		this.leftSideEncoder = leftEncoder;
		this.rightSideEncoder = rightEncoder;
	}
	
	public DriveLogic(SpeedController leftSide, SpeedController rightSide, INavX navX) {
		this(leftSide, rightSide);
		
		if (navX == null) {
			throw new NullPointerException("Null NavX provided. Make sure that the NavX is securely plugged in to the MXP");
		}
		this.navX = navX;
	}
	
	public DriveLogic(SpeedController leftSide, SpeedController rightSide, IEncoder leftEncoder, IEncoder rightEncoder, INavX navX) {
		this(leftSide, rightSide, leftEncoder, rightEncoder);
		
		if (navX == null) {
			throw new NullPointerException("Null NavX provided. Make sure that the NavX is securely plugged in to the MXP");
		}
		this.navX = navX;
	}
	
	public void driveTank(double leftSpeed, double rightSpeed) {
		leftMotor.set(checkAgainstMaxSpeed(leftSpeed));
		rightMotor.set(checkAgainstMaxSpeed(rightSpeed));
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
		if (leftSideEncoder == null) {
			throw new NullPointerException("Could not satisfy call to \"getLeftEncoderDist()\", left encoder was null");
		}
		return rightSideEncoder.getDistance();
	}

	@Override
	public double getRightEncoderDist() {
		if (leftSideEncoder == null) {
			throw new NullPointerException("Could not satisfy call to \"getRightEncoderDist()\", right encoder was null");
		}
		return rightSideEncoder.getDistance();
	}

	@Override
	public double getLeftEncoderRate() {
		if (leftSideEncoder == null) {
			throw new NullPointerException("Could not satisfy call to \"getLeftEncoderRate()\", left encoder was null");
		}
		return leftSideEncoder.getRate();
	}

	@Override
	public double getRightEncoderRate() {
		if (rightSideEncoder == null) {
			throw new NullPointerException("Could not satisfy call to \"getRightEncoderRate()\", right encoder was null");
		}
		return rightSideEncoder.getRate();
		
	}
	
	public double getAvgEncoderDist() {
			return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	}
	
	public double getAvgEncoderRate() {
		return (getLeftEncoderRate() + getRightEncoderRate()) / 2;
	}
	
	public double getAngle() {
		if (navX == null) {
			throw new NullPointerException("Could not satisfy call to \"getAngle()\": navX was null");
		}
		return navX.getAngle();
	}
	
	public INavX getNavX() {
		if (navX == null) {
			throw new NullPointerException("Could not satisfy call to \"getNavX()\": navX was null");
		}
		return navX;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		if (pidSourceType == PIDSourceType.kRate) {
			return getAvgEncoderRate();
		}
		else {
			return getAvgEncoderDist();
		}
	}

	@Override
	public IEncoder getLeftEncoder() {
		return leftSideEncoder;
	}

	@Override
	public IEncoder getRightEncoder() {
		return rightSideEncoder;
	}

	@Override
	public void disable() {
		leftMotor.set(0);
		rightMotor.set(0);
	}

	@Override
	public void log() {
		
	}


}
