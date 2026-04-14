public class CalculationEngine extends CalculationBase implements CalculatorOperations {
    private double lastResult = 0;

    public double getLastResult() {
        return lastResult;
    }

    public static String format(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.format("%.2f", value);
    }

    @Override
    public double add(double num1, double num2) {
        lastResult = normalizeZero(num1 + num2);
        return lastResult;
    }

    @Override
    public double subtract(double num1, double num2) {
        lastResult = normalizeZero(num1 - num2);
        return lastResult;
    }

    @Override
    public double multiply(double num1, double num2) {
        lastResult = normalizeZero(num1 * num2);
        return lastResult;
    }

    @Override
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        lastResult = normalizeZero(num1 / num2);
        return lastResult;
    }

}
