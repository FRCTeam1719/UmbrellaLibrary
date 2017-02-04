package mockHardware;

import edu.wpi.first.wpilibj.SpeedController;

public class MockSpeedController implements SpeedController {

	double currentSpeed;
	@Override
	public void pidWrite(double output) {		
	}

	@Override
	public double get() {
		return currentSpeed;
	}

	@Override
	public void set(double speed) {
		currentSpeed = speed;
	}

	@Override
	public void setInverted(boolean isInverted) {
	
	}

	@Override
	public boolean getInverted() {
		return false;
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void stopMotor() {
		currentSpeed = 0;
	}

}
