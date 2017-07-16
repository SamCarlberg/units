package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class Temperature extends Unit<Temperature> {

  Temperature(DoubleUnaryOperator toBaseConverter,
              DoubleUnaryOperator fromBaseConverter) {
    super(toBaseConverter, fromBaseConverter);
  }

}
