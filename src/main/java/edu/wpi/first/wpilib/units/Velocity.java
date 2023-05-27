package edu.wpi.first.wpilib.units;

import java.util.HashMap;
import java.util.Map;

public class Velocity<D extends Unit<D>> extends Unit<Velocity<D>> {

  /**
   * Stores velocity units that were created ad-hoc using {@link #combine(Unit, Time)}. Does not store
   * objects created directly by constructors.
   */
  // TODO: Move this to use primitive long keys instead of boxed values to avoid allocations
  private static final Map<Long, Velocity> cache = new HashMap<>(32);

  /**
   * Generates a cache key used for cache lookups. Still triggers an allocation of a Long (thanks, java)
   */
  private static long cacheKey(Unit<?> numerator, Unit<?> denominator) {
    return ((long) numerator.hashCode()) << 32L | denominator.hashCode();
  }

  /**
   * Creates a new velocity unit derived from an arbitrary numerator and time period units.
   *
   * <p>Results of this method are cached so future invocations with the same arguments will
   * return the pre-existing units instead of generating new identical ones.</p>
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
   * @param period the period for unit time
   * @return
   * @param <D> the type of the numerator unit
   */
  @SuppressWarnings("unchecked")
  public static <D extends Unit<D>> Velocity<D> combine(Unit<D> numerator, Time period) {
    Long key = cacheKey(numerator, period); // intentionally boxing here to avoid autoboxing allocations later
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    Velocity<D> velocity = new Velocity<>(numerator.toBaseUnits(1) / period.toBaseUnits(1));
    cache.put(key, velocity);
    return velocity;
  }

  Velocity(double baseUnitEquivalent) {
    super((Class) Velocity.class, baseUnitEquivalent);
  }

  Velocity(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super((Class) Velocity.class, toBaseConverter, fromBaseConverter);
  }

  protected Velocity(Class<? extends Velocity<D>> baseType, double baseUnitEquivalent) {
    super(baseType, baseUnitEquivalent);
  }

  protected Velocity(Class<? extends Velocity<D>> baseType, UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter) {
    super(baseType, toBaseConverter, fromBaseConverter);
  }

}
