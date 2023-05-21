package edu.wpi.first.wpilib.units;

public class ElectricPotential extends Unit<ElectricPotential> {

  ElectricPotential(double baseUnitEquivalent) {
    super(ElectricPotential.class, baseUnitEquivalent);
  }

  ElectricPotential(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(ElectricPotential.class, toBaseConverter, fromBaseConverter);
  }

  public Power times(Unit<ElectricCurrent> current) {
    return new Power(
        getConverterToBase().mult(current.getConverterToBase()),
        getConverterFromBase().mult(current.getConverterFromBase())
    );
  }

}
