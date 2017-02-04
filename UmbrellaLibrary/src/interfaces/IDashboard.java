package interfaces;

import edu.wpi.first.wpilibj.Sendable;

public interface IDashboard {

	public void _putBoolean(String key, boolean value);
	
	public boolean _getBoolean(String key);
	
	public void _putNumber(String key, double value);
	
	public double _getNumber(String key);
	
	public void _putString(String key, String value);
	
	public String _getString(String key);

	public void putData(String string, Sendable pidController);
	
}
