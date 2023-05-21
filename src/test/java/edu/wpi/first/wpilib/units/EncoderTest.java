package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static edu.wpi.first.wpilib.units.Units.*;
import static junit.framework.Assert.*;

public class EncoderTest {
  static class Encoder<U extends Unit<U>> {
    double ticks = 0;
    private Measure<U> distancePerPulse;

    public void setDistancePerPulse(Measure<U> distancePerPulse) {
      this.distancePerPulse = distancePerPulse;
    }

    public Measure<U> getDistance() {
      return distancePerPulse.times(ticks);
    }
  }

  @Test
  public void testAsDistance() {
    var ticksPerRevolution = Value.of(2048);

    var encoder = new Encoder<Distance>();

    // distance per rotation = (wheel circumference / gear ratio)
    // distance per tick = distance per rotation / ticks per rotation
    var wheelDiameter = Inches.of(6);
    var gearRatio = Value.of(10); // 10:1 ratio
    Measure<Distance> distancePerPulse = wheelDiameter.times(Math.PI).divide(gearRatio).divide(ticksPerRevolution);
    encoder.setDistancePerPulse(distancePerPulse);

    encoder.ticks = 0;
    assertEquals(0, encoder.getDistance().as(Inches), Measure.EQUIVALENCE_THRESHOLD);

    encoder.ticks = 2048; // one full encoder turn, 1/10th of a wheel rotation
    assertEquals(6 * Math.PI / 10, encoder.getDistance().as(Inches), Measure.EQUIVALENCE_THRESHOLD);
  }

  @Test
  public void testAsRevolutions() {
    var ticksPerRevolution = Value.of(2048);

    var encoder = new Encoder<Angle>();

    Measure<Angle> distancePerPulse = Revolutions.of(1).divide(ticksPerRevolution);
    encoder.setDistancePerPulse(distancePerPulse);

    encoder.ticks = 0;
    assertEquals(0, encoder.getDistance().as(Revolutions), Measure.EQUIVALENCE_THRESHOLD);

    encoder.ticks = 2048; // one full encoder turn, 1/10th of a wheel rotation
    assertEquals(1, encoder.getDistance().as(Revolutions), Measure.EQUIVALENCE_THRESHOLD);
  }
}
