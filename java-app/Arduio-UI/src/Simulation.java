import java.awt.*;

public class Simulation {
    public static SerialConnection serialConnection;
    private static String code;

    public static final int width = 1600;
    public static final int height = 800;
    public static boolean running = true;

    boolean threeInputMode = true;

    Button[] select3InputLogicGatesButtonList = new Button[7];
    Button[] select2InputLogicGatesButtonList; // will add later

    Button swapInputModeButton;

    Button input1Button;
    Button input2Button;
    Button input3Button;
    Button outputButton;

    Button testButton;

    int selectedGate = -1;

    public Simulation() {
        serialConnection = new SerialConnection();

        int buttonWidth = 160;
        int buttonHeight = 75;
        select3InputLogicGatesButtonList[0] = new Button(100, 700, buttonWidth, buttonHeight, "AND", Color.GRAY);
        select3InputLogicGatesButtonList[0].addPicture("and_gate_3_input.png");
        select3InputLogicGatesButtonList[1] = new Button(300, 700, buttonWidth, buttonHeight, "OR", Color.GRAY);
        select3InputLogicGatesButtonList[1].addPicture("or_gate_3_input.png");
        select3InputLogicGatesButtonList[2] = new Button(500, 700, buttonWidth, buttonHeight, "NAND", Color.GRAY);
        select3InputLogicGatesButtonList[2].addPicture("nand_gate_3_input.png");
        select3InputLogicGatesButtonList[3] = new Button(700, 700, buttonWidth, buttonHeight, "NOR", Color.GRAY);
        select3InputLogicGatesButtonList[3].addPicture("nor_gate_3_input.png");
        select3InputLogicGatesButtonList[4] = new Button(900, 700, buttonWidth, buttonHeight, "XOR", Color.GRAY);
        select3InputLogicGatesButtonList[4].addPicture("xor_gate_3_input.png");
        select3InputLogicGatesButtonList[5] = new Button(1100, 700, buttonWidth, buttonHeight, "XNOR", Color.GRAY);
        select3InputLogicGatesButtonList[5].addPicture("xnor_gate_3_input.png");
        select3InputLogicGatesButtonList[6] = new Button(1300, 700, buttonWidth, buttonHeight, "NOT", Color.GRAY);
        select3InputLogicGatesButtonList[6].addPicture("not_gate.png");

        swapInputModeButton = new Button(1500, 700, 75, 100, "", new Color(163, 56, 56));
        swapInputModeButton.addPicture("23.png");

        for (int i = 0; i < select3InputLogicGatesButtonList.length; i++) {
            select3InputLogicGatesButtonList[i].enableGradient(new Color(71, 91, 128), new Color(73, 92, 172));
            if(select2InputLogicGatesButtonList != null)
                select2InputLogicGatesButtonList[i].enableGradient(new Color(71, 91, 128), new Color(73, 92, 172));
        }

        int inputButtonWidth = 180;
        int inputButtonHeight = 20;
        input1Button = new Button(150, 500, inputButtonWidth, inputButtonHeight, "1", Color.red);
        input2Button = new Button(150, 400, inputButtonWidth, inputButtonHeight, "2", Color.red);
        input3Button = new Button(150, 300, inputButtonWidth, inputButtonHeight, "3", Color.red);
        outputButton = new Button(750, 395, inputButtonWidth, inputButtonHeight+5, "", Color.red);

        input1Button.setComment("0");
        input2Button.setComment("0");
        input3Button.setComment("0");
        outputButton.setComment("0");

        testButton = new Button(1400, 50, 100, 50, "Test", Color.BLUE);
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();

        // open port for arduino
        if (!SerialConnection.openPort("COM5")) {
            System.out.println("Failed to open COM5");
            return;
        }

        // Run the simulation
        // testCode();
        sim.run();

        serialConnection.closePort();
    }

    public void run() {
        // Set up Canvas
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        while (running) {
            // Background
            StdDraw.clear(StdDraw.BLACK);

            if(input1Button.isClicked()){
                input1Button.setComment(input1Button.getComment().equals("0") ? "1" : "0");
                input1Button.setBackground(input1Button.getBackground() == Color.red ? Color.green : Color.red);
            } else if(input2Button.isClicked()){
                input2Button.setComment(input2Button.getComment().equals("0") ? "1" : "0");
                input2Button.setBackground(input2Button.getBackground() == Color.red ? Color.green : Color.red);
            } else if(threeInputMode && input3Button.isClicked()){
                input3Button.setComment(input3Button.getComment().equals("0") ? "1" : "0");
                input3Button.setBackground(input3Button.getBackground() == Color.red ? Color.green : Color.red);
            } else if(outputButton.isClicked()){
                outputButton.setComment(outputButton.getComment().equals("0") ? "1" : "0");
                outputButton.setBackground(outputButton.getBackground() == Color.red ? Color.green : Color.red);
            }

            if(testButton.isClicked())
                sendCodeToArduino();

            StdDraw.text(150,700,"1");
            StdDraw.text(350,700,"2");
            StdDraw.text(550,700,"3");
            StdDraw.text(750,700,"4");
            StdDraw.text(950,700,"5");
            StdDraw.text(1150,700,"6");
            StdDraw.text(1350,700,"7");

            for (int i = 0; i < select3InputLogicGatesButtonList.length; i++) {
                if(select3InputLogicGatesButtonList[i].isClicked()) {
                    selectedGate = i+1;
                }
            }


            // Update and draw buttons
            if (threeInputMode) {
                for (Button button : select3InputLogicGatesButtonList) {
                    button.update();
                    button.draw();
                }
            }
            swapInputModeButton.update();
            swapInputModeButton.draw();
            input1Button.update();
            input1Button.draw();
            input2Button.update();
            input2Button.draw();
            input2Button.draw();
            if (threeInputMode) {
                input3Button.update();
                input3Button.draw();
            } else{
                input3Button.setEnabled(false);
                input3Button.enableGradient(Color.GRAY, Color.DARK_GRAY);
            }
            outputButton.update();
            outputButton.draw();
            testButton.update();
            testButton.draw();


            // Render
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    public void sendCodeToArduino() {
        // Code -> R~A~B~C~#~Y~O

        // R
        StringBuilder simOutput = new StringBuilder("0~");

        // A, B, C
        simOutput.append(input1Button.getComment()).append("~");
        simOutput.append(input2Button.getComment()).append("~");
        if (threeInputMode)
            simOutput.append(input3Button.getComment()).append("~");
        else
            simOutput.append("X~");

        // #
        simOutput.append(selectedGate).append("~");

        // Y
        simOutput.append("1~"); // to do (check logic)

        // O
        simOutput.append(outputButton.getComment());

        SerialConnection.sendCodeToArduino(simOutput.toString());
    }


    public static void testCode(){
        code = "0~1~0~0~X~1~1";
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
    }

    public static void setCode(String code) {
        Simulation.code = code;
    }

    public static void sendCode(String code) {
        SerialConnection.sendCodeToArduino(code);
    }
}
