package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for EventBlock Class
 */
class EventBlockTest {
  EventBlock eventSun950am10;
  EventBlock eventFri1206pm20;

  /**
   * setting up 2 eventBlocks
   */
  @BeforeEach
  void setup() {
    eventSun950am10 = new EventBlock("event1", "event1", DayOfWeek.SUNDAY,
        new Time(9, 50, DayNight.AM), 10);
    eventFri1206pm20 = new EventBlock("event2", "event2", DayOfWeek.FRIDAY,
        new Time(12, 06, DayNight.PM), 20);

  }

  /**
   * testing getStartTime method
   */
  @Test
  void getStartTime() {
    assertEquals("9:50 AM", eventSun950am10.getStartTime().toString());
    assertEquals("12:06 PM", eventFri1206pm20.getStartTime().toString());
  }

  /**
   * testing getDuration method
   */
  @Test
  void getDuration() {
    assertEquals(10, eventSun950am10.getDuration());
    assertEquals(20, eventFri1206pm20.getDuration());
  }

  /**
   * testing setStartTime method
   */
  @Test
  void setStartTime() {
    assertEquals("9:50 AM", eventSun950am10.getStartTime().toString());
    assertEquals("12:06 PM", eventFri1206pm20.getStartTime().toString());
    eventSun950am10.setStartTime(new Time(9, 55, DayNight.AM));
    assertEquals("9:55 AM", eventSun950am10.getStartTime().toString());
    eventFri1206pm20.setStartTime(new Time(10, 55, DayNight.PM));
    assertEquals("10:55 PM", eventFri1206pm20.getStartTime().toString());
  }

  /**
   * testing setDuration method
   */
  @Test
  void setDuration() {
    assertEquals(10, eventSun950am10.getDuration());
    assertEquals(20, eventFri1206pm20.getDuration());
    eventSun950am10.setDuration(100);
    eventFri1206pm20.setDuration(5);
    assertEquals(100, eventSun950am10.getDuration());
    assertEquals(5, eventFri1206pm20.getDuration());
  }

  /**
   * testing testEquals method
   */
  @Test
  void testEquals() {
    assertNotEquals(eventSun950am10, eventFri1206pm20);
    EventBlock eventSun950am10copy = new EventBlock("event1", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 10);
    assertEquals(eventSun950am10,  eventSun950am10copy);

    EventBlock eventSun950am10badCopy = new EventBlock("event1", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 11);
    assertNotEquals(eventSun950am10,  eventSun950am10badCopy);

    EventBlock eventSun950am10badCopy2 = new EventBlock("event1", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.PM), 10);
    assertNotEquals(eventSun950am10,  eventSun950am10badCopy2);

    EventBlock eventSun950am10badCopy3 = new EventBlock("event1", "event1",
        DayOfWeek.MONDAY, new Time(9, 50, DayNight.AM), 10);
    assertNotEquals(eventSun950am10,  eventSun950am10badCopy3);

    EventBlock eventSun950am10badCopy4 = new EventBlock("event1", "event2",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 10);
    assertNotEquals(eventSun950am10,  eventSun950am10badCopy4);

    EventBlock eventSun950am10badCopy5 = new EventBlock("event2", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 10);
    assertNotEquals(eventSun950am10,  eventSun950am10badCopy5);

    TaskBlock anotherType = new TaskBlock("asd", "asd", DayOfWeek.FRIDAY);
    assertNotEquals(eventSun950am10,  anotherType);
  }

  /**
   * testing hashCode method
   */
  @Test
  void testHashCode() {
    assertNotEquals(eventSun950am10.hashCode(), eventFri1206pm20.hashCode());
    EventBlock eventSun950am10copy = new EventBlock("event1", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 10);
    assertEquals(eventSun950am10.hashCode(),  eventSun950am10copy.hashCode());

    EventBlock eventSun950am10badCopy = new EventBlock("event1", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 11);
    assertNotEquals(eventSun950am10.hashCode(),  eventSun950am10badCopy.hashCode());

    EventBlock eventSun950am10badCopy2 = new EventBlock("event1", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.PM), 10);
    assertNotEquals(eventSun950am10.hashCode(),  eventSun950am10badCopy2.hashCode());

    EventBlock eventSun950am10badCopy3 = new EventBlock("event1", "event1",
        DayOfWeek.MONDAY, new Time(9, 50, DayNight.AM), 10);
    assertNotEquals(eventSun950am10.hashCode(),  eventSun950am10badCopy3.hashCode());

    EventBlock eventSun950am10badCopy4 = new EventBlock("event1", "event2",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 10);
    assertNotEquals(eventSun950am10.hashCode(),  eventSun950am10badCopy4.hashCode());

    EventBlock eventSun950am10badCopy5 = new EventBlock("event2", "event1",
        DayOfWeek.SUNDAY, new Time(9, 50, DayNight.AM), 10);
    assertNotEquals(eventSun950am10.hashCode(),  eventSun950am10badCopy5.hashCode());
  }
}
