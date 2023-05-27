package edu.wpi.first.wpilib.units;

/**
 * A combinatory unit type that is equivalent to the product of two other others. For example, Newton * Meters for
 * torque could be represented as a unit of <code>Mult&lt;Force, Distance, Torque&gt;</code>
 * @param <A>
 * @param <B>
 */
public class Mult<A extends Unit<A>, B extends Unit<B>> extends Unit<Mult<A, B>> {
  private final A a;
  private final B b;

  protected Mult(Class<? extends Mult<A, B>> baseType, A a, B b) {
    super(baseType, a.toBaseUnits(1) * b.toBaseUnits(1), a.name() + " " + b.name(), a.symbol() + "" + b.symbol());
    this.a = a;
    this.b = b;
  }

  public static <A extends Unit<A>, B extends Unit<B>> Mult<A, B> combine(A a, B b) {
    return new Mult(Mult.class, a, b);
  }

  public A unitA() {
    return a;
  }

  public B unitB() {
    return b;
  }

  @Override
  public String toString() {
    return "(" + a.toString() + " * " + b.toString() + ")";
  }
}
