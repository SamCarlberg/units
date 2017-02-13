package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static edu.wpi.first.wpilib.units.Units.Centimeters;
import static edu.wpi.first.wpilib.units.Units.Degrees;
import static edu.wpi.first.wpilib.units.Units.Feet;
import static edu.wpi.first.wpilib.units.Units.Grams;
import static edu.wpi.first.wpilib.units.Units.Inches;
import static edu.wpi.first.wpilib.units.Units.Kilo;
import static edu.wpi.first.wpilib.units.Units.Kilograms;
import static edu.wpi.first.wpilib.units.Units.Meters;
import static edu.wpi.first.wpilib.units.Units.Milli;
import static edu.wpi.first.wpilib.units.Units.Millimeters;
import static edu.wpi.first.wpilib.units.Units.Milliseconds;
import static edu.wpi.first.wpilib.units.Units.Minutes;
import static edu.wpi.first.wpilib.units.Units.Ounces;
import static edu.wpi.first.wpilib.units.Units.Percent;
import static edu.wpi.first.wpilib.units.Units.Pounds;
import static edu.wpi.first.wpilib.units.Units.Radians;
import static edu.wpi.first.wpilib.units.Units.Revolutions;
import static edu.wpi.first.wpilib.units.Units.Seconds;
import static edu.wpi.first.wpilib.units.Units.Value;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class UnitsTest {

    // Be accurate to 0.01%
    private static final double thresh = 1e-5;


    // Distances

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
        assertEquals(39.3701, Inches.convert(1, Meters), thresh);
        assertEquals(12, Inches.convert(1, Feet), thresh);
    }

    @Test
    public void testFeet() {
        assertEquals(3.28084, Feet.convert(1, Meters), thresh);
        assertEquals(1 / 12.0, Feet.convert(1, Inches), thresh);
    }


    // Time

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
    public void testPercent() {
        assertEquals(100, Percent.convert(1, Value), thresh);
    }


    // Helpers

    @Test
    public void testKilo() {
        Unit unit = new Unit(1);
        Unit kUnit = Kilo(unit);
        assertEquals(1e3, unit.convert(1, kUnit), 0);
        assertEquals(1e-3, kUnit.convert(1, unit), 0);
    }

    @Test
    public void testMilli() {
        Unit unit = new Unit(1);
        Unit mUnit = Milli(unit);
        assertEquals(1e-3, unit.convert(1, mUnit), 0);
        assertEquals(1e3, mUnit.convert(1, unit), 0);
    }

}