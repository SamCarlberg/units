package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class Power extends Unit<Power> {


  Power(double baseUnitEquivalent) {
    super(Power.class, baseUnitEquivalent);
  }

  Power(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(Power.class, toBaseConverter, fromBaseConverter);
  }

}
