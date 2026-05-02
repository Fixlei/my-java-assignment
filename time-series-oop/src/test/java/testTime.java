import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.timeclasses.Time;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class testTime {

  @BeforeAll
  public static void setUp() {
    Time time = new Time(12, 30, 00);
  }
  @Test
  public void test() {
    System.out.printf("Hello and welcome!");
  }

  @Test
  public void testCreateTime() {
    Time time = new Time();

    assertTrue("00:00:00".equals(time.toString()));
  }
  @Test
  public void testGetHour() {
    Time time = new Time(12, 30, 00);
    assertEquals(12, time.getHour());
  }
}
