package edu.wpi.first.wpilib.units;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Velocity<D extends Unit<D>> extends Unit<Velocity<D>> {

  private final D unit;
  private final Time period;

  /**
   * Stores velocity units that were created ad-hoc using {@link #combine(Unit, Time, String, String)}. Does not store
   * objects created directly by constructors.
   */
  // TODO: Move this to use primitive long keys instead of boxed values to avoid allocations
  private static final Map<Long, Velocity> cache = new HashMap<>(32);

  /**
   * Generates a cache key used for cache lookups. Still triggers an allocation of a Long (thanks, java)
   */
  private static long cacheKey(Unit<?> numerator, Unit<?> denominator) {
    return ((long) numerator.hashCode()) << 32L | ((long) denominator.hashCode()) & 0xFFFFFFFFL;
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
   * @param <D>       the type of the numerator unit
   * @param numerator the numerator unit
   * @param period    the period for unit time
   * @param name
   * @param symbol
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <D extends Unit<D>> Velocity<D> combine(Unit<D> numerator, Time period, String name, String symbol) {
    Long key = cacheKey(numerator, period); // intentionally boxing here to avoid autoboxing allocations later
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    Velocity<D> velocity = new Velocity<>((D) numerator, period, name, symbol);
    cache.put(key, velocity);
    return velocity;
  }

  @SuppressWarnings("unchecked")
  public static <D extends Unit<D>> Velocity<D> combine(Unit<D> numerator, Time period) {
    Long key = cacheKey(numerator, period); // intentionally boxing here to avoid autoboxing allocations later
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    var name = numerator.name() + " per " + period.name();
    var symbol = numerator.symbol() + "/" + period.symbol();

    Velocity<D> velocity = new Velocity<>((D) numerator, period, name, symbol);
    cache.put(key, velocity);
    return velocity;
  }

  Velocity(D unit, Time period, String name, String symbol) {
    super((Class) Velocity.class, unit.toBaseUnits(1) / period.toBaseUnits(1), name, symbol);
    this.unit = unit;
    this.period = period;
  }

  Velocity(UnaryFunction toBaseConverter, UnaryFunction fromBaseConverter, String name, String symbol) {
    super((Class) Velocity.class, toBaseConverter, fromBaseConverter, name, symbol);
    this.unit = Units.anonymous();
    this.period = Units.Seconds;
  }

  public D getUnit() {
    return unit;
  }

  public Time getPeriod() {
    return period;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Velocity<?> velocity = (Velocity<?>) o;
    return unit.equals(velocity.unit) && period.equals(velocity.period);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), unit, period);
  }
}
