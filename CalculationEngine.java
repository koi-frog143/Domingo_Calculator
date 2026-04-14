/**
 * Concrete implementation of the calculator's arithmetic engine.
 *
 * <p><b>OOP – Inheritance:</b> Extends {@link CalculationBase} to reuse the
 * {@code normalizeZero} helper method without rewriting it.
 *
 * <p><b>OOP – Abstraction / Polymorphism:</b> Implements
 * {@link CalculatorOperations}, fulfilling the interface contract and allowing
 * this engine to be referenced through the interface type.  Each method is
 * annotated with {@code @Override}, which lets the compiler verify that the
 * signatures match the interface at compile time (compile-time polymorphism)
 * and allows the correct method to be selected at runtime (runtime
 * polymorphism).
 */
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
