package edu.wpi.first.wpilib.units.temperature;

import edu.wpi.first.wpilib.units.ImmutableMeasure;
import edu.wpi.first.wpilib.units.Temperature;
import edu.wpi.first.wpilib.units.Units;

/**
 *
 */
public class Fahrenheit extends ImmutableMeasure<Temperature> {

  private Fahrenheit(double magnitude) {
    super(magnitude, Units.Celsius);
  }

  public static Fahrenheit of(double magnitude) {
    return new Fahrenheit(magnitude);
  }

}
