package edu.wpi.first.wpilib.units.electriccurrent;

import edu.wpi.first.wpilib.units.ElectricCurrent;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Milliamps extends Measure<ElectricCurrent> {

    private Milliamps(double magnitude) {
        super(magnitude, Units.Milliamps);
    }

    public static Milliamps of(double magnitude) {
        return new Milliamps(magnitude);
    }

}
