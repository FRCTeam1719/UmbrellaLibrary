package mockHardware;

import java.util.ArrayList;

import interfaces.IDashboard;
import interfaces.ISubsystem;
import interfaces.OI;
import interfaces.RobotInterface;

public class MockRobot implements RobotInterface{

	public MockOI oi;
	private MockDashboard dashboard;
	ArrayList<ISubsystem> subsystems;
	
	public MockRobot() {
		oi = new MockOI();
		dashboard = new MockDashboard();
		subsystems = new ArrayList<ISubsystem>();
	}
	
	@Override
	public OI getOi() {
		return oi;
	}

	@Override
	public IDashboard getDashboard() {
		return dashboard;
	}

	@Override
	public void registerSubsystem(ISubsystem subsys) {
		subsystems.add(subsys);
		
	}

	@Override
	public ISubsystem[] getSubsystems() {
		return (ISubsystem[]) subsystems.toArray();
	}

}
