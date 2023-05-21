package edu.wpi.first.wpilib.units.mass;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Mass;
import edu.wpi.first.wpilib.units.Units;

public class Grams extends ImmutableMeasure<Mass> {

  private Grams(double magnitude) {
    super(magnitude, Units.Grams);
  }

  public static Grams of(double magnitude) {
    return new Grams(magnitude);
  }

}
