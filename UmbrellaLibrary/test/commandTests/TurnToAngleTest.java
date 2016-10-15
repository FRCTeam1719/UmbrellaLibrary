package commandTests;

import org.junit.Test;

import commands.TurnToAngle;
import mockHardware.MockNavX;
import mockHardware.MockRobot;
import mockHardware.MockSpeedController;
import subsystems.LogicalDrive;

public class TurnToAngleTest {

	private MockRobot robot = new MockRobot();
	
	private MockSpeedController leftMotor = new MockSpeedController();
	private MockSpeedController rightMotor = new MockSpeedController();
	private MockNavX navX = new MockNavX();
	private LogicalDrive drive = new LogicalDrive(leftMotor, rightMotor, navX);
	private TurnToAngle command = new TurnToAngle(robot, drive, 90, true);
	
	@Test
	public void testExecute() {
	}
}
