package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest { // :)

  @Test
  public void testAggregate() {
    ExampleUnit base = new ExampleUnit(1);
    ExampleUnit tens = base.aggregate(10);
    assertEquals(1, tens.of(0.1).in(base), 0);
    assertEquals(10, tens.of(1).in(base), 0);
  }

  @Test
  public void testSplitInto() {
    ExampleUnit base = new ExampleUnit(1);
    ExampleUnit tenths = base.splitInto(10);
    assertEquals(1, tenths.of(10).in(base), 0);
    assertEquals(0.1, tenths.of(1).in(base), 0);
  }

  @Test
  public void testOf() {
    ExampleUnit u = new ExampleUnit(1);
    Measure<ExampleUnit> fiveOfSomething = u.of(5);
    assertEquals(5, fiveOfSomething.magnitude(), 0);
    assertEquals(u, fiveOfSomething.unit());
  }

}
