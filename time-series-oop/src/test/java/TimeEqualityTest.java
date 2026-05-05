import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.timeclasses.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * equals() Testing:testEqualsReflexive — An object should return true when compared to itself.
 * testEqualsSymmetric — The result of a.equals(b) and b.equals(a) should be identical.
 * testEqualsForDifferentTimes — equals() should return false for objects representing different times.
 * testEqualsWithNull — t.equals(null) should return false (and must not crash!).
 * testEqualsWithDifferentType — t.equals("string") should return false when compared to a different class.
 * testEqualsForSameValues — Two objects with the same values (e.g., 10, 20, 30) should return true.
 * hashCode() Testing:7.  testEqualObjectsHaveSameHashCode — Two objects that are equals() must
 * return the same hashCode().compareTo().
 * Testing:8.  testCompareToWhenLess — An earlier time should be less than a later time (return a negative integer).
 * 9.  testCompareToWhenGreater — A later time should be greater than an earlier time (return a positive integer).
 * 10. testCompareToWhenEqual — Return 0 when the times are identical.
 * 11. testCompareToWithSorting — After adding multiple Time objects to a List and calling
 * Collections.sort(), the order should be correct.
 */
public class TimeEqualityTest {
  @Test
  public void testEqualsReflexive() {
    Time time = new Time(11, 20, 33);
    assertTrue(time.equals(time));
  }

  @Test
  public void testEqualsSymmetric() {
    Time time = new Time(11, 20, 33);
    Time time2 = new Time(11, 20, 33);
    assertTrue(time.equals(time2));
    assertTrue(time2.equals(time));
  }

  @Test
  public void testEqualsWithNull() {
    Time time = new Time();
    assertFalse(time.equals(null));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    Time time = new Time(11, 20, 33);
    assertFalse(time.equals(new Object()));
  }

  @Test
  public void testEqualsWithSameClass() {
    Time time = new Time(11, 20, 33);
    Time time2 = new Time(11, 20, 33);
    assertTrue(time.equals(time2));

  }

  @Test
  public void testEqualObjectsHaveSameHashCode() {
    Time time = new Time(11, 20, 33);
    Time time2 = new Time(11, 20, 33);
    assertTrue(time.equals(time2));
    assertEquals(time.hashCode(), time2.hashCode());
  }

  @Test
  public void testCompareToWhenLess() {
    Time time1 = new Time(11, 20, 33);
    Time time2 = new Time(11, 20, 33);
    Time time3 = new Time(13, 20, 33);
    assertTrue(time1.compareTo(time2) == 0);
    assertTrue(time1.compareTo(time3) < 0);
    assertTrue(time2.compareTo(time3) < 0);
    assertEquals(0, time3.compareTo(new Time(13, 20, 33)));
  }

  @Test
  public void testCompareToWhenGreater() {
    Time time2 = new Time(11, 20, 33);
    Time time3 = new Time(13, 20, 33);
    assertTrue(time3.compareTo(time2) > 0);
  }

  @Test
  public void testCompareToWhenEqual() {
    Time time1 = new Time(11, 20, 33);
    Time time2 = new Time(11, 20, 33);

    assertTrue(time1.compareTo(time2) == 0);
  }

  @Test
  public void testCompareToWithSorting() {
    Time time1 = new Time(10, 20, 33);
    Time time2 = new Time(11, 20, 33);
    Time time3 = new Time(13, 20, 33);
    List<Time> timeList = new ArrayList<>();
    timeList.add(time1);
    timeList.add(time2);
    timeList.add(time3);
    Collections.sort(timeList);
    assertEquals(timeList.get(0), time1);
    assertEquals(timeList.get(1), time2);
    assertEquals(timeList.get(2), time3);

    assertTrue(timeList.get(0).compareTo(timeList.get(1)) < 0);
    assertTrue(timeList.get(1).compareTo(timeList.get(2)) < 0);
    assertTrue(timeList.get(0).compareTo(timeList.get(2)) < 0);
  }

  @Test
  public void testTransitive() {
    Time time1 = new Time(11, 20, 33);
    Time time2 = new Time(11, 20, 33);
    Time time3 = new Time(11, 20, 33);
    assertTrue(time1.equals(time2));
    assertTrue(time1.equals(time3));
    assertTrue(time2.equals(time3));
  }
}
