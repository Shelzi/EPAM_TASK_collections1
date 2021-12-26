package by.epam.lab.util;

public final class Converter {
    private Converter() {
    }

    public static String pennyToRuble(int pennyToConvert) {
        return String.format("%d.%02d", pennyToConvert / 100, pennyToConvert % 100);
    }

    public static String pennyToRuble(double pennyToConvert) {
        return String.format("%.3f", pennyToConvert / 100);
    }
}