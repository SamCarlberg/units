package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;

public class Meters extends Measure<Distance> {

    private Meters(double value) {
        super(value, Distance.METERS);
    }

    public static Meters of(double value) {
        return new Meters(value);
    }

    public static Meters of(Measure<Distance> measure) {
        return new Meters(measure.as(Distance.METERS).magnitude());
    }

    public Inches asInches() {
        return Inches.of(this);
    }

    public Feet asFeet() {
        return Feet.of(this);
    }

    public Millimeters asMillimeters() {
        return Millimeters.of(this);
    }

    public Centimeters asCentimeters() {
        return Centimeters.of(this);
    }

}
