package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked") // Don't care about the generic types
public class UnitTest { // :)

    @Test
    public void testMultiplierTo() {
        Unit a = new Unit(0.5);
        Unit b = new Unit(2);
        assertEquals(4, a.multiplierTo(b), 0);
        assertEquals(0.25, b.multiplierTo(a), 0);
    }

    @Test
    public void testMultiply() {
        Unit u = new Unit(1);
        Unit m = u.multiply(10);
        assertEquals(0.1, m.multiplierTo(u), 0);
        assertEquals(10, u.multiplierTo(m), 0);
    }

    @Test
    public void testDivide() {
        Unit u = new Unit(1);
        Unit d = u.divide(10);
        assertEquals(10, d.multiplierTo(u), 0);
        assertEquals(0.1, u.multiplierTo(d), 0);
    }

    @Test
    public void testOf() {
        Unit u = new Unit(1);
        Measure m = u.of(5);
        assertEquals(5, m.magnitude(), 0);
        assertEquals(u, m.unit());
    }

}
