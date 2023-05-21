package edu.wpi.first.wpilib.units;

/**
 * The base units of measure. These are not necessarily the SI standard units; notably, the <i>gram</i> is
 * used as a base unit rather than the SI standard <i>kilogram</i>.
 */
final class BaseUnits {

  private BaseUnits() {
    // Prevent instantiation
  }

  /**
   * The standard unit of distance, meters.
   */
  static final Distance Distance = new Distance(1);

  /**
   * The standard unit of time, seconds.
   */
  static final Time Time = new Time(1);

  /**
   * The standard unit of velocity, meters per second.
   */
  static final Velocity Velocity = new Velocity(1);

  /**
   * The standard unit of acceleration, meters per second per second (m/s<sup>2</sup>).
   */
  static final Acceleration Acceleration = new Acceleration(1);

  /**
   * The standard unit of mass, grams.
   */
  static final Mass Mass = new Mass(1);

  /**
   * The standard unit of angles, revolutions.
   */
  static final Angle Angle = new Angle(1);

  /**
   * The standard "unitless" unit.
   */
  static final Unitless Value = new Unitless(1);

  /**
   * The standard unit of electric potential, volts.
   */
  static final ElectricPotential ElectricPotential = new ElectricPotential(1);

  /**
   * The standard unit of electric current, amperes.
   */
  static final ElectricCurrent ElectricCurrent = new ElectricCurrent(1);

  /**
   * The standard unit of power, watts.
   */
  static final Power Power = new Power(1);

  static final Temperature Temperature = new Temperature(x -> x, x -> x);

}
