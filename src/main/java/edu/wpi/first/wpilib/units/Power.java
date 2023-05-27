package edu.wpi.first.wpilib.units;

public class Power extends Unit<Power> {


  Power(double baseUnitEquivalent, String name, String symbol) {
    super(Power.class, baseUnitEquivalent, name, symbol);
  }

  Power(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter, String name, String symbol) {
    super(Power.class, toBaseConverter, fromBaseConverter, name, symbol);
  }

}
