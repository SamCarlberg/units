package edu.wpi.first.wpilib.units.angle;

import edu.wpi.first.wpilib.units.Angle;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Revolutions extends Measure<Angle> {

    private Revolutions(double magnitude) {
        super(magnitude, Units.Revolutions);
    }

    public static Revolutions of(double magnitude) {
        return new Revolutions(magnitude);
    }

}
