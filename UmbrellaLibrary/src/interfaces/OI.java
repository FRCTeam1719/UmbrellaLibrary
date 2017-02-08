package interfaces;

import edu.wpi.first.wpilibj.Joystick;


/**
 * The Operator interface is how we get joystick information from the driver
 * station to our program. UmbrellaLibrary's interface assumes the driver joystick
 * has left and right joysticks
 * @author Kyle
 *
 */
public interface OI {
	
	/**
	 * Constants for various controller ports. Adjust and add to these
	 * as needed
	 * 
	 */
	
	//XBOX joystick constants
	public static final double XBOX_LEFT_X = 0;
	public static final double XBOX_LEFT_Y = 1;
	public static final double XBOX_RIGHT_X = 4;
	public static final double XBOX_RIGHT_Y = 5;
	
	
	Joystick getDriverJoystick();
	Joystick getOperaterJoystick();
	
	double getDriverLeftX();
	double getDriverLeftY();
	double getDriverRightX();
	double getDriverRightY();
}
