package edu.wpi.first.wpilib.units.time;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Time;
import edu.wpi.first.wpilib.units.Units;

public class Minutes extends ImmutableMeasure<Time> {

  private Minutes(double magnitude) {
    super(magnitude, Units.Minutes);
  }

  public static Minutes of(double magnitude) {
    return new Minutes(magnitude);
  }

}
