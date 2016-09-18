package interfaces;

import edu.wpi.first.wpilibj.Timer.Interface;

public interface RobotInterface {

	public OI getOi();
	public TestableDashboard getDashboard();
	public Interface getSystemTimer(String name);

}
