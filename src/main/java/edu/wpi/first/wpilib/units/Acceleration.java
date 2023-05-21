package edu.wpi.first.wpilib.units;

public class Acceleration extends Unit<Acceleration> {

  Acceleration(double baseUnitEquivalent) {
    super(Acceleration.class, baseUnitEquivalent);
  }

  Acceleration(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Acceleration.class, toBaseConverter, fromBaseConverter);
  }

}
