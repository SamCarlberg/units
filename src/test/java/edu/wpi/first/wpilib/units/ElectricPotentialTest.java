package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElectricPotentialTest {
  @Test
  public void testVoltsTimesAmps() {
    assertTrue(Units.Volts.times(Units.Amps).equivalent(Units.Watts));
  }

  @Test
  public void testMilliVoltsTimesMilliAmps() {
    // results in microwatts
    assertTrue(Units.Millivolts.times(Units.Milliamps).equivalent(Units.Milli(Units.Milliwatts)));
  }
}
