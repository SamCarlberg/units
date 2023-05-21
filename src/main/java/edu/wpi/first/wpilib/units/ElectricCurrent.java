package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class ElectricCurrent extends Unit<ElectricCurrent> {

  ElectricCurrent(double baseUnitEquivalent) {
    super(ElectricCurrent.class, baseUnitEquivalent);
  }

  ElectricCurrent(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(ElectricCurrent.class, toBaseConverter, fromBaseConverter);
  }

  public Power times(Unit<ElectricPotential> voltage) {
    return new Power(
        (double newUnitValue) -> getConverterToBase().applyAsDouble(newUnitValue) * voltage.getConverterToBase().applyAsDouble(newUnitValue),
        (double baseUnitValue) -> getConverterFromBase().applyAsDouble(baseUnitValue) * voltage.getConverterFromBase().applyAsDouble(baseUnitValue)
    );
  }

}
