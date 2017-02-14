package subsystems;

import commands.UseDriveTank;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.IDrive;
import interfaces.IEncoder;
import interfaces.INavX;
import interfaces.Loggable;
import interfaces.RobotInterface;

public class DrivePhysical extends Subsystem implements IDrive, Loggable {

	DriveLogic logicDrive;
	RobotInterface robot;
	
	public DrivePhysical(RobotInterface robot, SpeedController leftSide, SpeedController rightSide,
			IEncoder leftEncoder, IEncoder rightEncoder) {
		
		this.robot = robot;
		this.robot.registerSubsystem(this);
		logicDrive = new DriveLogic(leftSide, rightSide, leftEncoder, rightEncoder);
	}

	public DrivePhysical(RobotInterface robot, SpeedController leftSide, SpeedController rightSide, INavX navX) {
		this.robot = robot;
		logicDrive = new DriveLogic(leftSide, rightSide, navX);
	}

	public DrivePhysical(RobotInterface robot, SpeedController leftSide, SpeedController rightSide) {
		this.robot = robot;
		this.robot.registerSubsystem(this);
		logicDrive = new DriveLogic(leftSide, rightSide);
	}

	/**
	 * Move the robot by setting the left and right motor values
	 * 
	 * @param leftSpeed The value given to the left motor
	 * @param rightSpeed The value given to the right motor
	 */
	@Override
	public void driveTank(double leftVal, double rightVal) {
		logicDrive.driveTank(leftVal, rightVal);
	}
	
	
	/**
	 * Move the robot by providing a speed value and a rotate value
	 * 
	 * @param speed The speed to move the robot at
	 * @param rotate Rotates the robot while it moves
	 */
	@Override
	public void driveArcade(double speed, double rotate) {
		logicDrive.driveArcade(speed, rotate);
	}
	
	public void setMaxSpeed(double speed) {
		logicDrive.setMaxSpeed(speed);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UseDriveTank(robot, this));	
	}
	
	
	/**
	 * With this you can live-switch the default command, allowing you to change to
	 * UseDriveVelocityPID, UseDriveArcade, etc in the middle of a match
	 */
	@Override
	public void setDefaultCommand(Command command) {
		super.setDefaultCommand(command);
		command.start();
	}

	@Override
	public void log() {
		
	}

	@Override
	public double getLeftEncoderDist() {
		return logicDrive.getLeftEncoderDist();
	}

	@Override
	public double getRightEncoderDist() {
		return logicDrive.getRightEncoderDist();
	}

	@Override
	public double getLeftEncoderRate() {
		return logicDrive.getLeftEncoderRate();
	}

	@Override
	public double getRightEncoderRate() {
		return logicDrive.getRightEncoderRate();
	}

	@Override
	public double getYaw() {
		return logicDrive.getYaw();
	}
	
	@Override
	public double getPitch() {
		return logicDrive.getPitch();
	}

	@Override
	public double getRoll() {
		return logicDrive.getRoll();
	}
	
	@Override
	public INavX getNavX() {
		return logicDrive.getNavX();
	}

	@Override
	public double getAvgEncoderDist() {
		return logicDrive.getAvgEncoderDist();
	}

	@Override
	public double getAvgEncoderRate() {
		return logicDrive.getAvgEncoderRate();
	}

	@Override
	public IEncoder getLeftEncoder() {
		return logicDrive.getLeftEncoder();
	}

	@Override
	public IEncoder getRightEncoder() {
		return logicDrive.getRightEncoder();
	}

	@Override
	public void disable() {
		logicDrive.disable();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		logicDrive.setPIDSourceType(pidSource);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return logicDrive.getPIDSourceType();
	}

	@Override
	public double pidGet() {
		return logicDrive.pidGet();
	}

}
