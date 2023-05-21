package edu.wpi.first.wpilib.units;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

public final class Units {

  private Units() {
    // Prevent instantiation
  }

  // Pseudo-classes describing the more common units of measure.

  // Distance
  public static final Distance Meters = BaseUnits.Distance;
  public static final Distance Millimeters = Milli(Meters);
  public static final Distance Centimeters = Meters.splitInto(100);
  public static final Distance Inches = Meters.splitInto(39.3701);
  public static final Distance Feet = Inches.aggregate(12);

  // Time
  public static final Time Seconds = BaseUnits.Time;
  public static final Time Milliseconds = Milli(Seconds);
  public static final Time Minutes = Seconds.aggregate(60);

  // Velocity
  public static final Velocity MetersPerSecond = BaseUnits.Velocity;
  public static final Velocity FeetPerSecond = Feet.per(Seconds);
  public static final Velocity InchesPerSecond = Inches.per(Seconds);

  // Acceleration
  public static final Acceleration MetersPerSecondPerSecond = BaseUnits.Acceleration;
  public static final Acceleration Gs = MetersPerSecondPerSecond.aggregate(9.807);

  // Mass
  public static final Mass Grams = BaseUnits.Mass;
  public static final Mass Kilograms = Kilo(Grams);
  public static final Mass Pounds = Grams.aggregate(453.592);
  public static final Mass Ounces = Pounds.splitInto(16);

  // Angle
  public static final Angle Revolutions = BaseUnits.Angle;
  public static final Angle Radians = Revolutions.splitInto(2 * Math.PI);
  public static final Angle Degrees = Revolutions.splitInto(360);

  // Unitless
  public static final Unitless Value = BaseUnits.Value;
  public static final Unitless Percent = Value.splitInto(100);

  // Electric potential
  public static final ElectricPotential Volts = BaseUnits.ElectricPotential;
  public static final ElectricPotential Millivolts = Milli(Volts);

  // Electric current
  public static final ElectricCurrent Amps = BaseUnits.ElectricCurrent;
  public static final ElectricCurrent Milliamps = Milli(Amps);

  // Power
  public static final Power Watts = BaseUnits.Power;
  public static final Power Milliwatts = Milli(Watts);
  public static final Power Horsepower = Watts.aggregate(745.7);

  // Temperature
  public static Temperature Celsius = BaseUnits.Temperature;
//  public static Temperature Fahrenheit =
//      derive(Celsius)
//          .toBase(f -> (f - 32) * 5 / 9.)
//          .fromBase(c -> (c * 9. / 5) + 32)
//          .make();

  /**
   * Creates a unit equal to a thousandth of the base unit, eg Milliseconds = Milli(Units.Seconds).
   */
  public static <U extends Unit<U>> U Milli(Unit<U> baseUnit) {
    return baseUnit.splitInto(1000);
  }

  /**
   * Creates a unit equal to a thousand of the base unit, eg Kilograms = Kilo(Units.Grams).
   */
  public static <U extends Unit<U>> U Kilo(Unit<U> baseUnit) {
    return baseUnit.aggregate(1000);
  }

  @SuppressWarnings("unchecked")
  public static <U extends Unit<U>> UnitBuilder<U> derive(Unit<U> unit) {
    return new UnitBuilder<>((U) unit);
  }

  public static final class UnitBuilder<U extends Unit<U>> {

    private U base;
    private DoubleUnaryOperator fromBase;
    private DoubleUnaryOperator toBase;

    private UnitBuilder(U base) {
      this.base = Objects.requireNonNull(base, "Base unit cannot be null");
    }

    public UnitBuilder<U> fromBase(DoubleUnaryOperator fromBase) {
      this.fromBase = Objects.requireNonNull(fromBase, "fromBase function cannot be null");
      return this;
    }

    public UnitBuilder<U> toBase(DoubleUnaryOperator toBase) {
      this.toBase = Objects.requireNonNull(toBase, "toBase function cannot be null");
      return this;
    }

    public Unit<U> make() {
      Objects.requireNonNull(fromBase, "fromBase function was not set");
      Objects.requireNonNull(toBase, "toBase function was not set");
      return new Unit<>(base.baseType, base.getConverterFromBase().andThen(fromBase), base.getConverterToBase().andThen(toBase));
    }

  }

}
