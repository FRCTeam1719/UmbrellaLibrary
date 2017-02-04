package subsystems;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;

public class Logger {
	
	private OutputStream outputStream = null;
	private File directory = null;
	private File logFile = null;
	private BufferedWriter logWriter = null;
	private ArrayBlockingQueue<String> logBuffer = new ArrayBlockingQueue<String>(100);
	
	Runnable loggingTask = new Runnable() {
		public void run() {
			while (true) {
				try {
					String msg = logBuffer.take();
					write(msg);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	};
	
	public Logger(File directory) {
		this.directory = directory;
		if (this.directory != null) {
			logFile = new File(Calendar.getInstance().getTime() + ".log");
		}
		
		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(logFile));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			logWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public Logger(OutputStream outStream) {
		this.outputStream = outStream;
		
		logWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	
	
	private void write(String str) {
		try {
			logWriter.write(str);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
