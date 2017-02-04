package interfaces;

import edu.wpi.first.wpilibj.PIDSource;

public interface IEncoder extends Loggable, Sensor, PIDSource {

	public double getDistance();
	public double getRate();
	
	public void reset();
}
