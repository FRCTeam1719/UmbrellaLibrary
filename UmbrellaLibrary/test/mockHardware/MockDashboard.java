package mockHardware;

import java.util.Hashtable;

import edu.wpi.first.wpilibj.Sendable;
import interfaces.IDashboard;

public class MockDashboard implements IDashboard {

	private Hashtable<String,Boolean> booleans = new Hashtable<String,Boolean>();
	private Hashtable<String,Double> numbers = new Hashtable<String,Double>();
	private Hashtable<String,String> strings = new Hashtable<String,String>();
	
	@Override
	public void _putBoolean(String key, boolean value) {
		booleans.put(key, value);
	}

	@Override
	public boolean _getBoolean(String key) {
		return booleans.get(key);
	}

	@Override
	public void _putNumber(String key, double value) {
		numbers.put(key, value);
	}

	@Override
	public double _getNumber(String key) {
		return numbers.get(key);
	}

	@Override
	public void _putString(String key, String value) {
		strings.put(key, value);
	}

	@Override
	public String _getString(String key) {
		return strings.get(key);
	}

	@Override
	public void putData(String string, Sendable pidController) {
		
	}

}
