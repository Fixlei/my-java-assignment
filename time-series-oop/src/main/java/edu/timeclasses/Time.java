package edu.timeclasses;

import java.util.Objects;

/**
 * Task 1: Create Time.java
 * Implement a Time class that includes the following components:
 * Three private fields: hour, minute, and second (all of the type int).
 * Two Constructors:
 * No-argument constructor: Sets the default time to 00:00:00.
 * Parameterized constructor: Time(int hour, int minute, int second).
 * Three Getter methods: getHour(), getMinute(), and getSecond().
 * Three Setter methods: setHour(int), setMinute(int), and setSecond(int).
 * Note: Do not add validation logic in this section; it will be added in the next section.
 * Override toString(): The output format should be HH:MM:SS (two digits per unit, padded
 * with a leading zero if necessary).
 * <p>
 * Invariant: valid setter methods.
 * equals(), hashCode(), compareTo().equals / hashCode / Comparable.
 * Immutable Object: Immutability is the easiest way to make code thread-safe.
 */
public class Time implements Comparable<Time> {

  //Encapsulation
  private int hour;
  private int minute;
  private int second;

  /**
   * No-arg constructor: defaults to 00:00:00.
   */
  public Time() {
    this(0, 0, 0);
  }

  public Time(int hour, int minute, int second) {
    //using 'this' key word, exception cannot catch.eg: this.hour = hour;
    setHour(hour);
    setMinute(minute);
    setSecond(second);
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }

  public void setHour(int hour) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("Hour must be between 0 and 23");
    }
    this.hour = hour;
  }

  public void setMinute(int minute) {
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("Minute must be between 0 and 59");
    }
    this.minute = minute;
  }

  public void setSecond(int second) {
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException("Second must be between 0 and 59");
    }
    this.second = second;
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d:%02d", hour, minute, second);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Time)) {
      return false;
    }
    Time other = (Time) obj;
    return this.hour == other.hour && this.minute == other.minute && this.second == other.second;
  }

  @Override
  public int hashCode() {
    return Objects.hash(hour, minute, second);
  }

  @Override
  public int compareTo(Time other) {
    //int thisSeconds = this.toSecondOfDay();
    //int otherSeconds = other.toSecondOfDay();
    Objects.requireNonNull(other, "Cannot compare to null");
    return Integer.compare(this.toSecondOfDay(), other.toSecondOfDay());
  }

  public int toSecondOfDay() {
    return 3600 * hour + minute * 60 + second;
  }
}
