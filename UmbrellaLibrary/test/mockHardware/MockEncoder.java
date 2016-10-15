package mockHardware;

import interfaces.IEncoder;
import interfaces.Sensor;

public class MockEncoder implements Sensor, IEncoder{

	double currentDistance;
	double currentRate;
	
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

}
