package cs3500.pa05.model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

/**
 * Represents a model for a bullet journal week
 */
public interface Model {


  /**
   * Gets the tasks of the week
   *
   * @return a map of each day of the week to its list of tasks
   */
  Map<DayOfWeek, List<TaskBlock>> getTasks();

  /**
   * Gets the events of the week
   *
   * @return a map of each day  of the week to its list of events
   */
  Map<DayOfWeek, List<EventBlock>> getEvents();

  /**
   * Adds an event with the given components (name, description, day of the week, start time, and
   * duration) to this bullet journal
   *
   * @param name name of the event
   *
   * @param description description of the event
   *
   * @param dayOfTheWeek day of the week of the event
   *
   * @param start start time of the event
   *
   * @param duration duration of the event
   */
  void addEvent(String name, String description, DayOfWeek dayOfTheWeek, Time start, int duration);

  /**
   * Adds a task with the given components (name, description, and day of the week) to this bullet
   * journal
   *
   * @param name name of the task
   *
   * @param description description of the task
   *
   * @param dayOfTheWeek day of the week of the task
   */
  void addTask(String name, String description, DayOfWeek dayOfTheWeek);

  /**
   * Adds a task with the given components (name, descripton, and day of the week
   *
   * @param name of the task
   *
   * @param description of the task
   *
   * @param dayOfTheWeek of the task to be done
   *
   * @param isComplete is the task complete
   */
  void addTask(String name, String description, DayOfWeek dayOfTheWeek, boolean isComplete);

  /**
   * Removes the given event
   *
   * @param event event to remove
   */
  void removeEvent(EventBlock event);

  /**
   * Removes the given task
   *
   * @param task task to remove
   */
  void removeTask(TaskBlock task);

  /**
   * Checks if the max amount of events has been reached on a given day
   *
   * @param day day of the week to check if max events have been reached
   *
   * @return true if the max number of events has been reached for the given day
   */
  boolean areMaxEventsReached(DayOfWeek day);

  /**
   * Checks if the max amount of tasks has been reached on a given day
   *
   * @param day day of the week to check if max tasks have been reached
   *
   * @return true if the max number of tasks has been reached for the given day
   */
  boolean areMaxTasksReached(DayOfWeek day);

  /**
   * Updates the max amount of events allowed to the given max
   *
   * @param newMax new maximum number of events allowed
   */
  void updateMaxEvents(int newMax);

  /**
   * Updates the max number of tasks allowed to the given max
   *
   * @param newMax new maximum number of tasks allowed
   */
  void updateMaxTasks(int newMax);

  /**
   * Gets the max amount of tasks for this week in the bullet journal
   *
   * @return the max amount of tasks for this week in the bullet journal
   */
  int getMaxTasks();

  /**
   * Gets the max amount of events for this week in the bullet journal
   *
   * @return the max amount of events for this week
   */
  int getMaxEvents();

  /**
   * Gets a list of all events in this bullet journal
   *
   * @return a list of all events in this bullet journal
   */
  List<EventBlock> getAllEvents();

  /**
   * Gets a list of all tasks in this bullet journal
   *
   * @return a list of all tasks in this bullet journal
   */
  List<TaskBlock> getAllTasks();

  /**
   * Gets a list of all completed tasks in this bullet journal
   *
   * @return a list of all completed tasks in this bullet journal
   */
  List<TaskBlock> getCompletedTasks();
}
