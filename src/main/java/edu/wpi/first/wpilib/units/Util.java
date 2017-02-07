package edu.wpi.first.wpilib.units;

public final class Util {

    public static final double IDENTITY = 1;
    public static final double TEN = 10;
    public static final double HUNDRED = 100;
    public static final double THOUSAND = 1000;
    public static final double SIXTY = 60;

    /**
     * Computes the inverse of a given number x.
     *
     * @param x the number to invert
     *
     * @return the inverse of x, ie {@code 1/x}
     */
    public static double inv(double x) {
        return 1.0 / x;
    }

}
