package edu.wpi.first.wpilib.units;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

public final class Units {

    private Units() {
        // Prevent instantiation
    }

    // Pseudo-classes describing the more common units of measure.

    // Distance
    public static final Unit<Distance> Meters = BaseUnits.Distance;
    public static final Unit<Distance> Millimeters = Units.derive(Meters).fromBase(m -> m * 1e3).toBase(mm -> mm * 1e-3).make();
    public static final Unit<Distance> Centimeters = Units.derive(Meters).fromBase(m -> m * 1e2).toBase(cm -> cm * 1e-2).make();
    public static final Unit<Distance> Inches = Units.derive(Meters).fromBase(m -> m * 39.3701).toBase(in -> in / 39.3701).make();
    public static final Unit<Distance> Feet = Units.derive(Inches).fromBase(in -> in / 12).toBase(ft -> ft * 12).make();

    // Time
    public static final Unit<Time> Seconds = BaseUnits.Time;
    public static final Unit<Time> Milliseconds = Units.derive(Seconds).fromBase(s -> s * 1e3).toBase(ms -> ms * 1e-3).make();
    public static final Unit<Time> Minutes = Units.derive(Seconds).fromBase(s -> s * 60).toBase(min -> min / 60).make();

    // Mass
    public static final Unit<Mass> Grams = BaseUnits.Mass;
    public static final Unit<Mass> Kilograms = Grams.multiply(1e3);
    public static final Unit<Mass> Pounds = Grams.multiply(453.592);
    public static final Unit<Mass> Ounces = Pounds.divide(16);

    // Angle
    public static final Unit<Angle> Revolutions = BaseUnits.Angle;
    public static final Unit<Angle> Radians = Revolutions.divide(2 * Math.PI);
    public static final Unit<Angle> Degrees = Revolutions.divide(360);

    // Unitless
    public static final Unit<Unitless> Value = BaseUnits.Value;
    public static final Unit<Unitless> Percent = Value.multiply(100);

    @SuppressWarnings("unchecked")
    public static <U extends Unit<U>> UnitBuilder<U> derive(Unit<U> unit) {
        return new UnitBuilder<>((U) unit);
    }

    public static final class UnitBuilder<U extends Unit<U>> {

        private U base;
        private DoubleUnaryOperator fromBase;
        private DoubleUnaryOperator toBase;

        private UnitBuilder(U base) {
            this.base = Objects.requireNonNull(base, "Base unit cannot be null");
        }

        public UnitBuilder<U> fromBase(DoubleUnaryOperator fromBase) {
            this.fromBase = Objects.requireNonNull(fromBase, "fromBase function cannot be null");
            return this;
        }

        public UnitBuilder<U> toBase(DoubleUnaryOperator toBase) {
            this.toBase = Objects.requireNonNull(toBase, "toBase function cannot be null");
            return this;
        }

        public Unit<U> make() {
            Objects.requireNonNull(fromBase, "fromBase function was not set");
            Objects.requireNonNull(toBase, "toBase function was not set");
            return new Unit<>(base.getConverterToBase().andThen(toBase),
                              base.getConverterFromBase().andThen(fromBase));
        }

    }

}
