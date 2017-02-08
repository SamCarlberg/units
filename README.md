# Units

A simple Java unit library to provide compile-time safety for units. No more of this:

```java
double distance = 12.34; // What unit is this?
```

Now you can have this:

```java
Measure<Distance> distance = Units.Inches.of(12.34); // It has to be inches!
```

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
Unit<Distance> Furlongs = Units.Feet.multiply(660);
Unit<Mass> Stone = Units.Pounds.multiply(14);
Unit<Mass> Milligram = Units.Grams.divide(1000);
```

## Sample use

```java
interface Gyro {
  Measure<Angle> getAngle();
}

Gyro gyro = ...

Measure<Angle> angle = gyro.getAngle();
```
