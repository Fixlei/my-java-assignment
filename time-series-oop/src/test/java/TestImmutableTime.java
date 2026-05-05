import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.timeimmutable.Time;
import org.junit.jupiter.api.Test;

public class TestImmutableTime {
  @Test
  public void testWithHourReturnsNewObject() {
    Time time = new Time(0, 0,0);
    Time timeNew = time.withHour(15); //if .withHour(0), it remain same time
    assertNotSame(time, timeNew);  //objects address not the same
    assertNotEquals(time, timeNew);  // value not equal
  }

  @Test
  public void testWithHourDoesNotMutateOriginal() {
    Time time = new Time(10, 10,0);
    Time timeNew = time.withHour(15); //if .withHour(0), it remain same time
    assertEquals(10, time.getHour()); //original time remain same object
    assertEquals(10, time.getMinute());
    assertEquals(0, time.getSecond());
  }

  @Test
  public void testWithHourPreservesOtherFields(){
    Time originalTime = new Time(10, 20,30);
    Time newTime= originalTime.withHour(15);
    assertEquals(10, originalTime.getHour());
    assertEquals(20, newTime.getMinute());
    assertEquals(30, newTime.getSecond());
  }

  @Test
  public void testWithHourValidatesInput () {
    Time time = new Time(10, 20,0);
    assertThrows(IllegalArgumentException.class, () -> {
      time.withHour(99);
    });
  }


  @Test
  public void testWithHourFailureDoesNotAffectOriginal () {
    Time time = new Time(10, 20,0);
   try{
      time.withHour(99);
    }catch(IllegalArgumentException e){

   }
   assertEquals(10, time.getHour());
   assertEquals(20, time.getMinute());
   assertEquals(0, time.getSecond());
  }

  /* -- static factory methods -- */
  @Test
  public void testStaticFactoryOf() {
    Time time = new Time(10, 20,0);
   Time timeNew = Time.createTime(10, 20, 30);
    assertEquals(10, time.getHour());
  }
  @Test
  public void testMidnightfactory(){
    //class name calls static method.
    Time midnight = Time.midnight();
    assertTrue((new Time(0,0,0)).equals(midnight));
    assertEquals("00:00:00", midnight.toString());
  }

  @Test
  public void testNoonFactory(){
    Time time = Time.noon();
    assertEquals(Time.noon(), new Time(12,0,0));
    assertEquals("12:00:00", Time.noon().toString());
  }

  @Test
  public void testToSecondOfDayMidnight(){
    Time time = Time.midnight();
    assertEquals(Time.midnight(), new Time(0,0,0));
    assertEquals("00:00:00", Time.midnight().toString());
  }

  @Test
  public void testSecondDayOfMidnight(){
    assertEquals(0, Time.midnight().toSecondOfDay());
  }
  @Test
  public void testSecondOfDayOneHour(){
    assertEquals(3600, Time.midnight().toSecondOfDay());
  }
  @Test
  public void testSecondOfDayMaxTime(){
    assertEquals(86399, (new Time(23,59,59)).toSecondOfDay());
  }@Test
  public void testChainWithMethods(){
    Time result = Time.createTime(10,29,30).withHour(0).withMinute(0).withSecond(0);

    assertEquals(Time.midnight(), result);
  }

}























