package edu.wpi.first.wpilib.units.electriccurrent;

import edu.wpi.first.wpilib.units.ElectricCurrent;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Amps extends Measure<ElectricCurrent> {

    private Amps(double magnitude) {
        super(magnitude, Units.Amps);
    }

    public static Amps of(double magnitude) {
        return new Amps(magnitude);
    }

}
