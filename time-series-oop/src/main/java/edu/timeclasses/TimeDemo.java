package edu.timeclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Task 2: Create TimeTest.java (Testing Class)
 * Write a main method to perform the following:
 * Create a no-argument Time object and print it — it should display 00:00:00.
 * Create a Time(9, 5, 3) object and print it — it should display 09:05:03.
 * Use a setter to change its hour to 15, then print it again — it should display 15:05:03.
 * Create a Time(23, 59, 59) object and print it — it should display 23:59:59.
 */
public class TimeDemo {
  public static void main(String[] args) {

    Time time = new Time();
    //00:00:00
    System.out.printf("============================================\n\nDefault time: " + time.toString() + "\n");


    Time time2 = new Time(9, 5, 3);
    System.out.println("Before SetHour: " + time2.toString());
    time2.setHour(15);
    System.out.println("After SetHour: " + time2.toString());

    Time time3 = new Time(23, 59, 59);
    System.out.println("Edge Cases: " + time3.toString());

    List<Time> timeList = new ArrayList<Time>();
    timeList.add(time);
    timeList.add(time2);
    timeList.add(time3);
    System.out.printf("====================================\n\n");
    for (Time t : timeList) {
      System.out.println(t.toString());
    }
  }
}