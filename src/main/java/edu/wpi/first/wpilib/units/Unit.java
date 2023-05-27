package edu.wpi.first.wpilib.units;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
/**
 * A unit is some unit of measurement that defines a quantity, such as grams, meters, or seconds.
 *
 * @param <U> the self type, eg {@code class SomeUnit extends Unit<SomeUnit>}
 */
public class Unit<U extends Unit<U>> {

  private final UnaryFunction toBaseConverter;
  private final UnaryFunction fromBaseConverter;

  final Class<? extends U> baseType; // package-private for the builder

  private Measure<U> zero;
  private Measure<U> one;

  /**
   * Creates a new unit defined by its relationship to some base unit.
   *
   * @param baseType
   * @param toBaseConverter   a function for converting units of this type to the base unit
   * @param fromBaseConverter a function for converting units of the base unit to this one
   */
  protected Unit(Class<? extends U> baseType, UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    this.baseType = baseType;
    this.toBaseConverter = Objects.requireNonNull(toBaseConverter);
    this.fromBaseConverter = Objects.requireNonNull(fromBaseConverter);
  }

  /**
   * Creates a new unit with the given name and multiplier to the base unit.
   *
   * @param baseType
   * @param baseUnitEquivalent the multiplier to convert this unit to the base unit of this type. For example,
   *                           meters has a multiplier of 1, mm has a multiplier of 1e3, and km has a multiplier of 1e-3.
   */
  protected Unit(Class<? extends U> baseType, double baseUnitEquivalent) {
    this(baseType, x -> x * baseUnitEquivalent, x -> x / baseUnitEquivalent);
  }

  /**
   * Converts a value in terms of base units to a value in terms of this unit.
   *
   * @param valueInBaseUnits the value in base units to convert
   *
   * @return the equivalent value in terms of this unit
   */
  public double fromBaseUnits(double valueInBaseUnits) {
    return fromBaseConverter.apply(valueInBaseUnits);
  }

  /**
   * Converts a value in terms of this unit to a value in terms of the base unit.
   *
   * @param valueInNativeUnits the value in terms of this unit to convert
   *
   * @return the equivalent value in terms of the base unit
   */
  public double toBaseUnits(double valueInNativeUnits) {
    return toBaseConverter.apply(valueInNativeUnits);
  }

  /**
   * Converts a value of this unit to a value of another unit of the same type.
   *
   * @param value     a value measured in this unit
   * @param otherUnit the unit to convert the value to
   */
  public double convert(double value, Unit<U> otherUnit) {
    return this.fromBaseUnits(otherUnit.toBaseUnits(value));
  }

  public UnaryFunction getConverterToBase() {
    return toBaseConverter;
  }

  public UnaryFunction getConverterFromBase() {
    return fromBaseConverter;
  }

  /**
   * Creates a new measure of this unit with the given value.
   *
   * @param magnitude the magnitude of the measure to create
   */
  public Measure<U> of(double magnitude) {
    if (magnitude == 0) return zero();
    if (magnitude == 1) return one();
    return Measure.of(magnitude, this);
  }

  public Measure<U> zero() {
    // lazy init because 'this' is null in object initialization
    if (zero == null) zero = new ImmutableMeasure<>(0, this);
    return zero;
  }

  public Measure<U> one() {
    // lazy init because 'this' is null in object initialization
    if (one == null) one = new ImmutableMeasure<>(1, this);
    return one;
  }

  /**
   * Creates a new unit based off this one, where <i>scale</i> amount of this unit
   * is equivalent to one of the new unit. For example, {@code Unit km = Meters.aggregate(1000)}
   *
   * @param amount the magnitude of a measure of this unit to be equivalent to a measure of magnitude 1 of
   *               the resulting unit. For example, {@code Seconds.aggregate(60)} would result in minutes.
   * @see #splitInto(double)
   */
  public U aggregate(double amount) {
    if (amount == 1) {
      // Same units, just reuse this object.
      return (U) this;
    }
    Constructor<? extends U> ctor = null;

    try {
      ctor = baseType.getDeclaredConstructor(UnaryFunction.class, UnaryFunction.class);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(
          "Base unit type " + baseType.getName() + " does not have a constructor that takes base unit conversion functions",
          e
      );
    }

    try {
      return ctor.newInstance(
          this.toBaseConverter.pipeTo(x -> x * amount),
          this.fromBaseConverter.pipeTo(x -> x / amount)
      );
    } catch (InstantiationException e) {
      throw new RuntimeException("Class " + baseType.getName() + " cannot be instantiated", e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Could not access constructor " + ctor.getName(), e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException("Constructor " + ctor.getName() + " raised an error when called!", e);
    }
  }

  /**
   * Creates a new unit based off this one, where <i>scale</i> amount of the new new unit
   * is equivalent to one if this unit. For example. {@code Unit mm = Meters.splitInto(1000)}
   *
   * @param amount the magnitude of a measure of the resulting unit to be equivalent to a measure of magnitude 1 of
   *               this unit. For example, {@code Feet.splitInto(12)} would result in inches.
   * @see #aggregate(double)
   */
  public U splitInto(double amount) {
    return aggregate(1 / amount);
  }

  /**
   * Creates a velocity unit derived from this one. Can be chained to denote velocity, acceleration, jerk, etc.
   *
   * <pre>
   *   Meters.per(Second) // => linear velocity
   *   Kilograms.per(Second) // => mass flow
   *   Feet.per(Second).per(Second).of(32) // roughly 1G of acceleration
   * </pre>
   *
   * @param period the time period of the velocity, such as seconds or milliseconds
   * @return a
   */
  public Velocity<U> per(Time period) {
    return Velocity.combine(this, period);
  }

  /**
   * Takes this unit and creates a new proportional unit where this unit is the numerator and the given
   * denominator is the denominator.
   *
   * <pre>
   *   Volts.per(Meter) // V/m
   * </pre>
   *
   * @param denominator the denominator of the proportional unit
   * @return a combined proportional unit
   * @param <D> the type of the denominator units
   */
  public <D extends Unit<D>> Per<U, D> per(D denominator) {
    return Per.combine(this, denominator);
  }

  /**
   * <pre>
   *   Volts.mult(Meter) // V*m
   * </pre>
   * @param other the unit to multiply by
   * @return a combined unit equivalent to this unit multiplied by the other
   * @param <U2> the type of the unit to multiply by
   */
  public <U2 extends Unit<U2>> Mult<U, U2> mult(U2 other) {
    return Mult.combine((U) this, other);
  }

  public boolean equivalent(Unit<?> other) {
    // different unit types, not compatible
    if (this.baseType != other.baseType) return false;

    double arbitrary = 16_777.214; // 2^24 / 1e3

    return Math.abs(this.fromBaseConverter.apply(arbitrary) - other.fromBaseConverter.apply(arbitrary)) <= Measure.EQUIVALENCE_THRESHOLD &&
        Math.abs(this.toBaseConverter.apply(arbitrary) - other.toBaseConverter.apply(arbitrary)) <= Measure.EQUIVALENCE_THRESHOLD;
  }
}
