package edu.wpi.first.wpilib.units;

import static edu.wpi.first.wpilib.units.Util.IDENTITY;
import static edu.wpi.first.wpilib.units.Util.SIXTY;
import static edu.wpi.first.wpilib.units.Util.THOUSAND;
import static edu.wpi.first.wpilib.units.Util.inv;

public enum Time implements Unit<Time> {

    /**
     * Unit of time in milliseconds, or 1/1000th of a second.
     */
    MILLISECONDS("milliseconds") {
        @Override
        public double multiplierTo(Time otherUnit) {
            switch (otherUnit) {
                case MILLISECONDS:
                    return IDENTITY;
                case SECONDS:
                    return inv(THOUSAND);
                case MINUTES:
                    return inv(SIXTY * THOUSAND);
                default:
                    throw new IllegalArgumentException("Unknown time unit: " + otherUnit);
            }
        }
    },

    /**
     * Unit of time in seconds. This is also the SI standard unit of time measurement.
     */
    SECONDS("seconds") {
        @Override
        public double multiplierTo(Time otherUnit) {
            switch (otherUnit) {
                case MILLISECONDS:
                    return THOUSAND;
                case SECONDS:
                    return IDENTITY;
                case MINUTES:
                    return inv(SIXTY);
                default:
                    throw new IllegalArgumentException("Unknown time unit: " + otherUnit);
            }
        }
    },

    /**
     * Unit of time in minutes. 1 minute is equal to 60 seconds.
     */
    MINUTES("minutes") {
        @Override
        public double multiplierTo(Time otherUnit) {
            switch (otherUnit) {
                case MILLISECONDS:
                    return SIXTY * THOUSAND;
                case SECONDS:
                    return SIXTY;
                case MINUTES:
                    return IDENTITY;
                default:
                    throw new IllegalArgumentException("Unknown time unit: " + otherUnit);
            }
        }
    };

    private final String unitName;

    Time(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String unitName() {
        return unitName;
    }

}
