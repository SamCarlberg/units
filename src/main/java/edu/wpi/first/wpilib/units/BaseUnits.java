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
  public static final Distance Distance = new Distance(1, "Meter", "m");

  /**
   * The standard unit of time, seconds.
   */
  public static final Time Time = new Time(1, "Second", "s");

  /**
   * The standard unit of velocity, meters per second.
   */
  public static final Velocity<Distance> Velocity = new Velocity<>(1, "Meters per Second", "m/s");

  /**
   * The standard unit of mass, grams.
   */
  public static final Mass Mass = new Mass(1, "Kilogram", "Kg");

  /**
   * The standard unit of angles, revolutions.
   */
  public static final Angle Angle = new Angle(1, "Revolution", "R");

  /**
   * The standard "unitless" unit.
   */
  public static final Unitless Value = new Unitless(1, "<?>", "<?>");

  /**
   * The standard unit of electric potential, volts.
   */
  public static final ElectricPotential ElectricPotential = new ElectricPotential(1, "Volt", "V");

  /**
   * The standard unit of electric current, amperes.
   */
  public static final ElectricCurrent ElectricCurrent = new ElectricCurrent(1, "Amp", "A");

  /**
   * The standard unit of energy, joules.
   */
  public static final Energy Energy = new Energy(1, "Joule", "J");

  /**
   * The standard unit of power, watts.
   */
  public static final Power Power = new Power(1, "Watt", "W");

  public static final Temperature Temperature = new Temperature(x -> x, x -> x, "Kelvin", "K");

}
