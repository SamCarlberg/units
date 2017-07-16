package edu.wpi.first.wpilib.units.mass;

import edu.wpi.first.wpilib.units.Mass;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Pounds extends Measure<Mass> {

  private Pounds(double magnitude) {
    super(magnitude, Units.Pounds);
  }

  public static Pounds of(double magnitude) {
    return new Pounds(magnitude);
  }

}
