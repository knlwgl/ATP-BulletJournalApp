package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;

/**
 * Represents a block for a task on a bullet journal
 */
public class TaskBlock extends AbstractBlock {
  private boolean isCompleted;

  /**
   * Creates a TaskBlock with a name, description, day of the week, start time, and duration
   *
   * @param name          task name
   *
   * @param description   task description
   *
   * @param dayOfTheWeek  task day of the week
   *
   * @param isCompleted   task completion status
   */
  @JsonCreator
  public TaskBlock(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("day") DayOfWeek dayOfTheWeek,
                   @JsonProperty("isCompleted") boolean isCompleted) {
    super(name, description, dayOfTheWeek);
    this.isCompleted = isCompleted;
  }

  /**
   * Creates a TaskBlock with a name, description, day of the week, start time, and duration
   *
   * @param name          task name
   *
   * @param description   task description
   *
   * @param dayOfTheWeek  task day of the week
   */
  public TaskBlock(String name, String description, DayOfWeek dayOfTheWeek) {
    this(name, description, dayOfTheWeek, false);
  }

  /**
   * Toggles whether this task is complete
   *
   * @return true if this task is completed
   */
  public boolean toggleComplete() {
    isCompleted = !isCompleted;
    return isCompleted;
  }

  /**
   * Returns whether this task is completed
   *
   * @return true if this task is completed
   */
  public boolean getIsCompleted() {
    return isCompleted;
  }

  /**
   * Returns whether the given object is equal to this task
   *
   * @param obj object to compare this task to
   *
   * @return true if the given object is equal to this task
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TaskBlock other)) {
      return false;
    }
    return this.getName().equals(other.getName())
        && this.getDescription().equals(other.getDescription())
        && this.getDay().equals(other.getDay())
        && this.isCompleted == other.isCompleted;
  }

  /**
   * Returns the hash code of this task
   *
   * @return the hash code of this task
   */
  @Override
  public int hashCode() {
    return this.getName().hashCode() + this.getDay().hashCode() + this.getDescription().hashCode();
  }

}
