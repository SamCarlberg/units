package edu.wpi.first.wpilib.units.mass;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Mass;
import edu.wpi.first.wpilib.units.Units;

public class Ounces extends ImmutableMeasure<Mass> {

  private Ounces(double magnitude) {
    super(magnitude, Units.Ounces);
  }

  public static Ounces of(double magnitude) {
    return new Ounces(magnitude);
  }

}
