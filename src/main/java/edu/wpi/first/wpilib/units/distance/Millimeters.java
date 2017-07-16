package edu.wpi.first.wpilib.units.distance;

import edu.wpi.first.wpilib.units.Distance;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Millimeters extends Measure<Distance> {

  private Millimeters(double magnitude) {
    super(magnitude, Units.Millimeters);
  }

  public static Millimeters of(double magnitude) {
    return new Millimeters(magnitude);
  }

}
