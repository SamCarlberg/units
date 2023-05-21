package edu.wpi.first.wpilib.units.unitless;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Unitless;
import edu.wpi.first.wpilib.units.Units;

public class Percent extends ImmutableMeasure<Unitless> {

  private Percent(double magnitude) {
    super(magnitude, Units.Percent);
  }

  public static Percent of(double magnitude) {
    return new Percent(magnitude);
  }

}
