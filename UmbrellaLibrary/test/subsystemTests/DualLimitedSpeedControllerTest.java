package subsystemTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import devices.DualLimitedSpeedController;
import mockHardware.MockDigitalInput;
import mockHardware.MockSpeedController;

public class DualLimitedSpeedControllerTest {
	
	private MockDigitalInput forwardsDigitalInput = new MockDigitalInput();
	private MockDigitalInput backwardsDigitalInput = new MockDigitalInput();
	
	private MockSpeedController motor = new MockSpeedController();
	
	private DualLimitedSpeedController speedController = new DualLimitedSpeedController(forwardsDigitalInput, backwardsDigitalInput, motor, false);

	
	@Test
	public void testSetSpeed() {
		forwardsDigitalInput.set(true);
		speedController.set(1);
		assertTrue(speedController.get() == 1);
		speedController.set(0);
		
		forwardsDigitalInput.set(false);
		speedController.set(1);
		assertTrue(speedController.get() == 0);
		speedController.set(0);
		
		backwardsDigitalInput.set(true);
		speedController.set(-1);
		assertTrue(speedController.get() == -1);
		speedController.set(0);
		
		backwardsDigitalInput.set(false);
		speedController.set(-1);
		assertTrue(speedController.get() == 0);
		speedController.set(0);
		
		
	}

}
