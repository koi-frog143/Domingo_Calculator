/**
 * Provides shared utility behaviour for all calculation classes.
 *
 * <p><b>OOP – Encapsulation:</b> The helper method {@link #normalizeZero} is
 * declared {@code protected} so it is accessible to subclasses but hidden from
 * unrelated code outside the hierarchy.
 *
 * <p><b>OOP – Inheritance:</b> Subclasses (e.g. {@link CalculationEngine})
 * extend this class to inherit the shared helper without duplicating it.
 */
public class CalculationBase {

    /**
     * Converts negative zero ({@code -0.0}) to positive zero ({@code 0.0}) so
     * that results display consistently in the UI.
     *
     * @param value the value to normalize
     * @return {@code 0.0} when {@code value} is {@code -0.0}; otherwise
     *         {@code value} unchanged
     */
    protected double normalizeZero(double value) {
        return value == -0.0 ? 0.0 : value;
    }
}
