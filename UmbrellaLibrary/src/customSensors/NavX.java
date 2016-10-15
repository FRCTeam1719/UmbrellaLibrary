package customSensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;
import interfaces.INavX;
import interfaces.Loggable;
import interfaces.Sensor;

public class NavX extends AHRS implements INavX, Sensor, Loggable {

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

	public NavX(Port i2c_port_id, byte update_rate_hz) {
		super(i2c_port_id, update_rate_hz);
	}

	public NavX(edu.wpi.first.wpilibj.I2C.Port i2c_port_id, byte update_rate_hz) {
		super(i2c_port_id, update_rate_hz);
	}

	public NavX(Port spi_port_id, int spi_bitrate, byte update_rate_hz) {
		super(spi_port_id, spi_bitrate, update_rate_hz);
	}

	public NavX(edu.wpi.first.wpilibj.SerialPort.Port serial_port_id, SerialDataType data_type, byte update_rate_hz) {
		super(serial_port_id, data_type, update_rate_hz);
	}

	public NavX(Port serial_port_id) {
		super(serial_port_id);
	}

	public NavX(edu.wpi.first.wpilibj.I2C.Port serial_port_id) {
		super(serial_port_id);
	}

	public NavX(edu.wpi.first.wpilibj.SerialPort.Port serial_port_id) {
		super(serial_port_id);
	}
	
	
}
