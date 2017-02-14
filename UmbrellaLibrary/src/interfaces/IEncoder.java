package interfaces;

import edu.wpi.first.wpilibj.PIDSource;

public interface IEncoder extends Sensor, PIDSource {

	public double getDistance();
	public double getRate();
	
	public void reset();
	
	public void setDistancePerRevolution(double distancePerRev);
}
