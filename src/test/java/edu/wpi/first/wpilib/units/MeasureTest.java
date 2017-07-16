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
    Measure<Distance> m = new Measure<>(1, Units.Feet);
    Measure<Distance> m2 = m.times(10);
    assertEquals(10, m2.magnitude(), 0);
    assertFalse(m2 == m); // make sure state wasn't changed
  }

  @Test
  public void testDivide() {
    Measure<Distance> m = new Measure<>(1, Units.Meters);
    Measure<Distance> m2 = m.divide(10);
    assertEquals(0.1, m2.magnitude(), 0);
    assertFalse(m2 == m);
  }

  @Test
  public void testAdd() {
    Measure<Distance> m1 = new Measure<>(1, Units.Feet);
    Measure<Distance> m2 = new Measure<>(2, Units.Inches);
    assertTrue(m1.add(m2).isEquivalent(new Measure<>(1 + 2 / 12d, Units.Feet)));
    assertTrue(m2.add(m1).isEquivalent(new Measure<>(14, Units.Inches)));
  }

  @Test
  public void testSubtract() {
    Measure<Distance> m1 = new Measure<>(1, Units.Feet);
    Measure<Distance> m2 = new Measure<>(2, Units.Inches);
    assertTrue(m1.subtract(m2).isEquivalent(new Measure<>(1 - 2 / 12d, Units.Feet)));
    assertTrue(m2.subtract(m1).isEquivalent(new Measure<>(-10, Units.Inches)));
  }

  @Test
  public void testNegate() {
    Measure<Distance> m = new Measure<>(123, Units.Feet);
    Measure<Distance> n = m.negate();
    assertEquals(-m.magnitude(), n.magnitude(), 0);
    assertEquals(m.unit(), n.unit());
  }

  @Test
  public void testEquivalency() {
    Measure<Distance> inches = new Measure<>(12, Units.Inches);
    Measure<Distance> feet = new Measure<>(1, Units.Feet);
    assertTrue(inches.isEquivalent(feet));
    assertTrue(feet.isEquivalent(inches));
  }

  @Test
  public void testAs() {
    Measure<Distance> m = new Measure<>(12, Units.Inches);
    assertEquals(1, m.as(Units.Feet), Measure.EQUIVALENCE_THRESHOLD);
  }

}
