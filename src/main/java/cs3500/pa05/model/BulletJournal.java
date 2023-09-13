package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a bullet journal
 */
public class BulletJournal implements Model {
  @JsonProperty("tasks") private final Map<DayOfWeek, List<TaskBlock>> tasks;
  @JsonProperty("events") private final Map<DayOfWeek, List<EventBlock>> events;
  @JsonProperty("maxTasks") private int maxTask;
  @JsonProperty("maxEvents") private int maxEvent;

  /**
   * Constructs a (new) bullet journal (empty information)
   */
  public BulletJournal() {
    this.maxTask = -1;
    this.maxEvent = -1;
    this.tasks = new HashMap<>();
    this.events = new HashMap<>();
    for (DayOfWeek d : DayOfWeek.values()) {
      this.events.put(d, new ArrayList<>());
      this.tasks.put(d, new ArrayList<>());
    }
  }

  /**
   * Constructs a bullet journal with the given max number of tasks, max number of events,
   * map of week day to list of tasks, and map of week day to list of events
   *
   * @param maxTask max number of tasks
   *
   * @param maxEvent max number of events
   *
   * @param tasks map of week day to list of tasks
   *
   * @param events map of week day to list of events
   */
  public BulletJournal(@JsonProperty("maxTasks") int maxTask,
                       @JsonProperty("maxEvents") int maxEvent,
                       @JsonProperty("tasks") Map<DayOfWeek, List<TaskBlock>> tasks,
                       @JsonProperty("events")  Map<DayOfWeek, List<EventBlock>> events) {
    this.maxTask = maxTask;
    this.maxEvent = maxEvent;
    this.tasks = tasks;
    this.events = events;
  }

  /**
   * Gets the tasks of the week for this bullet journal
   *
   * @return each day of the week mapped to a list of tasks
   */
  @Override
  public Map<DayOfWeek, List<TaskBlock>> getTasks() {
    return tasks;
  }

  /**
   * Retrieves the events of the week
   *
   * @return each day of the week mapped to a list of events
   */
  @Override
  public Map<DayOfWeek, List<EventBlock>> getEvents() {
    return events;
  }

  /**
   * Adds an event to the week with the given name, description, day of the week, start time,
   * and duration
   *
   * @param name         name of the event
   *
   * @param description  description of the event
   *
   * @param dayOfTheWeek day of the week of the event
   *
   * @param start        start time of the event
   *
   * @param duration     duration of the event
   */
  @Override
  public void addEvent(String name, String description, DayOfWeek dayOfTheWeek, Time start,
                       int duration) {
    // get the list
    List<EventBlock> eventBlocks = events.get(dayOfTheWeek);
    // add to the list
    eventBlocks.add(new EventBlock(name, description, dayOfTheWeek, start, duration));
  }

  /**
   * Adds a task to the week
   *
   * @param name         name of the task
   *
   * @param description  description of the task
   *
   * @param dayOfTheWeek day of the week of the task
   */
  @Override
  public void addTask(String name, String description, DayOfWeek dayOfTheWeek) {
    // get the list
    List<TaskBlock> taskBlocks = tasks.get(dayOfTheWeek);
    // add to the list
    taskBlocks.add(new TaskBlock(name, description, dayOfTheWeek));
  }

  /**
   * Adds a task with the given information (name, description, and marker of completion) to the
   * given day of the week in this bullet journal
   *
   * @param name name of the task
   *
   * @param description description of the task
   *
   * @param dayOfTheWeek day of the week of the task
   *
   * @param isComplete true if the task is complete
   */
  @Override
  public void addTask(String name, String description, DayOfWeek dayOfTheWeek, boolean isComplete) {
    // get the list
    List<TaskBlock> taskBlocks = tasks.get(dayOfTheWeek);
    // add to the list
    taskBlocks.add(new TaskBlock(name, description, dayOfTheWeek, isComplete));
  }

  /**
   * Removes the given event
   *
   * @param event event to remove
   */
  @Override
  public void removeEvent(EventBlock event) {
    // for each keyset
    // go through each list and check the name, if equal, then remove
    for (List<EventBlock> blocks : events.values()) {
      blocks.removeIf(block -> block.equals(event));
    }
  }

  /**
   * Removes the given task
   *
   * @param task task to remove
   */
  @Override
  public void removeTask(TaskBlock task) {
    // for each keyset
    // go through each list and check the name, if equal, then remove
    for (List<TaskBlock> blocks : tasks.values()) {
      blocks.removeIf(block -> block.equals(task));
    }
  }

  /**
   * Checks if the max amount of events has been reached on a given day
   *
   * @param day day of the week to check if max events have been reached
   *
   * @return true if the max number of events has been reached for the given day
   */
  @Override
  public boolean areMaxEventsReached(DayOfWeek day) {
    return this.events.get(day).size() > this.maxEvent && this.maxEvent != -1;
  }

  /**
   * Checks if the max amount of tasks has been reached on a given day
   *
   * @param day day of the week to check if max tasks have been reached
   *
   * @return true if the max number of tasks has been reached for the given day
   */
  @Override
  public boolean areMaxTasksReached(DayOfWeek day) {
    return this.tasks.get(day).size() > this.maxTask && this.maxTask != -1;
  }

  /**
   * Updates the max amount of events allowed to the given max
   *
   * @param newMax new maximum number of events allowed
   */
  @Override
  public void updateMaxEvents(int newMax) {
    maxEvent = newMax;
  }

  /**
   * Updates the max number of tasks allowed to the given max
   *
   * @param newMax new maximum number of tasks allowed
   */
  @Override
  public void updateMaxTasks(int newMax) {
    maxTask = newMax;
  }

  /**
   * Gets the max amount of tasks for this week in the bullet journal
   *
   * @return the max amount of tasks for this week in the bullet journal
   */
  @Override
  public int getMaxTasks() {
    return this.maxTask;
  }

  /**
   * Gets the max amount of events for this week in the bullet journal
   *
   * @return the max amount of events for this week
   */
  @Override
  public int getMaxEvents() {
    return this.maxEvent;
  }

  /**
   * Gets a list of all events in this bullet journal
   *
   * @return a list of all events in this bullet journal
   */
  @Override
  public List<EventBlock> getAllEvents() {
    List<EventBlock> allEvents = new ArrayList<>();
    for (List<EventBlock> eventlist : this.events.values()) {
      allEvents.addAll(eventlist);
    }
    return allEvents;
  }

  /**
   * Gets a list of all tasks in this bullet journal
   *
   * @return a list of all tasks in this bullet journal
   */
  @Override
  public List<TaskBlock> getAllTasks() {
    List<TaskBlock> allTasks = new ArrayList<>();
    for (List<TaskBlock> taskList : this.tasks.values()) {
      allTasks.addAll(taskList);
    }
    return allTasks;
  }

  /**
   * Gets a list of all completed tasks in this bullet journal
   *
   * @return a list of all completed tasks in this bullet journal
   */
  @Override
  public List<TaskBlock> getCompletedTasks() {
    List<TaskBlock> completedTasks = this.getAllTasks();
    completedTasks.removeIf(taskBlock -> !taskBlock.getIsCompleted());
    return completedTasks;
  }
}
