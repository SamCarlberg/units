package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MeasureTest {

  @Test
  public void testBasics() {
    Unit<Distance> unit = Units.Feet;
    double magnitude = 10;
    Measure<Distance> m = unit.of(magnitude);
    assertEquals("Wrong units", unit, m.unit());
    assertEquals("Wrong magnitude", magnitude, m.magnitude(), 0);
  }

  @Test
  public void testMultiply() {
    Measure<Distance> m = Measure.of(1, Units.Feet);
    Measure<Distance> m2 = m.times(10);
    assertEquals(10, m2.magnitude(), 0);
    assertFalse(m2 == m); // make sure state wasn't changed
  }

  @Test
  public void testDivide() {
    Measure<Distance> m = Measure.of(1, Units.Meters);
    Measure<Distance> m2 = m.divide(10);
    assertEquals(0.1, m2.magnitude(), 0);
    assertFalse(m2 == m);
  }

  @Test
  public void testAdd() {
    Measure<Distance> m1 = Measure.of(1, Units.Feet);
    Measure<Distance> m2 = Measure.of(2, Units.Inches);
    assertTrue(m1.add(m2).isEquivalent(Measure.of(1 + 2 / 12d, Units.Feet)));
    assertTrue(m2.add(m1).isEquivalent(Measure.of(14, Units.Inches)));
  }

  @Test
  public void testSubtract() {
    Measure<Distance> m1 = Measure.of(1, Units.Feet);
    Measure<Distance> m2 = Measure.of(2, Units.Inches);
    assertTrue(m1.subtract(m2).isEquivalent(Measure.of(1 - 2 / 12d, Units.Feet)));
    assertTrue(m2.subtract(m1).isEquivalent(Measure.of(-10, Units.Inches)));
  }

  @Test
  public void testNegate() {
    Measure<Distance> m = Measure.of(123, Units.Feet);
    Measure<Distance> n = m.negate();
    assertEquals(-m.magnitude(), n.magnitude(), 0);
    assertEquals(m.unit(), n.unit());
  }

  @Test
  public void testEquivalency() {
    Measure<Distance> inches = Measure.of(12, Units.Inches);
    Measure<Distance> feet = Measure.of(1, Units.Feet);
    assertTrue(inches.isEquivalent(feet));
    assertTrue(feet.isEquivalent(inches));
  }

  @Test
  public void testAs() {
    Measure<Distance> m = Measure.of(12, Units.Inches);
    assertEquals(1, m.in(Units.Feet), Measure.EQUIVALENCE_THRESHOLD);
  }

  @Test
  public void testPerUnit() {
    var measure = Measure.of(144, Units.Kilograms);
    var dt = Measure.of(53, Units.Milliseconds);

    // 144 Kg / (53 ms) = (1000 / 53) * 144 Kg/s = (144,000 / 53) Kg/s

    var result = measure.per(dt);
    assertEquals(144_000.0 / 53, result.baseUnitMagnitude(), 1e-5);
    assertEquals(Units.Kilograms.per(Units.Milliseconds), result.unit());
  }

  @Test
  public void testTimesMeasure() {
    var m1 = Units.Volts.of(1.567);
    var m2 = Units.Kilograms.of(8.4e-5);

    assertEquals(Units.Volts.mult(Units.Kilograms).of(1.567 * 8.4e-5), m1.times(m2));
  }

  @Test
  public void testPerTimesMeasure() {
    var m1 = Units.Feet.per(Units.Milliseconds).of(19);
    var m2 = Units.Seconds.of(44);

    // 19 ft/ms = 19,000 ft/s
    // 19,000 ft/s * 44s = 836,000 ft
    assertEquals(Units.Feet.of(836_000), m1.times(m2));

    // 42 ex per foot * 17mm = 42 ex * 17mm / (304.8mm/ft) = 42 * 17 / 304.8 = 2.34252
    var exampleUnit = new ExampleUnit(1);
    var m3 = exampleUnit.per(Units.Feet).of(42);
    var m4 = Units.Millimeters.of(17);
    assertEquals(exampleUnit.of(42 * 17 / (12 * 25.4)), m3.times(m4));
  }

  @Test
  public void testToShortString() {
    var measure = Measure.of(343, Units.Volts);
    assertEquals("3.430e+02 V", measure.toShortString());
  }

  @Test
  public void testToLongString() {
    var measure = Measure.of(343, Units.Volts);
    assertEquals("343.0 Volt", measure.toLongString());
    assertEquals("343.0001 Volt", Measure.of(343.0001, Units.Volts).toLongString());
    assertEquals("1.2345678912345679E8 Volt", Measure.of(123456789.123456789, Units.Volts).toLongString());
  }

}
