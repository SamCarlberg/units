package edu.wpi.first.wpilib.units.power;

import edu.wpi.first.wpilib.units.ElectricCurrent;
import edu.wpi.first.wpilib.units.ElectricPotential;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Power;
import edu.wpi.first.wpilib.units.Units;

public class Horsepower extends Measure<Power> {

    private Horsepower(double magnitude) {
        super(magnitude, Units.Horsepower);
    }

    public static Horsepower of(double magnitude) {
        return new Horsepower(magnitude);
    }

}
