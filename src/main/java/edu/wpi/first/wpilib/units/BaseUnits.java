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
    static final Unit<Distance> Distance = new Distance(1);

    /**
     * The standard unit of time, seconds.
     */
    static final Unit<Time> Time = new Time(1);

    /**
     * The standard unit of mass, grams.
     */
    static final Unit<Mass> Mass = new Mass(1);

    /**
     * The standard unit of angles, revolutions.
     */
    static final Unit<Angle> Angle = new Angle(1);

    /**
     * The standard "unitless" unit.
     */
    static final Unit<Unitless> Value = new Unitless(1);

    /**
     * The standard unit of electric potential, volts.
     */
    static final Unit<ElectricPotential> ElectricPotential = new ElectricPotential(1);

    /**
     * The standard unit of electric current, amperes.
     */
    static final Unit<ElectricCurrent> ElectricCurrent = new ElectricCurrent(1);

    /**
     * The standard unit of power, watts.
     */
    static final Unit<Power> Power = new Power(1);

}
