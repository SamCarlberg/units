package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static junit.framework.Assert.*;

public class DistanceTest {
    @Test
    public void testBaseUnitDistancePerTime() {
        Velocity<Distance> anonBaseUnit = new Distance(1, "D", "d").per(new Time(1, "T", "t"));

        assertTrue(BaseUnits.Velocity.equivalent(anonBaseUnit));
    }

    @Test
    public void testFeetPerSecond() {
        Velocity<Distance> feetPerMillisecond = Units.Feet.per(Units.Milliseconds);

        // one foot per millisecond = (1 / 3.28084) meters per (1 / 1000) seconds = (1000 / 3.28084) meters per second
        double asBaseMeasure = feetPerMillisecond.of(1).in(Units.MetersPerSecond);
        assertEquals(1000 / 3.28084, asBaseMeasure, 1e-3);

        // one meter per second = 1 mm per millisecond = 0.00328084 feet per millisecond
        double asContrivedMeasure = Units.MetersPerSecond.of(1).in(feetPerMillisecond);
        assertEquals(3.28084 / 1000, asContrivedMeasure, 1e-8);
    }
}
