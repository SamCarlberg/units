package edu.wpi.first.wpilib.units.electricpotental;

import edu.wpi.first.wpilib.units.ElectricPotential;
import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Units;

public class Millivolts extends ImmutableMeasure<ElectricPotential> {

  private Millivolts(double magnitude) {
    super(magnitude, Units.Millivolts);
  }

  public static Millivolts of(double magnitude) {
    return new Millivolts(magnitude);
  }

}
