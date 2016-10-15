package mockHardware;

import interfaces.OI;

public class MockOI implements OI {

	double leftJoystickReading = 0;
	double rightJoystickReading = 0;
	
	@Override
	public double getLeftJoystickReading() {
		return leftJoystickReading;
	}

	@Override
	public double getRightJoystickReading() {
		
		return rightJoystickReading;
	}
	
	public void setLeftJoystickReading(double newVal) {
		leftJoystickReading = newVal;
	}
	
	public void setRightJoystickReading(double newVal) {
		rightJoystickReading = newVal;
	}

}
