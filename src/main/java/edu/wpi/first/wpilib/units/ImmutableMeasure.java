package edu.wpi.first.wpilib.units;

import java.util.Objects;

/**
 * A measure holds the magnitude and unit of some dimension, such as distance, time, or speed. A measure is <i>immutable</i>
 * and <i>type safe</i>, making it easy to use in concurrent situations and gives compile-time safety. Two measures
 * with the same <i>unit</i> and <i>magnitude</i> are effectively the same object.
 *
 * @param <U> the unit type of the measure
 */
public class ImmutableMeasure<U extends Unit<U>> implements Measure<U> {

  private final double magnitude;
  private final U unit;

  /**
   * @param magnitude the magnitude of this measure
   * @param unit      the unit of this measure.
   */
  @SuppressWarnings("unchecked")
  public ImmutableMeasure(double magnitude, Unit<U> unit) {
    Objects.requireNonNull(unit, "Unit cannot be null");
    this.magnitude = magnitude;
    this.unit = (U) unit;
  }

  /**
   * Gets the unitless magnitude of this measure.
   */
  @Override
  public double magnitude() {
    return magnitude;
  }

  /**
   * Gets the units of this measure.
   */
  @Override
  public U unit() {
    return unit;
  }

  /**
   * Multiplies this measurement by some constant multiplier and returns the result.
   *
   * @param multiplier the constant to multiply by
   */
  @Override
  public Measure<U> times(double multiplier) {
    return Measure.of(magnitude * multiplier, unit);
  }

  /**
   * Adds another measure to this one. The resulting measure has the same unit as this one.
   */
  @Override
  public Measure<U> add(Measure<U> other) {
    return Measure.of(magnitude + other.in(this.unit), unit);
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
    Measure<?> that = (Measure<?>) obj;
    return Objects.equals(unit, that.unit()) && this.isEquivalent(that);
  }

  @Override
  public int hashCode() {
    return Objects.hash(magnitude, unit);
  }

  @Override
  public Measure<U> copy() {
    return this; // already immutable, no need to allocate a new object
  }

  @Override
  public String toString() {
    return toShortString();
  }
}
