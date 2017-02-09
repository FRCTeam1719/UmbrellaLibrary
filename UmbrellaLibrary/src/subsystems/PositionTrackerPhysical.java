package subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import interfaces.IEncoder;
import interfaces.INavX;
import interfaces.IPositionTracker;

public class PositionTrackerPhysical extends Subsystem implements IPositionTracker {
	
	
	//Periodically updates the positionTracker
	private class UpdatePositionTracker extends Command {
		
		IPositionTracker posTracker;
		public UpdatePositionTracker(IPositionTracker tracker) {
			this.posTracker = tracker;
		}
		
		@Override
		protected void execute() {
			posTracker.update();
		}

		@Override
		protected boolean isFinished() {
			return false;
		}
		
	}

	PositionTrackerLogic logicalTracker;
	
	public PositionTrackerPhysical(IEncoder lEnc, IEncoder rEnc, INavX navx) {
		logicalTracker = new PositionTrackerLogic(lEnc, rEnc, navx);
	}
	
	@Override
	public void disable() {
		// No actuators
	}

	@Override
	public void log() {
		
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new UpdatePositionTracker(this));
	}

	@Override
	public void update() {
		logicalTracker.update();
	}

	@Override
	public double getX() {
		return logicalTracker.getX();
	}

	@Override
	public double getY() {
		return logicalTracker.getY();
	}

	@Override
	public double getYaw() {
		return logicalTracker.getYaw();
	}

	@Override
	public void reset() {
		logicalTracker.reset();
	}

}
