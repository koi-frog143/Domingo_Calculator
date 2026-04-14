public class CalculatorUtils {
    private CalculatorUtils() {
    }

    public static String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
