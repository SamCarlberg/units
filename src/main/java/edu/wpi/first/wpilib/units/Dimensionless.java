package edu.wpi.first.wpilib.units;

import static edu.wpi.first.wpilib.units.Util.HUNDRED;
import static edu.wpi.first.wpilib.units.Util.IDENTITY;
import static edu.wpi.first.wpilib.units.Util.inv;

public enum Dimensionless implements Unit<Dimensionless> {

    /**
     * Represents a unitless value such as a probability.
     */
    UNITY("unity") {
        @Override
        public double multiplierTo(Dimensionless otherUnit) {
            switch (otherUnit) {
                case UNITY:
                    return IDENTITY;
                case PERCENT:
                    return HUNDRED;
                default:
                    throw new IllegalArgumentException("Unknown dimensionless unit: " + otherUnit);
            }
        }
    },

    /**
     * Represents a unitless value as a percent of some total or maximum value.
     */
    PERCENT("percent") {
        @Override
        public double multiplierTo(Dimensionless otherUnit) {
            switch (otherUnit) {
                case UNITY:
                    return inv(HUNDRED);
                case PERCENT:
                    return IDENTITY;
                default:
                    throw new IllegalArgumentException("Unknown dimensionless unit: " + otherUnit);
            }
        }
    };

    private final String unitName;

    Dimensionless(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String unitName() {
        return unitName;
    }

}
