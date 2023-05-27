package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElectricCurrentTest {
  @Test
  public void testAmpsTimesVolts() {
    Power combined = Units.Amps.times(Units.Volts, "Watt", "w");

    assertTrue(combined.equivalent(Units.Watts));
  }

  @Test
  public void testMilliAmpsTimesMilliVolts() {
    // results in microwatts
    assertTrue(Units.Milliamps.times(Units.Millivolts, "Microwatt", "uW").equivalent(Units.Milli(Units.Milliwatts)));
  }
}
