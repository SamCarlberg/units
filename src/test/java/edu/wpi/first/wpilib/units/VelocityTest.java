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
    Velocity<Velocity<Distance>> metersPerSecondPerMillisecond = MetersPerSecond.per(Millisecond);

    assertEquals(1000, metersPerSecondPerMillisecond.of(1).in(MetersPerSecondPerSecond), 0);
    assertEquals(0, metersPerSecondPerMillisecond.of(0).in(MetersPerSecondPerSecond), 0);
  }

  @Test
  public void testCache() {
    assertSame("Feet.per(Second) should return a cached object instance", FeetPerSecond, Feet.per(Second));

    // completely arbitrary units chosen because they won't have already been cached
    var someDistance = new ExampleUnit(5);
    var someTime = new ExampleUnit(600);
    var firstInvocation = someDistance.per(someTime);
    var secondInvocation = someDistance.per(someTime);
    assertSame(firstInvocation + " was not the same object as " + secondInvocation, firstInvocation, secondInvocation);
  }
}
