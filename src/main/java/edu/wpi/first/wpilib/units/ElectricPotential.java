package edu.wpi.first.wpilib.units;

public class ElectricPotential extends Unit<ElectricPotential> {

  ElectricPotential(double baseUnitEquivalent, String name, String symbol) {
    super(ElectricPotential.class, baseUnitEquivalent, name, symbol);
  }

  ElectricPotential(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter, String name, String symbol) {
    super(ElectricPotential.class, toBaseConverter, fromBaseConverter, name, symbol);
  }

  public Power times(Unit<ElectricCurrent> current, String name, String symbol) {
    return new Power(toBaseUnits(1) * current.toBaseUnits(1), name, symbol);
  }

}
