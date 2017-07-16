package edu.wpi.first.wpilib.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked") // Don't care about the generic types
public class UnitTest { // :)


  @Test
  public void testAggregate() {
    Unit u = new Unit(1);
    Unit m = u.aggregate(10);
    assertEquals(1, m.convert(10, u), 0);
    assertEquals(1, u.convert(0.1, m), 0);
  }

  @Test
  public void testSplitInto() {
    Unit u = new Unit(1);
    Unit d = u.splitInto(10);
    assertEquals(1, d.convert(0.1, u), 0);
    assertEquals(1, u.convert(10, d), 0);
  }

  @Test
  public void testOf() {
    Unit u = new Unit(1);
    Measure m = u.of(5);
    assertEquals(5, m.magnitude(), 0);
    assertEquals(u, m.unit());
  }

}
