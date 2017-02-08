package edu.wpi.first.wpilib.units;

import java.util.Objects;

/**
 * A measure holds the magnitude and unit of some dimension, such as distance, time, or speed. A measure is <i>immutable</i>
 * and <i>type safe</i>, making it easy to use in concurrent situations and gives compile-time safety. Two measures
 * with the same <i>unit</i> and <i>magnitude</i> are effectively the same object.
 *
 * @param <U> the unit type of the measure
 */
public class Measure<U extends Unit<U>> implements Comparable<Measure<U>> {

    /**
     * The threshold for two measures to be considered equivalent if converted to the same unit.
     * This is only needed due to floating-point error.
     */
    public static final double EQUIVALENCE_THRESHOLD = 1e-12;

    private final double magnitude;
    private final U unit;

    /**
     * @param magnitude the magnitude of this measure
     * @param unit      the unit of this measure.
     */
    @SuppressWarnings("unchecked")
    public Measure(double magnitude, Unit<U> unit) {
        Objects.requireNonNull(unit, "Unit cannot be null");
        this.magnitude = magnitude;
        this.unit = (U) unit;
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
    public U unit() {
        return unit;
    }

    /**
     * Converts this measure to a measure with a different unit of the same type, eg minutes to seconds.
     *
     * @param unit the unit to convert this measure to
     *
     * @return the value of this measure in the given unit
     */
    @SuppressWarnings("unchecked") // U == Unit<U> by design, so casting is OK
    public double as(Unit<U> unit) {
        if (unit == this.unit) {
            // Same unit (eg inches, seconds, etc). No conversion necessary.
            return magnitude;
        } else {
            return this.unit.convert(magnitude, unit);
        }
    }

    /**
     * Multiplies this measurement by some constant multiplier and returns the result.
     *
     * @param multiplier the constant to multiply by
     */
    public Measure<U> times(double multiplier) {
        return new Measure<>(magnitude * multiplier, unit);
    }

    /**
     * Divides this measurement by some constant divisor and returns the result. This is equivalent to
     * {@code times(1 / divisor)}
     *
     * @param divisor the constant to divide by
     *
     * @see #times(double)
     */
    public Measure<U> divide(double divisor) {
        return times(1 / divisor);
    }

    /**
     * Adds another measure to this one. The resulting measure has the same unit as this one.
     */
    public Measure<U> add(Measure<U> other) {
        return new Measure<>(magnitude + other.unit().convert(other.magnitude(), unit), unit);
    }

    /**
     * Subtracts another measure from this one. The resulting measure has the same unit as this one.
     */
    public Measure<U> subtract(Measure<U> other) {
        return add(other.negate());
    }

    /**
     * Negates this measure an returns the result.
     */
    public Measure<U> negate() {
        return new Measure<>(-magnitude, unit);
    }

    /**
     * Gets the magnitude of this measure in terms of the base unit.
     */
    public double baseUnitMagnitude() {
        return magnitude * unit.getBaseUnitEquivalent();
    }

    /**
     * Checks if this measure is equivalent to another. Two measures are equivalent if the base unit
     * type is the same (eg distance, time, ...) and have equal magnitude if converted to the same unit.
     *
     * @param other the measure to compare to.
     *
     * @return true if this measurement is equivalent to the given one, false if not
     */
    public boolean isEquivalent(Measure<U> other) {
        return Math.abs(baseUnitMagnitude() - other.baseUnitMagnitude()) <= EQUIVALENCE_THRESHOLD;
    }

    /**
     * Checks for <i>object equality</i>. To check if two measures are <i>equivalent</i>,
     * use {@link #isEquivalent(Measure) isEquivalent}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Measure<?> m = (Measure<?>) obj;
        return m.unit() == this.unit
                && m.magnitude() == this.magnitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(magnitude, unit);
    }

    @Override
    public int compareTo(Measure<U> o) {
        return Double.compare(this.baseUnitMagnitude(), o.baseUnitMagnitude());
    }

}
