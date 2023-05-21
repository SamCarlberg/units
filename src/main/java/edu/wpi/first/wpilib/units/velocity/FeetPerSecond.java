package edu.wpi.first.wpilib.units.velocity;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Units;
import edu.wpi.first.wpilib.units.Velocity;

public class FeetPerSecond extends ImmutableMeasure<Velocity> {

  private FeetPerSecond(double magnitude) {
    super(magnitude, Units.FeetPerSecond);
  }

  public static FeetPerSecond of(double magnitude) {
    return new FeetPerSecond(magnitude);
  }

}
