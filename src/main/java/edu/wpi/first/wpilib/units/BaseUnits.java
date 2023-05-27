package edu.wpi.first.wpilib.units;

/**
 * The base units of measure. These are not necessarily the SI standard units; notably, the <i>gram</i> is
 * used as a base unit rather than the SI standard <i>kilogram</i>.
 */
public final class BaseUnits {

  private BaseUnits() {
    // Prevent instantiation
  }

  /**
   * The standard unit of distance, meters.
   */
  public static final Distance Distance = new Distance(1);

  /**
   * The standard unit of time, seconds.
   */
  public static final Time Time = new Time(1);

  /**
   * The standard unit of velocity, meters per second.
   */
  public static final Velocity<Distance> Velocity = new Velocity<>(1);

  /**
   * The standard unit of mass, grams.
   */
  public static final Mass Mass = new Mass(1);

  /**
   * The standard unit of angles, revolutions.
   */
  public static final Angle Angle = new Angle(1);

  /**
   * The standard "unitless" unit.
   */
  public static final Unitless Value = new Unitless(1);

  /**
   * The standard unit of electric potential, volts.
   */
  public static final ElectricPotential ElectricPotential = new ElectricPotential(1);

  /**
   * The standard unit of electric current, amperes.
   */
  public static final ElectricCurrent ElectricCurrent = new ElectricCurrent(1);

  /**
   * The standard unit of energy, joules.
   */
  public static final Energy Energy = new Energy(1);

  /**
   * The standard unit of power, watts.
   */
  public static final Power Power = new Power(1);

  public static final Temperature Temperature = new Temperature(x -> x, x -> x);

}
