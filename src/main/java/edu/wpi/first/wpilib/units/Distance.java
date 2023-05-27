package edu.wpi.first.wpilib.units;

public class Distance extends Unit<Distance> {

  /**
   * Creates a new unit with the given name and multiplier to the base unit.
   *
   * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
   *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
   * @param name
   */
  Distance(double baseUnitEquivalent, String name, String symbol) {
    super(Distance.class, baseUnitEquivalent, name, symbol);
  }

  Distance(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter, String name, String symbol) {
    super(Distance.class, toBaseConverter, fromBaseConverter, name, symbol);
  }
}
