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
  }

  @Test
  public void testMillimeters() {
    assertEquals(1000, Millimeters.convert(1, Meters), thresh);
    assertEquals(1, Meters.convert(1000, Millimeters), thresh);
  }

  @Test
  public void testCentimeters() {
    assertEquals(100, Centimeters.convert(1, Meters), thresh);
    assertEquals(1, Meters.convert(100, Centimeters), thresh);
  }

  @Test
  public void testInches() {
    assertEquals(1, Meters.convert(39.3701, Inches), thresh);
    assertEquals(39.3701, Inches.convert(1, Meters), 1e-4);
    assertEquals(1 / 25.4, Inches.convert(1, Millimeters), 0); // should be exact
    assertEquals(12, Inches.convert(1, Feet), thresh);
  }

  @Test
  public void testFeet() {
    assertEquals(3.28084, Feet.convert(1, Meters), thresh);
    assertEquals(1 / 12.0, Feet.convert(1, Inches), thresh);
  }


  // Velocities

  @Test
  public void testMetersPerSecond() {
    testBaseUnit(MetersPerSecond);
  }

  @Test
  public void testFeetPerSecond() {
    assertEquals(3.28084, FeetPerSecond.convert(1, MetersPerSecond), thresh);
    assertEquals(1 / 3.28084, MetersPerSecond.convert(1, FeetPerSecond), thresh);
  }


  // Time

  @Test
  public void testSeconds() {
    testBaseUnit(Seconds);
  }

  @Test
  public void testMillisecond() {
    assertEquals(1000, Milliseconds.convert(1, Seconds), thresh);
  }

  @Test
  public void testMinutes() {
    assertEquals(60, Seconds.convert(1, Minutes), thresh);
  }


  // Mass

  @Test
  public void testGrams() {
    testBaseUnit(Grams);
  }

  @Test
  public void testKilograms() {
    assertEquals(1000, Grams.convert(1, Kilograms), thresh);
  }

  @Test
  public void testPounds() {
    assertEquals(453.592, Grams.convert(1, Pounds), thresh);
  }

  @Test
  public void testOunces() {
    assertEquals(16, Ounces.convert(1, Pounds), thresh);
  }


  // Angle

  @Test
  public void testRevolutions() {
    testBaseUnit(Revolutions);
  }

  @Test
  public void testRadians() {
    assertEquals(2 * Math.PI, Radians.convert(1, Revolutions), thresh);
    assertEquals(2 * Math.PI, Radians.convert(360, Degrees), thresh);
  }

  @Test
  public void testDegrees() {
    assertEquals(360, Degrees.convert(1, Revolutions), thresh);
    assertEquals(360, Degrees.convert(2 * Math.PI, Radians), thresh);
  }


  // Unitless

  @Test
  public void testValue() {
    testBaseUnit(Value);
  }

  @Test
  public void testPercent() {
    assertEquals(100, Percent.convert(1, Value), thresh);
  }


  // Electric potential

  @Test
  public void testVolts() {
    testBaseUnit(Volts);
  }

  @Test
  public void testMillivolts() {
    assertEquals(1000, Millivolts.convert(1, Volts), thresh);
  }


  // Electric current

  @Test
  public void testAmps() {
    testBaseUnit(Amps);
  }

  @Test
  public void testMilliamps() {
    assertEquals(1000, Milliamps.convert(1, Amps), thresh);
  }


  // Power

  @Test
  public void testWatts() {
    testBaseUnit(Watts);
  }

  @Test
  public void testMilliwatts() {
    assertEquals(1000, Milliwatts.convert(1, Watts), thresh);
  }

  @Test
  public void testHorsepower() {
    assertEquals(745.7, Watts.convert(1, Horsepower), thresh);
  }

  // Temperature

  @Test
  public void testKelvin() {
    testBaseUnit(Kelvin);
  }

  @Test
  public void testCelsius() {
    assertEquals(0, Celsius.of(-273.15).in(Kelvin), thresh);
    assertEquals(273.15, Celsius.of(0).in(Kelvin), thresh);
    assertEquals(0, Kelvin.of(273.15).in(Celsius), thresh);
    System.out.println(Celsius.of(0).in(Kelvin));
    assertTrue(Celsius.of(0).isEquivalent(Kelvin.of(273.15)));
    assertTrue(Celsius.of(-273.15).isEquivalent(Kelvin.of(0)));
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
