package customSensors;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import interfaces.IEncoder;

/**
 * Class which provides subclasses a consistent interface for having a name,
 * primarily for logging purposes
 * 
 * @author Kyle
 *
 */
public abstract class NamedEncoder extends Encoder implements IEncoder {

	//The name of the encoder
	protected String encoderName;

	public NamedEncoder(String name, DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection,
			EncodingType encodingType) {
		super(sourceA, sourceB, reverseDirection, encodingType);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection) {
		super(sourceA, sourceB, reverseDirection);
		
		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource,
			boolean reverseDirection) {
		super(sourceA, sourceB, indexSource, reverseDirection);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource) {
		super(sourceA, sourceB, indexSource);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, DigitalSource sourceA, DigitalSource sourceB) {
		super(sourceA, sourceB);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
		super(channelA, channelB, reverseDirection, encodingType);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, int channelA, int channelB, boolean reverseDirection) {
		super(channelA, channelB, reverseDirection);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, int channelA, int channelB, int indexChannel, boolean reverseDirection) {
		super(channelA, channelB, indexChannel, reverseDirection);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, int channelA, int channelB, int indexChannel) {
		super(channelA, channelB, indexChannel);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public NamedEncoder(String name, int channelA, int channelB) {
		super(channelA, channelB);

		if (name == null) {
			throw new NullPointerException("Error creating NamedEncoder: Given name was null");
		}
		
		this.encoderName = name;
	}

	public String getName() {
		return encoderName;
	}

}
