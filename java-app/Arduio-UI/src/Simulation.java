public class Simulation {
    public static SerialConnection serialConnection;
    private static String code;

    public Simulation() {
        serialConnection = new SerialConnection();
    }

    public static void setCode(String code) {
        Simulation.code = code;
    }

    public static void main(String[] args) {
        if (!SerialConnection.openPort("COM4")) {
            System.out.println("Failed to open COM4");
            return;
        }

        code = "0~0~0~1~X~0~1";
        sendCode(code);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        // Test digits 0-9
        for (int i = 0; i < 10; i++) {
            code = "0~0~0~0~" + i + "~1~0"; // R=0, A=0,B=0,C=0, #=i, Y=1(correct), O=0(output LED)
            sendCode(code);

            try {
                Thread.sleep(3000); // wait 3 seconds to see the number
            } catch (InterruptedException ignored) {}
        }

        code = "1~0~0~0~X~2~0";
        sendCode(code);

        serialConnection.closePort();
    }

    public static void sendCode(String code) {
        SerialConnection.sendCodeToArduino(code);
    }
}
