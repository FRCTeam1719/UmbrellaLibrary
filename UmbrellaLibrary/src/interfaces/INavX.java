package interfaces;

import edu.wpi.first.wpilibj.PIDSource;

public interface INavX extends PIDSource {
	
	public double getAngle();
	public double getRate();
	
	public float getYaw();
	public float getPitch();
	public float getRoll();
	
	public void reset();
}
