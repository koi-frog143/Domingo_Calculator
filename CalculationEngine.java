public class CalculationEngine extends CalculationBase implements CalculatorOperations {
    @Override
    public double add(double num1, double num2) {
        return normalizeZero(num1 + num2);
    }

    @Override
    public double subtract(double num1, double num2) {
        return normalizeZero(num1 - num2);
    }

    @Override
    public double multiply(double num1, double num2) {
        return normalizeZero(num1 * num2);
    }

    @Override
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return normalizeZero(num1 / num2);
    }
}
