package by.epam.lab.entity;

public enum RoundMethod {
    FLOOR {
        double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    ROUND {
        double roundFunction(double d) {
            return Math.round(d);
        }
    },
    CEIL {
        double roundFunction(double d) {
            return Math.ceil(d);
        }
    };

    abstract double roundFunction(double value);

    int round(double roundedValue, RoundMethod roundMethod, int d) {
        int tenPow = (int) Math.pow(10, d);
        return (int) roundMethod.roundFunction(roundedValue / tenPow) * tenPow;
    }
}