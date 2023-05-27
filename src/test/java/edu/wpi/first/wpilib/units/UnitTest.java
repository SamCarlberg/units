package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest { // :)
  @Test
  public void testOf() {
    ExampleUnit u = new ExampleUnit(1);
    Measure<ExampleUnit> fiveOfSomething = u.of(5);
    assertEquals(5, fiveOfSomething.magnitude(), 0);
    assertEquals(u, fiveOfSomething.unit());
  }

}
