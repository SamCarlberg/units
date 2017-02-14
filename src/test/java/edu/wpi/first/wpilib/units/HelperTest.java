package edu.wpi.first.wpilib.units;

import edu.wpi.first.wpilib.units.electriccurrent.Amps;
import edu.wpi.first.wpilib.units.electricpotental.Volts;
import edu.wpi.first.wpilib.units.power.Milliwatts;
import edu.wpi.first.wpilib.units.power.Watts;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelperTest {

    @Test
    public void testWatts() {
        assertEquals(Watts.of(10), Watts.of(Volts.of(100), Amps.of(0.1)));
    }

    @Test
    public void testMilliwatts() {
        assertEquals(Milliwatts.of(10_000), Milliwatts.of(Volts.of(100), Amps.of(0.1)));
    }

}
