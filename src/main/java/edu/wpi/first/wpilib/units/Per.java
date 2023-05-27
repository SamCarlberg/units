package edu.wpi.first.wpilib.units;

/**
 * Generic combinatory unit type that represents the proportion of one unit to another, such as Meters per Second
 * or Radians per Celsius.
 *
 * <p>Note that due to restrictions with the Java type system, velocities (change per unit time) are represented
 * by the {@link Velocity} class. Accelerations are represented by {@code Velocity<Velocity<X>>}, and so on.
 *
 * @param <N> the type of the numerator unit
 * @param <D> the type of the denominator unit
 */
public class Per<N extends Unit<N>, D extends Unit<D>> extends Unit<Per<N, D>> {
  private final Unit<? extends N> numerator;
  private final Unit<? extends D> denominator;

  protected Per(Class<? extends Per<N, D>> baseType, Unit<? extends N> numerator, Unit<? extends D> denominator) {
    super(baseType, numerator.toBaseUnits(1) / denominator.toBaseUnits(1));
    this.numerator = numerator;
    this.denominator = denominator;
  }

  /**
   * Creates a new velocity unit derived from an arbitrary numerator and time denominator units.
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
   * this factory directly.
   *
   * @param numerator the numerator unit
   * @param denominator the denominator for unit time
   * @return
   * @param <N> the type of the numerator unit
   */
  public static <N extends Unit<N>, D extends Unit<D>> Per<N, D> combine(Unit<N> numerator, Unit<D> denominator) {
    return new Per<N, D>(
        (Class) Per.class,
        numerator,
        denominator
    );
  }

  public Unit<? extends N> numerator() {
    return numerator;
  }

  public Unit<? extends D> denominator() {
    return denominator;
  }
}
