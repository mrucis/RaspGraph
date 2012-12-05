
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;

public class SerialPortHandler {
	private SerialPort serialPort;
	private OutputStream outStream;
	private InputStream inStream;

	// List of available serial ports
	public HashMap getPortNames() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Enumeration portList = CommPortIdentifier.getPortIdentifiers();
			int nr = 0;
			while (portList.hasMoreElements()) {
				CommPortIdentifier portId = (CommPortIdentifier) portList
						.nextElement();
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					map.put(nr + "", portId.getName());
					// System.out.println(portId.getName());
				}
				nr++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	// reads message from serial port till EOL
	public String read() throws Exception {

		byte[] buffer = new byte[1024];
		String bufString = "";
		int data;

		try {
			int len = 0;
			while ((data = inStream.read()) > -1) {
				if (data == '\n') {
					break;
				}
				buffer[len++] = (byte) data;
			}
			bufString = new String(buffer, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return bufString;
	}

	public void write(String message) throws Exception {
		try {
			outStream.write(message.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public void connect(String portName) throws Exception {
		try {
			// Obtain a CommPortIdentifier object for the port you want to open
			CommPortIdentifier portId = CommPortIdentifier
					.getPortIdentifier(portName);

			// Get the port's ownership
			serialPort = (SerialPort) portId.open("Drawer", 5000);
			serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

			outStream = serialPort.getOutputStream();
			inStream = serialPort.getInputStream();
		} catch (Exception e) {
			serialPort.close();
			e.printStackTrace();
		}
	}
}
