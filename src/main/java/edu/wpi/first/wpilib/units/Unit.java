package edu.wpi.first.wpilib.units;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

/**
 * A unit is some unit of measurement that defines a quantity, such as grams, meters, or seconds.
 *
 * @param <U> the self type, eg {@code class SomeUnit extends Unit<SomeUnit>}
 */
public class Unit<U extends Unit<U>> {

    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseConverter;

    /**
     * Creates a new unit defined by its relationship to some base unit.
     *
     * @param toBaseConverter   a function for converting units of this type to the base unit
     * @param fromBaseConverter a function for converting units of the base unit to this one
     */
    protected Unit(DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseConverter) {
        this.toBaseConverter = Objects.requireNonNull(toBaseConverter);
        this.fromBaseConverter = Objects.requireNonNull(fromBaseConverter);
    }

    /**
     * Creates a new unit with the given name and multiplier to the base unit.
     *
     * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
     *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
     */
    protected Unit(double baseUnitEquivalent) {
        this(x -> x * baseUnitEquivalent,
             x -> x / baseUnitEquivalent);
    }

    /**
     * Converts a value of this unit to a value of another unit of the same type.
     *
     * @param value     a value measured in this unit
     * @param otherUnit the unit to convert the value to
     */
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
        return this.fromBaseConverter.andThen(otherUnit.toBaseConverter).applyAsDouble(1);
    }

    public DoubleUnaryOperator getConverterToBase() {
        return toBaseConverter;
    }

    public DoubleUnaryOperator getConverterFromBase() {
        return fromBaseConverter;
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
     * @see #divide(double)
     */
    public Unit<U> multiply(double scale) {
        if (scale == 1) {
            // Same units, just reuse this object.
            return this;
        }
        return new Unit<>(this.toBaseConverter.andThen(x -> x * scale),
                          this.fromBaseConverter.andThen(x -> x / scale));
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
