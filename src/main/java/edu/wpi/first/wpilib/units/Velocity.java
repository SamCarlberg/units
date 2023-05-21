package edu.wpi.first.wpilib.units;

public class Velocity<D extends Unit<D>> extends Unit<Velocity<D>> {

  /**
   * Creates a new velocity unit derived from an arbitrary numerator and time period units.
   *
   * <pre>
   *   Velocity.combine(Kilograms, Second) // => mass flow
   *   Velocity.combine(Feet, Millisecond) // => linear speed
   *   Velocity.combine(Radians, Second) // => angular speed
   *
   *   Velocity.combine(Feet.per(Second), Second) // => linear acceleration in ft/s/s
   *   Velocity.combine(Radians.per(Second), Second) // => angular acceleration
   * </pre>
   *
   * <p>It's recommended to use the convenience function {@link Unit#per(Time)} instead of calling
   * this factory ddirectly.
   *
   * @param numerator the numerator unit
   * @param period the period for unit time
   * @return
   * @param <N> the type of the numerator unit
   */
  public static <N extends Unit<N>> Velocity<N> combine(Unit<N> numerator, Time period) {
    return new Velocity<N>(
        numerator.getConverterToBase().div(period.getConverterToBase().apply(1)),
        numerator.getConverterFromBase().div(period.getConverterFromBase().apply(1))
    );
  }

  Velocity(double baseUnitEquivalent) {
    super((Class) Velocity.class, baseUnitEquivalent);
  }

  Velocity(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super((Class) Velocity.class, toBaseConverter, fromBaseConverter);
  }

}
