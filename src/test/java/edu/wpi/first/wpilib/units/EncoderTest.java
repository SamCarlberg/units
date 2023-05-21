package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static edu.wpi.first.wpilib.units.Units.*;
import static junit.framework.Assert.*;

public class EncoderTest {
  static class Encoder<U extends Unit<U>> {
    int ticks = 0;
    private Measure<U> distancePerPulse;
    private MutableMeasure<U> distance;
    private MutableMeasure<Velocity<U>> rate;

    public void setDistancePerPulse(Measure<U> distancePerPulse) {
      this.distancePerPulse = distancePerPulse;
      this.distance = new MutableMeasure<>(0, distancePerPulse.unit());
      this.rate = new MutableMeasure<>(0, distancePerPulse.unit().per(Second));
    }

    public Measure<U> getDistance() {
      return distance;
    }

    public Measure<Velocity<U>> getRate() {
      return rate;
    }

    public void setTicks(int ticks) {
      // pretend we read from JNI here instead of being passed a specific value
      var change = ticks - this.ticks;
      this.ticks = ticks;
      distance.setMagnitude(distancePerPulse.magnitude() * ticks);

      // assumes the last update was 1 second ago - fine for tests
      rate.setMagnitude(distancePerPulse.magnitude() * change);
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
    assertEquals(0, encoder.getRate().as(Inches.per(Second)), Measure.EQUIVALENCE_THRESHOLD);

    encoder.setTicks(2048); // one full encoder turn, 1/10th of a wheel rotation
    assertEquals(6 * Math.PI / 10, encoder.getDistance().as(Inches), Measure.EQUIVALENCE_THRESHOLD);

    encoder.setTicks(0); // one full encoder turn back, 1/10th of a wheel rotation - rate should be negative
    assertEquals(-6 * Math.PI / 10, encoder.getRate().as(Inches.per(Second)), Measure.EQUIVALENCE_THRESHOLD);
  }

  @Test
  public void testAsRevolutions() {
    var ticksPerRevolution = Value.of(2048);

    var encoder = new Encoder<Angle>();

    Measure<Angle> distancePerPulse = Revolutions.of(1).divide(ticksPerRevolution);
    encoder.setDistancePerPulse(distancePerPulse);

    encoder.ticks = 0;
    assertEquals(0, encoder.getDistance().as(Revolutions), Measure.EQUIVALENCE_THRESHOLD);
    assertEquals(0, encoder.getRate().as(Revolutions.per(Second)), Measure.EQUIVALENCE_THRESHOLD);

    encoder.setTicks(2048); // one full encoder turn, 1/10th of a wheel rotation
    assertEquals(1, encoder.getDistance().as(Revolutions), Measure.EQUIVALENCE_THRESHOLD);
    assertEquals(1, encoder.getRate().as(Revolutions.per(Second)), Measure.EQUIVALENCE_THRESHOLD);
  }
}
