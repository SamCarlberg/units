package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;

public class Inches extends Measure<Distance> {

    private Inches(double value) {
        super(value, Distance.INCHES);
    }

    public static Inches of(double value) {
        return new Inches(value);
    }

    public static Inches of(Measure<Distance> measure) {
        return new Inches(measure.as(Distance.INCHES).magnitude());
    }

    public Feet asFeet() {
        return Feet.of(this);
    }

    public Millimeters asMillimeters() {
        return Millimeters.of(this);
    }

}
