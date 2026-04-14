public class CalculationEngine extends CalculationBase implements CalculatorOperations {
    private double lastResult = 0;

    public double getLastResult() {
        return lastResult;
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

    public static void main(String[] args) {
        CalculationEngine engine = new CalculationEngine();

        System.out.println("CalculationEngine demo:");
        System.out.println("10 + 5 = " + CalculatorUtils.format(engine.add(10, 5)));
        System.out.println("Last result: " + CalculatorUtils.format(engine.getLastResult()));
        System.out.println("20 - 3 = " + CalculatorUtils.format(engine.subtract(20, 3)));
        System.out.println("Last result: " + CalculatorUtils.format(engine.getLastResult()));
        System.out.println("4 * 7 = " + CalculatorUtils.format(engine.multiply(4, 7)));
        System.out.println("Last result: " + CalculatorUtils.format(engine.getLastResult()));
        System.out.println("15 / 3 = " + CalculatorUtils.format(engine.divide(15, 3)));
        System.out.println("Last result: " + CalculatorUtils.format(engine.getLastResult()));
    }

}
