package com.frcteam1719.customsensors;

import com.frcteam1719.interfaces.Loggable;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class LoggablePotentiometer extends AnalogPotentiometer implements Loggable {

	public LoggablePotentiometer(AnalogInput input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

	public LoggablePotentiometer(AnalogInput input, double fullRange, double offset) {
		super(input, fullRange, offset);
		// TODO Auto-generated constructor stub
	}

	public LoggablePotentiometer(AnalogInput input, double scale) {
		super(input, scale);
		// TODO Auto-generated constructor stub
	}

	public LoggablePotentiometer(int channel, double fullRange, double offset) {
		super(channel, fullRange, offset);
		// TODO Auto-generated constructor stub
	}

	public LoggablePotentiometer(int channel, double scale) {
		super(channel, scale);
		// TODO Auto-generated constructor stub
	}

	public LoggablePotentiometer(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}

}
