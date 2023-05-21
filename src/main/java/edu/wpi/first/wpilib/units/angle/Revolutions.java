package edu.wpi.first.wpilib.units.angle;

import edu.wpi.first.wpilib.units.Angle;
import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Units;

public class Revolutions extends ImmutableMeasure<Angle> {

  private Revolutions(double magnitude) {
    super(magnitude, Units.Revolutions);
  }

  public static Revolutions of(double magnitude) {
    return new Revolutions(magnitude);
  }

}
