package edu.wpi.first.wpilib.units.temperature;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Temperature;
import edu.wpi.first.wpilib.units.Units;

/**
 *
 */
public class Celsius extends ImmutableMeasure<Temperature> {

  private Celsius(double magnitude) {
    super(magnitude, Units.Celsius);
  }

  public static Celsius of(double magnitude) {
    return new Celsius(magnitude);
  }

}
