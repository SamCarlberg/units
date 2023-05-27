package edu.wpi.first.wpilib.units;

public class Acceleration extends Unit<Acceleration> {

  Acceleration(double baseUnitEquivalent) {
    super(Acceleration.class, baseUnitEquivalent);
  }

  Acceleration(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Acceleration.class, toBaseConverter, fromBaseConverter);
  }

  Acceleration(Velocity<?> velocity, Unit<? extends Time> period) {
    this(velocity.toBaseUnits(1) / Math.pow(period.toBaseUnits(1), 2));
  }

}
