package interfaces;

public interface IEncoder extends Loggable, Sensor{

	public double getDistance();
	public double getRate();
	
	public void reset();
}
