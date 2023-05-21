package edu.wpi.first.wpilib.units;

import java.util.function.DoubleUnaryOperator;

class ExampleUnit extends Unit<ExampleUnit> {
    ExampleUnit(double baseUnitEquivalent) {
      super(ExampleUnit.class, baseUnitEquivalent);
    }

    ExampleUnit(DoubleUnaryOperator toBase, DoubleUnaryOperator fromBase) {
      super(ExampleUnit.class, toBase, fromBase);
    }
  }