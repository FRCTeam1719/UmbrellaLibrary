package commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import interfaces.IDrive;
import interfaces.RobotInterface;

public class MoveForwardsDist extends TestableCommand {

	IDrive drive;

	//These will be written to by the PID controller threads
	volatile double leftMotorOutput = 0;
	volatile double rightMotorOutput = 0;

	// Helper class for setting the motor speeds
	private class SetMotorSpeeds implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			leftMotorOutput = output;
			rightMotorOutput = output;
		}
	}

	// Helper class for adjusting the motor speeds for making the robot drive
	// straight
	private class AdjustMotorSpeeds implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			leftMotorOutput += output;
			rightMotorOutput -= output;
		}

		
	}

	double desiredDistance;

	// Constants for the PID controllers
	double align_kP = 0;
	double align_kI = 0;
	double align_kD = 0;

	double dist_kP = 0;
	double dist_kI = 0;
	double dist_kD = 0;
	
	//Stop the command after this time
	private final double TIMEOUT_TIME = 5;
	
	//Constants for the PID controllers
	private final int ALIGNMENT_BUF_SIZE = 5;
	private final int DIST_BUF_SIZE = 10;
	
	private final double ALIGNMENT_PERCENT_TOLERANCE = 2;
	private final double DIST_PERCENT_TOLERANCE = 5;

	PIDController alignmentController;
	PIDController distanceController;
	
	Timer timeoutTimer;

	public MoveForwardsDist(RobotInterface robot, IDrive system, double distance) {
		super(robot, system);
		this.drive = system;
		this.desiredDistance = distance;
		
		//Set the source types for the sensors
		drive.setPIDSourceType(PIDSourceType.kDisplacement);
		drive.getNavX().setPIDSourceType(PIDSourceType.kDisplacement);
		
		alignmentController = new PIDController(align_kP, align_kI, align_kD, drive.getNavX(), new AdjustMotorSpeeds());
		distanceController = new PIDController(dist_kP, dist_kI, dist_kD, drive, new SetMotorSpeeds());
		
		robot.getDashboard().putData("MoveForwardsDist alignment controller", alignmentController);
		robot.getDashboard().putData("MoveForwardsDist distance controller", distanceController);
	}

	@Override
	protected void initialize() {
		
		//Set up the alignment controller
		alignmentController.setOutputRange(-1, 1);
		alignmentController.setInputRange(-180, 180);
		alignmentController.setContinuous(true);
		alignmentController.setToleranceBuffer(ALIGNMENT_BUF_SIZE);
		alignmentController.setPercentTolerance(ALIGNMENT_PERCENT_TOLERANCE);
		
		//Set up the distance controller
		double distMaxInput = desiredDistance * 1.5;
		distanceController.setOutputRange(-1, 1);
		distanceController.setInputRange(-distMaxInput, distMaxInput);
		distanceController.setContinuous(false);
		distanceController.setToleranceBuffer(DIST_BUF_SIZE);
		distanceController.setPercentTolerance(DIST_PERCENT_TOLERANCE);
		
		alignmentController.setSetpoint(0);
		distanceController.setSetpoint(desiredDistance);

		alignmentController.enable();
		distanceController.enable();
		
		timeoutTimer.start();

	}

	@Override
	protected void execute() {
		
		drive.driveTank(leftMotorOutput, rightMotorOutput);
	}

	@Override
	protected boolean isFinished() {
		return (alignmentController.onTarget() && distanceController.onTarget()) || timeoutTimer.get() >= TIMEOUT_TIME;
	}

	@Override
	protected void end() {
		drive.driveTank(0, 0);
	}

	@Override
	protected void interrupted() {

	}


}
