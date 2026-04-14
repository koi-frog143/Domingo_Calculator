import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GUICalculator extends JFrame {
    private static final Color BACKGROUND_TOP = new Color(42, 47, 67);
    private static final Color BACKGROUND_BOTTOM = new Color(26, 31, 49);
    private static final Color DISPLAY_BG = new Color(241, 245, 249);
    private static final Color DISPLAY_TEXT = new Color(20, 26, 40);
    private static final Color DIGIT_BG = new Color(58, 65, 89);
    private static final Color OPERATOR_BG = new Color(243, 166, 43);
    private static final Color EQUALS_BG = new Color(67, 178, 122);
    private static final Color CLEAR_BG = new Color(224, 83, 91);

    private final JTextField txtDisplay = new JTextField("0");
    private final CalculationEngine calculationEngine = new CalculationEngine();

    private double firstNumber = 0;
    private String currentOperator = "";
    private boolean startNewInput = true;

    public GUICalculator() {
        setTitle("Domingo Calculator (GUI)");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GradientPanel contentPanel = new GradientPanel();
        contentPanel.setLayout(new BorderLayout(14, 14));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setContentPane(contentPanel);

        setupDisplay();
        setupButtons();

        setPreferredSize(new Dimension(380, 520));
        pack();
        setLocationRelativeTo(null);
    }

    private void setupDisplay() {
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisplay.setEditable(false);
        txtDisplay.setFont(new Font("Segoe UI", Font.BOLD, 40));
        txtDisplay.setBackground(DISPLAY_BG);
        txtDisplay.setForeground(DISPLAY_TEXT);
        txtDisplay.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 209, 223), 2),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        add(txtDisplay, BorderLayout.NORTH);
    }

    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        addButton(buttonPanel, "Clear", 0, 0, 1, 1);
        addButton(buttonPanel, "/", 1, 0, 1, 1);
        addButton(buttonPanel, "*", 2, 0, 1, 1);
        addButton(buttonPanel, "-", 3, 0, 1, 1);

        addButton(buttonPanel, "7", 0, 1, 1, 1);
        addButton(buttonPanel, "8", 1, 1, 1, 1);
        addButton(buttonPanel, "9", 2, 1, 1, 1);
        addButton(buttonPanel, "+", 3, 1, 1, 2);

        addButton(buttonPanel, "4", 0, 2, 1, 1);
        addButton(buttonPanel, "5", 1, 2, 1, 1);
        addButton(buttonPanel, "6", 2, 2, 1, 1);

        addButton(buttonPanel, "1", 0, 3, 1, 1);
        addButton(buttonPanel, "2", 1, 3, 1, 1);
        addButton(buttonPanel, "3", 2, 3, 1, 1);
        addButton(buttonPanel, "=", 3, 3, 1, 2);

        addButton(buttonPanel, "0", 0, 4, 2, 1);
        addButton(buttonPanel, ".", 2, 4, 1, 1);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, String text, int gridX, int gridY, int gridWidth, int gridHeight) {
        JButton button = createStyledButton(text);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.gridheight = gridHeight;
        constraints.weightx = gridWidth;
        constraints.weighty = gridHeight;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(6, 6, 6, 6);

        panel.add(button, constraints);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        button.setOpaque(true);
        button.setForeground(Color.WHITE);
        button.setBackground(getButtonBackground(text));
        button.addActionListener(this::handleButtonClick);
        return button;
    }

    private Color getButtonBackground(String buttonText) {
        switch (buttonText) {
            case "Clear":
                return CLEAR_BG;
            case "/":
            case "*":
            case "-":
            case "+":
                return OPERATOR_BG;
            case "=":
                return EQUALS_BG;
            default:
                return DIGIT_BG;
        }
    }

    private void handleButtonClick(ActionEvent event) {
        String input = ((JButton) event.getSource()).getText();

        if ("0123456789".contains(input)) {
            appendDigit(input);
            return;
        }

        switch (input) {
            case ".":
                appendDecimalPoint();
                break;
            case "Clear":
                clearAll();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(input);
                break;
            case "=":
                computeResult();
                break;
            default:
                break;
        }
    }

    private void appendDigit(String digit) {
        if (startNewInput || "0".equals(txtDisplay.getText())) {
            txtDisplay.setText(digit);
            startNewInput = false;
        } else {
            txtDisplay.setText(txtDisplay.getText() + digit);
        }
    }

    private void appendDecimalPoint() {
        if (startNewInput) {
            txtDisplay.setText("0.");
            startNewInput = false;
            return;
        }

        if (!txtDisplay.getText().contains(".")) {
            txtDisplay.setText(txtDisplay.getText() + ".");
        }
    }

    private void clearAll() {
        firstNumber = 0;
        currentOperator = "";
        startNewInput = true;
        txtDisplay.setText("0");
    }

    private void setOperator(String operator) {
        if (!currentOperator.isEmpty() && !startNewInput) {
            computeResult();
        }

        firstNumber = Double.parseDouble(txtDisplay.getText());
        currentOperator = operator;
        startNewInput = true;
    }

    private void computeResult() {
        if (currentOperator.isEmpty()) {
            return;
        }

        try {
            double secondNumber = Double.parseDouble(txtDisplay.getText());
            double result;

            switch (currentOperator) {
                case "+":
                    result = calculationEngine.add(firstNumber, secondNumber);
                    break;
                case "-":
                    result = calculationEngine.subtract(firstNumber, secondNumber);
                    break;
                case "*":
                    result = calculationEngine.multiply(firstNumber, secondNumber);
                    break;
                case "/":
                    result = calculationEngine.divide(firstNumber, secondNumber);
                    break;
                default:
                    return;
            }

            txtDisplay.setText(formatResult(result));
            firstNumber = result;
            currentOperator = "";
            startNewInput = true;
        } catch (ArithmeticException ex) {
            txtDisplay.setText("Error");
            firstNumber = 0;
            currentOperator = "";
            startNewInput = true;
        }
    }

    private String formatResult(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUICalculator guiCalculator = new GUICalculator();
            guiCalculator.setVisible(true);
        });
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            GradientPaint paint = new GradientPaint(
                0,
                0,
                BACKGROUND_TOP,
                0,
                getHeight(),
                BACKGROUND_BOTTOM);
            graphics2D.setPaint(paint);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
            graphics2D.dispose();
        }
    }
}