package commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import interfaces.IDrive;
import interfaces.LogicalSubsystem;
import interfaces.RobotInterface;

public class MoveForwardsDist extends TestableCommand implements PIDOutput {
	
	public static final double MOVE_FORWARDS_DIST_TOLERANCE_FEET = 0.1;
	public static final double MOVE_FORWARDS_TIMEOUT = 5;

	public static final String MOVE_FORWARDS_KP_DASHBOARD_STRING = "Move forwards kP: ";
	public static final String MOVE_FORWARDS_KI_DASHBOARD_STRING = "Move forwards kI: ";
	public static final String MOVE_FORWARDS_KD_DASHBOARD_STRING = "Move forwards kD: ";
	
	private double desiredDistance;
	
	private double leftOutput, rightOutput;
	
	private PIDController pidController;
	private Timer timeoutTimer;
	
	private double kP;
	private double kI;
	private double kD;
	
	public MoveForwardsDist(RobotInterface robot, LogicalSubsystem system, double distance) {
		super(robot, system);
		
		this.desiredDistance = distance;
	}

	@Override
	protected void initialize() {
		kP = robot.getDashboard()._getNumber(MOVE_FORWARDS_KP_DASHBOARD_STRING);
		kI = robot.getDashboard()._getNumber(MOVE_FORWARDS_KI_DASHBOARD_STRING);
		kD = robot.getDashboard()._getNumber(MOVE_FORWARDS_KD_DASHBOARD_STRING);
		
		pidController = new PIDController(kP, kI, kD, ((IDrive) system).getNavX(), this);
		
		pidController.setOutputRange(-1, 1);
		pidController.setToleranceBuffer(20);
		pidController.setAbsoluteTolerance(MOVE_FORWARDS_DIST_TOLERANCE_FEET);
		
		pidController.setSetpoint(desiredDistance);
		pidController.enable();
		timeoutTimer.start();
	}

	@Override
	protected void execute() {
		
		((IDrive) system).operateDrive(leftOutput, rightOutput);
	}

	@Override
	protected boolean isFinished() {
		return pidController.onTarget() || (timeoutTimer.get() >= MOVE_FORWARDS_TIMEOUT);
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
