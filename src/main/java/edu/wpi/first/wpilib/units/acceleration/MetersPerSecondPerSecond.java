package edu.wpi.first.wpilib.units.acceleration;

import edu.wpi.first.wpilib.units.Acceleration;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class MetersPerSecondPerSecond extends Measure<Acceleration> {

  private MetersPerSecondPerSecond(double magnitude) {
    super(magnitude, Units.MetersPerSecondPerSecond);
  }

  public static MetersPerSecondPerSecond of(double magnitude) {
    return new MetersPerSecondPerSecond(magnitude);
  }

}
