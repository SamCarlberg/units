package edu.wpi.first.wpilib.units;

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

  default double as(Unit<U> unit) {
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
  Measure<U> times(double multiplier);

  default Measure<U> times(Measure<? extends Unitless> scalar) {
    return times(scalar.baseUnitMagnitude());
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

  default Measure<U> divide(Measure<? extends Unitless> scalar) {
    return divide(scalar.baseUnitMagnitude());
  }

  /**
   * Adds another measure to this one. The resulting measure has the same unit as this one.
   */
  Measure<U> add(Measure<U> other);

  default Measure<U> subtract(Measure<U> other) {
    return add(other.negate());
  }

  /**
   * Negates this measure and returns the result.
   */
  Measure<U> negate();

  /**
   * Gets the magnitude of this measure in terms of the base unit.
   */
  default double baseUnitMagnitude() {
    return unit().getConverterToBase().apply(magnitude());
  }

  default boolean isEquivalent(Measure<U> other) {
    return Math.abs(baseUnitMagnitude() - other.baseUnitMagnitude()) <= EQUIVALENCE_THRESHOLD;
  }

  @Override
  default int compareTo(Measure<U> o) {
    return Double.compare(this.baseUnitMagnitude(), o.baseUnitMagnitude());
  }
}
