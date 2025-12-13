import com.fazecast.jSerialComm.SerialPort;

public class SerialTest {
    public static void main(String[] args) {
        // 1. List available ports
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Available ports:");
        for (SerialPort port : ports)
            System.out.println(port.getSystemPortName());


        // 2. Select port and set parameters
        SerialPort arduinoPort = SerialPort.getCommPort("COM4");
        arduinoPort.setBaudRate(9600);

        // 3. Open port
        if (arduinoPort.openPort()) {
            System.out.println("Port opened successfully!");

            try {
                Thread.sleep(2000); // wait for Arduino to initialize
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 4. Send string to Arduino
            String testString = "1010101010\n"; // newline is important for Arduino read
            byte[] bytes = testString.getBytes();
            arduinoPort.writeBytes(bytes, bytes.length);

            try {
                // wait long enough for LED to finish blinking
                Thread.sleep(testString.length() * 500 + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 5. Close port
            arduinoPort.closePort();
            System.out.println("Port closed.");
        } else
            System.out.println("Failed to open port.");
    }
}
