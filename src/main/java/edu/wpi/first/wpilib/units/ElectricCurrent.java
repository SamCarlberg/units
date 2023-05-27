package edu.wpi.first.wpilib.units;

public class ElectricCurrent extends Unit<ElectricCurrent> {

  ElectricCurrent(double baseUnitEquivalent, String name, String symbol) {
    super(ElectricCurrent.class, baseUnitEquivalent, name, symbol);
  }

  ElectricCurrent(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter, String name, String symbol) {
    super(ElectricCurrent.class, toBaseConverter, fromBaseConverter, name, symbol);
  }

  public Power times(Unit<ElectricPotential> voltage, String name, String symbol) {
    return new Power(this.toBaseUnits(1) * voltage.toBaseUnits(1), name, symbol);
  }

}
