package com.frcteam1719.commands;

import com.frcteam1719.interfaces.IDrive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends Command implements PIDOutput {
	
	public static final double TURN_TO_ANGLE_TOLERANCE = 5D;
	public static final double TURN_TO_ANGLE_TIMEOUT = 5D;
	public static final String TURN_TO_ANGLE_KP_DASHBOARD_STRING = "Turn to angle kP: ";
	public static final String TURN_TO_ANGLE_KI_DASHBOARD_STRING = "Turn to angle kI: ";
	public static final String TURN_TO_ANGLE_KD_DASHBOARD_STRING = "Turn to angle kD: ";

	
	PIDController pidController;
	Timer timeoutTimer;
	
	private double leftOutput, rightOutput;
	
	private double kP;
	private double kI;
	private double kD;
	
	private boolean reset = true;
	private double desiredAngle;
	
	IDrive drive;
	
	public TurnToAngle(IDrive drive, double angle, boolean reset) {
		this.drive = drive;
		this.desiredAngle = angle;
		this.reset = reset;
		
		pidController = new PIDController(kP, kI, kD, drive.getNavX(), this);
		
		SmartDashboard.putData("TurnToAngle PID Controller", pidController);
	}

	@Override
	protected void initialize() {
		
		if (reset) {
			drive.getNavX().reset();
		}
		
		pidController.setOutputRange(-1, 1);
		pidController.setInputRange(-180, 180);
		pidController.setContinuous(true);
		pidController.setToleranceBuffer(20);
		pidController.setAbsoluteTolerance(TURN_TO_ANGLE_TOLERANCE);
		
		pidController.setSetpoint(desiredAngle);
		pidController.enable();
		timeoutTimer.start();
	}

	@Override
	protected void execute() {

		drive.driveTank(leftOutput, rightOutput);
		
	}

	@Override
	protected boolean isFinished() {
		return pidController.onTarget() || (timeoutTimer.get() >= TURN_TO_ANGLE_TIMEOUT);
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
		leftOutput = output;
		rightOutput = -output;
		
	}

}
