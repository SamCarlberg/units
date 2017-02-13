package edu.wpi.first.wpilib.units.power;

import edu.wpi.first.wpilib.units.ElectricCurrent;
import edu.wpi.first.wpilib.units.ElectricPotential;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Power;
import edu.wpi.first.wpilib.units.Units;

public class Milliwatts extends Measure<Power> {

    private Milliwatts(double magnitude) {
        super(magnitude, Units.Milliwatts);
    }

    public static Milliwatts of(double magnitude) {
        return new Milliwatts(magnitude);
    }

    public static Milliwatts of(Measure<ElectricPotential> electricPotential, Measure<ElectricCurrent> electricCurrent) {
        return new Milliwatts(electricPotential.as(Units.Volts) * electricCurrent.as(Units.Amps));
    }

}
