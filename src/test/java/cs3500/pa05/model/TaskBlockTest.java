package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import javafx.concurrent.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing TaskBlock class
 */
class TaskBlockTest {

  TaskBlock task1;
  TaskBlock task2;

  /**
   * setting up 2 taskblocks for testing
   */
  @BeforeEach
  void setup() {
    task1 = new TaskBlock("task1", "task1", DayOfWeek.FRIDAY);
    task2 = new TaskBlock("task2", "task2", DayOfWeek.SATURDAY);
  }

  /**
   * testing toggleComplete method
   */
  @Test
  void toggleComplete() {
    assertFalse(task1.getIsCompleted());
    task1.toggleComplete();
    assertTrue(task1.getIsCompleted());
    task1.toggleComplete();
    assertFalse(task1.getIsCompleted());
  }

  /**
   * testing getIsCompleted method
   */
  @Test
  void getIsCompleted() {
    task1 = new TaskBlock("task1", "task1", DayOfWeek.FRIDAY, false);
    assertFalse(task1.getIsCompleted());
    task1 = new TaskBlock("task1", "task1", DayOfWeek.FRIDAY, true);
    assertTrue(task1.getIsCompleted());
  }

  /**
   * testing equals method
   */
  @Test
  void testEquals() {
    assertNotEquals(task1, task2);
    TaskBlock task1copy = new TaskBlock("task1", "task1", DayOfWeek.FRIDAY);
    assertEquals(task1, task1copy);

    TaskBlock task1badcopy = new TaskBlock("task1", "task1", DayOfWeek.SATURDAY);
    assertNotEquals(task1, task1badcopy);

    TaskBlock task1badcopy2 = new TaskBlock("task1bad", "task1", DayOfWeek.FRIDAY);
    assertNotEquals(task1, task1badcopy2);

    TaskBlock task1badcopy3 = new TaskBlock("task1", "task1bad", DayOfWeek.FRIDAY);
    assertNotEquals(task1, task1badcopy3);

    Time anotherType = new Time(9, 50, DayNight.AM);
    assertNotEquals(task1, anotherType);
  }

  /**
   * testing hashCode method
   */
  @Test
  void testHashCode() {
    assertNotEquals(task1.hashCode(), task2.hashCode());
    TaskBlock task1copy = new TaskBlock("task1", "task1", DayOfWeek.FRIDAY);
    assertEquals(task1.hashCode(), task1copy.hashCode());

    TaskBlock task1badcopy = new TaskBlock("task1", "task1", DayOfWeek.SATURDAY);
    assertNotEquals(task1.hashCode(), task1badcopy.hashCode());

    TaskBlock task1badcopy2 = new TaskBlock("task1bad", "task1", DayOfWeek.FRIDAY);
    assertNotEquals(task1.hashCode(), task1badcopy2.hashCode());

    TaskBlock task1badcopy3 = new TaskBlock("task1", "task1bad", DayOfWeek.FRIDAY);
    assertNotEquals(task1.hashCode(), task1badcopy3.hashCode());
  }

  // testing the abstract methods
  /**
   * testing getName method
   */
  @Test
  void testGetName() {
    assertEquals("task1", task1.getName());
    assertEquals("task2", task2.getName());
  }

  /**
   * testing getDesc method
   */
  @Test
  void testGetDesc() {
    assertEquals("task1", task1.getDescription());
    assertEquals("task2", task2.getDescription());
  }

  /**
   * testing getDay method
   */
  @Test
  void testGetDay() {
    assertEquals(DayOfWeek.FRIDAY, task1.getDay());
    assertEquals(DayOfWeek.SATURDAY, task2.getDay());
  }

  /**
   * testing setName method
   */
  @Test
  void testSetName() {
    assertEquals("task1", task1.getName());
    assertEquals("task2", task2.getName());
    task1.setName("task1Name");
    assertEquals("task1Name", task1.getName());
    task2.setName("task2Name");
    assertEquals("task2Name", task2.getName());
  }

  /**
   * testing setDesc method
   */
  @Test
  void testSetDesc() {
    assertEquals("task1", task1.getDescription());
    assertEquals("task2", task2.getDescription());
    task1.setDesc("task1Desc");
    assertEquals("task1Desc", task1.getDescription());
    task2.setDesc("task2Desc");
    assertEquals("task2Desc", task2.getDescription());
  }

  /**
   * testing setDay method
   */
  @Test
  void testSetDay() {
    assertEquals(DayOfWeek.FRIDAY, task1.getDay());
    assertEquals(DayOfWeek.SATURDAY, task2.getDay());
    task1.setDay(DayOfWeek.MONDAY);
    assertEquals(DayOfWeek.MONDAY, task1.getDay());
    task2.setDay(DayOfWeek.TUESDAY);
    assertEquals(DayOfWeek.TUESDAY, task2.getDay());
  }
}