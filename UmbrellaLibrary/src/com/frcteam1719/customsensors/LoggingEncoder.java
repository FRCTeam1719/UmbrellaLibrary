package com.frcteam1719.customsensors;

import com.frcteam1719.interfaces.Loggable;
import com.frcteam1719.subsystems.Logger;

import edu.wpi.first.wpilibj.DigitalSource;

public class LoggingEncoder extends NamedEncoder implements Loggable {

	// The internal logger which we will send messages to
	private Logger logger;

	// The amount of ticks that the encoder counts over one rotation
	protected int ticksPerRevolution;

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, DigitalSource sourceA, DigitalSource sourceB,
			boolean reverseDirection, EncodingType encodingType) {
		super(name, sourceA, sourceB, reverseDirection, encodingType);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, DigitalSource sourceA, DigitalSource sourceB,
			boolean reverseDirection) {
		super(name, sourceA, sourceB, reverseDirection);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, DigitalSource sourceA, DigitalSource sourceB,
			DigitalSource indexSource, boolean reverseDirection) {
		super(name, sourceA, sourceB, indexSource, reverseDirection);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, DigitalSource sourceA, DigitalSource sourceB,
			DigitalSource indexSource) {
		super(name, sourceA, sourceB, indexSource);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, DigitalSource sourceA, DigitalSource sourceB) {
		super(name, sourceA, sourceB);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, int channelA, int channelB,
			boolean reverseDirection, EncodingType encodingType) {
		super(name, channelA, channelB, reverseDirection, encodingType);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, int channelA, int channelB,
			boolean reverseDirection) {
		super(name, channelA, channelB, reverseDirection);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, int channelA, int channelB, int indexChannel,
			boolean reverseDirection) {
		super(name, channelA, channelB, indexChannel, reverseDirection);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, int channelA, int channelB, int indexChannel) {
		super(name, channelA, channelB, indexChannel);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public LoggingEncoder(int ticksPerRev, Logger logger, String name, int channelA, int channelB) {
		super(name, channelA, channelB);

		if (logger == null) {
			throw new NullPointerException("Error creating LoggingEncoder: Given Logger was null");
		}

		if (ticksPerRev <= 0) {
			throw new IllegalArgumentException("Error creating LoggingEncoder: Given ticksPerRev was 0 or negative");
		}
		this.logger = logger;
		this.ticksPerRevolution = ticksPerRev;
	}

	public void setDistancePerRevolution(double distancePerRev) {
		double distPerPulse = (double) distancePerRev / ticksPerRevolution;
		setDistancePerPulse(distPerPulse);
	}
	
	/**
	 * Log the current distance and rate
	 */
	@Override
	public void log() {
		System.out.println("[" + getName() + "]: Distance: " + getDistance());
		System.out.println("[" + getName() + "]: Rate: " + getRate());
	}

}
