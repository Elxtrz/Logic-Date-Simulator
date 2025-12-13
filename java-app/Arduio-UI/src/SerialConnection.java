import com.fazecast.jSerialComm.SerialPort;

public class SerialConnection {

    private static SerialPort arduinoPort;

    public static boolean openPort(String portName) {
        arduinoPort = SerialPort.getCommPort(portName);
        arduinoPort.setBaudRate(9600);

        if (arduinoPort.openPort()) {
            System.out.println("Arduino port opened: " + portName);
            try {
                Thread.sleep(2000); // wait for Arduino reset
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("Failed to open Arduino port!");
            return false;
        }
    }

    public static void sendCodeToArduino(String code) {
        if (arduinoPort != null && arduinoPort.isOpen()) {
            String toSend = code + "\n"; // Arduino reads until newline
            byte[] bytes = toSend.getBytes();
            arduinoPort.writeBytes(bytes, bytes.length);
            System.out.println("Sent: " + code);
        } else
            System.out.println("Arduino port not open!");
    }

    public static void closePort() {
        if (arduinoPort != null && arduinoPort.isOpen()) {
            arduinoPort.closePort();
            System.out.println("Arduino port closed.");
        }
    }
}
