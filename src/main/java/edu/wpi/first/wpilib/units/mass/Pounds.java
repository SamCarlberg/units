package edu.wpi.first.wpilib.units.mass;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Mass;
import edu.wpi.first.wpilib.units.Units;

public class Pounds extends ImmutableMeasure<Mass> {

  private Pounds(double magnitude) {
    super(magnitude, Units.Pounds);
  }

  public static Pounds of(double magnitude) {
    return new Pounds(magnitude);
  }

}
