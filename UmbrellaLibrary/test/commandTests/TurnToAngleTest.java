package commandTests;

import org.junit.Test;

import commands.TurnToAngle;
import mockHardware.MockNavX;
import mockHardware.MockRobot;
import mockHardware.MockSpeedController;
import subsystems.DriveLogic;

public class TurnToAngleTest {

	private MockRobot robot = new MockRobot();
	
	private MockSpeedController leftMotor = new MockSpeedController();
	private MockSpeedController rightMotor = new MockSpeedController();
	private MockNavX navX = new MockNavX();
	private DriveLogic drive = new DriveLogic(leftMotor, rightMotor, navX);
	private TurnToAngle command = new TurnToAngle(robot, drive, 90, true);
	
	@Test
	public void testExecute() {
	}
}
