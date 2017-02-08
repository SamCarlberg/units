package edu.wpi.first.wpilib.units;

/**
 * A unit is some unit of measurement that defines a quantity, such as grams, meters, or seconds.
 *
 * @param <U> the self type, eg {@code class SomeUnit extends Unit<SomeUnit>}
 */
public class Unit<U extends Unit<U>> {

    private final double baseUnitEquivalent;

    /**
     * Creates a new unit with the given name and multiplier to the base unit.
     *
     * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
     *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
     */
    protected Unit(double baseUnitEquivalent) {
        if (baseUnitEquivalent <= 0) {
            throw new IllegalArgumentException(
                    "Multiplier to base unit must be a positive number. Given: " + baseUnitEquivalent);
        }
        this.baseUnitEquivalent = baseUnitEquivalent;
    }

    public double convert(double value, Unit<U> otherUnit) {
        return value * otherUnit.multiplierTo(this);
    }

    /**
     * Gets the multiplier for converting values of this unit into values of another unit (eg feet to inches).
     *
     * @param otherUnit the unit to get the conversion multiplier for
     *
     * @return a multiplier <i>k</i> such that a measure with this unit multiplied by <i>k</i> will have an
     * equivalent value in the given unit
     */
    public double multiplierTo(Unit<U> otherUnit) {
        return otherUnit.getBaseUnitEquivalent() / this.getBaseUnitEquivalent();
    }

    /**
     * Gets the magnitude of this unit in terms of the base unit.
     */
    public final double getBaseUnitEquivalent() {
        return baseUnitEquivalent;
    }

    /**
     * Creates a new measure of this unit with the given value.
     *
     * @param magnitude the magnitude of the measure to create
     */
    public Measure<U> of(double magnitude) {
        return new Measure<>(magnitude, this);
    }

    /**
     * Creates a new unit based off this one, where <i>scale</i> amount of this this unit
     * is equivalent to one of the new unit. For example, {@code Unit km = Units.Meters.multiply("km", 1000)}
     * <p>
     * This is equivalent to {@code divide(1 / scale)}
     * </p>
     *
     * @param scale the scale factor of the new unit as compared to this one
     *
     * @implNote Subclasses should override this, otherwise unexpected behavior can occur:
     * <pre><code>
     *     class Time extends Unit&lt;Time&gt; { }
     *     <br>
     *     Time t = Units.Seconds.multiply(...); // Error!
     * </code></pre>
     * @see #divide(double)
     */
    public Unit<U> multiply(double scale) {
        return new Unit<>(baseUnitEquivalent * scale);
    }

    /**
     * Creates a new unit based off this one, where <i>scale</i> amount of the new new unit
     * is equivalent to one if this unit. For example. {@code Unit mm = Units.Meters.divide("mm", 1000)}
     * <p>
     * This is equivalent to {@code multiply(1 / scale)}
     * </p>
     *
     * @param scale the scale factor of this unit as compared to the new one
     *
     * @see #multiply(double)
     */
    public Unit<U> divide(double scale) {
        return multiply(1 / scale);
    }

}
