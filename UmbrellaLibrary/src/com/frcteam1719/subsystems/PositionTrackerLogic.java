package com.frcteam1719.subsystems;

import com.frcteam1719.customsensors.NavX;
import com.frcteam1719.interfaces.ISubsystem;

import edu.wpi.first.wpilibj.Encoder;

public class PositionTrackerLogic implements ISubsystem {

	Encoder leftEnc;
	Encoder rightEnc;
	NavX navx;

	double currentX = 0;
	double currentY = 0;
	double currentHeading = 0;

	double totalLeftDist = 0;
	double totalRightDist = 0;

	public PositionTrackerLogic(Encoder leftEncoder, Encoder rightEncoder, NavX navx) {
		this.leftEnc = leftEncoder;
		this.rightEnc = rightEncoder;
		this.navx = navx;
	}

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

	public double getX() {
		return currentX;
	}

	public double getY() {
		return currentY;
	}

	public double getYaw() {
		return currentHeading;
	}

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
