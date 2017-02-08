package edu.wpi.first.wpilib.units;

public class Unitless extends Unit<Unitless> {

    /**
     * Creates a new unit with the given name and multiplier to the base unit.
     *
     * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
     *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
     */
    protected Unitless(double baseUnitEquivalent) {
        super(baseUnitEquivalent);
    }

    @Override
    public Unitless multiply(double scale) {
        return new Unitless(getBaseUnitEquivalent() * scale);
    }

}
