/**
 * Defines the contract for all basic arithmetic operations supported by the
 * calculator.
 *
 * <p><b>OOP – Abstraction:</b> As a Java interface, {@code CalculatorOperations}
 * separates <em>what</em> the calculator can do from <em>how</em> it does it.
 * Any class that implements this interface is guaranteed to provide all four
 * operations, while callers remain decoupled from the concrete implementation.
 */
public interface CalculatorOperations {

    /**
     * Returns the sum of {@code a} and {@code b}.
     *
     * @param a the first operand
     * @param b the second operand
     * @return {@code a + b}
     */
    double add(double a, double b);

    /**
     * Returns the difference of {@code a} and {@code b}.
     *
     * @param a the first operand
     * @param b the second operand
     * @return {@code a - b}
     */
    double subtract(double a, double b);

    /**
     * Returns the product of {@code a} and {@code b}.
     *
     * @param a the first operand
     * @param b the second operand
     * @return {@code a * b}
     */
    double multiply(double a, double b);

    /**
     * Returns the quotient of {@code a} divided by {@code b}.
     *
     * @param a the dividend
     * @param b the divisor (must not be zero)
     * @return {@code a / b}
     * @throws ArithmeticException if {@code b} is zero
     */
    double divide(double a, double b);
}
