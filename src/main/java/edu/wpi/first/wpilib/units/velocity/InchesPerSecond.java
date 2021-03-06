package edu.wpi.first.wpilib.units.velocity;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;
import edu.wpi.first.wpilib.units.Velocity;

public class InchesPerSecond extends Measure<Velocity> {

  private InchesPerSecond(double magnitude) {
    super(magnitude, Units.InchesPerSecond);
  }

  public static InchesPerSecond of(double magnitude) {
    return new InchesPerSecond(magnitude);
  }

}
