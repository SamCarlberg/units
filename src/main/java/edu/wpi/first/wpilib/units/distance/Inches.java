package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Inches extends Measure<Distance> {

    private Inches(double magnitude) {
        super(magnitude, Units.Inches);
    }

    public static Inches of(double magnitude) {
        return new Inches(magnitude);
    }

}
