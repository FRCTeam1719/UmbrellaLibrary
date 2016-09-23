package commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.IDashboard;
import interfaces.LogicalSubsystem;
import interfaces.OI;
import interfaces.RobotInterface;

public abstract class TestableCommand extends Command {

	protected LogicalSubsystem system;
	protected RobotInterface robot;
	protected OI oi;
	protected IDashboard dashboard;
	
	public TestableCommand(RobotInterface robot, LogicalSubsystem system){
		this.system = system;
		try{
			requires((Subsystem) system);
		}catch(ClassCastException e){
			System.out.println("Non subsystem passed, is a test running?");
		}
		this.robot = robot;
		oi = this.robot.getOi();
		dashboard = robot.getDashboard();

	}

}
