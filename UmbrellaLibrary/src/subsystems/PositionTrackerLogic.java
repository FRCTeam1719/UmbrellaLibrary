package subsystems;

import interfaces.IEncoder;
import interfaces.INavX;
import interfaces.IPositionTracker;

public class PositionTrackerLogic implements IPositionTracker {

	IEncoder leftEnc;
	IEncoder rightEnc;
	INavX navx;

	double currentX = 0;
	double currentY = 0;
	double currentHeading = 0;

	double totalLeftDist = 0;
	double totalRightDist = 0;

	public PositionTrackerLogic(IEncoder leftEncoder, IEncoder rightEncoder, INavX navx) {
		this.leftEnc = leftEncoder;
		this.rightEnc = rightEncoder;
		this.navx = navx;
	}

	@Override
	public void update() {
		currentHeading = navx.getYaw();

		// Changes in left and right encoder distances
		double changeLeft = totalLeftDist - leftEnc.getDistance();
		double changeRight = totalRightDist - rightEnc.getDistance();
		double avgChange = (changeLeft + changeRight) / 2;

		// Update the total encoder distances
		totalLeftDist += changeLeft;
		totalRightDist += changeRight;

		// Update the position based on the heading and the change in distance
		currentX += Math.sin(Math.toRadians(currentHeading)) * avgChange;
		currentY += Math.cos(Math.toRadians(currentHeading)) * avgChange;

	}

	@Override
	public double getX() {
		return currentX;
	}

	@Override
	public double getY() {
		return currentY;
	}

	@Override
	public double getYaw() {
		return currentHeading;
	}

	@Override
	public void reset() {
		currentX = 0;
		currentY = 0;
		currentHeading = 0;

		totalLeftDist = 0;
		totalRightDist = 0;

		leftEnc.reset();
		rightEnc.reset();
		navx.reset();
	}

	@Override
	public void disable() {
		// No actuators, dont' need to do anything to disable
	}

	@Override
	public void log() {

	}

}
