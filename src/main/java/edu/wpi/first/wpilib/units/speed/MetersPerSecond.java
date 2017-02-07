package edu.wpi.first.wpilib.units.speed;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Speed;

public class MetersPerSecond extends Measure<Speed> {

    private MetersPerSecond(double value) {
        super(value, Speed.METERS_PER_SECOND);
    }

    public static MetersPerSecond of(double value) {
        return new MetersPerSecond(value);
    }

    public static MetersPerSecond of(Measure<Speed> measure) {
        return new MetersPerSecond(measure.as(Speed.METERS_PER_SECOND).magnitude());
    }

    public FeetPerSecond asFps() {
        return FeetPerSecond.of(this);
    }

    public MilesPerHour asMph() {
        return MilesPerHour.of(this);
    }

}
