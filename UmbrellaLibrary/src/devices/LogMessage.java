package devices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogMessage {

	private String message;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar cal = Calendar.getInstance();
	
	public LogMessage(String message) {
		this.message = message;
	}
	
	public String getFullMessage() {
		return "[" + dateFormat.format(cal) + "]: " + message;
	}
}
