package edu.wpi.first.wpilib.units.velocity;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;
import edu.wpi.first.wpilib.units.Velocity;

public class FeetPerSecond extends Measure<Velocity> {

  private FeetPerSecond(double magnitude) {
    super(magnitude, Units.FeetPerSecond);
  }

  public static FeetPerSecond of(double magnitude) {
    return new FeetPerSecond(magnitude);
  }

}
