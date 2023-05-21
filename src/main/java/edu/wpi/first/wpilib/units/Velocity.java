package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class Velocity extends Unit<Velocity> {

  Velocity(double baseUnitEquivalent) {
    super(Velocity.class, baseUnitEquivalent);
  }

  Velocity(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(Velocity.class, toBaseConverter, fromBaseConverter);
  }

}
