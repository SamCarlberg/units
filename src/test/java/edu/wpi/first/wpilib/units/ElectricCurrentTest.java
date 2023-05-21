package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElectricCurrentTest {
  @Test
  public void testAmpsTimesVolts() {
    assertTrue(Units.Amps.times(Units.Volts).equivalent(Units.Watts));
  }

  @Test
  public void testMilliAmpsTimesMilliVolts() {
    // results in microwatts
    assertTrue(Units.Milliamps.times(Units.Millivolts).equivalent(Units.Milli(Units.Milliwatts)));
  }
}
