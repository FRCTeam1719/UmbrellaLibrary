package subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

import devices.LogMessage;

/**
 * Logger class for writing logs to disk
 * @author Kyle
 *
 */
public class Logger {
	
	// The rate at which the log buffer will be written to disk
	private long updateRate = 1000;

	// The file which the logger is writing to
	private File logFile;

	// Internal object which we will use to write to the file
	private FileWriter fileWriter;

	// Buffered stream for the file writer to make things go faster
	private BufferedWriter bufferedFileWriter;
	
	// Thread-safe queue for writing the logs to disk
	private ArrayBlockingQueue<LogMessage> logBuffer;
	
	// Timer which will schedule the task which writes messages to disk
	private Timer logWriterTimer;
	
	// Task which writes the logs to disk
	private WriteMessagesToDisk writeTask;
	
	/**
	 * Task which writes the buffer to disk
	 * @author Kyle
	 *
	 */
	private class WriteMessagesToDisk extends TimerTask {

		@Override
		public void run() {
			writeLogBufferToDisk();
		}
		
	}

	/**
	 * Create a new Logger
	 * @param outputDirectory The directory in which to store the log files
	 * @throws FileNotFoundException
	 */
	public Logger(File outputDirectory) throws FileNotFoundException {

		// Make sure the directory given is valid
		if (outputDirectory == null) {
			throw new NullPointerException("Error creating Logger, given output directory was null");
		} else if (!outputDirectory.exists()) {
			throw new FileNotFoundException();
		}

		// Assume that two files are never going to be created at the exact same
		// instant
		String newFileName = LocalDateTime.now().toString() + ".log";
		logFile = new File(newFileName);

		// Initialize file writing objects
		try {
			fileWriter = new FileWriter(logFile);
			bufferedFileWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			System.out.println("IOException creating File writer");
			e.printStackTrace();
		}
		
		// Initialize the log queue
		logBuffer = new ArrayBlockingQueue<>(1000);
		
		// Create and start the task which periodically writes the buffer to disk
		writeTask = new WriteMessagesToDisk();
		logWriterTimer = new Timer();
		logWriterTimer.scheduleAtFixedRate(writeTask, 0, updateRate);
	}
	
	
	/**
	 * Add a message to the queue, to be written to disk
	 * @param message The message to add
	 */
	public void addMessage(LogMessage message) {
		if (logBuffer.remainingCapacity() == 0) {
			System.err.println("Error: log buffer is full, cannot add message: " + message.getFullMessage());
			return;
		}
		
		logBuffer.add(message);
	}
	
	/**
	 * Changes the update rate at which the logs get written
	 * @param newUpdateRate The new update rate, in milliseconds
	 */
	public void setWriteUpdateRate(long newUpdateRate) {
		
		// Cancel the current task, create a new one, and schedule the new one at the new update rate
		updateRate = newUpdateRate;
		writeTask.cancel();
		writeTask = new WriteMessagesToDisk();
		logWriterTimer.scheduleAtFixedRate(writeTask, 0, updateRate);
	}
	
	/**
	 * Gets the current update rate
	 * @return The current update rate, in milliseconds
	 */
	public long getWriteUpdateRate() {
		return updateRate;
	}
	
	/**
	 * Write a message to the disk
	 * @param message The message to write
	 */
	private void writeMessageToDisk(LogMessage message) {
		try {
			bufferedFileWriter.write(message.getFullMessage());
		} catch (IOException e) {
			System.err.println("Error writing message to disk: " + message.getFullMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Write all messages in the buffer to disk
	 */
	private void writeLogBufferToDisk() {
		LogMessage currentMessage;
		while (!logBuffer.isEmpty()) {
			currentMessage = logBuffer.remove();
			writeMessageToDisk(currentMessage);
		}
	}

}
