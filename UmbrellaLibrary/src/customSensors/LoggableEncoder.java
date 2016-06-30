package customSensors;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import interfaces.Loggable;
import interfaces.Sensor;

public class LoggableEncoder extends Encoder implements Sensor, Loggable {

	public LoggableEncoder(DigitalSource aSource, DigitalSource bSource) {
		super(aSource, bSource);
	}
	

	public LoggableEncoder(DigitalSource aSource, DigitalSource bSource, boolean reverseDirection,
			EncodingType encodingType) {
		super(aSource, bSource, reverseDirection, encodingType);
	}

	public LoggableEncoder(DigitalSource aSource, DigitalSource bSource, DigitalSource indexSource,
			boolean reverseDirection) {
		super(aSource, bSource, indexSource, reverseDirection);
	}

	public LoggableEncoder(DigitalSource aSource, DigitalSource bSource, DigitalSource indexSource) {
		super(aSource, bSource, indexSource);
	}

	public LoggableEncoder(int aChannel, int bChannel, boolean reverseDirection, EncodingType encodingType) {
		super(aChannel, bChannel, reverseDirection, encodingType);
	}

	public LoggableEncoder(int aChannel, int bChannel, boolean reverseDirection) {
		super(aChannel, bChannel, reverseDirection);
	}

	public LoggableEncoder(int aChannel, int bChannel, int indexChannel, boolean reverseDirection) {
		super(aChannel, bChannel, indexChannel, reverseDirection);
	}

	public LoggableEncoder(int aChannel, int bChannel, int indexChannel) {
		super(aChannel, bChannel, indexChannel);
	}

	public LoggableEncoder(int aChannel, int bChannel) {
		super(aChannel, bChannel);
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

	
	
}
