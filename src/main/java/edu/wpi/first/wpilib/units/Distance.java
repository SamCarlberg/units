package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

public class Distance extends Unit<Distance> {

  /**
   * Creates a new unit with the given name and multiplier to the base unit.
   *
   * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
   *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
   */
  Distance(double baseUnitEquivalent) {
    super(Distance.class, baseUnitEquivalent);
  }

  Distance(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
    super(Distance.class, toBaseConverter, fromBaseConverter);
  }

  public Velocity per(Unit<Time> period) {
    return new Velocity(
        (double newUnitValue) -> getConverterToBase().applyAsDouble(newUnitValue) / period.getConverterToBase().applyAsDouble(newUnitValue),
        (double baseUnitValue) -> getConverterFromBase().applyAsDouble(baseUnitValue) / period.getConverterFromBase().applyAsDouble(baseUnitValue)
    );
  }
}
