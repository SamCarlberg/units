package edu.wpi.first.wpilib.units;

import java.text.DecimalFormat;

/**
 * A measure holds the magnitude and unit of some dimension, such as distance, time, or speed. Two measures
 * with the same <i>unit</i> and <i>magnitude</i> are effectively the same object.
 *
 * @param <U> the unit type of the measure
 */
public interface Measure<U extends Unit<U>> extends Comparable<Measure<U>> {
  /**
   * The threshold for two measures to be considered equivalent if converted to the same unit.
   * This is only needed due to floating-point error.
   */
  double EQUIVALENCE_THRESHOLD = 1e-12;

  /**
   * A zero-valued measure on its base units of measurement. For non-scalar unit types like temperature,
   * this is equivalent to <i>absolute zero</i>.
   */
  Measure ZERO = Measure.of(0, Units.AnonymousBaseUnit);

  /**
   * A zero-valued measure on its base units of measurement. For non-scalar unit types like temperature,
   * this is equivalent to <i>absolute zero</i>.
   */
  Measure ONE = Measure.of(1, Units.AnonymousBaseUnit);

  /**
   * A zero-valued measure on its base units of measurement. For non-scalar unit types like temperature,
   * this is equivalent to <i>absolute zero</i>.
   *
   * <pre>
   *   Feet.of(0).isEquivalent(Measure.zero())
   *   Celsius.of(-273.15).isEquivalent(Measure.zero())
   *   Millimeters.per(Second).of(0).isEquivalent(Measure.zero())
   * </pre>
   */
  static <U extends Unit<U>> Measure<U> zero() {
    return ZERO;
  }

  static <U extends Unit<U>> Measure<U> one() {
    return ONE;
  }

  /**
   * Creates a new measure object with the given magnitude and unit.
   */
  static <U extends Unit<U>> Measure<U> of(double magnitude, Unit<U> unit) {
    return new ImmutableMeasure<>(magnitude, unit);
  }

  /**
   * Gets the unitless magnitude of this measure.
   */
  double magnitude();

  /**
   * Gets the units of this measure.
   */
  U unit();

  /**
   * Converts this measure to a measure with a different unit of the same type, eg minutes to seconds.
   *
   * <pre>
   *   Meters.of(12).in(Feet) // => 39.3701
   *   Seconds.of(15).in(Minutes) // => 0.25
   * </pre>
   *
   * @param unit the unit to convert this measure to
   *
   * @return the value of this measure in the given unit
   */
  default double in(U unit) {
    if (this.unit() == unit) {
      return magnitude();
    } else {
      return unit.convert(magnitude(), this.unit());
    }
  }

  /**
   * Multiplies this measurement by some constant multiplier and returns the result.
   *
   * @param multiplier the constant to multiply by
   */
  default Measure<U> times(double multiplier) {
    return Measure.of(magnitude() * multiplier, unit());
  }

  @SuppressWarnings("unchecked")
  default <U2 extends Unit<U2>, R extends Unit<R>> Measure<R> times(Measure<U2> other) {
    if (other.unit() instanceof Unitless) {
      // scalar multiplication
      return (Measure<R>) times(other.baseUnitMagnitude());
    }

    if (unit() instanceof Per && other.unit().baseType.equals(((Per<?, ?>) unit()).denominator().baseType)) {
      // denominator of the Per cancels out, return with just the units of the numerator
      Unit<?> numerator = ((Per<?, ?>) unit()).numerator();
      return (Measure<R>) numerator.ofBaseUnits(baseUnitMagnitude() * other.baseUnitMagnitude());
    } else if (unit() instanceof Velocity && other.unit().baseType.equals(Time.class)) {
      // Multiplying a velocity by a time, return the scalar unit (eg Distance)
      Unit<?> numerator = ((Velocity<?>) unit()).getUnit();
      return (Measure<R>) numerator.ofBaseUnits(baseUnitMagnitude() * other.baseUnitMagnitude());
    } else if (unit() instanceof Per &&
        other.unit() instanceof Per &&
        ((Per<U, ?>) unit()).denominator().baseType.equals(((Per<?, U>) other.unit()).numerator().baseType) &&
        ((Per<?, U>) unit()).numerator().baseType.equals(((Per<U, ?>) other.unit()).denominator().baseType)) {
      // multiplying eg meters per second * milliseconds per foot
      // return a scalar
      return (Measure<R>) Units.Value.of(baseUnitMagnitude() * other.baseUnitMagnitude());
    }

    return (Measure<R>) unit().mult(other.unit()).ofBaseUnits(baseUnitMagnitude() * other.baseUnitMagnitude());
  }

  /**
   * Divides this measurement by some constant divisor and returns the result. This is equivalent to
   * {@code times(1 / divisor)}
   *
   * @param divisor the constant to divide by
   *
   * @see #times(double)
   */
  default Measure<U> divide(double divisor) {
    return times(1 / divisor);
  }

  default Measure<U> divide(Measure<Unitless> scalar) {
    return divide(scalar.baseUnitMagnitude());
  }

  default Measure<Velocity<U>> per(Measure<Time> period) {
    var newUnit = unit().per(period.unit());
    return Measure.of(magnitude() / period.magnitude(), unit().per(period.unit()));
  }

  default <U2 extends Unit<U2>> Measure<Per<U, U2>> per(U2 denominator) {
    var newUnit = unit().per(denominator);
    return Measure.of(magnitude(), newUnit);
  }

  /**
   * Adds another measure to this one. The resulting measure has the same unit as this one.
   */
  default Measure<U> add(Measure<U> other) {
    return Measure.of(magnitude() + other.in(unit()), unit());
  }

  default Measure<U> subtract(Measure<U> other) {
    // delegate implementation to `add`
    return add(other.negate());
  }

  /**
   * Negates this measure and returns the result.
   */
  default Measure<U> negate() {
    return times(-1);
  }

  /**
   * Returns an immutable copy of this measure. The copy can be used freely and is guaranteed never to change.
   */
  Measure<U> copy();

  /**
   * Gets the magnitude of this measure in terms of the base unit.
   */
  default double baseUnitMagnitude() {
    return unit().getConverterToBase().apply(magnitude());
  }

  /**
   * <pre>
   *   Inches.of(11).isNear(Inches.of(10), 0.1) // => true
   *   Inches.of(12).isNear(Inches.of(10), 0.1) // => false
   * </pre>
   * @param other the other measurement to compare against
   * @param varianceThreshold the acceptable variance threshold, in terms of an acceptable +/- error range multiplier.
   *                          Checking if a value is within 10% means a value of 0.1 should be passed; checking if
   *                          a value is within 1% means a value of 0.01 should be passed, and so on.
   * @return
   */
  default boolean isNear(Measure<U> other, double varianceThreshold) {
    var allowedVariance = Math.abs(varianceThreshold); // abs so negative inputs are calculated correctly
    return other.baseUnitMagnitude() * (1 - allowedVariance) <= this.baseUnitMagnitude() &&
        other.baseUnitMagnitude() * (1 + allowedVariance) >= this.baseUnitMagnitude();
  }

  /**
   * Checks if this measure is equivalent to another measure of the same unit.
   *
   * @param other the measure to compare to
   *
   * @return
   */
  default boolean isEquivalent(Measure<?> other) {
    if (this.unit().baseType != other.unit().baseType) return false; // Disjoint units, not compatible

    return Math.abs(baseUnitMagnitude() - other.baseUnitMagnitude()) <= EQUIVALENCE_THRESHOLD;
  }

  @Override
  default int compareTo(Measure<U> o) {
    return Double.compare(this.baseUnitMagnitude(), o.baseUnitMagnitude());
  }

  /**
   * Checks if this measure is greater than another measure of the same unit.
   * @param o the other measure to compare to
   */
  default boolean gt(Measure<U> o) {
    return compareTo(o) > 0;
  }

  /**
   * Checks if this measure is greater than or equivalent to another measure of the same unit.
   * @param o the other measure to compare to
   */
  default boolean gte(Measure<U> o) {
    return (compareTo(o) > 0) || isEquivalent(o);
  }

  /**
   * Checks if this measure is less than another measure of the same unit.
   * @param o the other measure to compare to
   */
  default boolean lt(Measure<U> o) {
    return compareTo(o) < 0;
  }

  /**
   * Checks if this measure is less than or equivalent to than another measure of the same unit.
   * @param o the other measure to compare to
   */
  default boolean lte(Measure<U> o) {
    return (compareTo(o) < 0) || isEquivalent(o);
  }

  /**
   * Returns the measure with the absolute value closest to positive infinity.
   *
   * @param measures the set of measures to compare
   * @return
   * @param <U>
   */
  static <U extends Unit<U>> Measure<U> max(Measure<U>... measures) {
    if (measures.length == 0) return null; // nothing to compare

    Measure<U> max = null;
    for (Measure<U> measure : measures) {
      if (max == null || measure.gt(max)) {
        max = measure;
      }
    }

    return max;
  }

  /**
   * Returns the measure with the absolute value closest to negative infinity.
   *
   * @param measures the set of measures to compare
   * @return
   * @param <U>
   */
  static <U extends Unit<U>> Measure<U> min(Measure<U>... measures) {
    if (measures.length == 0) return null; // nothing to compare

    Measure<U> max = null;
    for (Measure<U> measure : measures) {
      if (max == null || measure.lt(max)) {
        max = measure;
      }
    }

    return max;
  }

  /**
   * Returns a string representation of this measurement in a shorthand form.
   * The symbol of the backing unit is used, rather than the full name, and
   * the magnitude is represented in scientific notation.
   *
   * @return the short form representation of this measurement
   */
  default String toShortString() {
    // eg 1.234e+04 V/m (1234 Volt per Meter in long form)
    return String.format("%.3e %s", magnitude(), unit().symbol());
  }

  /**
   * Returns a string representation of this measurement in a longhand form.
   * The name of the backing unit is used, rather than its symbol, and
   * the magnitude is represented in a full string, no scientific notation.
   * (Very large values may be represented in scientific notation, however)
   *
   * @return the long form representation of this measurement
   */
  default String toLongString() {
    // eg 1234 Volt per Meter (1.234e+04 V/m in short form)
    return String.format("%s %s", magnitude(), unit().name());
  }
}
