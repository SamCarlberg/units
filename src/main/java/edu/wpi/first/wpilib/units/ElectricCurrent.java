package edu.wpi.first.wpilib.units;

public class ElectricCurrent extends Unit<ElectricCurrent> {

  ElectricCurrent(double baseUnitEquivalent) {
    super(ElectricCurrent.class, baseUnitEquivalent);
  }

  ElectricCurrent(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(ElectricCurrent.class, toBaseConverter, fromBaseConverter);
  }

  public Power times(Unit<ElectricPotential> voltage) {
    return new Power(this.toBaseUnits(1) * voltage.toBaseUnits(1));
  }

}
