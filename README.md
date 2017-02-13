# Units

A simple Java unit library to provide compile-time safety for units. No more of this:

```java
double distance = 12.34; // What unit is this?
```

Now you can have this:

```java
Measure<Distance> distance = Units.Inches.of(12.34); // It has to be inches!
```

And since `Inches` is a built-in unit, we can use a special subclass of `Measure<Distance>`

```java
Inches distance = Inches.of(12.34);
```

Note that this `Inches` class is _not_ the same thing as `Units.Inches`

Of course, units can be converted from one type to another, as long as they measure the same kind of thing!

```java
void doSomething(Measure<Distance> distance) {
  // Convert the distance to some known type that this class can use
  double inches = distance.as(Units.Inches);
  // ...
}
```


## Creating custom units

This library has many common units, but sometimes you just want to use furlongs or stone:

```java
Unit<Distance> Furlongs = Units.Feet.aggregate(660);
Unit<Mass> Stone = Units.Pounds.aggregate(14);
Unit<Mass> Milligram = Units.Grams.splitInto(1000);
```

#### `aggregate(double)` and `splitInto(double)`

`unit.aggregate(n)` creates a new unit that is equivalent to _n_ of the original unit; the new unit is the "aggregate" of _n_ of the original unit

`unit.splitInto(n)` is the reverse; it creates a new unit that is equivalent to 1 / _n_ of the original unit (the original unit is "split into" _n_ equal parts, each of which is equal to 1 of the resulting unit)

For example,
```java
Unit kilo = someUnit.aggregate(1000);
Unit milli = someUnit.splitInto(1000);
```

## Sample use

```java
interface Gyro {
  Measure<Angle> getAngle();
}

Gyro gyro = ...

Measure<Angle> angle = gyro.getAngle();
```

Generally, if you want to declare some constant measure (such as a target angle or distance), those should be declared using the specialized measure classes, eg `Inches targetDistance = Inches.of(...)`. This makes the code much clearer since the unit is determinable just by looking at the left-hand side of the declaration.

If you're just grabbing the output of a sensor and passing that to something that consumes that kind of measure (e.g. reading a voltage from an analog sensor and passing that to a motor), then it's okay to use a generic `Measure<ElectricPotential> sensorOutput = mySensor.getRawVoltage()`
