package edu.wpi.first.wpilib.units.velocity;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;
import edu.wpi.first.wpilib.units.Velocity;

public class MetersPerSecond extends Measure<Velocity> {

  private MetersPerSecond(double magnitude) {
    super(magnitude, Units.MetersPerSecond);
  }

  public static MetersPerSecond of(double magnitude) {
    return new MetersPerSecond(magnitude);
  }

}
