package edu.wpi.first.wpilib.units;

public class Power extends Unit<Power> {


  Power(double baseUnitEquivalent) {
    super(Power.class, baseUnitEquivalent);
  }

  Power(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(Power.class, toBaseConverter, fromBaseConverter);
  }

}
