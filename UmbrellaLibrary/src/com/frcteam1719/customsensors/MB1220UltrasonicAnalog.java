package com.frcteam1719.customsensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class MB1220UltrasonicAnalog {

	private AnalogInput in;
	private static final double SCALING_5V = 1024.0D / 5.0D;
	
	public MB1220UltrasonicAnalog(int par1) {
		in = new AnalogInput(par1);
	}

	public int getDistanceCM() {
		int var1 = (int) Math.round(in.getVoltage() * SCALING_5V);
		return ((var1 == 343) ? -1 : var1);
	}
	
	public double getDistanceM() {
	    int var1 = getDistanceCM();
		return ((var1 == -1) ? -1 : (0.01D * var1));
	}
}
