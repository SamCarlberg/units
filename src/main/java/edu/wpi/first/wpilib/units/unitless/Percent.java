package edu.wpi.first.wpilib.units.unitless;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Unitless;
import edu.wpi.first.wpilib.units.Units;

public class Percent extends Measure<Unitless> {

  private Percent(double magnitude) {
    super(magnitude, Units.Percent);
  }

  public static Percent of(double magnitude) {
    return new Percent(magnitude);
  }

}
