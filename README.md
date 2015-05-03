# Umbrella-Library

### How to use

Add the lines below to your build.xml file.  Where you see square brackets (like these []) replace their contents with the actual value on your system.

	<exec executable="rm">
	<arg value="-rf" />
	<arg value="${basedir}/src/org/usfirst/frc1719j/ulib" />
	</exec>
	<exec executable="cp">
	<arg value="-R" />
	<arg value="[path to UmbrellaLibrary repository]/UmbrellaLibrary/src/org" />
	<arg value="src/" />
	</exec>

### The Umbrella Library Manifesto

The Umbrella Library seeks to provide support for generic robot functions reliably, so that in-season programming effort can be spent on robot-specific tasks. The Umbrella Library wishes to provide support for the following:

	- A Consistent Drive Platform
		- Uses sensors to improve drivability and reliability
		- Supports Tank and Meccanum
		- Reliable distance Detection 

	- Sensor Library
		- Provide support for a wide range of sensors
		- Currently plan is to include the following:
			- LIDAR
			- Gyro
			- Accelerometer 
			- Encoders
			- Ultrasonic
			- Infrared

	- Vision
		- Provide a basic vision system able to identify basic shapes+colors
