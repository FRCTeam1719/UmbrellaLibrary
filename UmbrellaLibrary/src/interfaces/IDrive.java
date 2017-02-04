package interfaces;

import edu.wpi.first.wpilibj.PIDSource;

public interface IDrive extends ISubsystem, PIDSource {
	
	public void driveTank(double leftVal, double rightVal);
	public void setMaxSpeed(double speed);
	
	//Get Sensor Information
	public double getLeftEncoderDist();
	public double getRightEncoderDist();
	
	public double getLeftEncoderRate();
	public double getRightEncoderRate();
	
	public double getAvgEncoderDist();
	public double getAvgEncoderRate();
	
	public double getAngle();
	
	//Get Sensors
	public INavX getNavX();
	public IEncoder getLeftEncoder();
	public IEncoder getRightEncoder();
}
