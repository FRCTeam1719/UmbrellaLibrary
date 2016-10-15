package mockHardware;

import interfaces.IDashboard;
import interfaces.OI;
import interfaces.RobotInterface;

public class MockRobot implements RobotInterface{

	public MockOI oi;
	private MockDashboard dashboard;
	
	public MockRobot() {
		oi = new MockOI();
		dashboard = new MockDashboard();
	}
	
	@Override
	public OI getOi() {
		return oi;
	}

	@Override
	public IDashboard getDashboard() {
		return dashboard;
	}

}
