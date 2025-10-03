import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
enum Transformation {
    ORIGINAL("Original (Blue)"), 
    TRANSLATE("Translation (Green)"), 
    SCALE("Scaling (Orange)"), 
    ROTATE("Rotation (Red)"), 
    SHEAR("Shear (Purple)");

    private final String menuName;

    Transformation(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }
}

public class TransformMenu extends JPanel implements ActionListener {
    
    // --- Transformation Parameters (from original file) ---
    double angle = Math.toRadians(45); // rotation angle in radians (45 degrees)
    double scaleX = 1.5;             // scale 1.5x in X
    double scaleY = 1.2;             // scale 1.2x in Y
    int tx = 100;                    // translate +100 in X
    int ty = 50;                     // translate +50 in Y
    double shx = 0.5;                // shear factor in X
    double shy = 0.3;                // shear factor in Y
    
    // --- Application State ---
    private Transformation currentTransform = Transformation.ORIGINAL; // Default state

    // --- Triangle Definition (from original file) ---
    private final int[] xPoints = { 100, 150, 200 };
    private final int[] yPoints = { 200, 100, 200 };

    public TransformMenu() {
        // JPanel constructor is sufficient here
    }

    /**
     * Creates and returns the JMenuBar for the application.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu transformMenu = new JMenu("Transformation");
        
        // Create a menu item for each transformation
        for (Transformation t : Transformation.values()) {
            JMenuItem item = new JMenuItem(t.getMenuName());
            item.setActionCommand(t.name()); // Set the enum name as the command
            item.addActionListener(this);
            transformMenu.add(item);
        }
        
        menuBar.add(transformMenu);
        return menuBar;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawPolygon(xPoints, yPoints, 3);
        
        // Use of switch statement to draw only the selected transformation
        switch (currentTransform) {
            case ORIGINAL:
                drawOriginal(g2);
                break;
            case TRANSLATE:
                drawTranslation(g2);
                break;
            case SCALE:
                drawScaling(g2);
                break;
            case ROTATE:
                drawRotation(g2);
                break;
            case SHEAR:
                drawShear(g2);
                break;
        }
    }

    // --- Drawing Methods for each Transformation ---

    private void drawOriginal(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.drawPolygon(xPoints, yPoints, 3);
    }

    private void drawTranslation(Graphics2D g2) {
        int[] xTranslated = new int[3];
        int[] yTranslated = new int[3];
        for (int i = 0; i < 3; i++) {
            xTranslated[i] = xPoints[i] + tx;
            yTranslated[i] = yPoints[i] + ty;
        }
        g2.setColor(Color.GREEN);
        g2.drawPolygon(xTranslated, yTranslated, 3);
    }

    private void drawScaling(Graphics2D g2) {
        int[] xScaled = new int[3];
        int[] yScaled = new int[3];
        for (int i = 0; i < 3; i++) {
            xScaled[i] = (int) (xPoints[i] * scaleX);
            yScaled[i] = (int) (yPoints[i] * scaleY);
        }
        g2.setColor(Color.ORANGE);
        g2.drawPolygon(xScaled, yScaled, 3);
    }

    private void drawRotation(Graphics2D g2) {
        int[] xRotated = new int[3];
        int[] yRotated = new int[3];
        
        // Step 1: Find center of triangle
        double cx = (xPoints[0] + xPoints[1] + xPoints[2]) / 3.0;
        double cy = (yPoints[0] + yPoints[1] + yPoints[2]) / 3.0;
        
        for (int i = 0; i < 3; i++) {
            // Step 2: Shift to origin
            double xShifted = xPoints[i] - cx;
            double yShifted = yPoints[i] - cy;
            
            // Step 3: Rotate
            double xRot = xShifted * Math.cos(angle) - yShifted * Math.sin(angle);
            double yRot = xShifted * Math.sin(angle) + yShifted * Math.cos(angle);
            
            // Step 4: Shift back
            xRotated[i] = (int) (xRot + cx);
            yRotated[i] = (int) (yRot + cy);
        }
        g2.setColor(Color.RED);
        g2.drawPolygon(xRotated, yRotated, 3);
    }

    private void drawShear(Graphics2D g2) {
        int[] xShear = new int[3];
        int[] yShear = new int[3];
        for (int i = 0; i < 3; i++) {
            // Shear in X and Y
            xShear[i] = (int) (xPoints[i] + shx * yPoints[i]);
            yShear[i] = (int) (yPoints[i] + shy * xPoints[i]);
        }
        g2.setColor(Color.MAGENTA);
        g2.drawPolygon(xShear, yShear, 3);
    }
    
    public void actionPerformed(ActionEvent e) {
        // Get the command string (which is the name of the enum constant)
        String command = e.getActionCommand();
        try {
            // Convert the command string back to the enum
            currentTransform = Transformation.valueOf(command);
            // Request a repaint of the panel to draw the new transformation
            repaint();
        } catch (IllegalArgumentException ex) {
            System.err.println("Unknown action command: " + command);
        }
    }

    // --- Main Method ---

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("2D Transformations - Menu Driven");
            TransformMenu panel = new TransformMenu();
            
            // Set the menu bar for the frame
            frame.setJMenuBar(panel.createMenuBar());
            
            frame.add(panel);
            frame.setSize(700, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}