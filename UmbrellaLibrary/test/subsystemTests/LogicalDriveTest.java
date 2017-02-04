package subsystemTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mockHardware.MockEncoder;
import mockHardware.MockRobot;
import mockHardware.MockSpeedController;
import subsystems.DriveLogic;

public class LogicalDriveTest {

	MockRobot robot;
	MockSpeedController leftMotor;
	MockSpeedController rightMotor;
	MockEncoder leftEnc;
	MockEncoder rightEnc;
	
	DriveLogic drive;
	
	
	void setup() {
		
		leftMotor = new MockSpeedController();
		rightMotor = new MockSpeedController();
		leftEnc = new MockEncoder();
		rightEnc = new MockEncoder();
		
		drive = new DriveLogic(leftMotor, rightMotor, leftEnc, rightEnc);
	}
	
	@Test
	public void testCreateDrive() {
		setup();
		
		assertTrue(leftMotor.get() == 0);
		assertTrue(rightMotor.get() == 0);
	}
	
	@Test
	public void testSetMaxSpeed() {
		for (double speed = -0.1; speed < 1.1; speed += 0.1) {
			assert(Math.abs(drive.checkAgainstMaxSpeed(speed)) < 1);
			assert(Math.abs(drive.checkAgainstMaxSpeed(speed)) > 0);
		}
	}
}
