package com.frcteam1719.customsensors;

import com.frcteam1719.interfaces.Loggable;

import edu.wpi.first.wpilibj.DigitalInput;

public class LoggableDigitalInput extends DigitalInput implements Loggable {

	public LoggableDigitalInput(int channel) {
		super(channel);
	}

	@Override
	public void log() {
	
	}

}
