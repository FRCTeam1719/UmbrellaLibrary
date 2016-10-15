package interfaces;

public interface IDrive extends LogicalSubsystem {
	
	public void operateDrive(double leftVal, double rightVal);
	public void setMaxSpeed(double speed);
	
	public double getLeftEncoderDist();
	public double getRightEncoderDist();
	
	public double getLeftEncoderRate();
	public double getRightEncoderRate();
	
	public double getAvgEncoderDist();
	public double getAvgEncoderRate();
	
	public double getAngle();
	public INavX getNavX();
}
