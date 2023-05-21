package edu.wpi.first.wpilib.units;

class ExampleUnit extends Unit<ExampleUnit> {
  ExampleUnit(double baseUnitEquivalent) {
    super(ExampleUnit.class, baseUnitEquivalent);
  }

  ExampleUnit(UnaryFunction toBase, UnaryFunction fromBase) {
    super(ExampleUnit.class, toBase, fromBase);
  }
}
