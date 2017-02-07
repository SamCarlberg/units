package edu.wpi.first.wpilib.units.angle;

import edu.wpi.first.wpilib.units.Angle;
import edu.wpi.first.wpilib.units.Measure;

public class Degrees extends Measure<Angle> {

    private Degrees(double value) {
        super(value, Angle.DEGREES);
    }

    public static Degrees of(double value) {
        return new Degrees(value);
    }

    public static Degrees of(Measure<Angle> measure) {
        return new Degrees(measure.as(Angle.DEGREES).magnitude());
    }

    public Radians asRadians() {
        return Radians.of(this);
    }

}
