package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Meters extends Measure<Distance> {

    private Meters(double magnitude) {
        super(magnitude, Units.Meters);
    }

    public static Meters of(double magnitude) {
        return new Meters(magnitude);
    }

}
