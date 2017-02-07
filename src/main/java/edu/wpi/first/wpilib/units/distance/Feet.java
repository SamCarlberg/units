package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;

public class Feet extends Measure<Distance> {

    private Feet(double value) {
        super(value, Distance.FEET);
    }

    public static Feet of(double value) {
        return new Feet(value);
    }

    public static Feet of(Measure<Distance> measure) {
        return new Feet(measure.as(Distance.FEET).magnitude());
    }

    public Inches asInches() {
        return Inches.of(this);
    }

    public Millimeters asMillimeters() {
        return Millimeters.of(this);
    }

    public Centimeters asCentimeters() {
        return Centimeters.of(this);
    }

    public Meters asMeters() {
        return Meters.of(this);
    }

}
