package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class Acceleration extends Unit<Acceleration> {

  Acceleration(double baseUnitEquivalent) {
    super(Acceleration.class, baseUnitEquivalent);
  }

  Acceleration(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(Acceleration.class, toBaseConverter, fromBaseConverter);
  }

}
