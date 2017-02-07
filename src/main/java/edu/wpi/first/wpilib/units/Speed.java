package edu.wpi.first.wpilib.units;

import static edu.wpi.first.wpilib.units.Util.IDENTITY;

public enum Speed implements Unit<Speed> {

    FEET_PER_SECOND("feet per second") {
        @Override
        public double multiplierTo(Speed otherUnit) {
            switch (otherUnit) {
                case FEET_PER_SECOND:
                    return IDENTITY;
                case MILES_PER_HOUR:
                    return 15 / 22.;
                case METERS_PER_SECOND:
                    return 0.3048;
                default:
                    throw new IllegalArgumentException("Unknown speed unit: " + otherUnit);
            }
        }
    },
    MILES_PER_HOUR("miles per hour") {
        @Override
        public double multiplierTo(Speed otherUnit) {
            switch (otherUnit) {
                case FEET_PER_SECOND:
                    return 22 / 15.;
                case MILES_PER_HOUR:
                    return IDENTITY;
                case METERS_PER_SECOND:
                    return 1609.34 / 3600;
                default:
                    throw new IllegalArgumentException("Unknown speed unit: " + otherUnit);
            }
        }
    },

    /**
     * Unit of speed in meters per second. This is the SI standard unit.
     */
    METERS_PER_SECOND("meters per second") {
        @Override
        public double multiplierTo(Speed otherUnit) {
            switch (otherUnit) {
                case FEET_PER_SECOND:
                    return 1 / 0.3048;
                case MILES_PER_HOUR:
                    return 3600 / 1609.34;
                case METERS_PER_SECOND:
                    return IDENTITY;
                default:
                    throw new IllegalArgumentException("Unknown speed unit: " + otherUnit);
            }
        }
    };

    private final String unitName;

    Speed(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String unitName() {
        return unitName;
    }

}
