package edu.wpi.first.wpilib.units;

class ExampleUnit extends Unit<ExampleUnit> {
  ExampleUnit(double baseUnitEquivalent) {
    this(baseUnitEquivalent, "Example", "ex");
  }

  ExampleUnit(UnaryFunction toBase, UnaryFunction fromBase, String name, String symbol) {
    super(ExampleUnit.class, toBase, fromBase, name, symbol);
  }

  ExampleUnit(double baseUnitEquivalent, String name, String symbol) {
    super(ExampleUnit.class, baseUnitEquivalent, name, symbol);
  }
}
