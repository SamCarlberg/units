package edu.wpi.first.wpilib.units;

import edu.wpi.first.wpilib.units.distance.Inches;

import static edu.wpi.first.wpilib.units.Util.HUNDRED;
import static edu.wpi.first.wpilib.units.Util.IDENTITY;
import static edu.wpi.first.wpilib.units.Util.THOUSAND;

public enum Distance implements Unit<Distance> {

    INCHES("inches") {
        @Override
        public double multiplierTo(Distance otherUnit) {
            switch (otherUnit) {
                case INCHES:
                    return IDENTITY;
                case FEET:
                    return 1 / 12.0;
                case MILLIMETERS:
                    return 25.4;
                case CENTIMETERS:
                    return 2.54;
                case METERS:
                    return 0.0254;
                default:
                    throw new IllegalArgumentException("Unknown distance unit: " + otherUnit);
            }
        }

        @Override
        public Inches of(double magnitude) {
            return Inches.of(magnitude);
        }
    },

    FEET("feet") {
        @Override
        public double multiplierTo(Distance otherUnit) {
            return INCHES.multiplierTo(otherUnit) * 12.0; // sneaky
        }
    },

    MILLIMETERS("millimeters") {
        @Override
        public double multiplierTo(Distance otherUnit) {
            return METERS.multiplierTo(otherUnit) / THOUSAND; // sneaky
        }
    },

    CENTIMETERS("centimeters") {
        @Override
        public double multiplierTo(Distance otherUnit) {
            return METERS.multiplierTo(otherUnit) / HUNDRED; // sneaky
        }
    },

    METERS("meters") {
        @Override
        public double multiplierTo(Distance otherUnit) {
            switch (otherUnit) {
                case INCHES:
                    return 39.3701;
                case FEET:
                    return 3.28084;
                case MILLIMETERS:
                    return THOUSAND;
                case CENTIMETERS:
                    return HUNDRED;
                case METERS:
                    return IDENTITY;
                default:
                    throw new IllegalArgumentException("Unknown distance unit: " + otherUnit);
            }
        }
    };

    private final String unitName;

    Distance(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String unitName() {
        return unitName;
    }

}
