package mockHardware;

import edu.wpi.first.wpilibj.Joystick;
import interfaces.OI;

public class MockOI implements OI {

	double leftJoystickReading = 0;
	double rightJoystickReading = 0;
	

	public void setLeftJoystickReading(double newVal) {
		leftJoystickReading = newVal;
	}
	
	public void setRightJoystickReading(double newVal) {
		rightJoystickReading = newVal;
	}

	@Override
	public Joystick getDriverJoystick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joystick getOperaterJoystick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDriverLeftX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDriverLeftY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDriverRightX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDriverRightY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
