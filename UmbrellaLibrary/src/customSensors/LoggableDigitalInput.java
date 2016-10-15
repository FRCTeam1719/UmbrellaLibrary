package customSensors;

import edu.wpi.first.wpilibj.DigitalInput;
import interfaces.IDigitalInput;
import interfaces.Loggable;

public class LoggableDigitalInput extends DigitalInput implements IDigitalInput, Loggable {

	public LoggableDigitalInput(int channel) {
		super(channel);
	}

	@Override
	public void log() {
	
	}

}
