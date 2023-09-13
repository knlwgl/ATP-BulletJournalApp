package cs3500.pa05.model;

import java.time.DayOfWeek;

/**
 * An abstract block (task/event) on a bullet journal
 */
public abstract class AbstractBlock {
  private String name;
  private String description;
  private DayOfWeek day;

  /**
   * Constructs an abstract block (task/event) on a bullet journal given a name, description,
   * and day of the week
   *
   * @param name block name
   *
   * @param description block description
   *
   * @param dayOfTheWeek block day of the week
   */
  public AbstractBlock(String name, String description, DayOfWeek dayOfTheWeek) {
    this.name = name;
    this.description = description;
    this.day = dayOfTheWeek;
  }

  /**
   * Gets the name of this abstract block
   *
   * @return the name of this abstract block
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the description of this abstract block
   *
   * @return the description of this abstract block
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the day of the week of this abstract block
   *
   * @return the day of the week of this abstract block
   */
  public DayOfWeek getDay() {
    return day;
  }

  /**
   * Sets the name of this abstract block to the given name
   *
   * @param name name to assign to this abstract block
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of this abstract block to the given description
   *
   * @param text description to assign to this abstract black
   */
  public void setDesc(String text) {
    this.description = text;
  }

  /**
   * Sets the day of the week to the given day of the week
   *
   * @param day day of the week
   */
  public void setDay(DayOfWeek day) {
    this.day = day;
  }
}