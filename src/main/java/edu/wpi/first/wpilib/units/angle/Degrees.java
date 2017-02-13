package edu.wpi.first.wpilib.units.angle;

import edu.wpi.first.wpilib.units.Angle;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Degrees extends Measure<Angle> {

    private Degrees(double magnitude) {
        super(magnitude, Units.Degrees);
    }

    public static Degrees of(double magnitude) {
        return new Degrees(magnitude);
    }

}
