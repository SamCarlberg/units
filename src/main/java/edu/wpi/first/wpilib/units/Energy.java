package edu.wpi.first.wpilib.units;

public class Energy extends Unit<Energy> {
  protected Energy(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter, String name, String symbol) {
    super(Energy.class, toBaseConverter, fromBaseConverter, name, symbol);
  }

  protected Energy(double baseUnitEquivalent, String name, String symbol) {
    super(Energy.class, baseUnitEquivalent, name, symbol);
  }
}
