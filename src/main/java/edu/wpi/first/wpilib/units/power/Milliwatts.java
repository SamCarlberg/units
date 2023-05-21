package edu.wpi.first.wpilib.units.power;

import edu.wpi.first.wpilib.units.*;

public class Milliwatts extends ImmutableMeasure<Power> {

  private Milliwatts(double magnitude) {
    super(magnitude, Units.Milliwatts);
  }

  public static Milliwatts of(double magnitude) {
    return new Milliwatts(magnitude);
  }

  public static Milliwatts of(Measure<ElectricPotential> electricPotential, Measure<ElectricCurrent> electricCurrent) {
    return new Milliwatts(electricPotential.as(Units.Volts) * electricCurrent.as(Units.Amps) * 1e3);
  }

}
