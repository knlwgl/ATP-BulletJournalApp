package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing BulletJournal class
 */
class BulletJournalTest {

  Model emptyJournal;
  EventBlock testEventSunday;
  TaskBlock testTaskMonday;

  Model nonEmptyJournal;

  /**
   * setting up 2 bullet journals. One empty with no max limit, one non empty with max limit of 2
   */
  @BeforeEach
  void setup() {
    emptyJournal = new BulletJournal();

    // setting up a non empty BulletJournal
    testEventSunday = new EventBlock("testNameEvent", "testDescEvent",
        DayOfWeek.SUNDAY, new Time(9, 10, DayNight.AM), 60);
    testTaskMonday = new TaskBlock("testNameTask", "testDescTask", DayOfWeek.MONDAY);
    Map<DayOfWeek, List<EventBlock>> eventMap = new HashMap<>();
    Map<DayOfWeek, List<TaskBlock>> taskMap = new HashMap<>();
    for (DayOfWeek d : DayOfWeek.values()) {
      eventMap.put(d, new ArrayList<>());
      taskMap.put(d, new ArrayList<>());
    }
    eventMap.get(DayOfWeek.SUNDAY).add(testEventSunday);
    taskMap.get(DayOfWeek.MONDAY).add(testTaskMonday);
    nonEmptyJournal = new BulletJournal(2, 2, taskMap, eventMap);
  }

  /**
   * testing getTasks method
   */
  @Test
  void getTasks() {
    //emptyjournal empty task map
    for (DayOfWeek d : DayOfWeek.values()) {
      assertEquals(0, emptyJournal.getTasks().get(d).size());
    }
    // nonjournal
    assertEquals(1, nonEmptyJournal.getTasks().get(DayOfWeek.MONDAY).size());
  }

  /**
   * testing getEvents method
   */
  @Test
  void getEvents() {
    //emptyjournal empty event map
    for (DayOfWeek d : DayOfWeek.values()) {
      assertEquals(0, emptyJournal.getEvents().get(d).size());
    }

    assertEquals(1, nonEmptyJournal.getEvents().get(DayOfWeek.SUNDAY).size());
  }

  /**
   * testing addEvent method
   */
  @Test
  void addEvent() {
    // adding Event to empty journal
    assertEquals(0, emptyJournal.getAllEvents().size());
    assertEquals(0, emptyJournal.getEvents().get(DayOfWeek.SUNDAY).size());
    String name = "test";
    String description = "test";
    DayOfWeek day = DayOfWeek.SUNDAY;
    Time time = new Time(9, 11, DayNight.AM);
    int duration = 60;
    emptyJournal.addEvent(name, description, day, time, duration);
    assertEquals(1, emptyJournal.getAllEvents().size());
    // checking in map that it was added to the right key value
    assertEquals(1, emptyJournal.getEvents().get(DayOfWeek.SUNDAY).size());
  }

  /**
   * testing addTask method
   */
  @Test
  void addTask() {
    // adding Event to empty journal
    assertEquals(0, emptyJournal.getAllTasks().size());
    assertEquals(0, emptyJournal.getTasks().get(DayOfWeek.SUNDAY).size());
    String name = "test";
    String description = "test";
    DayOfWeek day = DayOfWeek.SUNDAY;
    emptyJournal.addTask(name, description, day);
    assertEquals(1, emptyJournal.getAllTasks().size());
    // checking in map that it was added to the right key value
    assertEquals(1, emptyJournal.getTasks().get(DayOfWeek.SUNDAY).size());
  }

  /**
   * testing addTask method with additional boolean constructor
   */
  @Test
  void testAddTask() {
    // adding Event to empty journal
    assertEquals(0, emptyJournal.getAllTasks().size());
    assertEquals(0, emptyJournal.getTasks().get(DayOfWeek.SUNDAY).size());
    String name = "test";
    String description = "test";
    DayOfWeek day = DayOfWeek.SUNDAY;
    emptyJournal.addTask(name, description, day, true);
    assertEquals(1, emptyJournal.getAllTasks().size());
    // checking in map that it was added to the right key value
    assertEquals(1, emptyJournal.getTasks().get(DayOfWeek.SUNDAY).size());
    // checking the boolean value
    assertTrue(emptyJournal.getTasks().get(DayOfWeek.SUNDAY).get(0).getIsCompleted());
  }

  /**
   * testing removeEvent method
   */
  @Test
  void removeEvent() {
    //removing an event in non empty journal
    assertEquals(1, nonEmptyJournal.getEvents().get(DayOfWeek.SUNDAY).size());
    nonEmptyJournal.removeEvent(testEventSunday);
    assertEquals(0, nonEmptyJournal.getEvents().get(DayOfWeek.SUNDAY).size());
  }

  /**
   * testing removeTask method
   */
  @Test
  void removeTask() {
    //removing an task in non empty journal
    assertEquals(1, nonEmptyJournal.getTasks().get(DayOfWeek.MONDAY).size());
    nonEmptyJournal.removeTask(testTaskMonday);
    assertEquals(0, nonEmptyJournal.getTasks().get(DayOfWeek.MONDAY).size());
  }

  /**
   * testing maxEventsExceeded method
   */
  @Test
  void maxEventsExceeded() {
    // emptyJournal has no max Events
    assertFalse(emptyJournal.areMaxEventsReached(DayOfWeek.MONDAY));
    // nonEmptyJournal max event is set to 2
    // Sunday : event1
    assertFalse(nonEmptyJournal.areMaxEventsReached(DayOfWeek.SUNDAY));
    // testing on a day of empty events
    assertFalse(nonEmptyJournal.areMaxEventsReached(DayOfWeek.TUESDAY));
    // adding 2 additional tasks to same day
    nonEmptyJournal.addTask("testTask2", "testTasks2", DayOfWeek.SUNDAY);
    nonEmptyJournal.addTask("testTask3", "testTasks3", DayOfWeek.SUNDAY);
    //Sunday : event1 testTask2, testTask3
    assertFalse(nonEmptyJournal.areMaxEventsReached(DayOfWeek.SUNDAY));
    // adding an event to sunday
    nonEmptyJournal.addEvent("testEvent2", "testEvent2", DayOfWeek.SUNDAY,
        new Time(10, 11, DayNight.PM), 5);
    //Sunday : event1 testEvent2, testTask2, testTask3
    assertFalse(nonEmptyJournal.areMaxEventsReached(DayOfWeek.SUNDAY));
    nonEmptyJournal.addEvent("testEvent3", "testEvent3", DayOfWeek.SUNDAY,
        new Time(11, 11, DayNight.PM), 5);
    assertTrue(nonEmptyJournal.areMaxEventsReached(DayOfWeek.SUNDAY));
  }

  /**
   * testing maxTasksExceeded method
   */
  @Test
  void maxTasksExceeded() {
    // emptyJournal has no max tasks
    assertFalse(emptyJournal.areMaxTasksReached(DayOfWeek.MONDAY));
    // nonEmptyJournal max task is set to 2
    // Monday : tasks1
    assertFalse(nonEmptyJournal.areMaxTasksReached(DayOfWeek.MONDAY));
    // testing on a day of empty tasks
    assertFalse(nonEmptyJournal.areMaxTasksReached(DayOfWeek.TUESDAY));
    // adding 2 additional events to Monday
    nonEmptyJournal.addEvent("testEvent", "testEvent", DayOfWeek.MONDAY,
        new Time(9, 50, DayNight.AM), 10);
    nonEmptyJournal.addEvent("testEvent2", "testEvent2", DayOfWeek.MONDAY,
        new Time(12, 6, DayNight.PM), 60);
    //Monday : tasks1 testEvent, testEvent2
    assertFalse(nonEmptyJournal.areMaxTasksReached(DayOfWeek.MONDAY));
    // adding an tasks to sunday
    nonEmptyJournal.addTask("testTask2", "testTask2", DayOfWeek.MONDAY);
    //Monday : tasks1 testEvent, testEvent2, task2
    assertFalse(nonEmptyJournal.areMaxTasksReached(DayOfWeek.MONDAY));
    nonEmptyJournal.addTask("testTask3", "testTask3", DayOfWeek.MONDAY);
    //Monday : tasks1 testEvent, testEvent2, task2, task3
    assertEquals(3, nonEmptyJournal.getAllTasks().size());
    assertTrue(nonEmptyJournal.areMaxTasksReached(DayOfWeek.MONDAY));
  }

  /**
   * testing updateMaxEvents method
   */
  @Test
  void updateMaxEvents() {
    assertEquals(2, nonEmptyJournal.getMaxEvents());
    nonEmptyJournal.updateMaxEvents(3);
    assertEquals(3, nonEmptyJournal.getMaxEvents());
    nonEmptyJournal.updateMaxEvents(2);
    assertEquals(2, nonEmptyJournal.getMaxEvents());

  }

  /**
   * testing updateMaxTasks method
   */
  @Test
  void updateMaxTasks() {
    assertEquals(2, nonEmptyJournal.getMaxTasks());
    nonEmptyJournal.updateMaxTasks(3);
    assertEquals(3, nonEmptyJournal.getMaxTasks());
    nonEmptyJournal.updateMaxTasks(2);
    assertEquals(2, nonEmptyJournal.getMaxTasks());
  }

  /**
   * testing getMaxTasks method
   */
  @Test
  void getMaxTasks() {
    assertEquals(-1, emptyJournal.getMaxTasks());
    assertEquals(2, nonEmptyJournal.getMaxTasks());
    nonEmptyJournal.updateMaxTasks(4);
    assertEquals(4, nonEmptyJournal.getMaxTasks());
  }

  /**
   * testing getMaxEvents method
   */
  @Test
  void getMaxEvents() {
    assertEquals(-1, emptyJournal.getMaxEvents());
    assertEquals(2, nonEmptyJournal.getMaxEvents());
    nonEmptyJournal.updateMaxEvents(3);
    assertEquals(3, nonEmptyJournal.getMaxEvents());
  }

  /**
   * testing getAllEvents method
   */
  @Test
  void getAllEvents() {
    assertEquals(0, emptyJournal.getAllEvents().size());
    assertEquals(1, nonEmptyJournal.getAllEvents().size());

  }

  /**
   * testing getAllTasks method
   */
  @Test
  void getAllTasks() {
    assertEquals(0, emptyJournal.getAllTasks().size());
    assertEquals(1, nonEmptyJournal.getAllTasks().size());
  }

  /**
   * testing getCompletedTasks method
   */
  @Test
  void getCompletedTasks() {
    // task is actually not completed
    assertEquals(1, nonEmptyJournal.getAllTasks().size());
    assertEquals(0, nonEmptyJournal.getCompletedTasks().size());
    // setting task to complete
    nonEmptyJournal.getAllTasks().get(0).toggleComplete();
    // now task is completed
    assertEquals(1, nonEmptyJournal.getCompletedTasks().size());
  }
}