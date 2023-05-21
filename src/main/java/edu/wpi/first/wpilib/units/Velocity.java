package edu.wpi.first.wpilib.units;

public class Velocity extends Unit<Velocity> {

  Velocity(double baseUnitEquivalent) {
    super(Velocity.class, baseUnitEquivalent);
  }

  Velocity(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Velocity.class, toBaseConverter, fromBaseConverter);
  }

  public Acceleration per(Unit<Time> period) {
    return new Acceleration(
        (double newUnitValue) -> getConverterToBase().apply(newUnitValue) / period.getConverterToBase().apply(1),
        (double baseUnitValue) -> getConverterFromBase().apply(baseUnitValue) / period.getConverterFromBase().apply(1)
    );
  }

}
