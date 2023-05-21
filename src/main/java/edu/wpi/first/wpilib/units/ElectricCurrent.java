package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class ElectricCurrent extends Unit<ElectricCurrent> {

  ElectricCurrent(double baseUnitEquivalent) {
    super(ElectricCurrent.class, baseUnitEquivalent);
  }

  ElectricCurrent(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(ElectricCurrent.class, toBaseConverter, fromBaseConverter);
  }

}
