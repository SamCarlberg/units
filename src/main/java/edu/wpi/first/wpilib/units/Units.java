package edu.wpi.first.wpilib.units;

public final class Units {

    private Units() {
        // Prevent instantiation
    }

    // Pseudo-classes describing the more common units of measure.

    // Distance
    public static final Unit<Distance> Meters = BaseUnits.Distance;
    public static final Unit<Distance> Millimeters = Meters.divide(1e3);
    public static final Unit<Distance> Centimeters = Meters.divide(1e2);
    public static final Unit<Distance> Inches = Meters.divide(39.3701);
    public static final Unit<Distance> Feet = Inches.multiply(12);

    // Time
    public static final Unit<Time> Seconds = BaseUnits.Time;
    public static final Unit<Time> Milliseconds = Seconds.divide(1e3);
    public static final Unit<Time> Minutes = Seconds.multiply(60);

    // Mass
    public static final Unit<Mass> Grams = BaseUnits.Mass;
    public static final Unit<Mass> Kilograms = Grams.multiply(1e3);
    public static final Unit<Mass> Pounds = Grams.multiply(453.592);
    public static final Unit<Mass> Ounces = Pounds.divide(16);

    // Angle
    public static final Unit<Angle> Revolutions = BaseUnits.Angle;
    public static final Unit<Angle> Radians = Revolutions.divide(2 * Math.PI);
    public static final Unit<Angle> Degrees = Revolutions.divide(360);

    // Unitless
    public static final Unit<Unitless> Value = BaseUnits.Value;
    public static final Unit<Unitless> Percent = Value.multiply(100);

}
