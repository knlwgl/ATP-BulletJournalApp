package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.DayOfWeek;

/**
 * Represents a block for an event on a bullet journal
 */
public class EventBlock extends AbstractBlock {
  private Time startTime;
  private int duration;

  /**
   * Creates an EventBlock with a name, description, day of the week, start time, and duration
   *
   * @param name          event name
   *
   * @param description   event description
   *
   * @param dayOfTheWeek  event day of the week
   *
   * @param startTime     event start time
   *
   * @param duration      event duration
   */
  @JsonCreator
  public EventBlock(@JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("day") DayOfWeek dayOfTheWeek,
                    @JsonProperty("startTime") Time startTime,
                    @JsonProperty("duration") int duration) {
    super(name, description, dayOfTheWeek);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Gets the start time of this event
   *
   * @return start time of this event
   */
  public Time getStartTime() {
    return startTime;
  }

  /**
   * Gets the duration of this event
   *
   * @return the duration of this event (as an int)
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Gets the start time of this event
   *
   * @param startTime start time of this event
   */
  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets the duration of this event
   *
   * @param duration duration of this event
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Determines whether the given Object is equal to this EventBlock
   *
   * @param obj Object to compare against
   *
   * @return true if the given Object is the equal to this EventBlock
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof EventBlock other)) {
      return false;
    }
    return this.getName().equals(other.getName())
        && this.getDescription().equals(other.getDescription())
        && this.getDay().equals(other.getDay())
        && this.getStartTime().equals(other.getStartTime())
        && this.getDuration() == other.getDuration();
  }

  /**
   * Gets the hash code of this EventBlock
   *
   * @return the hash code of this event block
   */
  @Override
  public int hashCode() {
    return this.getName().hashCode() + this.getDay().hashCode() + this.getDescription().hashCode()
        + this.getStartTime().hashCode() + this.getDuration() * 1000;
  }
}
