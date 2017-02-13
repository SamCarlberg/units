package edu.wpi.first.wpilib.units.mass;

import edu.wpi.first.wpilib.units.Mass;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Kilograms extends Measure<Mass> {

    private Kilograms(double magnitude) {
        super(magnitude, Units.Kilograms);
    }

    public static Kilograms of(double magnitude) {
        return new Kilograms(magnitude);
    }

}
