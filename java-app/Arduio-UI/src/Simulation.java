import java.awt.*;
import java.util.HashMap;

public class Simulation {
    // Global Variables
    static int simulationNumber = 2;

    int totalSimulations = 2;

    public static SerialConnection serialConnection;
    private static String code;

    public static final int width = 1600;
    public static final int height = 800;
    public static boolean running = true;

    Button toggleSimulationButton; // Future update: use .comment() to get the number of simulation and then add text sating "Simulation: X"

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

    int selectGate_Sim2;

    boolean isButton1Selected_Sim2;

    Button showNextStepButton_Sim2;

    Button resetButton_Sim2;

    Button gateNumberButton_Sim2;

    ButtonGroup wireFromGate1to2_Sim2;

    boolean buttonLock;

    int stepCounter_Sim2;

    int outputFromFirstGate_Sim2;

    boolean displayTruthTableGate1_Sim2;
    boolean displayTruthTableGate2_Sim2;

    Button arrowPointerButton;

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
        selectGate_Sim2 = -1;
        outputFromFirstGate_Sim2 = -1;
        buttonLock = false;

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

        showNextStepButton_Sim2 = new Button(1500, 50, 100, 50, "Next Step", Color.BLUE);
        showNextStepButton_Sim2.enableGradient(new Color(215, 239, 71), new Color(41, 154, 12));

        int inputButtonWidth = 230;
        int inputButtonHeight = 20;
        input1Button_Sim2 = new Button(150, 500, inputButtonWidth, inputButtonHeight, "1", Color.red);
        input2Button_Sim2 = new Button(150, 400, inputButtonWidth, inputButtonHeight, "2", Color.red);
        input3Button_Sim2 = new Button(320, 300, 565, inputButtonHeight, "3", Color.red);
        outputButton_Sim2 = new Button(980, 340, inputButtonWidth, inputButtonHeight + 5, "", Color.red);

        input1Button_Sim2.setComment("0");
        input2Button_Sim2.setComment("0");
        input3Button_Sim2.setComment("0");
        outputButton_Sim2.setComment("0");

        isButton1Selected_Sim2 = true;
        firstLogicGate_Sim2 = new Button(400, 450, 250, 150, "", Color.BLACK);
        firstLogicGate_Sim2.enableGradient(new Color(24, 12, 55), new Color(78, 24, 24));
        secondLogicGate_Sim2 = new Button(730, 340, 250, 150, "", Color.BLACK);

        int wireX = 550;
        wireFromGate1to2_Sim2 = new ButtonGroup();
        wireFromGate1to2_Sim2.addButton(new Button(wireX, 450, 40, inputButtonHeight, "", Color.RED));
        wireFromGate1to2_Sim2.addButton(new Button(wireX + 30, 410, inputButtonHeight, 100, "", Color.RED));
        wireFromGate1to2_Sim2.addButton(new Button(wireX + 35, 350, 30, inputButtonHeight, "", Color.RED));

        stepCounter_Sim2 = 0;

        displayTruthTableGate1_Sim2 = false;
        displayTruthTableGate2_Sim2 = false;

        arrowPointerButton = new Button(1300, 600, 50, 50, "", Color.BLACK);
        arrowPointerButton.addPicture("arrow.png");
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
                sim.runCombine2LogicGates_Sim2();
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
                sendCodeToArduino_Sim1(false);

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
                sendCodeToArduino_Sim1(true);
                initialize_Sim1(); // Just reset everything saved
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

    private void runCombine2LogicGates_Sim2() {
        // Set up Canvas
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        while (running) {
            // Background
            StdDraw.clear(StdDraw.BLACK);

            // Global button
            if(toggleSimulationButton.isClicked()){
                simulationNumber++;
                if(simulationNumber > totalSimulations)
                    simulationNumber = 1;

                SerialConnection.closePort();
                main(new String[] {});
            }

            // Update and draw buttons
            for (Button button : select2InputLogicGatesButtonList) {
                button.update();
                button.draw();
            }

            toggleSimulationButton.update();
            toggleSimulationButton.draw();

            gateNumberButton_Sim2.update();
            gateNumberButton_Sim2.draw();

            resetButton_Sim2.update();
            resetButton_Sim2.draw();

            showNextStepButton_Sim2.update();
            showNextStepButton_Sim2.draw();

            firstLogicGate_Sim2.update();
            firstLogicGate_Sim2.draw();
            secondLogicGate_Sim2.update();
            secondLogicGate_Sim2.draw();

            input1Button_Sim2.update();
            input1Button_Sim2.draw();
            input2Button_Sim2.update();
            input2Button_Sim2.draw();
            input2Button_Sim2.draw();
            input3Button_Sim2.update();
            input3Button_Sim2.draw();
            outputButton_Sim2.update();
            outputButton_Sim2.draw();

            wireFromGate1to2_Sim2.updateAll();
            wireFromGate1to2_Sim2.drawAll();

            arrowPointerButton.update();

            // Click Checking
            if(!buttonLock) {
                for (int i = 0; i < select2InputLogicGatesButtonList.length; i++) {
                    if (select2InputLogicGatesButtonList[i].isClicked()) {
                        selectGate_Sim2 = i + 1;
                        gateNumberButton_Sim2.setText("Gate: " + selectGate_Sim2);
                        String[] pics = {
                                "and_gate.png",
                                "or_gate.png",
                                "nand_gate.png",
                                "nor_gate.png",
                                "xor_gate.png",
                                "xnor_gate.png",
                                "not_gate.png"
                        };
                        if (isButton1Selected_Sim2) {
                            if (selectGate_Sim2 == 7 && firstLogicGate_Sim2.hasPicture) {
                                HashMap<Integer, Integer> map = new HashMap<>();
                                map.put(0, 2);
                                map.put(1, 3);
                                map.put(2, 0);
                                map.put(3, 1);
                                map.put(4, 5);
                                map.put(5, 4);
                                int j = map.get(Integer.parseInt(firstLogicGate_Sim2.getComment()));
                                firstLogicGate_Sim2.addPicture(pics[j]);
                                firstLogicGate_Sim2.setComment(j + "");
                                gateNumberButton_Sim2.setText("Gate: " + (j + 1));

                            } else if (selectGate_Sim2 != 7) {
                                firstLogicGate_Sim2.addPicture(pics[i]);
                                firstLogicGate_Sim2.setComment(i + "");
                            }
                        } else {
                            if (selectGate_Sim2 == 7 && secondLogicGate_Sim2.hasPicture) {
                                HashMap<Integer, Integer> map = new HashMap<>();
                                map.put(0, 2);
                                map.put(1, 3);
                                map.put(2, 0);
                                map.put(3, 1);
                                map.put(4, 5);
                                map.put(5, 4);
                                int j = map.get(Integer.parseInt(secondLogicGate_Sim2.getComment()));
                                secondLogicGate_Sim2.addPicture(pics[j]);
                                secondLogicGate_Sim2.setComment(j + "");
                                gateNumberButton_Sim2.setText("Gate: " + (j + 1));

                            } else if (selectGate_Sim2 != 7) {
                                secondLogicGate_Sim2.addPicture(pics[i]);
                                secondLogicGate_Sim2.setComment(i + "");
                            }
                        }
                    }
                    if (input1Button_Sim2.isClicked()) {
                        input1Button_Sim2.setComment(input1Button_Sim2.getComment().equals("0") ? "1" : "0");
                        input1Button_Sim2.setBackground(input1Button_Sim2.getBackground() == Color.red ? Color.green : Color.red);
                    } else if (input2Button_Sim2.isClicked()) {
                        input2Button_Sim2.setComment(input2Button_Sim2.getComment().equals("0") ? "1" : "0");
                        input2Button_Sim2.setBackground(input2Button_Sim2.getBackground() == Color.red ? Color.green : Color.red);
                    } else if (input3Button_Sim2.isClicked()) {
                        input3Button_Sim2.setComment(input3Button_Sim2.getComment().equals("0") ? "1" : "0");
                        input3Button_Sim2.setBackground(input3Button_Sim2.getBackground() == Color.red ? Color.green : Color.red);
                    }
                }

                if (firstLogicGate_Sim2.isClicked())
                    isButton1Selected_Sim2 = true;

                if (secondLogicGate_Sim2.isClicked())
                    isButton1Selected_Sim2 = false;

            }

            if (isButton1Selected_Sim2) {
                firstLogicGate_Sim2.enableGradient(new Color(24, 12, 55), new Color(78, 24, 24));
                secondLogicGate_Sim2.enableGradient(Color.BLACK, Color.BLACK);
            } else {
                secondLogicGate_Sim2.enableGradient(new Color(24, 12, 55), new Color(78, 24, 24));
                firstLogicGate_Sim2.enableGradient(Color.BLACK, Color.BLACK);
            }

            // special buttons can be clicked anytime
            if (showNextStepButton_Sim2.isClicked() && firstLogicGate_Sim2.hasPicture && secondLogicGate_Sim2.hasPicture) {
                buttonLock = true;
                stepCounter_Sim2++;

                // truth table variables
                if(stepCounter_Sim2 == 1)
                    displayTruthTableGate1_Sim2 = true;
                else if(stepCounter_Sim2 == 2) {
                    displayTruthTableGate1_Sim2 = false;
                    displayTruthTableGate2_Sim2 = true;
                } else
                    displayTruthTableGate2_Sim2 = false;

                if(stepCounter_Sim2 <= 2)
                    sendCodeToArduino_Sim2(false);
                else {
                    sendCodeToArduino_Sim2(true);
                    initialize_Sim2();
                }
            }
            if (resetButton_Sim2.isClicked()) {
                sendCodeToArduino_Sim1(true);
                initialize_Sim2(); // Just reset everything saved
            }

            // Display truth tables
            if(displayTruthTableGate1_Sim2) {
                drawTruthTable(Integer.parseInt(firstLogicGate_Sim2.getComment()), 2);
                arrowPointerButton.draw();
            }

            if(displayTruthTableGate2_Sim2) {
                drawTruthTable(Integer.parseInt(secondLogicGate_Sim2.getComment()), 2);
                arrowPointerButton.draw();
            }


            // Gate text
            StdDraw.setPenColor(new Color(32, 84, 151));
            StdDraw.setFont(new Font("Arial", Font.BOLD, 40));
            if(firstLogicGate_Sim2.hasPicture && stepCounter_Sim2 > 0)
                StdDraw.text(firstLogicGate_Sim2.getX(), firstLogicGate_Sim2.getY(),"1");
            if(secondLogicGate_Sim2.hasPicture && stepCounter_Sim2 > 1)
                StdDraw.text(secondLogicGate_Sim2.getX(), secondLogicGate_Sim2.getY(),"2");

            // Logic gate numbering
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

    public void sendCodeToArduino_Sim1(boolean reset) {
        if (reset) {
            SerialConnection.sendCodeToArduino("1~0~0~0~X~0~0");
            return;
        }

        // Code -> R~A~B~C~#~Y~O

        // R - Reset
        StringBuilder simOutput = new StringBuilder("0~");

        // A, B, C - Inputs
        simOutput.append(input1Button_Sim1.getComment()).append("~");
        simOutput.append(input2Button_Sim1.getComment()).append("~");
        simOutput.append(input3Button_Sim1.getComment()).append("~");

        // # - Selected Gate
        simOutput.append(selectedGate).append("~");

        // Y - correctness
        simOutput.append(checkInput_Sim1(simOutput.toString(), outputButton_Sim1.getComment()));

        // O - Output led (on/off)
        simOutput.append(outputButton_Sim1.getComment());

        SerialConnection.sendCodeToArduino(simOutput.toString());
    }

    public void sendCodeToArduino_Sim2(boolean reset) {
        if (reset) {
            SerialConnection.sendCodeToArduino("1~0~0~0~X~0~0");
            return;
        }

        // Code -> R~A~B~C~#~Y~O

        // R - Reset
        StringBuilder simOutput = new StringBuilder("0~");

        // A, B, C - Inputs
        simOutput.append(stepCounter_Sim2 == 1 ? input1Button_Sim2.getComment() : outputFromFirstGate_Sim2).append("~");
        simOutput.append(stepCounter_Sim2 == 1 ? input2Button_Sim2.getComment() : "0").append("~");
        simOutput.append(input3Button_Sim2.getComment()).append("~");

        // # - the step number
        simOutput.append(stepCounter_Sim2).append("~");

        // Y - correctness
        simOutput.append(stepCounter_Sim2 == 1 ? "0~" : "1~");

        // O - Output led (on/off)
        int gate;
        int a;
        int b;
        if (stepCounter_Sim2 == 1) {
            isButton1Selected_Sim2 = true;
            gate = Integer.parseInt(firstLogicGate_Sim2.getComment());
            a = Integer.parseInt(input1Button_Sim2.getComment());
            b = Integer.parseInt(input2Button_Sim2.getComment());
        } else {
            isButton1Selected_Sim2 = false;
            gate = Integer.parseInt(secondLogicGate_Sim2.getComment());
            a = outputFromFirstGate_Sim2;
            b = Integer.parseInt(input3Button_Sim2.getComment());
        }

        if(stepCounter_Sim2 == 1) {
            outputFromFirstGate_Sim2 = evaluateGate2Input(gate, a, b);
            wireFromGate1to2_Sim2.changeAllColor(outputFromFirstGate_Sim2 == 1 ? Color.GREEN : Color.RED);
        } else
            outputButton_Sim2.setBackground(evaluateGate2Input(gate, a, b) == 1 ? Color.GREEN : Color.RED);


        simOutput.append(evaluateGate2Input(gate, a, b));

        SerialConnection.sendCodeToArduino(simOutput.toString());
    }

    private void drawTruthTable(int gate, int numInput){
        int rows = (int) Math.pow(2, numInput);
        int cols = numInput + 1;

        int cellWidth = 80;
        int cellHeight = 40;

        int startX = 1280;
        int startY = 420;

        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 14));

        // Draw header (inputs and output) on top
        for (int c = 0; c < cols; c++) {
            int x = startX + c * cellWidth;
            int y = startY + cellHeight; // header above the first data row
            StdDraw.rectangle(x, y, (double) cellWidth / 2, (double) cellHeight / 2);
            if (c < numInput) {
                String label;
                if (numInput == 2)
                    label = (c == 0) ? "A" : "B";
                else
                    label = "A" + (c + 1);

                StdDraw.text(x, y, label);
            } else
                StdDraw.text(x, y, "Out");
        }

        // Determine which row to point at (if any)
        boolean shouldPoint = false;
        int selectedRow = 0;
        if (numInput == 2) {
            try {
                if (displayTruthTableGate1_Sim2) {
                    int a = Integer.parseInt(input1Button_Sim2.getComment());
                    int b = Integer.parseInt(input2Button_Sim2.getComment());
                    selectedRow = (a << 1) | b; // A is MSB, B is LSB
                    shouldPoint = true;
                } else if (displayTruthTableGate2_Sim2) {
                    int a = outputFromFirstGate_Sim2; // use first gate output as A
                    int b = Integer.parseInt(input3Button_Sim2.getComment()); // input3 as B
                    selectedRow = (a << 1) | b;
                    shouldPoint = true;
                }
            } catch (NumberFormatException ignored) {
                shouldPoint = false;
            }
        }

        // Draw data rows
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = startX + c * cellWidth;
                int y = startY - r * cellHeight;
                StdDraw.rectangle(x, y, (double) cellWidth / 2, (double) cellHeight / 2);

                if (c < numInput) {
                    // input value for this row and column (binary counting order)
                    int inputValue = (r >> (numInput - c - 1)) & 1;
                    StdDraw.text(x, y, String.valueOf(inputValue));
                } else {
                    // calculate output for this row
                    int a = (numInput >= 1) ? ((r >> (numInput - 1)) & 1) : 0;
                    int b = (numInput >= 2) ? ((r >> (numInput - 2)) & 1) : 0;
                    int outputValue = evaluateGate2Input(gate, a, b);
                    StdDraw.text(x, y, String.valueOf(outputValue));
                }
            }
        }

        if (shouldPoint) {
            // place the arrow outside the table on the left
            int arrowX = startX - cellWidth;
            int arrowY = startY - selectedRow * cellHeight;
            arrowPointerButton.setX(arrowX);
            arrowPointerButton.setY(arrowY);
        }
    }

    private String checkInput_Sim1(String input, String output) {
        String[] parts = input.split("~");

        int A = Integer.parseInt(parts[1]);
        int B = Integer.parseInt(parts[2]);
        int C = Integer.parseInt(parts[3]);
        int gateNumber = Integer.parseInt(parts[4]);

        int expectedOutput = Integer.parseInt(output);
        int actualOutput = evaluateGate3Input(gateNumber, A, B, C);

        if (actualOutput == expectedOutput) {
            isCorrectButton.enableGradient(new Color(78, 230, 132), new Color(14, 112, 34));
            isCorrectButton.setText("Correct");
        } else {
            isCorrectButton.enableGradient(new Color(230, 78, 78), new Color(193, 13, 34));
            isCorrectButton.setText("Wrong");
        }

        return (actualOutput == expectedOutput) ? "1~" : "0~";
    }

    private int evaluateGate3Input(int gate, int A, int B, int C) {
        // 0 -> Off
        // 1 -> On
        return switch (gate) {
            case 1 -> // AND
                    A & B & C;
            case 2 -> // OR
                    A | B | C;
            case 3 -> // NAND
                    (A & B & C) == 1 ? 0 : 1;
            case 4 -> // NOR
                    (A | B | C) == 1 ? 0 : 1;
            case 5 -> // XOR (odd parity)
                    A ^ B ^ C;
            case 6 -> // XNOR
                    (A ^ B ^ C) == 1 ? 0 : 1;
            case 7 -> // NOT (invert B only)
                    B == 1 ? 0 : 1;
            default -> throw new IllegalArgumentException("Invalid gate number");
        };
    }

    private int evaluateGate2Input(int gate, int A, int B) {
        gate++;
        // 0 -> Off
        // 1 -> On
        return switch (gate) {
            case 1 -> // AND
                    A & B;
            case 2 -> // OR
                    A | B;
            case 3 -> // NAND
                    (A & B) == 1 ? 0 : 1;
            case 4 -> // NOR
                    (A | B) == 1 ? 0 : 1;
            case 5 -> // XOR
                    A ^ B;
            case 6 -> // XNOR
                    (A ^ B) == 1 ? 0 : 1;
            case 7 -> // NOT (invert B only)
                    B == 1 ? 0 : 1;
            default -> throw new IllegalArgumentException("Invalid gate number");
        };
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
}