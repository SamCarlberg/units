package edu.wpi.first.wpilib.units;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
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
  public static final Time Second = Seconds; // singularized alias
  public static final Time Milliseconds = Milli(Seconds);
  public static final Time Millisecond = Milliseconds; // singularized alias
  public static final Time Minutes = Seconds.aggregate(60);
  public static final Time Minute = Minutes; // singularized alias

  // Velocity
  public static final Velocity<Distance> MetersPerSecond = Meters.per(Second);
  public static final Velocity<Distance> FeetPerSecond = Feet.per(Second);
  public static final Velocity<Distance> InchesPerSecond = Inches.per(Second);

  // Acceleration
  public static final Velocity<Velocity<Distance>> MetersPerSecondPerSecond = MetersPerSecond.per(Second);
  public static final Velocity<Velocity<Distance>> Gs = MetersPerSecondPerSecond.aggregate(9.807);

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
  public static final Temperature Kelvin = BaseUnits.Temperature;
  public static final Temperature Celsius =
      derive(Kelvin)
          .offset(+273.15)
          .make();

  public static final Temperature Fahrenheit =
      derive(Celsius)
          .mappingInputRange(0, 100).toOutputRange(32, 212)
          .make();

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

    private final U base;
    private UnaryFunction fromBase;
    private UnaryFunction toBase;

    private UnitBuilder(U base) {
      this.base = Objects.requireNonNull(base, "Base unit cannot be null");
    }

    /**
     * Sets the unit conversions based on a simple offset. The new unit will have its values equal to
     * (base value - offset).
     *
     * @param offset the offset
     */
    public UnitBuilder<U> offset(double offset) {
      toBase = derivedValue -> derivedValue + offset;
      fromBase = baseValue -> baseValue - offset;
      return this;
    }

    class MappingBuilder {
      private final double minInput, maxInput;

      MappingBuilder(double minInput, double maxInput) {
        this.minInput = minInput;
        this.maxInput = maxInput;
      }

      static double mapValue(double value, double inMin, double inMax, double outMin, double outMax) {
        return (value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
      }

      UnitBuilder<U> toOutputRange(double minOutput, double maxOutput) {
        UnitBuilder.this.fromBase = x -> mapValue(x, minInput, maxInput, minOutput, maxOutput);
        UnitBuilder.this.toBase = y -> mapValue(y, minOutput, maxOutput, minInput, maxInput);
        return UnitBuilder.this;
      }
    }

    public MappingBuilder mappingInputRange(double minBase, double maxBase) {
      return new MappingBuilder(minBase, maxBase);
    }

    public UnitBuilder<U> fromBase(UnaryFunction fromBase) {
      this.fromBase = Objects.requireNonNull(fromBase, "fromBase function cannot be null");
      return this;
    }

    public UnitBuilder<U> toBase(UnaryFunction toBase) {
      this.toBase = Objects.requireNonNull(toBase, "toBase function cannot be null");
      return this;
    }

    public U make() {
      Objects.requireNonNull(fromBase, "fromBase function was not set");
      Objects.requireNonNull(toBase, "toBase function was not set");
      try {
        Constructor<U> ctor = base.baseType.getDeclaredConstructor(UnaryFunction.class, UnaryFunction.class);
        return (U) ctor.newInstance(
            toBase.pipeTo(base.getConverterToBase()), // (derived) -> derived to derivation base, then to true base
            base.getConverterFromBase().pipeTo(fromBase) // (base) -> true base to derivation base, then to derived
        );
      } catch (InstantiationException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }

  }

}
