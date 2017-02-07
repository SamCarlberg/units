package edu.wpi.first.wpilib.units.angle;

import edu.wpi.first.wpilib.units.Angle;
import edu.wpi.first.wpilib.units.Measure;

public class Radians extends Measure<Angle> {

    private Radians(double value) {
        super(value, Angle.RADIANS);
    }

    public static Radians of(double value) {
        return new Radians(value);
    }

    public static Radians of(Measure<Angle> measure) {
        return new Radians(measure.as(Angle.RADIANS).magnitude());
    }

    public Degrees asDegrees() {
        return Degrees.of(this);
    }

}
