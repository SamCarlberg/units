package edu.wpi.first.wpilib.units.acceleration;

import edu.wpi.first.wpilib.units.Acceleration;
import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Units;

public class Gs extends Measure<Acceleration> {

  private Gs(double magnitude) {
    super(magnitude, Units.Gs);
  }

  public static Gs of(double magnitude) {
    return new Gs(magnitude);
  }

}
