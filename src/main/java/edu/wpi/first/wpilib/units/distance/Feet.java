package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Feet extends Measure<Distance> {

  private Feet(double magnitude) {
    super(magnitude, Units.Feet);
  }

  public static Feet of(double magnitude) {
    return new Feet(magnitude);
  }

}
