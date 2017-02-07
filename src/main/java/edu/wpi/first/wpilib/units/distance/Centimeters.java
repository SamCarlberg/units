package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;

public class Centimeters extends Measure<Distance> {

    private Centimeters(double value) {
        super(value, Distance.CENTIMETERS);
    }

    public static Centimeters of(double value) {
        return new Centimeters(value);
    }

    public static Centimeters of(Measure<Distance> measure) {
        return new Centimeters(measure.as(Distance.CENTIMETERS).magnitude());
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

}
