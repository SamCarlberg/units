package edu.wpi.first.wpilib.units.time;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Time;
import edu.wpi.first.wpilib.units.Units;

public class Seconds extends ImmutableMeasure<Time> {

  private Seconds(double magnitude) {
    super(magnitude, Units.Seconds);
  }

  public static Seconds of(double magnitude) {
    return new Seconds(magnitude);
  }

}
