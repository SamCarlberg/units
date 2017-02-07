package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceTest {
    
    private static final double tolerance = 1e-6;

    @Test
    public void testInches() {
        Measure<Distance> in = Distance.INCHES.of(10);

        assertEquals(10, in.as(Distance.INCHES).magnitude(), 0);
        assertEquals(10 / 12.0, in.as(Distance.FEET).magnitude(), tolerance);
        assertEquals(254, in.as(Distance.MILLIMETERS).magnitude(), tolerance);
        assertEquals(25.4, in.as(Distance.CENTIMETERS).magnitude(), tolerance);
        assertEquals(0.254, in.as(Distance.METERS).magnitude(), tolerance);
    }
    
    @Test
    public void testFeet() {
        Measure<Distance> ft = Distance.FEET.of(10);

        assertEquals(120, ft.as(Distance.INCHES).magnitude(), tolerance);
        assertEquals(10, ft.as(Distance.FEET).magnitude(), 0);
        assertEquals(3048, ft.as(Distance.MILLIMETERS).magnitude(), tolerance);
        assertEquals(304.8, ft.as(Distance.CENTIMETERS).magnitude(), tolerance);
        assertEquals(3.048, ft.as(Distance.METERS).magnitude(), tolerance);
    }

    @Test
    public void testMm() {
        Measure<Distance> mm = Distance.MILLIMETERS.of(10);

        assertEquals(10 / 25.4, mm.as(Distance.INCHES).magnitude(), tolerance);
        assertEquals(10 / (12 * 25.4), mm.as(Distance.FEET).magnitude(), tolerance);
        assertEquals(10, mm.as(Distance.MILLIMETERS).magnitude(), 0);
        assertEquals(1, mm.as(Distance.CENTIMETERS).magnitude(), tolerance);
        assertEquals(0.01, mm.as(Distance.METERS).magnitude(), tolerance);
    }

}