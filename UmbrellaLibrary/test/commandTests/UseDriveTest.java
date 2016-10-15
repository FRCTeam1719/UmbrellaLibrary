package commandTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import commands.UseDrive;
import mockHardware.MockRobot;
import mockHardware.MockSpeedController;
import subsystems.LogicalDrive;

public class UseDriveTest {


	private MockRobot robot = new MockRobot();
	private MockSpeedController leftMotor = new MockSpeedController();
	private MockSpeedController rightMotor = new MockSpeedController();
	
	private LogicalDrive drive = new LogicalDrive(leftMotor, rightMotor);
	
	private UseDrive command = new UseDrive(robot, drive);
	
	
	@Test
	public void testInitialize() {
		assertTrue(leftMotor.get() == 1);
		assertTrue(rightMotor.get() == 0);
	}
	
	@Test
	public void testExecute() {
		
		assertTrue(leftMotor.get() == 0);
		assertTrue(rightMotor.get() == 0);
		
		robot.oi.setLeftJoystickReading(0.5);
		command.execute();
		assertTrue(leftMotor.get() > 0);
		assertTrue(rightMotor.get() == 0);
		
		robot.oi.setLeftJoystickReading(0);
		
		robot.oi.setRightJoystickReading(0.5);
		command.execute();
		assertTrue(leftMotor.get() == 0);
		assertTrue(rightMotor.get() > 0);
	

	}

}
