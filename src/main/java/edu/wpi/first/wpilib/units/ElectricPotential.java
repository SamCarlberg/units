package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class ElectricPotential extends Unit<ElectricPotential> {

  ElectricPotential(double baseUnitEquivalent) {
    super(ElectricPotential.class, baseUnitEquivalent);
  }

  ElectricPotential(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(ElectricPotential.class, toBaseConverter, fromBaseConverter);
  }

  public Power times(Unit<ElectricCurrent> current) {
    return new Power(
        (double newUnitValue) -> getConverterToBase().applyAsDouble(newUnitValue) * current.getConverterToBase().applyAsDouble(newUnitValue),
        (double baseUnitValue) -> getConverterFromBase().applyAsDouble(baseUnitValue) * current.getConverterFromBase().applyAsDouble(baseUnitValue)
    );
  }

}
