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