package edu.wpi.first.wpilib.units;

import java.util.HashMap;
import java.util.Map;

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
  private final N numerator;
  private final D denominator;

  /**
   * Keep a cache of created instances so expressions like Volts.per(Meter) don't do any allocations
   * after the first.
   */
  private static final Map<Long, Per> cache = new HashMap<>();

  protected Per(Class<Per<N, D>> baseType, N numerator, D denominator) {
    super(baseType, numerator.toBaseUnits(1) / denominator.toBaseUnits(1), numerator.name() + " per " + denominator.name(), numerator.symbol() + "/" + denominator.symbol());
    this.numerator = numerator;
    this.denominator = denominator;
  }

  /**
   * Creates a new Per unit derived from an arbitrary numerator and time denominator units.
   * Using a denominator with a unit of time is discouraged; use {@link Velocity} instead.
   *
   * <pre>
   *   Per.combine(Volts, Meters) // => possible PID constant
   * </pre>
   *
   * <p>It's recommended to use the convenience function {@link Unit#per(Unit)} instead of calling
   * this factory directly.
   *
   * @param numerator the numerator unit
   * @param denominator the denominator for unit time
   * @return
   * @param <N> the type of the numerator unit
   */
  @SuppressWarnings("unchecked")
  public static <N extends Unit<N>, D extends Unit<D>> Per<N, D> combine(N numerator, D denominator) {
    final Long key = ((long) numerator.hashCode()) << 32L | ((long) denominator.hashCode()) & 0xFFFFFFFFL;
    var existing = cache.get(key);
    if (existing != null) return existing;

    var newUnit = new Per<N, D>(
        (Class) Per.class,
        numerator,
        denominator
    );
    cache.put(key, newUnit);
    return newUnit;
  }

  public N numerator() {
    return numerator;
  }

  public D denominator() {
    return denominator;
  }
}
