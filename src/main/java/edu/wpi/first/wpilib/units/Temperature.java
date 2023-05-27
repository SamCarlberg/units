package edu.wpi.first.wpilib.units;

public class Temperature extends Unit<Temperature> {

  Temperature(UnaryFunction toBaseConverter,
              UnaryFunction fromBaseConverter,
              String name,
              String symbol) {
    super(Temperature.class, toBaseConverter, fromBaseConverter, name, symbol);
  }

}
