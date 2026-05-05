import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.timeclasses.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTime {
  private Time timeShare;

  @BeforeEach
  public void setUp() {
    timeShare = new Time(12, 30, 00);
  }

  @Test
  public void testSetUp() {
    System.out.println(timeShare);
    assertEquals("12:30:00", timeShare.toString());
  }

  @Test
  public void testCreateTime() {
    Time time = new Time();
    assertEquals("00:00:00", time.toString());
  }

  @Test
  public void testGetHour() {
    Time time = new Time(12, 30, 00);
    assertEquals(12, time.getHour());
  }

  @Test
  public void testGetMinute() {
    Time time = new Time(12, 30, 00);
    assertEquals(30, time.getMinute());
  }

  @Test
  public void testGetSecond() {
    Time time = new Time(12, 30, 00);
    assertEquals(00, time.getSecond());
  }

  @Test
  public void testSameTime() {
    Time time = new Time(2, 3, 9);
    Time time2 = new Time(02, 03, 9);

    assertEquals(time.toString(), time2.toString());
  }

  @Test
  public void testInvalidHourSetting() {
    assertThrows(IllegalArgumentException.class, () -> {
      Time time = new Time(12, 30, 00);
      time.setHour(25);
    });
  }

  @Test
  public void testInvalidMinuteSetting() {
    assertThrows(IllegalArgumentException.class, () -> {
      Time time = new Time(12, 30, 00);
      time.setMinute(69);
    });
  }

  @Test
  public void testInvalidSecondSetting() {
    assertThrows(IllegalArgumentException.class, () -> {
      Time time = new Time(12, 30, 00);
      time.setSecond(69);
    });
  }

  /* -- invariant object testing -- */
  @Test
  public void testFailedSetterDoesNotCorruptObj() {
    Time time = new Time(10, 20, 30);
    assertThrows(IllegalArgumentException.class, () -> {
      time.setHour(25);
    });
    assertEquals(10, time.getHour());
    assertEquals(20, time.getMinute());
    assertEquals(30, time.getSecond());
    assertEquals("10:20:30", time.toString());
  }

  @Test
  public void testExceptionMsg() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Time(65, 0, 0)
    );
    assertTrue(exception.getMessage().contains("Hour"));
    assertTrue(exception.getMessage().toLowerCase().contains("23"));
  }


  @Test
  public void testEqualTimes() {
    Time time1 = new Time(12, 30, 10);
    Time time2 = new Time();
    time2.setHour(12);
    time2.setMinute(30);
    time2.setSecond(10);
    assertEquals(time1, time2);
  }
}
