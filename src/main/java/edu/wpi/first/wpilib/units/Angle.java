package edu.wpi.first.wpilib.units;

import static edu.wpi.first.wpilib.units.Util.IDENTITY;

public enum Angle implements Unit<Angle> {

    /**
     * Unit of angle in radians. 1 radian is equal to 1/2pi of a full rotation,
     * or approximately 57 {@link #DEGREES degrees}. This is also the SI standard unit
     * of angle measurement.
     */
    RADIANS("radians") {
        @Override
        public double multiplierTo(Angle otherUnit) {
            switch (otherUnit) {
                case RADIANS:
                    return IDENTITY;
                case DEGREES:
                    return 180 / Math.PI;
                default:
                    throw new IllegalArgumentException("Unknown angle unit: " + otherUnit);
            }
        }
    },

    /**
     * Unit of angle in degrees. 1 degree is equal to 1/360th of a full rotation,
     * or approximately 1/57th of a {@link #RADIANS radian}.
     */
    DEGREES("degrees") {
        @Override
        public double multiplierTo(Angle otherUnit) {
            switch (otherUnit) {
                case RADIANS:
                    return Math.PI / 180.0;
                case DEGREES:
                    return IDENTITY;
                default:
                    throw new IllegalArgumentException("Unknown angle unit: " + otherUnit);
            }
        }
    };

    private final String unitName;

    Angle(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String unitName() {
        return unitName;
    }

}
