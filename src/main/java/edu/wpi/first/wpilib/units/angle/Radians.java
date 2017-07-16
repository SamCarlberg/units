package edu.wpi.first.wpilib.units.angle;

import edu.wpi.first.wpilib.units.Angle;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Radians extends Measure<Angle> {

  private Radians(double magnitude) {
    super(magnitude, Units.Radians);
  }

  public static Radians of(double magnitude) {
    return new Radians(magnitude);
  }

}
