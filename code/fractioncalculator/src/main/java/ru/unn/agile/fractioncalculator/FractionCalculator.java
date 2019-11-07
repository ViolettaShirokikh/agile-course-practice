package ru.unn.agile.fractioncalculator;

public final class FractionCalculator {

    private FractionCalculator() {}

    public static Fraction reduce(Fraction fraction) {
        var tmpNumerator = Math.abs(fraction.getNumerator());
        var tmpDenominator = Math.abs(fraction.getDenominator());
        while (tmpNumerator != 0 && tmpDenominator != 0) {
            if (tmpNumerator > tmpDenominator) {
                tmpNumerator %= tmpDenominator;
            } else {
                tmpDenominator %= tmpNumerator;
            }
        }
        var nod = tmpNumerator + tmpDenominator;
        if (fraction.getDenominator() < 0 && fraction.getNumerator() < 0) {
            nod *= -1;
        }
        return new Fraction(
                fraction.getNumerator() / nod,
                fraction.getDenominator() / nod
        );
    }

    public static Fraction multiple(Fraction first, Fraction second) {
        return new Fraction(
                first.getNumerator() * second.getNumerator(),
                first.getDenominator() * second.getDenominator()
        );
    }

    public static Fraction divide(Fraction first, Fraction second) {
        var reverseSecond = new Fraction(second.getDenominator(), second.getNumerator());
        return multiple(first, reverseSecond);
    }

    public static Fraction sum(Fraction first, Fraction second) {
        return new Fraction(
                first.getNumerator() * second.getDenominator() + second.getNumerator() * first.getDenominator(),
                first.getDenominator() * second.getDenominator()
        );
    }

    public static Fraction minus(Fraction first, Fraction second) {
        var negativeSecond = new Fraction(-second.getNumerator(), second.getDenominator());
        return sum(first, negativeSecond);
    }
}
