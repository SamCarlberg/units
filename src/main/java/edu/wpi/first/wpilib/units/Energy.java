package edu.wpi.first.wpilib.units;

public class Energy extends Unit<Energy> {
  protected Energy(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Energy.class, toBaseConverter, fromBaseConverter);
  }

  protected Energy(double baseUnitEquivalent) {
    super(Energy.class, baseUnitEquivalent);
  }
}
