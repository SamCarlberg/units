package edu.wpi.first.wpilib.units;

import java.util.Objects;

public class FilteredMeasure<U extends Unit<U>> implements Measure<U> {
  private final MutableMeasure<U> rawMeasure;
  private final UnaryFunction filter;

  public FilteredMeasure(Measure<U> rawMeasure, UnaryFunction filter) {
    this.rawMeasure = MutableMeasure.mutable(rawMeasure);
    this.filter = Objects.requireNonNullElseGet(filter, () -> x -> x);
  }

  public Measure<U> update(Measure<U> newValue) {
    rawMeasure.setMagnitude(filter.apply(newValue.in(rawMeasure.unit())));
    return this;
  }

  @Override
  public double magnitude() {
    return rawMeasure.magnitude();
  }

  @Override
  public U unit() {
    return rawMeasure.unit();
  }

  @Override
  public Measure<U> times(double multiplier) {
    return rawMeasure.times(multiplier);
  }

  @Override
  public Measure<U> add(Measure<U> other) {
    return rawMeasure.add(other);
  }

  @Override
  public Measure<U> copy() {
    return rawMeasure.copy();
  }
}
