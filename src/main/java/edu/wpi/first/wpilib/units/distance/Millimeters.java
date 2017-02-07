package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;

public class Millimeters extends Measure<Distance> {

    private Millimeters(double value) {
        super(value, Distance.MILLIMETERS);
    }

    public static Millimeters of(double value) {
        return new Millimeters(value);
    }

    public static Millimeters of(Measure<Distance> measure) {
        return new Millimeters(measure.as(Distance.MILLIMETERS).magnitude());
    }

    public Inches asInches() {
        return Inches.of(this);
    }

    public Feet asFeet() {
        return Feet.of(this);
    }

    public Centimeters asCentimeters() {
        return Centimeters.of(this);
    }

    public Meters asMeters() {
        return Meters.of(this);
    }

}
