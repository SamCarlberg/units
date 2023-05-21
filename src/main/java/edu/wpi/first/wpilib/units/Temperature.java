package edu.wpi.first.wpilib.units;

public class Temperature extends Unit<Temperature> {

  Temperature(UnaryFunction toBaseConverter,
              UnaryFunction fromBaseConverter) {
    super(Temperature.class, toBaseConverter, fromBaseConverter);
  }

}
