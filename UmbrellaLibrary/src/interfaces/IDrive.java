package interfaces;

public interface IDrive extends LogicalSubsystem {
	
	public void operateDrive(double leftVal, double rightVal);
	public void setMaxSpeed(double speed);
}
