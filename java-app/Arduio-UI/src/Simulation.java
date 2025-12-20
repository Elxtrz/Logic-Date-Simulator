import java.awt.*;

public class Simulation {
    // Global Variables
    static int simulationNumber = 1;

    int totalSimulations = 2;

    public static SerialConnection serialConnection;
    private static String code;

    public static final int width = 1600;
    public static final int height = 800;
    public static boolean running = true;

    Button toggleSimulationButton;

    // Sim1 buttons
    Button[] select3InputLogicGatesButtonList = new Button[7];

    Button gateNumberButton_Sim1;

    Button input1Button_Sim1;
    Button input2Button_Sim1;
    Button input3Button_Sim1;
    Button outputButton_Sim1;

    Button testButton;

    int selectedGate;

    Button resetButton_Sim1;

    Button isCorrectButton;

    Button selectedGateButton_Sim1;


    // Sim2 buttons
    Button[] select2InputLogicGatesButtonList = new Button[7];

    Button input1Button_Sim2;
    Button input2Button_Sim2;
    Button input3Button_Sim2;
    Button outputButton_Sim2;

    Button firstLogicGate_Sim2;
    Button secondLogicGate_Sim2;

    int selectGate1_Sim2 = -1;
    int selectGate2_Sim2 = -1;

    boolean isButton1Selected_Sim2 = false;

    Button showNextStepButton_Sim2;

    Button resetButton_Sim2;

    Button gateNumberButton_Sim2;

    public Simulation() {
        // Set up Serial Connection
//        serialConnection = new SerialConnection();

        // Sim1 Buttons Initialization
        initialize_Sim1();

        // Sim2 Buttons Initialization
        initialize_Sim2();

        // Toggle Simulation Button
        toggleSimulationButton = new Button(150, 50, 80, 50, "Toggle", Color.MAGENTA);
        toggleSimulationButton.enableGradient(new Color(255, 105, 180), new Color(138, 43, 226));
    }

    public void initialize_Sim1() {
        selectedGate = -1;

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

        gateNumberButton_Sim1 = new Button(1500, 700, 100, 60, "Gate: " + selectedGate, new Color(228, 107, 107));

        for (Button button : select3InputLogicGatesButtonList)
            button.enableGradient(new Color(71, 91, 128), new Color(73, 92, 172));

        int inputButtonWidth = 230;
        int inputButtonHeight = 20;
        input1Button_Sim1 = new Button(150, 500, inputButtonWidth, inputButtonHeight, "1", Color.red);
        input2Button_Sim1 = new Button(150, 400, inputButtonWidth, inputButtonHeight, "2", Color.red);
        input3Button_Sim1 = new Button(150, 300, inputButtonWidth, inputButtonHeight, "3", Color.red);
        outputButton_Sim1 = new Button(700, 400, inputButtonWidth, inputButtonHeight + 5, "", Color.red);

        input1Button_Sim1.setComment("0");
        input2Button_Sim1.setComment("0");
        input3Button_Sim1.setComment("0");
        outputButton_Sim1.setComment("0");

        testButton = new Button(1500, 50, 100, 50, "Test", Color.BLUE);
        testButton.enableGradient(new Color(215, 239, 71), new Color(41, 154, 12));

        resetButton_Sim1 = new Button(60, 50, 50, 50, "", new Color(216, 116, 54));
        resetButton_Sim1.addPicture("reset_icon.png");

        isCorrectButton = new Button(1300, 50, 150, 50, "Is Correct?");
        isCorrectButton.enableGradient(new Color(78, 230, 132), new Color(193, 13, 34));

        selectedGateButton_Sim1 = new Button(430, 400, 300, 225, "", Color.BLACK);
    }

    public void initialize_Sim2() {
        int buttonWidth = 160;
        int buttonHeight = 75;
        select2InputLogicGatesButtonList[0] = new Button(100, 700, buttonWidth, buttonHeight, "AND", Color.GRAY);
        select2InputLogicGatesButtonList[0].addPicture("and_gate.png");
        select2InputLogicGatesButtonList[1] = new Button(300, 700, buttonWidth, buttonHeight, "OR", Color.GRAY);
        select2InputLogicGatesButtonList[1].addPicture("or_gate.png");
        select2InputLogicGatesButtonList[2] = new Button(500, 700, buttonWidth, buttonHeight, "NAND", Color.GRAY);
        select2InputLogicGatesButtonList[2].addPicture("nand_gate.png");
        select2InputLogicGatesButtonList[3] = new Button(700, 700, buttonWidth, buttonHeight, "NOR", Color.GRAY);
        select2InputLogicGatesButtonList[3].addPicture("nor_gate.png");
        select2InputLogicGatesButtonList[4] = new Button(900, 700, buttonWidth, buttonHeight, "XOR", Color.GRAY);
        select2InputLogicGatesButtonList[4].addPicture("xor_gate.png");
        select2InputLogicGatesButtonList[5] = new Button(1100, 700, buttonWidth, buttonHeight, "XNOR", Color.GRAY);
        select2InputLogicGatesButtonList[5].addPicture("xnor_gate.png");
        select2InputLogicGatesButtonList[6] = new Button(1300, 700, buttonWidth, buttonHeight, "NOT", Color.GRAY);
        select2InputLogicGatesButtonList[6].addPicture("not_gate.png");

        for (Button button : select2InputLogicGatesButtonList)
            button.enableGradient(new Color(71, 91, 128), new Color(73, 92, 172));

        resetButton_Sim2 = new Button(60, 50, 50, 50, "", new Color(216, 116, 54));
        resetButton_Sim2.addPicture("reset_icon.png");

        gateNumberButton_Sim2 = new Button(1500, 700, 100, 60, "Gate: " + selectedGate, new Color(228, 107, 107));
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();

        // open port for arduino
        if (!SerialConnection.openPort("COM5")) {
            System.out.println("Failed to open COM5");
            return;
        }

        // Run the simulation
        switch (simulationNumber) {
            case 0:
                testCode();
                break;
            case 1:
                sim.runTestingYourself_Sim1();
                break;
            case 2:
                sim.runCombineLogicGates_Sim2();
            default:
                System.out.println("No simulation selected");
        }

        serialConnection.closePort();
    }

    public void runTestingYourself_Sim1() {
        // Set up Canvas
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        while (running) {
            // Background
            StdDraw.clear(StdDraw.BLACK);

            if (input1Button_Sim1.isClicked()) {
                input1Button_Sim1.setComment(input1Button_Sim1.getComment().equals("0") ? "1" : "0");
                input1Button_Sim1.setBackground(input1Button_Sim1.getBackground() == Color.red ? Color.green : Color.red);
            } else if (input2Button_Sim1.isClicked()) {
                input2Button_Sim1.setComment(input2Button_Sim1.getComment().equals("0") ? "1" : "0");
                input2Button_Sim1.setBackground(input2Button_Sim1.getBackground() == Color.red ? Color.green : Color.red);
            } else if (input3Button_Sim1.isClicked()) {
                input3Button_Sim1.setComment(input3Button_Sim1.getComment().equals("0") ? "1" : "0");
                input3Button_Sim1.setBackground(input3Button_Sim1.getBackground() == Color.red ? Color.green : Color.red);
            } else if (outputButton_Sim1.isClicked()) {
                outputButton_Sim1.setComment(outputButton_Sim1.getComment().equals("0") ? "1" : "0");
                outputButton_Sim1.setBackground(outputButton_Sim1.getBackground() == Color.red ? Color.green : Color.red);
            }

            if (testButton.isClicked() && selectedGate != -1)
                sendCodeToArduino(false);

            StdDraw.setFont(new Font("Arial", Font.BOLD, 22));
            for (int i = 0; i < select3InputLogicGatesButtonList.length; i++) {
                if (select3InputLogicGatesButtonList[i].isClicked()) {
                    selectedGate = i + 1;
                    gateNumberButton_Sim1.setText("Gate: " + selectedGate);
                }
            }

            if (selectedGate != -1)
                drawGates(selectedGate);

            if (resetButton_Sim1.isClicked()) {
                sendCodeToArduino(true);
                selectedGate = -1;
                gateNumberButton_Sim1.setText("Gate: " + selectedGate);

                input1Button_Sim1.setComment("0");
                input1Button_Sim1.setBackground(Color.red);
                input2Button_Sim1.setComment("0");
                input2Button_Sim1.setBackground(Color.red);
                input3Button_Sim1.setComment("0");
                input3Button_Sim1.setBackground(Color.red);
                outputButton_Sim1.setComment("0");
                outputButton_Sim1.setBackground(Color.red);

                isCorrectButton.enableGradient(new Color(78, 230, 132), new Color(193, 13, 34));
                isCorrectButton.setText("Is Correct?");
            }

            // Update and draw buttons
            for (Button button : select3InputLogicGatesButtonList) {
                button.update();
                button.draw();
            }


            gateNumberButton_Sim1.update();
            gateNumberButton_Sim1.draw();

            input1Button_Sim1.update();
            input1Button_Sim1.draw();
            input2Button_Sim1.update();
            input2Button_Sim1.draw();
            input2Button_Sim1.draw();
            input3Button_Sim1.update();
            input3Button_Sim1.draw();
            outputButton_Sim1.update();
            outputButton_Sim1.draw();

            testButton.update();
            testButton.draw();

            resetButton_Sim1.update();
            resetButton_Sim1.draw();

            isCorrectButton.update();
            isCorrectButton.draw();

            selectedGateButton_Sim1.update();
            selectedGateButton_Sim1.draw();

            toggleSimulationButton.update();
            toggleSimulationButton.draw();

            if(toggleSimulationButton.isClicked()){
                simulationNumber++;
                if(simulationNumber > totalSimulations)
                    simulationNumber = 1;

                SerialConnection.closePort();
                main(new String[] {});
            }

            StdDraw.setPenColor(Color.red);
            StdDraw.setFont(new Font("Arial", Font.BOLD, 22));
            StdDraw.text(100, 750, "1");
            StdDraw.text(300, 750, "2");
            StdDraw.text(500, 750, "3");
            StdDraw.text(700, 750, "4");
            StdDraw.text(900, 750, "5");
            StdDraw.text(1100, 750, "6");
            StdDraw.text(1300, 750, "7");
            StdDraw.setPenColor(Color.black);

            // Render
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    private void runCombineLogicGates_Sim2() {
        // Set up Canvas
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        while (running) {
            // Background
            StdDraw.clear(StdDraw.BLACK);


            // Update and draw buttons
            for (Button button : select2InputLogicGatesButtonList) {
                button.update();
                button.draw();
            }

            // Global button
            if(toggleSimulationButton.isClicked()){
                simulationNumber++;
                if(simulationNumber > totalSimulations)
                    simulationNumber = 1;

                SerialConnection.closePort();
                main(new String[] {});
            }

            // logic gate numbering
            StdDraw.setPenColor(Color.red);
            StdDraw.setFont(new Font("Arial", Font.BOLD, 22));
            StdDraw.text(100, 750, "1");
            StdDraw.text(300, 750, "2");
            StdDraw.text(500, 750, "3");
            StdDraw.text(700, 750, "4");
            StdDraw.text(900, 750, "5");
            StdDraw.text(1100, 750, "6");
            StdDraw.text(1300, 750, "7");
            StdDraw.setPenColor(Color.black);

            // Render
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    private void drawGates(int selectedGate) {
        StdDraw.setFont(new Font("Arial", Font.BOLD, 22));
        for (int i = 0; i < select3InputLogicGatesButtonList.length; i++) {
            if (select3InputLogicGatesButtonList[i].isClicked()) {
                selectedGate = i + 1;
                gateNumberButton_Sim1.setText("Gate: " + selectedGate);
                String[] pics = {
                        "and_gate_3_input.png",
                        "or_gate_3_input.png",
                        "nand_gate_3_input.png",
                        "nor_gate_3_input.png",
                        "xor_gate_3_input.png",
                        "xnor_gate_3_input.png",
                        "not_gate.png"
                };
                selectedGateButton_Sim1.addPicture(pics[i]);
            }
        }
    }

    public void sendCodeToArduino(boolean reset) {
        if (reset) {
            SerialConnection.sendCodeToArduino("1~0~0~0~X~0~0");
            return;
        }

        // Code -> R~A~B~C~#~Y~O

        // R
        StringBuilder simOutput = new StringBuilder("0~");

        // A, B, C
        simOutput.append(input1Button_Sim1.getComment()).append("~");
        simOutput.append(input2Button_Sim1.getComment()).append("~");
        simOutput.append(input3Button_Sim1.getComment()).append("~");

        // #
        simOutput.append(selectedGate).append("~");

        // Y
        simOutput.append(checkInput_Sim1(simOutput.toString(), outputButton_Sim1.getComment()));

        // O
        simOutput.append(outputButton_Sim1.getComment());

        SerialConnection.sendCodeToArduino(simOutput.toString());
    }

    private String checkInput_Sim1(String input, String output) {
        String[] parts = input.split("~");

        int A = Integer.parseInt(parts[1]);
        int B = Integer.parseInt(parts[2]);
        int C = Integer.parseInt(parts[3]);
        int gateNumber = Integer.parseInt(parts[4]);

        int expectedOutput = Integer.parseInt(output);
        int actualOutput = evaluateGate(gateNumber, A, B, C);

        if (actualOutput == expectedOutput) {
            isCorrectButton.enableGradient(new Color(78, 230, 132), new Color(14, 112, 34));
            isCorrectButton.setText("Correct");
        } else {
            isCorrectButton.enableGradient(new Color(230, 78, 78), new Color(193, 13, 34));
            isCorrectButton.setText("Wrong");
        }

        return (actualOutput == expectedOutput) ? "1~" : "0~";
    }

    private int evaluateGate(int gate, int A, int B, int C) {
        // 0 -> Off
        // 1 -> On
        switch (gate) {
            case 1: // AND
                return A & B & C;

            case 2: // OR
                return A | B | C;

            case 3: // NAND
                return (A & B & C) == 1 ? 0 : 1;

            case 4: // NOR
                return (A | B | C) == 1 ? 0 : 1;

            case 5: // XOR (odd parity)
                return A ^ B ^ C;

            case 6: // XNOR
                return (A ^ B ^ C) == 1 ? 0 : 1;

            case 7: // NOT (invert B only)
                return B == 1 ? 0 : 1;

            default:
                throw new IllegalArgumentException("Invalid gate number");
        }
    }

    public static void testCode() {
        code = "0~1~0~0~X~1~1";
        sendCode(code);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {

        }

        // Test digits 0-9
        for (int i = 0; i < 10; i++) {
            code = "0~0~0~0~" + i + "~1~0"; // R=0, A=0,B=0,C=0, #=i, Y=1(correct), O=0(output LED)
            sendCode(code);

            try {
                Thread.sleep(3000); // wait 3 seconds to see the number
            } catch (InterruptedException ignored) {
            }
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

    public static void setSimulationNumber(int simulationNumber) {
        Simulation.simulationNumber = simulationNumber;
    }

    public static int getSimulationNumber() {
        return simulationNumber;
    }
}
