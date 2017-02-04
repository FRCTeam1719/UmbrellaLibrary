package interfaces;

public interface RobotInterface {

	public OI getOi();
	public IDashboard getDashboard();
	
	public void registerSubsystem(ISubsystem subsys);
	
	public ISubsystem[] getSubsystems();

}
