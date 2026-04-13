public class CalculationBase {
    protected double normalizeZero(double value) {
        return value == -0.0 ? 0.0 : value;
    }
}
