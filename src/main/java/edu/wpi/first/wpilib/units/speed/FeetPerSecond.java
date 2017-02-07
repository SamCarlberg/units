package edu.wpi.first.wpilib.units.speed;

import edu.wpi.first.wpilib.units.Measure;
import edu.wpi.first.wpilib.units.Speed;

public class FeetPerSecond extends Measure<Speed> {

    private FeetPerSecond(double value) {
        super(value, Speed.FEET_PER_SECOND);
    }

    public static FeetPerSecond of(double value) {
        return new FeetPerSecond(value);
    }

    public static FeetPerSecond of(Measure<Speed> measure) {
        return new FeetPerSecond(measure.as(Speed.FEET_PER_SECOND).magnitude());
    }

    public MilesPerHour asMph() {
        return MilesPerHour.of(this);
    }

    public MetersPerSecond asMps() {
        return MetersPerSecond.of(this);
    }

}
