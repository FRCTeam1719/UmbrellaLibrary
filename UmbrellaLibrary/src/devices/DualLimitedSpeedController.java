package devices;

import edu.wpi.first.wpilibj.SpeedController;
import interfaces.IDigitalInput;

public class DualLimitedSpeedController implements SpeedController {
	
	boolean triggerValue;
	
	SpeedController motor;
	IDigitalInput forwardInput;
	IDigitalInput backwardInput;
		
	public DualLimitedSpeedController(IDigitalInput forward, IDigitalInput back, SpeedController motor, boolean normallyClosed) {
		this.motor = motor;
		this.forwardInput = forward;
		this.backwardInput = back;
		this.triggerValue = normallyClosed;
	}

	@Override
	public void pidWrite(double output) {
		set(output);		
	}

	@Override
	public double get() {
		return motor.get();
	}

	private void setSpeed(double speed) {
		if (speed < 0) {
			if (backwardInput.get() != triggerValue) {
				motor.set(speed);
			}
			else {
				motor.stopMotor();
			}
		}
		else if (speed > 0) {
			if (forwardInput.get() != triggerValue) {
				motor.set(speed);
			}
			else {
				motor.stopMotor();
			}
		}
	}
	@Override
	public void set(double speed, byte syncGroup) {
		setSpeed(speed);
	}

	@Override
	public void set(double speed) {
		setSpeed(speed);
	}

	@Override
	public void setInverted(boolean isInverted) {
		motor.setInverted(isInverted);
	}

	@Override
	public boolean getInverted() {
		return motor.getInverted();
	}

	@Override
	public void disable() {
		motor.disable();
	}

	@Override
	public void stopMotor() {
		motor.stopMotor();
	}
}
