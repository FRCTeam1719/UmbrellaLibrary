package commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.LogicalSubsystem;
import interfaces.RobotInterface;

public abstract class TestableCommand extends Command {

	RobotInterface robot;
	LogicalSubsystem subsystem;
	
	public TestableCommand(RobotInterface robot, LogicalSubsystem subsystem) {
		try {
			requires( (Subsystem) subsystem);
		}
		catch (ClassCastException e) {
			System.out.println("Non subsystem passed, is a test running?");
		}
		this.robot = robot;
		this.subsystem = subsystem;
	}

}
