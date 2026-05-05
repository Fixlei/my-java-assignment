package edu.timeimmutable;

public final class Time implements Comparable<Time>{

  private final int hour;
  private final int minute;
  private final int second;

  public Time(int hour, int minute, int second) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("hour must be between 0 and 23");
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("minute must be between 0 and 59");
    }
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException("second must be between 0 and 59");
    }
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public static Time createTime(int hour, int minute, int second) {
    return new Time(hour, minute, second);  // copy object
  }

  /* -- wither method instead of setter -- */
  public Time withHour(int hour) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("hour must be between 0 and 23");
    }
    return new Time(hour, this.minute, this.second);
  }

  public Time withMinute(int minute) {
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("minute must be between 0 and 59");
    }
    return new Time(this.hour, minute, this.second);
  }

  public Time withSecond(int second) {
    if (second < 0 || second > 59) {
      throw new IllegalArgumentException("second must be between 0 and 59");
    }
    return new Time(this.hour, this.minute, second);
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

  //static methods belong to class not object's
  public static Time midnight() {
    return new Time(0, 0, 0); // copy object
  }

  public static Time noon() {
    return new Time(12, 0, 0); // copy object
  }

  public int toSecondOfDay() {
    return hour * 3600 + minute * 60 + second;
  }

  @Override
  public int compareTo(Time o) {
    return Integer.compare(this.toSecondOfDay(), o.toSecondOfDay());
  }
  @Override
  public boolean equals(Object other){
    if (this ==other) return true;
    if (other == null) return false;
    if(!(other instanceof Time)) return false;

    Time otherTime = (Time)other;
    return this.hour == ((Time) other).hour &&
        this.minute == ((Time) other).minute &&
        this.second == ((Time) other).second;
  }

  @Override
  public String toString(){
    return String.format("%02d:%02d:%02d", this.hour, this.minute, this.second);
  }
}
