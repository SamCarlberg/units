package edu.wpi.first.wpilib.units;

/**
 * A unit is some unit of measurement that defines the magnitude of some quantity.
 *
 * @param <U> the self type. Note that this is an <i>intersection type</i> with {@link Enum}; all Unit classes must also be enums.
 */
public interface Unit<U extends Enum<U> & Unit<U>> {

    /**
     * Gets the multiplier for converting values of this unit into values of another unit (eg feet to inches).
     *
     * @param otherUnit the unit to get he conversion multiplierTo for
     *
     * @return a multiplierTo <i>k</i> such that a measure with this unit multiplied by <i>k</i> will have an
     * equivalent value in the given unit
     */
    double multiplierTo(U otherUnit);

    /**
     * Creates a new measure of this unit with the given value.
     *
     * @param magnitude the magnitude of the measure to create
     */
    @SuppressWarnings("unchecked")
    default Measure<U> of(double magnitude) {
        return new Measure<>(magnitude, (U) this);
    }

    /**
     * The name of this unit, eg "inches", "seconds". This should be a plural.
     */
    String unitName();

}
