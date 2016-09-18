package interfaces;

public interface TestableDashboard {

	public void _putBoolean(String key, boolean value);
	
	public boolean _getBoolean(String key);
	
	public void _putNumber(String key, double value);
	
	public double _getNumber(String key);
	
	public void _putString(String key, String value);
	
	public String _getString(String key);
	
}
