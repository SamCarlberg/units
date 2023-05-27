package edu.wpi.first.wpilib.units;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Objects;
public final class Units {

  private Units() {
    // Prevent instantiation
  }

  // Pseudo-classes describing the more common units of measure.

  public static final Unit AnonymousBaseUnit = new Unitless(1, "<?>", "<?>");

  // Distance
  public static final Distance Meters = BaseUnits.Distance;
  public static final Distance Millimeters = Milli(Meters, "Millimeter", "mm");
  public static final Distance Centimeters = derive(Meters).splitInto(100).named("Centimeter").symbol("cm").make();
  public static final Distance Inches = derive(Millimeters).aggregate(25.4).named("Inch").symbol("in").make();
  public static final Distance Feet = derive(Inches).aggregate(12).named("Foot").symbol("ft").make();

  // Time
  public static final Time Seconds = BaseUnits.Time;
  public static final Time Second = Seconds; // singularized alias
  public static final Time Milliseconds = Milli(Seconds);
  public static final Time Millisecond = Milliseconds; // singularized alias
  public static final Time Minutes = derive(Seconds).aggregate(60).named("Minute").symbol("min").make();
  public static final Time Minute = Minutes; // singularized alias

  // Velocity
  public static final Velocity<Distance> MetersPerSecond = Meters.per(Second);
  public static final Velocity<Distance> FeetPerSecond = Feet.per(Second);
  public static final Velocity<Distance> InchesPerSecond = Inches.per(Second);

  // Acceleration
  public static final Velocity<Velocity<Distance>> MetersPerSecondPerSecond = MetersPerSecond.per(Second);
  public static final Velocity<Velocity<Distance>> Gs = derive(MetersPerSecondPerSecond).aggregate(9.807).named("G").symbol("G").make();

  // Mass
  public static final Mass Kilograms = BaseUnits.Mass;
  public static final Mass Grams = Milli(Kilograms, "Gram", "g");
  public static final Mass Pounds = derive(Grams).aggregate(453.592).named("Pound").symbol("lb.").make();
  public static final Mass Ounces = derive(Pounds).splitInto(16).named("Ounce").symbol("oz.").make();

  // Angle
  public static final Angle Revolutions = BaseUnits.Angle;
  public static final Angle Radians = derive(Revolutions).splitInto(2 * Math.PI).named("Radian").symbol("rad").make();
  public static final Angle Degrees = derive(Revolutions).splitInto(360).named("Degree").symbol("°").make();

  // Unitless
  public static final Unitless Value = BaseUnits.Value;
  public static final Unitless Percent = derive(Value).splitInto(100).named("Percent").symbol("%").make();

  // Electric potential
  public static final ElectricPotential Volts = BaseUnits.ElectricPotential;
  public static final ElectricPotential Millivolts = Milli(Volts);

  // Electric current
  public static final ElectricCurrent Amps = BaseUnits.ElectricCurrent;
  public static final ElectricCurrent Milliamps = Milli(Amps);

  // Energy
  public static final Energy Joules = BaseUnits.Energy;
  public static final Energy Millijoules = Milli(Joules);
  public static final Energy Kilojoules = Kilo(Joules);

  // Power
  public static final Power Watts = BaseUnits.Power;
  public static final Power Milliwatts = Milli(Watts);
  public static final Power Horsepower = derive(Watts).aggregate(745.7).named("Horsepower").symbol("HP").make();

  // Temperature
  public static final Temperature Kelvin = BaseUnits.Temperature;
  public static final Temperature Celsius =
      derive(Kelvin)
          .offset(+273.15)
          .named("Celsius")
          .symbol("°C")
          .make();

  public static final Temperature Fahrenheit =
      derive(Celsius)
          .mappingInputRange(0, 100).toOutputRange(32, 212)
          .named("Fahrenheit")
          .symbol("°F")
          .make();

  /**
   * Creates a unit equal to a thousandth of the base unit, eg Milliseconds = Milli(Units.Seconds).
   */
  public static <U extends Unit<U>> U Milli(Unit<U> baseUnit, String name, String symbol) {
    return derive(baseUnit)
        .splitInto(1000)
        .named(name)
        .symbol(symbol)
        .make();
  }

  /**
   * Creates a unit equal to a thousandth of the base unit, eg Milliseconds = Milli(Units.Seconds).
   */
  public static <U extends Unit<U>> U Milli(Unit<U> baseUnit) {
    return Milli(baseUnit, "Milli" + baseUnit.name().toLowerCase(Locale.ROOT), "m" + baseUnit.symbol());
  }

  /**
   * Creates a unit equal to a thousand of the base unit, eg Kilograms = Kilo(Units.Grams).
   */
  public static <U extends Unit<U>> U Kilo(Unit<U> baseUnit, String name, String symbol) {
    return derive(baseUnit)
        .aggregate(1000)
        .named(name)
        .symbol(symbol)
        .make();
  }

  /**
   * Creates a unit equal to a thousand of the base unit, eg Kilograms = Kilo(Units.Grams).
   */
  public static <U extends Unit<U>> U Kilo(Unit<U> baseUnit) {
    return Kilo(baseUnit, "Kilo" + baseUnit.name().toLowerCase(Locale.ROOT), "K" + baseUnit.symbol());
  }

  @SuppressWarnings("unchecked")
  public static <U extends Unit<U>> UnitBuilder<U> derive(Unit<U> unit) {
    return new UnitBuilder<>((U) unit);
  }

  public static <U extends Unit<U>> U anonymous() {
    return (U) AnonymousBaseUnit;
  }

  public static final class UnitBuilder<U extends Unit<U>> {

    private final U base;
    private UnaryFunction fromBase;
    private UnaryFunction toBase;
    private String name;
    private String symbol;

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

      double mapValue(double value, double inMin, double inMax, double outMin, double outMax) {
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

    public UnitBuilder<U> named(String name) {
      this.name = name;
      return this;
    }

    public UnitBuilder<U> symbol(String symbol) {
      this.symbol = symbol;
      return this;
    }

    public UnitBuilder<U> splitInto(double fraction) {
      if (fraction == 0) throw new IllegalArgumentException("Fraction must be nonzero");

      return toBase(x -> x / fraction).fromBase(b -> b * fraction);
    }

    public UnitBuilder<U> aggregate(double aggregation) {
      if (aggregation == 0) throw new IllegalArgumentException("Aggregation amount must be nonzero");

      return toBase(x -> x * aggregation).fromBase(b -> b / aggregation);
    }

    public U make() {
      Objects.requireNonNull(fromBase, "fromBase function was not set");
      Objects.requireNonNull(toBase, "toBase function was not set");
      Objects.requireNonNull(name, "new unit name was not set");
      Objects.requireNonNull(symbol, "new unit symbol was not set");
      try {
        Constructor<? extends U> ctor = base.baseType.getDeclaredConstructor(UnaryFunction.class, UnaryFunction.class, String.class, String.class);
        return ctor.newInstance(
            toBase.pipeTo(base.getConverterToBase()), // (derived) -> derived to derivation base, then to true base
            base.getConverterFromBase().pipeTo(fromBase), // (base) -> true base to derivation base, then to derived
            name,
            symbol
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
