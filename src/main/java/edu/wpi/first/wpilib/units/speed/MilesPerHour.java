package edu.wpi.first.wpilib.units.speed;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Speed;

public class MilesPerHour extends Measure<Speed> {

    private MilesPerHour(double value) {
        super(value, Speed.MILES_PER_HOUR);
    }

    public static MilesPerHour of(double value) {
        return new MilesPerHour(value);
    }

    public static MilesPerHour of(Measure<Speed> measure) {
        return new MilesPerHour(measure.as(Speed.MILES_PER_HOUR).magnitude());
    }

    public FeetPerSecond asFps() {
        return FeetPerSecond.of(this);
    }

    public MetersPerSecond asMps() {
        return MetersPerSecond.of(this);
    }

}
