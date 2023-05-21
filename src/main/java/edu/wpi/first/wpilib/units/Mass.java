package edu.wpi.first.wpilib.units;

public class Mass extends Unit<Mass> {

  /**
   * Creates a new unit with the given name and multiplier to the base unit.
   *
   * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
   *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
   */
  Mass(double baseUnitEquivalent) {
    super(Mass.class, baseUnitEquivalent);
  }

  Mass(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Mass.class, toBaseConverter, fromBaseConverter);
  }

}
