package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static edu.wpi.first.wpilib.units.Units.*;
import static org.junit.Assert.*;

public class VelocityTest {
  @Test
  public void testBaseUnit() {
    assertTrue(MetersPerSecond.equivalent(BaseUnits.Velocity));
    assertTrue(Meters.per(Second).equivalent(BaseUnits.Velocity));
  }

  @Test
  public void testToAcceleration() {
    var metersPerSecondPerMillisecond = MetersPerSecond.per(Millisecond);

    assertEquals(1000, metersPerSecondPerMillisecond.of(1).as(MetersPerSecondPerSecond), 0);
    assertEquals(0, metersPerSecondPerMillisecond.of(0).as(MetersPerSecondPerSecond), 0);
  }
}
