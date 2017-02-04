package mockHardware;

import edu.wpi.first.wpilibj.PIDSourceType;
import interfaces.IEncoder;
import interfaces.Sensor;

public class MockEncoder implements Sensor, IEncoder{

	double currentDistance;
	double currentRate;
	PIDSourceType sourceType = PIDSourceType.kDisplacement;
	
	@Override
	public double getDistance() {
		return currentDistance;
	}

	@Override
	public double getRate() {
		return currentRate;
	}

	@Override
	public void reset() {
		currentDistance = 0;		
	}
	
	public void setDistance(double newDist) {
		currentDistance = newDist;
	}
	
	public void setRate(double newRate) {
		currentRate = newRate;
	}

	@Override
	public void log() {		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	@Override
	public double pidGet() {
		if (sourceType == PIDSourceType.kDisplacement) {
			return currentDistance;
		}
		else {
			return currentRate;
		}
	}

}
