# Units

A simple Java unit library to provide compile-time safety for units. No more of this:

```java
double distance = 12.34; // What unit is this?
```

Now you can have this:

```java
Feet distance = Feet.of(12.34); // It has to be feet!

// or

Feet distance = Distance.FEET.of(12.34);

```

A measure holds a magnitude (`magnitude`) and a unit (`FEET`, et cetera). All unit types are enums, similar to `java.util.concurrent.TimeUnit`.

## Sample use

Two examples. One with a more generic interface that allows for the class to return any unit it wants, and a more concrete interface that requires it to return a specific unit but allows easier conversion on the user side.

##### More generic

```java
interface Gyro {
  Measure<Angle> getAngle();
}

Gyro gyro = ...

double degrees = gyro.getAngle().as(Angle.DEGREES);

```

##### More concrete
```java
interface Gyro {
  Radians getAngle();
}

Gyro gyro = ...

double degrees = gyro.getAngle().asDegrees();

```
