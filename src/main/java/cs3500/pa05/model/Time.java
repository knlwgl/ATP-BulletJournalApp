package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a time of the day (hour, minute, and AM/PM)
 */
public class Time {
  private int hour;
  private int minute;
  private DayNight dayNight;

  /**
   * Creates a Time object with an hour indicator, minute indicator, and day/night marker (AM/PM)
   *
   * @param hour hour indicator
   *
   * @param minute minute indicator
   *
   * @param dayNight AM/PM indicator
   */
  @JsonCreator
  public Time(@JsonProperty("hour") int hour, @JsonProperty("minute") int minute,
              @JsonProperty("zone") DayNight dayNight) {
    // do not need to check for errors as popup will verify
    this.hour = hour;
    this.minute = minute;
    this.dayNight = dayNight;
  }

  /**
   * Produces a String representation of this Time object
   *
   * @return this Time object as a String
   */
  @Override
  public String toString() {
    if (minute < 10) {
      return String.format("%s:0%s %s", hour, minute, dayNight.toString());
    } else {
      return String.format("%s:%s %s", hour, minute, dayNight.toString());
    }
  }

  /**
   * Gets the hour marker of this Time object
   *
   * @return the hour marker of this Time object, as an int
   */
  public int getHour() {
    return hour;
  }

  /**
   * Gets the minute marker of this Time object
   *
   * @return the minute marker of this Time object, as an int
   */
  public int getMinute() {
    return minute;
  }

  /**
   * Gets the day/night marker of this Time object
   *
   * @return the day/night marker of this Time object (AM or PM)
   */
  public DayNight getZone() {
    return dayNight;
  }

  /**
   * Sets the hour marker of this Time object to the given int hour marker
   *
   * @param hour int representing the hour marker to set this Time object to
   */
  public void setHour(int hour) {
    this.hour = hour;
  }

  /**
   * Sets the minute marker of this Time object to the given int minute marker
   *
   * @param minute int representing the minute marker to set this Time object to
   */
  public void setMinute(int minute) {
    this.minute = minute;
  }

  /**
   * Sets the day/night marker of this Time object to the given DayNight marker
   *
   * @param dayNight day/night marker (either AM or PM)
   */
  public void setZone(DayNight dayNight) {
    this.dayNight = dayNight;
  }

  /**
   * Determines whether the given object is equal to this Time object
   *
   * @param obj object to compare this Time to
   *
   * @return true if the given object is equal to this Time object
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Time other)) {
      return false;
    }

    return this.hour == other.hour
        && this.minute == other.minute
        && this.dayNight.compareTo(other.dayNight) == 0;
  }

  /**
   * Returns the hash code of this Time
   *
   * @return the hash code of this Time
   */
  @Override
  public int hashCode() {
    return this.getHour() * 1000 + this.getMinute() * 1000 + this.getZone().hashCode();
  }
}
