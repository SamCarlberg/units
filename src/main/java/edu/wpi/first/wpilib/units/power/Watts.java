package edu.wpi.first.wpilib.units.power;

import edu.wpi.first.wpilib.units.*;

public class Watts extends ImmutableMeasure<Power> {

  private Watts(double magnitude) {
    super(magnitude, Units.Watts);
  }

  public static Watts of(double magnitude) {
    return new Watts(magnitude);
  }

  public static Watts of(Measure<ElectricPotential> electricPotential, Measure<ElectricCurrent> electricCurrent) {
    return new Watts(electricPotential.as(Units.Volts) * electricCurrent.as(Units.Amps));
  }

}
