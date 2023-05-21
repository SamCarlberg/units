package edu.wpi.first.wpilib.units;

/**
 * A specialization of {@link Measure} that allows for mutability. This is intended to be used for memory use
 * reasons (such as on the memory-restricted RoboRIO 1 or 2 or SBC coprocessors) and should NOT be exposed
 * in the public API for a class that uses it.
 *
 * <p>The advantage of using this class is that only one instance of a measurement object will exist at a time,
 * as opposed to instantiating a new immutable instance every time a value is fetched. This can greatly reduce
 * memory pressure, but comes at the cost of increased code complexity and sensitivity to race conditions if used
 * poorly.
 * </p>
 *
 * @param <U>
 */
public class MutableMeasure<U extends Unit<U>> implements Measure<U> {
  private double magnitude;
  private final U unit;

  public MutableMeasure(double initialMagnitude, U unit) {
    this.magnitude = initialMagnitude;
    this.unit = unit;
  }

  public MutableMeasure(U unit) {
    this(0, unit);
  }

  @Override
  public double magnitude() {
    return magnitude;
  }

  /**
   * Sets the new magnitude of the measurement. The magnitude must be in terms of the {@link #unit()}.
   *
   * @param magnitude the new magnitude of the measurement
   */
  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
  }

  @Override
  public U unit() {
    return unit;
  }

  @Override
  public Measure<U> times(double multiplier) {
    return Measure.of(magnitude * multiplier, unit);
  }

  @Override
  public Measure<U> add(Measure<U> other) {
    return Measure.of(magnitude + other.as(unit), unit);
  }

  @Override
  public Measure<U> negate() {
    return Measure.of(-magnitude, unit);
  }
}
