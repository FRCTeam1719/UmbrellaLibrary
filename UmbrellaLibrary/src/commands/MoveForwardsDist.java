package commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import interfaces.IDrive;
import interfaces.LogicalSubsystem;
import interfaces.RobotInterface;

public class MoveForwardsDist extends TestableCommand implements PIDOutput {
	
	public static final double MOVE_FORWARDS_DIST_TOLERANCE_FEET = 0.1;
	public static final double MOVE_FORWARDS_TIMEOUT_SECONDS = 5;
	
	private double desiredDistance;
	
	private double leftOutput, rightOutput;
	
	private PIDController pidController;
	private Timer timeoutTimer;
	
	private double kP = 0;
	private double kI = 0;
	private double kD = 0;
	
	public MoveForwardsDist(RobotInterface robot, LogicalSubsystem system, double distance) {
		super(robot, system);
		this.desiredDistance = distance;
	}

	@Override
	protected void initialize() {
		
		//Initialize PID controller
		pidController = new PIDController(kP, kI, kD, ((IDrive) system).getNavX(), this);
		
		pidController.setOutputRange(-1, 1);
		pidController.setToleranceBuffer(20);
		pidController.setAbsoluteTolerance(MOVE_FORWARDS_DIST_TOLERANCE_FEET);
		
		pidController.setSetpoint(desiredDistance);
		pidController.enable();
		
		robot.getDashboard().putData("MoveForwardsDist Command controller", pidController);
		timeoutTimer.start();
	}

	@Override
	protected void execute() {
		
		((IDrive) system).driveTank(leftOutput, rightOutput);
	}

	@Override
	protected boolean isFinished() {
		return pidController.onTarget() || (timeoutTimer.get() >= MOVE_FORWARDS_TIMEOUT_SECONDS);
	}

	@Override
	protected void end() {
		pidController.disable();
		
	}

	@Override
	protected void interrupted() {
		pidController.disable();
		
	}

	@Override
	public void pidWrite(double output) {
		this.leftOutput = output;
		this.rightOutput = -output;
		
	}

}
