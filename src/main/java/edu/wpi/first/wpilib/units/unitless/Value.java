package edu.wpi.first.wpilib.units.unitless;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Unitless;
import edu.wpi.first.wpilib.units.Units;

public class Value extends Measure<Unitless> {

  private Value(double magnitude) {
    super(magnitude, Units.Value);
  }

  public static Value of(double magnitude) {
    return new Value(magnitude);
  }

}
