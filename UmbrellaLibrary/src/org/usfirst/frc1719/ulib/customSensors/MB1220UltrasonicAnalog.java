package org.usfirst.frc1719.ulib.customSensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class MB1220UltrasonicAnalog {

	private AnalogInput in;
	private static final double SCALING_5V = 1024.0D / 5.0D;
	
	public MB1220UltrasonicAnalog(int par1) {
		in = new AnalogInput(par1);
	}

	public long getDistanceCM() {
		return Math.round(in.getVoltage() * SCALING_5V);
	}
	
	public double getDistanceM() {
		return 0.01D * ((double) getDistanceCM());
	}
}
