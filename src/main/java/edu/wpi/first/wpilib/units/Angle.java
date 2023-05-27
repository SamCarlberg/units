package edu.wpi.first.wpilib.units;

// technically, angles are unitless dimensions
// eg Mass * Distance * Velocity<Angle> is equivalent to (Mass * Distance) / Time - otherwise known as Power
// in other words, Velocity<Angle> is /actually/ Frequency
public class Angle extends Unit<Angle> {

  /**
   * Creates a new unit with the given name and multiplier to the base unit.
   *
   * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
   *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
   */
  Angle(double baseUnitEquivalent) {
    super(Angle.class, baseUnitEquivalent);
  }

  Angle(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Angle.class, toBaseConverter, fromBaseConverter);
  }

}
