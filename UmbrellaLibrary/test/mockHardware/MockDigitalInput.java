package mockHardware;

import interfaces.IDigitalInput;

public class MockDigitalInput implements IDigitalInput {

	boolean value = false;
	
	@Override
	public boolean get() {
		return value;
	}
	
	public void set(boolean newVal) {
		value = newVal;
	}

	
}
