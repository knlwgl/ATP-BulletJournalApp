package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing Time class
 */
class TimeTest {

  Time t950Am;
  Time t1206Pm;

  /**
   * setting up 2 Time variable for tests
   */
  @BeforeEach
  void setup() {
    t950Am = new Time(9, 50, DayNight.AM);
    t1206Pm = new Time(12, 6, DayNight.PM);
  }

  /**
   * testing toString method
   */
  @Test
  void testToString() {
    // testing AM and one digit hour
    assertEquals("9:50 AM", t950Am.toString());
    // testing PM and one digit minutes
    assertEquals("12:06 PM", t1206Pm.toString());
  }

  /**
   * testing getHours method
   */
  @Test
  void getHours() {
    // one digit
    assertEquals(9, t950Am.getHour());
    // two digit
    assertEquals(12, t1206Pm.getHour());
  }

  /**
   * testing getMinutes method
   */
  @Test
  void getMinutes() {
    // one digit
    assertEquals(6, t1206Pm.getMinute());
    // two digit
    assertEquals(50, t950Am.getMinute());
  }

  /**
   * testing getTimezone method
   */
  @Test
  void getTimezone() {
    // AM
    assertEquals(DayNight.AM, t950Am.getZone());
    // PM
    assertEquals(DayNight.PM, t1206Pm.getZone());
  }

  /**
   * testing setHours method
   */
  @Test
  void setHours() {
    // one digit
    assertEquals(9, t950Am.getHour());
    // two digit
    assertEquals(12, t1206Pm.getHour());

    t950Am.setHour(8);
    assertEquals(8, t950Am.getHour());
    t1206Pm.setHour(2);
    assertEquals(2, t1206Pm.getHour());
  }

  /**
   * testing setMinutes method
   */
  @Test
  void setMinutes() {
    // one digit
    assertEquals(6, t1206Pm.getMinute());
    // two digit
    assertEquals(50, t950Am.getMinute());

    t950Am.setMinute(8);
    assertEquals(8, t950Am.getMinute());
    t1206Pm.setMinute(20);
    assertEquals(20, t1206Pm.getMinute());
  }

  /**
   * testing setTimezone method
   */
  @Test
  void setTimezone() {
    // AM
    assertEquals(DayNight.AM, t950Am.getZone());
    // PM
    assertEquals(DayNight.PM, t1206Pm.getZone());

    t950Am.setZone(DayNight.PM);
    assertEquals(DayNight.PM, t950Am.getZone());
    t1206Pm.setZone(DayNight.AM);
    assertEquals(DayNight.AM, t1206Pm.getZone());
  }

  /**
   * testing equals method
   */
  @Test
  void testEquals() {
    assertNotEquals(t950Am, t1206Pm);
    Time t950copy = new Time(9, 50, DayNight.AM);
    assertEquals(t950Am, t950copy);

    Time t950badCopy = new Time(9, 50, DayNight.PM);
    assertNotEquals(t950Am, t950badCopy);

    Time t950badCopy2 = new Time(9, 51, DayNight.AM);
    assertNotEquals(t950Am, t950badCopy2);

    Time t950badCopy3 = new Time(10, 50, DayNight.AM);
    assertNotEquals(t950Am, t950badCopy3);

    TaskBlock anotherType = new TaskBlock("asd", "asd", DayOfWeek.FRIDAY);
    assertNotEquals(t950Am, anotherType);
  }

  /**
   * testing hashCode method
   */
  @Test
  void testHashCode() {
    assertNotEquals(t950Am.hashCode(), t1206Pm.hashCode());
    Time t950copy = new Time(9, 50, DayNight.AM);
    assertEquals(t950Am.hashCode(), t950copy.hashCode());

    Time t950badCopy = new Time(9, 50, DayNight.PM);
    assertNotEquals(t950Am.hashCode(), t950badCopy.hashCode());

    Time t950badCopy2 = new Time(9, 51, DayNight.AM);
    assertNotEquals(t950Am.hashCode(), t950badCopy2.hashCode());

    Time t950badCopy3 = new Time(10, 50, DayNight.AM);
    assertNotEquals(t950Am.hashCode(), t950badCopy3.hashCode());
  }
}