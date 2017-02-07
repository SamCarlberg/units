package edu.wpi.first.wpilib.units;

import java.util.Objects;

import static edu.wpi.first.wpilib.units.Util.inv;

/**
 * A measure holds the magnitude and unit of some dimension, such as distance, time, or speed. A measure is <i>immutable</i>
 * and <i>type safe</i>, making it easy to use in concurrent situations.
 *
 * @param <U> the unit type of the measure
 */
public class Measure<U extends Enum<U> & Unit<U>> {

    private final double magnitude;
    private final U unit;

    /**
     * @param magnitude the magnitude of this measure
     * @param unit      the unit of this measure
     */
    public Measure(double magnitude, U unit) {
        this.magnitude = magnitude;
        this.unit = unit;
    }

    /**
     * Gets the unitless magnitude of this measure.
     */
    public double magnitude() {
        return magnitude;
    }

    /**
     * Gets the units of this measure.
     */
    public U units() {
        return unit;
    }

    /**
     * @param otherUnit
     *
     * @return
     */
    public Measure<U> as(U otherUnit) {
        if (otherUnit == this.unit) {
            // Same unit (eg inches, seconds, etc). No conversion necessary, and measures are immutable
            // so no need to create a new one.
            return this;
        } else {
            return new Measure<>(this.unit.multiplierTo(otherUnit) * this.magnitude, otherUnit);
        }
    }

    /**
     * Multiplies this measurement by some constant multiplierTo and returns the result.
     *
     * @param multiplier the constant to multiply by
     *
     * @return
     */
    public Measure<U> times(double multiplier) {
        return new Measure<>(this.magnitude * multiplier, this.unit);
    }

    /**
     * Divides this measurement by some constant divisor and returns the result. This is equivalent to
     * {@code divide(1 / divisor)}
     *
     * @param divisor the constant to divide by
     *
     * @return
     */
    public Measure<U> divide(double divisor) {
        return times(inv(divisor));
    }

    /**
     * Adds another measure to this one. The resulting measure has the same unit as this one.
     */
    public Measure<U> add(Measure<U> other) {
        return new Measure<>(this.magnitude + (other.units().multiplierTo(unit) * other.magnitude()), unit);
    }

    /**
     * Subtracts another measure from this one. The resulting measure has the same unit as this one.
     */
    public Measure<U> subtract(Measure<U> other) {
        return new Measure<>(this.magnitude - (other.units().multiplierTo(unit) * other.magnitude()), unit);
    }

    /**
     * Checks if this measure is equivalent to another. Two measures are equivalent if the base unit
     * type is the same (eg distance, time, ...) and have equal magnitude if converted to the same
     *
     * @param other the measure to compare to.
     *
     * @return true if this measurement is equivalent to the given one, false if not
     */
    public boolean isEquivalent(Measure<U> other) {
        return this.unit.multiplierTo(other.units()) * this.magnitude == other.magnitude();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Measure<?> m = (Measure<?>) obj;
        return m.units() == this.unit
                && m.magnitude() == this.magnitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(magnitude, unit);
    }

    @Override
    public String toString() {
        return String.format("%s %s", magnitude, unit.unitName());
    }

}
