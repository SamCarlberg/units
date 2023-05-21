package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static junit.framework.Assert.*;

public class DistanceTest {
    @Test
    public void testBaseUnitDistancePerTime() {
        Unit<Velocity> anonBaseUnit = new Distance(1).per(new Time(1));

        assertTrue(BaseUnits.Velocity.equivalent(anonBaseUnit));
    }

    @Test
    public void testFeetPerSecond() {
        Unit<Velocity> feetPerMillisecond = Units.Feet.per(Units.Milliseconds);

        // one foot per millisecond = (1 / 3.28084) meters per (1 / 1000) seconds = (1000 / 3.28084) meters per second
        double asBaseMeasure = feetPerMillisecond.of(1).as(Units.MetersPerSecond);
        assertEquals(1000 / 3.28084, asBaseMeasure, 1e-3);

        // one meter per second = 1 mm per millisecond = 0.00328084 feet per millisecond
        double asContrivedMeasure = Units.MetersPerSecond.of(1).as(feetPerMillisecond);
        assertEquals(3.28084 / 1000, asContrivedMeasure, 1e-8);
    }
}