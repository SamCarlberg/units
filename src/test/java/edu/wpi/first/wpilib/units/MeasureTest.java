package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MeasureTest {

    @Test
    public void testBasics() {
        Distance unit = Distance.FEET;
        double magnitude = 10;
        Measure<Distance> m = new Measure<>(magnitude, unit);
        assertEquals("Wrong units", unit, m.units());
        assertEquals("Wrong magnitude", magnitude, m.magnitude(), 0);
    }

    @Test
    public void testAs() {
        for (Distance d1 : Distance.values()) {
            for (Distance d2 : Distance.values()) {
                assertEquals(
                        "Incorrect conversion: " + d1 + " -> " + d2,
                        new Measure<>(1, d1).magnitude(),
                        new Measure<>(d1.multiplierTo(d2), d2).as(d1).magnitude(),
                        1e-6
                );
            }
        }
    }

    @Test
    public void testMultiply() {
        Measure<Distance> m = new Measure<>(1, Distance.FEET);
        Measure<Distance> m2 = m.times(10);
        assertEquals(10, m2.magnitude(), 0);
        assertFalse(m2 == m); // make sure state wasn't changed
    }

    @Test
    public void testDivide() {
        Measure<Distance> m = new Measure<>(1, Distance.METERS);
        Measure<Distance> m2 = m.divide(10);
        assertEquals(0.1, m2.magnitude(), 0);
        assertFalse(m2 == m);
    }

    @Test
    public void testAdd() {
        Measure<Distance> m1 = new Measure<>(1, Distance.FEET);
        Measure<Distance> m2 = new Measure<>(2, Distance.INCHES);
        assertEquals(m1.add(m2), new Measure<>(1 + 2 / 12d, Distance.FEET));
        assertEquals(m2.add(m1), new Measure<>(14, Distance.INCHES));
    }

    @Test
    public void testSubtract() {
        Measure<Distance> m1 = new Measure<>(1, Distance.FEET);
        Measure<Distance> m2 = new Measure<>(2, Distance.INCHES);
        assertEquals(m1.subtract(m2), new Measure<>(1 - 2 / 12d, Distance.FEET));
        assertEquals(m2.subtract(m1), new Measure<>(-10, Distance.INCHES));
    }

    @Test
    public void testEquivalency() {
        Measure<Distance> inches = new Measure<>(12, Distance.INCHES);
        Measure<Distance> feet = new Measure<>(1, Distance.FEET);
        assertTrue(inches.isEquivalent(feet));
        assertTrue(feet.isEquivalent(inches));
    }

}