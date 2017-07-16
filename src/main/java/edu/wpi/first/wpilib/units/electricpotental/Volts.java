package edu.wpi.first.wpilib.units.electricpotental;

import edu.wpi.first.wpilib.units.ElectricPotential;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Volts extends Measure<ElectricPotential> {

  private Volts(double magnitude) {
    super(magnitude, Units.Volts);
  }

  public static Volts of(double magnitude) {
    return new Volts(magnitude);
  }

}
