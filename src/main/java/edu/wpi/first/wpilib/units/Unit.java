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
    return otherUnit.toBaseConverter.andThen(this.fromBaseConverter).applyAsDouble(value);
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
   * is equivalent to one of the new unit. For example, {@code Unit km = Meters.aggregate(1000)}
   *
   * @param amount the magnitude of a measure of this unit to be equivalent to a measure of magnitude 1 of
   *               the resulting unit. For example, {@code Seconds.aggregate(60)} would result in minutes.
   *
   * @see #splitInto(double)
   */
  public Unit<U> aggregate(double amount) {
    if (amount == 1) {
      // Same units, just reuse this object.
      return this;
    }
    return new Unit<>(this.toBaseConverter.andThen(x -> x * amount),
        this.fromBaseConverter.andThen(x -> x / amount));
  }

  /**
   * Creates a new unit based off this one, where <i>scale</i> amount of the new new unit
   * is equivalent to one if this unit. For example. {@code Unit mm = Meters.splitInto(1000)}
   *
   * @param amount the magnitude of a measure of the resulting unit to be equivalent to a measure of magnitude 1 of
   *               this unit. For example, {@code Feet.splitInto(12)} would result in inches.
   *
   * @see #aggregate(double)
   */
  public Unit<U> splitInto(double amount) {
    return aggregate(1 / amount);
  }

}
