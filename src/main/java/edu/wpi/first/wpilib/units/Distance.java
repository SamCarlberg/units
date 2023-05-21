package edu.wpi.first.wpilib.units;

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

  Distance(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Distance.class, toBaseConverter, fromBaseConverter);
  }

  public Velocity per(Unit<Time> period) {
    return new Velocity(
        (double newUnitValue) -> getConverterToBase().apply(newUnitValue) / period.getConverterToBase().apply(1),
        (double baseUnitValue) -> getConverterFromBase().apply(baseUnitValue) / period.getConverterFromBase().apply(1)
    );
  }
}
