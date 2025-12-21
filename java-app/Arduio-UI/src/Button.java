import java.awt.*;
import java.util.*;

/*
Code by Tijil Saka
 */

public class Button {
    String text;
    int x;
    int y;
    int height;
    int width;
    String comment;

    boolean previousMousePressed = false;
    boolean enabled = true;
    boolean clicked = false;
    boolean particlesEnabled;

    boolean border = false;

    int textX;
    int textY;

    Color background;

    boolean canSpin = false;
    boolean spinClockwise = false;
    boolean spinCounterClockwise = false;
    double spinSpeed = 0.1;
    double angle = 0;

    boolean isGradient = false;
    Color colorA;
    Color colorB;

    boolean rounded;
    boolean hasPicture;
    private String imagePath;

    boolean canChangeSize = false;
    double sizeMax = 1.05;
    double sizeMin = 1.0;
    double size = sizeMin;
    double sizeSpeed = 0.02;
    boolean increasing = true;

    ArrayList<Particle> particles = new ArrayList<>();
    Random random = new Random();

    private Color textColor = Color.black;
    private Font textFont = new Font("Arial", Font.PLAIN, 18);

    long lastClickTime = System.currentTimeMillis();
    long clickCooldown = 500; // milliseconds

    public Button(int x, int y, int width, int height, String text) {
        this.text = text;
        this.x = x;
        this.y = y;
        textX = x;
        textY = y;
        this.width = width;
        this.height = height;
        background = Color.white;
    }

    public Button(int x, int y, int width, int height, String text, Color background) {
        this(x, y, width, height, text);
        this.background = background;
    }

    public Button(int x, int y, int width, int height) {
        this.text = "";
        this.x = x;
        this.y = y;
        textX = x;
        textY = y;
        this.width = width;
        this.height = height;
        background = Color.white;
    }

    public void draw() {
        double rotatedX = x;
        double rotatedY = y;
        if (canSpin) {
            rotatedX = x + Math.cos(angle) * width / 2;
            rotatedY = y + Math.sin(angle) * height / 2;
        }

        double w = width * size;
        double h = height * size;

        // draw background (gradient or solid)
        if (isGradient) {
            for (int i = 0; i <= (int) h; i++) {
                double t = (double) i / h;
                int red = (int) (colorA.getRed() * (1 - t) + colorB.getRed() * t);
                int green = (int) (colorA.getGreen() * (1 - t) + colorB.getGreen() * t);
                int blue = (int) (colorA.getBlue() * (1 - t) + colorB.getBlue() * t);
                StdDraw.setPenColor(new Color(red, green, blue));
                StdDraw.line(rotatedX - w / 2, rotatedY - h / 2 + i, rotatedX + w / 2, rotatedY - h / 2 + i);
            }
        } else {
            StdDraw.setPenColor(background);
            if (rounded)
                drawRoundedRectangle(rotatedX, rotatedY, w, h, 50);
            else
                StdDraw.filledRectangle(rotatedX, rotatedY, w / 2, h / 2);

        }

        // picture
        if (hasPicture && imagePath != null) {
            double drawW = w;
            double drawH = h;
            try {
                StdDraw.picture(rotatedX, rotatedY, imagePath, drawW, drawH);
            } catch (Exception ignored) {
            }
        }

        if (border) {
            StdDraw.setPenColor(Color.black);
            StdDraw.setPenRadius(0.003);
            if (rounded) {
                drawRoundedRectangle(rotatedX, rotatedY, w, h, 50);
            } else {
                StdDraw.rectangle(rotatedX, rotatedY, w / 2, h / 2);
            }
            StdDraw.setPenRadius(0.007);
        }

        StdDraw.setPenColor(this.textColor);
        StdDraw.setFont(this.textFont);
        StdDraw.text(rotatedX, rotatedY, text);
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public void addPicture(String path) {
        this.imagePath = path;
        this.hasPicture = true;
    }

    public boolean hasPicture() {
        return hasPicture;
    }

    public void drawRoundedRectangle(double x, double y, double width, double height, int numSides) {
        double[] xCords = new double[numSides];
        double[] yCords = new double[numSides];

        for (int i = 0; i < numSides; i++) {
            double angle = 2 * Math.PI * i / numSides;
            xCords[i] = x + width / 2 * Math.cos(angle);
            yCords[i] = y + height / 2 * Math.sin(angle);
        }

        StdDraw.polygon(xCords, yCords);
    }

    public void enableGradient(Color colorA, Color colorB) {
        this.isGradient = true;
        this.colorA = colorA;
        this.colorB = colorB;
    }

    public void setCanChangeSize(boolean canChangeSize) {
        this.canChangeSize = canChangeSize;
    }

    public void update() {
        if (enabled)
            trackMouseClicks(x - (width >> 1), x + (width >> 1), y - (height >> 1), y + (height >> 1));
        updateParticles();
        if (canSpin) {
            if (spinClockwise) {
                angle += spinSpeed;
            } else if (spinCounterClockwise) {
                angle -= spinSpeed;
            }
        }
        if (canChangeSize) {
            if (increasing) {
                size += sizeSpeed;
                if (size >= sizeMax) {
                    increasing = false;
                }
            } else {
                size -= sizeSpeed;
                if (size <= sizeMin) {
                    increasing = true;
                }
            }
        }
    }

    public boolean isClicked() {
        return clicked;
    }

    public String getText() {
        return text;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSpinCounterClockwise(boolean spinCounterClockwise) {
        this.spinCounterClockwise = spinCounterClockwise;
        spinClockwise = !spinCounterClockwise;
    }

    public void setSpinClockwise(boolean spinClockwise) {
        this.spinClockwise = spinClockwise;
        spinCounterClockwise = !spinClockwise;
    }

    public void setCanSpin(boolean canSpin) {
        this.canSpin = canSpin;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getBackground() {
        return background;
    }

    public void trackMouseClicks(double x1, double x2, double y1, double y2) {
        boolean isMousePressed = StdDraw.isMousePressed();
        if (!isMousePressed) {
            previousMousePressed = clicked;
            clicked = false;
            return;
        }

        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();

        double minX = x1;
        double maxX = x2;
        double minY = y1;
        double maxY = y2;

        if (mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY) {
            long currentTime = System.currentTimeMillis();
            if (!previousMousePressed && currentTime - lastClickTime >= clickCooldown) {
                previousMousePressed = clicked;
                clicked = true;
                lastClickTime = currentTime;
                if (particlesEnabled)
                    createParticles();
            } else {
                previousMousePressed = clicked;
                clicked = false;
            }
        } else {
            previousMousePressed = clicked;
            clicked = false;
        }
    }

    public void setParticlesEnabled(boolean particlesEnabled) {
        this.particlesEnabled = particlesEnabled;
    }

    public void createParticles() {
        for (int i = 0; i < 15; i++) { // change 100 to 15
            double angle = 2 * Math.PI * random.nextDouble();
            double speed = 15 * random.nextDouble();
            double dx = Math.cos(angle) * speed;
            double dy = Math.sin(angle) * speed;
            Color particleColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            particles.add(new Particle(x, y, dx, dy, particleColor));
        }
    }

    public void updateParticles() {
        for (Particle p : particles) {
            p.update();
            p.draw();
        }
        particles.removeIf(p -> p.isOld() || !p.isVisible());
        StdDraw.setPenColor(Color.black); // reset pen color to black
    }

    public void setTextX(int textX) {
        this.textX = textX;
    }

    public void setTextY(int textY) {
        this.textY = textY;
    }

    public void setEnabled(boolean c) {
        enabled = c;
    }

    public void setText(String c) {
        text = c;
    }

    public void setTextInfo(Color color, int fontSize) {
        this.textColor = color;
        this.textFont = new Font("Arial", Font.BOLD, fontSize);
    }

    public void setButtonPos(int x, int y, String xy) {
        if ("x".equals(xy)) {
            this.y = y;
        } else if ("y".equals(xy)) {
            this.x = x;
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

}

class Particle {
    enum ShapeType { CIRCLE, SQUARE, TRIANGLE, DIAMOND, STAR }
    double x, y;
    double dx, dy;
    long timestamp;
    Color color;
    ShapeType shapeType;

    public Particle(double x, double y, double dx, double dy, Color color) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        this.timestamp = System.currentTimeMillis();
        this.shapeType = ShapeType.values()[new Random().nextInt(ShapeType.values().length)];
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public boolean isOld() {
        return System.currentTimeMillis() - timestamp > 1500;
    }

    public boolean isVisible() {
        return x >= 0 && x <= Simulation.width && y >= 0 && y <= Simulation.height;
    }

    public void update() {
        x += dx;
        y += dy;
        dy -= 0.1; // gravity
    }

    public void draw() {
        StdDraw.setPenColor(color);
        switch (shapeType) {
            case CIRCLE:
                StdDraw.filledCircle(x, y, 3);
                break;
            case SQUARE:
                StdDraw.filledSquare(x, y, 3);
                break;
            case TRIANGLE:
                double[] xPoints = {x, x - 3, x + 3};
                double[] yPoints = {y + 3, y - 3, y - 3};
                StdDraw.filledPolygon(xPoints, yPoints);
                break;
            case DIAMOND:
                double[] dxPoints = {x, x - 3, x, x + 3};
                double[] dyPoints = {y - 3, y, y + 3, y};
                StdDraw.filledPolygon(dxPoints, dyPoints);
                break;
            case STAR:
                double[] sxPoints = {x, x - 3, x + 3, x - 2, x + 2};
                double[] syPoints = {y + 3, y - 1, y - 1, y - 3, y - 3};
                StdDraw.filledPolygon(sxPoints, syPoints);
                break;
        }
    }
}

class ButtonGroup {
    ArrayList<Button> buttons;

    public ButtonGroup() {
        buttons = new ArrayList<>();
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void drawAll() {
        for (Button button : buttons) {
            button.draw();
        }
    }

    public void updateAll() {
        for (Button button : buttons) {
            button.update();
        }
    }

    public void changeAllColor(Color color) {
        for (Button button : buttons)
            button.setBackground(color);
    }
}