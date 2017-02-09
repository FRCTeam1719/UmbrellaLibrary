package interfaces;

/**
 * Interface for a position tracking subsystem
 * 
 * @author Kyle
 *
 */
public interface IPositionTracker extends ISubsystem {

	/**
	 * Updates the position every time this is called. Call it frequently
	 * (preferably as often as possible) in order to maintain accuracy
	 */
	public void update();

	/**
	 * 
	 * @return The current x (left / right) position on the field,
	 * relative to the last reset
	 */
	public double getX();

	/**
	 * 
	 * @return The current y (forwards / backwards) position on the field,
	 * relative to the last reset
	 */
	public double getY();

	/**
	 * 
	 * @return The current heading relative to the last reset
	 */
	public double getYaw();

	/**
	 * resets the position and heading, sets the position to (0, 0) and heading to 0
	 */
	public void reset();

}
