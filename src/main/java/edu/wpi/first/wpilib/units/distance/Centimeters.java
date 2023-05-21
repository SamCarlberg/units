package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Units;

public class Centimeters extends ImmutableMeasure<Distance> {

  private Centimeters(double magnitude) {
    super(magnitude, Units.Centimeters);
  }

  public static Centimeters of(double magnitude) {
    return new Centimeters(magnitude);
  }

}
