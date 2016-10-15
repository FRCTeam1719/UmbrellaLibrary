package mockHardware;

import edu.wpi.first.wpilibj.PIDSourceType;
import interfaces.INavX;

public class MockNavX implements INavX {

	PIDSourceType pidSourceType = PIDSourceType.kDisplacement;
	
	float roll = 0;
	float pitch = 0;
	float yaw = 0;
	
	double yawRate = 0;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSourceType = pidSource;
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		if ( pidSourceType == PIDSourceType.kRate ) {
    		return getRate();
    	} else {
    		return getYaw();
    	}
	}

	@Override
	public double getAngle() {
		return yaw;
	}

	@Override
	public float getYaw() {
		return yaw;
	}
	
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	@Override
	public float getPitch() {
		return pitch;
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	@Override
	public float getRoll() {
		return roll;
	}
	
	public void setRoll(float roll) {
		this.roll = roll;
	}

	@Override
	public void reset() {
		yaw = 0;
	}

	@Override
	public double getRate() {
		return yawRate;
	}
	
	public void setRate(double rate) {
		yawRate = rate;
	}

}
