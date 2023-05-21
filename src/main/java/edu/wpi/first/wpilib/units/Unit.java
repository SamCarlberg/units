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

  final Class<U> baseType; // package-private for the builder

  /**
   * Creates a new unit defined by its relationship to some base unit.
   *
   * @param baseType
   * @param toBaseConverter   a function for converting units of this type to the base unit
   * @param fromBaseConverter a function for converting units of the base unit to this one
   */
  protected Unit(Class<U> baseType, UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
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
  protected Unit(Class<U> baseType, double baseUnitEquivalent) {
    this(baseType, x -> x * baseUnitEquivalent, x -> x / baseUnitEquivalent);
  }

  /**
   * Converts a value of this unit to a value of another unit of the same type.
   *
   * @param value     a value measured in this unit
   * @param otherUnit the unit to convert the value to
   */
  public double convert(double value, Unit<U> otherUnit) {
    return otherUnit.toBaseConverter.pipeTo(this.fromBaseConverter).apply(value);
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
    return Measure.of(magnitude, this);
  }

  /**
   * Creates a new unit based off this one, where <i>scale</i> amount of this this unit
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
    Constructor<U> ctor = null;

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

  public boolean equivalent(Unit<U> other) {
    return Math.abs(this.fromBaseConverter.apply(1) - other.fromBaseConverter.apply(1)) <= Measure.EQUIVALENCE_THRESHOLD &&
        Math.abs(this.toBaseConverter.apply(1) - other.toBaseConverter.apply(1)) <= Measure.EQUIVALENCE_THRESHOLD;
  }

}
