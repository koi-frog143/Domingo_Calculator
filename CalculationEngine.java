public class CalculationEngine extends CalculationBase implements CalculatorOperations {
    @Override
    public double add(double a, double b) {
        return normalizeZero(a + b);
    }

    @Override
    public double subtract(double a, double b) {
        return normalizeZero(a - b);
    }

    @Override
    public double multiply(double a, double b) {
        return normalizeZero(a * b);
    }

    @Override
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return normalizeZero(a / b);
    }
}
