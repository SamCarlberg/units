package edu.wpi.first.wpilib.units;

public class ElectricCurrent extends Unit<ElectricCurrent> {

  ElectricCurrent(double baseUnitEquivalent) {
    super(ElectricCurrent.class, baseUnitEquivalent);
  }

  ElectricCurrent(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(ElectricCurrent.class, toBaseConverter, fromBaseConverter);
  }

  public Power times(Unit<ElectricPotential> voltage) {
    return new Power(
        getConverterToBase().mult(voltage.getConverterToBase()),
        getConverterFromBase().mult(voltage.getConverterFromBase())
    );
  }

}
