package edu.wpi.first.wpilib.units.time;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Time;
import edu.wpi.first.wpilib.units.Units;

public class Milliseconds extends ImmutableMeasure<Time> {

  private Milliseconds(double magnitude) {
    super(magnitude, Units.Milliseconds);
  }

  public static Milliseconds of(double magnitude) {
    return new Milliseconds(magnitude);
  }

}
