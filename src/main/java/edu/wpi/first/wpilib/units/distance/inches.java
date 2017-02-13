package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class inches extends Measure<Distance> {

    private inches(double magnitude) {
        super(magnitude, Units.Inches);
    }

    public static inches of(double magnitude) {
        return new inches(magnitude);
    }

}
