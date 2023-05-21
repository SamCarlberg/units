package edu.wpi.first.wpilib.units.electricpotental;

import edu.wpi.first.wpilib.units.*;

public class Volts extends ImmutableMeasure<ElectricPotential> {

  private Volts(double magnitude) {
    super(magnitude, Units.Volts);
  }

  public static Volts of(double magnitude) {
    return new Volts(magnitude);
  }

  // can't overload `times` because it'd have the same erasure as Measure<U> times(Measure<Unitless>)
  // both erase down to Measure times(Measure)

  public Measure<Power> timesP(Measure<ElectricCurrent> current) {
    return Units.Watts.of(this.as(Units.Volts) * current.as(Units.Amps));
  }
}
