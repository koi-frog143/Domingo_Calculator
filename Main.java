import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CalculationEngine engine = new CalculationEngine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Domingo Calculator (Console) ===");
        System.out.println("Operators: + - * /   |   Type 'exit' to quit");
        System.out.println();

        while (true) {
            System.out.print("Enter first number: ");
            String firstInput = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(firstInput)) {
                break;
            }

            double firstNumber;
            try {
                firstNumber = Double.parseDouble(firstInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
                continue;
            }

            System.out.print("Enter operator (+, -, *, /): ");
            String operator = scanner.nextLine().trim();
            if (!"+".equals(operator) && !"-".equals(operator)
                    && !"*".equals(operator) && !"/".equals(operator)) {
                System.out.println("Invalid operator. Please try again.");
                continue;
            }

            System.out.print("Enter second number: ");
            String secondInput = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(secondInput)) {
                break;
            }

            double secondNumber;
            try {
                secondNumber = Double.parseDouble(secondInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
                continue;
            }

            try {
                perform(engine, firstNumber, operator, secondNumber);
                System.out.println("Result: " + CalculatorUtils.format(engine.getLastResult()));
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void perform(CalculationEngine engine, double a, String operator, double b) {
        switch (operator) {
            case "+":
                engine.add(a, b);
                break;
            case "-":
                engine.subtract(a, b);
                break;
            case "*":
                engine.multiply(a, b);
                break;
            case "/":
                engine.divide(a, b);
                break;
        }
    }
}
