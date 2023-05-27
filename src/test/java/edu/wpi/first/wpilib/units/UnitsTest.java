package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static edu.wpi.first.wpilib.units.Units.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitsTest {

  // Be accurate to 0.01%
  private static final double thresh = 1e-5;

  void testBaseUnit(Unit<?> baseUnit) {
    assertEquals(0, baseUnit.of(0).baseUnitMagnitude(), 0);
    assertEquals(1, baseUnit.of(1).baseUnitMagnitude(), 0);
    assertEquals(-1, baseUnit.of(-1).baseUnitMagnitude(), 0);
    assertEquals(100, baseUnit.of(100).baseUnitMagnitude(), 0);
    assertEquals(3.14159, baseUnit.of(3.14159).baseUnitMagnitude(), 0);
    assertEquals(Float.MAX_VALUE, baseUnit.of(Float.MAX_VALUE).baseUnitMagnitude(), 0);
    assertEquals(Float.MIN_VALUE, baseUnit.of(Float.MIN_VALUE).baseUnitMagnitude(), 0);
  }


  // Distances

  @Test
  public void testMeters() {
    testBaseUnit(Meters);
    assertEquals("Meter", Meters.name());
    assertEquals("m", Meters.symbol());
  }

  @Test
  public void testMillimeters() {
    assertEquals(1000, Millimeters.convert(1, Meters), thresh);
    assertEquals(1, Meters.convert(1000, Millimeters), thresh);
    assertEquals("Millimeter", Millimeters.name());
    assertEquals("mm", Millimeters.symbol());
  }

  @Test
  public void testCentimeters() {
    assertEquals(100, Centimeters.convert(1, Meters), thresh);
    assertEquals(1, Meters.convert(100, Centimeters), thresh);
    assertEquals("Centimeter", Centimeters.name());
    assertEquals("cm", Centimeters.symbol());
  }

  @Test
  public void testInches() {
    assertEquals(1, Meters.convert(39.3701, Inches), thresh);
    assertEquals(39.3701, Inches.convert(1, Meters), 1e-4);
    assertEquals(1 / 25.4, Inches.convert(1, Millimeters), 0); // should be exact
    assertEquals(12, Inches.convert(1, Feet), thresh);
    assertEquals("Inch", Inches.name());
    assertEquals("in", Inches.symbol());
  }

  @Test
  public void testFeet() {
    assertEquals(3.28084, Feet.convert(1, Meters), thresh);
    assertEquals(1 / 12.0, Feet.convert(1, Inches), thresh);
    assertEquals("Foot", Feet.name());
    assertEquals("ft", Feet.symbol());
  }


  // Velocities

  @Test
  public void testMetersPerSecond() {
    testBaseUnit(MetersPerSecond);
    assertEquals("Meter per Second", MetersPerSecond.name());
    assertEquals("m/s", MetersPerSecond.symbol());
  }

  @Test
  public void testFeetPerSecond() {
    assertEquals(3.28084, FeetPerSecond.convert(1, MetersPerSecond), thresh);
    assertEquals(1 / 3.28084, MetersPerSecond.convert(1, FeetPerSecond), thresh);
    assertEquals("Foot per Second", FeetPerSecond.name());
    assertEquals("ft/s", FeetPerSecond.symbol());
  }


  // Time

  @Test
  public void testSeconds() {
    testBaseUnit(Seconds);
    assertEquals("Second", Seconds.name());
    assertEquals("s", Seconds.symbol());
  }

  @Test
  public void testMillisecond() {
    assertEquals(1000, Milliseconds.convert(1, Seconds), thresh);
    assertEquals("Millisecond", Milliseconds.name());
    assertEquals("ms", Milliseconds.symbol());
  }

  @Test
  public void testMinutes() {
    assertEquals(60, Seconds.convert(1, Minutes), thresh);
    assertEquals("Minute", Minutes.name());
    assertEquals("min", Minutes.symbol());
  }


  // Mass

  @Test
  public void testKilograms() {
    testBaseUnit(Kilograms);
    assertEquals("Kilogram", Kilograms.name());
    assertEquals("Kg", Kilograms.symbol());
  }

  @Test
  public void testGrams() {
    assertEquals(1000, Grams.convert(1, Kilograms), thresh);
    assertEquals("Gram", Grams.name());
    assertEquals("g", Grams.symbol());
  }

  @Test
  public void testPounds() {
    assertEquals(453.592, Grams.convert(1, Pounds), thresh);
    assertEquals("Pound", Pounds.name());
    assertEquals("lb.", Pounds.symbol());
  }

  @Test
  public void testOunces() {
    assertEquals(16, Ounces.convert(1, Pounds), thresh);
    assertEquals("Ounce", Ounces.name());
    assertEquals("oz.", Ounces.symbol());
  }


  // Angle

  @Test
  public void testRevolutions() {
    testBaseUnit(Revolutions);
    assertEquals("Revolution", Revolutions.name());
    assertEquals("R", Revolutions.symbol());
  }

  @Test
  public void testRadians() {
    assertEquals(2 * Math.PI, Radians.convert(1, Revolutions), thresh);
    assertEquals(2 * Math.PI, Radians.convert(360, Degrees), thresh);
    assertEquals("Radian", Radians.name());
    assertEquals("rad", Radians.symbol());
  }

  @Test
  public void testDegrees() {
    assertEquals(360, Degrees.convert(1, Revolutions), thresh);
    assertEquals(360, Degrees.convert(2 * Math.PI, Radians), thresh);
    assertEquals("Degree", Degrees.name());
    assertEquals("°", Degrees.symbol());
  }


  // Unitless

  @Test
  public void testValue() {
    testBaseUnit(Value);
    assertEquals("<?>", Value.name());
    assertEquals("<?>", Value.symbol());
  }

  @Test
  public void testPercent() {
    assertEquals(100, Percent.convert(1, Value), thresh);
    assertEquals("Percent", Percent.name());
    assertEquals("%", Percent.symbol());
  }


  // Electric potential

  @Test
  public void testVolts() {
    testBaseUnit(Volts);
    assertEquals("Volt", Volts.name());
    assertEquals("V", Volts.symbol());
  }

  @Test
  public void testMillivolts() {
    assertEquals(1000, Millivolts.convert(1, Volts), thresh);
    assertEquals("Millivolt", Millivolts.name());
    assertEquals("mV", Millivolts.symbol());
  }


  // Electric current

  @Test
  public void testAmps() {
    testBaseUnit(Amps);
    assertEquals("Amp", Amps.name());
    assertEquals("A", Amps.symbol());
  }

  @Test
  public void testMilliamps() {
    assertEquals(1000, Milliamps.convert(1, Amps), thresh);
    assertEquals("Milliamp", Milliamps.name());
    assertEquals("mA", Milliamps.symbol());
  }


  // Power

  @Test
  public void testWatts() {
    testBaseUnit(Watts);
    assertEquals("Watt", Watts.name());
    assertEquals("W", Watts.symbol());
  }

  @Test
  public void testMilliwatts() {
    assertEquals(1000, Milliwatts.convert(1, Watts), thresh);
    assertEquals("Milliwatt", Milliwatts.name());
    assertEquals("mW", Milliwatts.symbol());
  }

  @Test
  public void testHorsepower() {
    assertEquals(745.7, Watts.convert(1, Horsepower), thresh);
    assertEquals("Horsepower", Horsepower.name());
    assertEquals("HP", Horsepower.symbol());
  }

  // Temperature

  @Test
  public void testKelvin() {
    testBaseUnit(Kelvin);
    assertEquals("Kelvin", Kelvin.name());
    assertEquals("K", Kelvin.symbol()); // note: there's no degree symbol for Kelvin!
  }

  @Test
  public void testCelsius() {
    assertEquals(0, Celsius.of(-273.15).in(Kelvin), thresh);
    assertEquals(273.15, Celsius.of(0).in(Kelvin), thresh);
    assertEquals(0, Kelvin.of(273.15).in(Celsius), thresh);
    System.out.println(Celsius.of(0).in(Kelvin));
    assertTrue(Celsius.of(0).isEquivalent(Kelvin.of(273.15)));
    assertTrue(Celsius.of(-273.15).isEquivalent(Kelvin.of(0)));
    assertEquals("Celsius", Celsius.name());
    assertEquals("°C", Celsius.symbol());
  }

  @Test
  public void testFahrenheit() {
    assertEquals(0, Fahrenheit.of(32).in(Celsius), thresh);
    assertEquals(100, Fahrenheit.of(212).in(Celsius), thresh);
    assertEquals(-459.67, Kelvin.of(0).in(Fahrenheit), thresh);
    assertEquals(273.15, Fahrenheit.of(32).in(Kelvin), thresh);
    assertEquals(32, Kelvin.of(273.15).in(Fahrenheit), thresh);
    assertTrue(Fahrenheit.of(32).isEquivalent(Celsius.of(0)));
    assertTrue(Fahrenheit.of(212).isEquivalent(Celsius.of(100)));
    assertEquals("Fahrenheit", Fahrenheit.name());
    assertEquals("°F", Fahrenheit.symbol());
  }

  // Helpers

  @Test
  public void testKilo() {
    ExampleUnit unit = new ExampleUnit(1);
    ExampleUnit kiloUnit = Kilo(unit);
    assertEquals(1e3, unit.convert(1, kiloUnit), 0);
    assertEquals(1e-3, kiloUnit.convert(1, unit), 0);
  }

  @Test
  public void testMilli() {
    ExampleUnit unit = new ExampleUnit(1);
    ExampleUnit milliUnit = Milli(unit);
    assertEquals(1e-3, unit.convert(1, milliUnit), 0);
    assertEquals(1e3, milliUnit.convert(1, unit), 0);
  }
}
