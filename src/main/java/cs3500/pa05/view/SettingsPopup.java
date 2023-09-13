package cs3500.pa05.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * represents the settings popup
 */
public class SettingsPopup extends Stage {
  private final TextField maxEvents;
  private final TextField maxTasks;

  protected static final Font titleFont = Font.font("Courier New", FontWeight.EXTRA_BOLD, 16);
  protected static final Font bodyFont = Font.font("Courier New", FontWeight.NORMAL, 14);

  /**
   * sets the scene and title of the settings popup
   *
   * @param save button with functionality
   *
   * @param maxEvents the current max Events
   *
   * @param maxTasks the current max Tasks
   */
  public SettingsPopup(Button save, int maxEvents, int maxTasks) {
    VBox main = new VBox();
    main.setBackground(Background.fill(Color.valueOf("#faedcd")));
    main.setSpacing(15);
    this.setScene(new Scene(main, 300, 200));
    this.setTitle("Settings");

    Insets margin = new Insets(10, 15, 0, 15);
    Label title = new Label("Select Per Day Maximums:");
    title.setFont(titleFont);
    VBox.setMargin(title, margin);
    main.getChildren().add(title);

    this.maxTasks = new TextField();
    this.maxEvents = new TextField();
    initTextFields(main, margin, maxEvents, maxTasks);

    HBox saveBox = new HBox();
    saveBox.setAlignment(Pos.CENTER_RIGHT);
    save.setText("SAVE");
    save.setFont(titleFont);
    saveBox.getChildren().add(save);
    VBox.setMargin(saveBox, new Insets(10, 15, 0, 15));
    main.getChildren().add(saveBox);
  }

  /**
   * initializes the textFields of the popup
   *
   * @param main VBox of the popup
   *
   * @param margin of the VBox
   *
   * @param maxEvents the max events that either user has provided or not
   *
   * @param maxTasks the max tasks that either user has provided or not
   */
  private void initTextFields(VBox main, Insets margin, int maxEvents, int maxTasks) {
    HBox taskBox = new HBox();
    taskBox.setSpacing(10);
    Label taskLabel = new Label("Tasks: ");
    taskLabel.setFont(titleFont);
    taskBox.getChildren().add(taskLabel);
    this.maxTasks.setFont(bodyFont);
    this.maxTasks.setTextFormatter(new TextFormatter<Integer>(this::processChange));
    this.maxTasks.setText(maxTasks != -1 ? Integer.toString(maxTasks) : "");
    taskBox.getChildren().add(this.maxTasks);
    VBox.setMargin(taskBox, margin);
    main.getChildren().add(taskBox);

    HBox eventBox = new HBox();
    eventBox.setSpacing(10);
    Label eventLabel = new Label("Events: ");
    eventLabel.setFont(titleFont);
    eventBox.getChildren().add(eventLabel);
    this.maxEvents.setFont(bodyFont);
    this.maxEvents.setTextFormatter(new TextFormatter<Integer>(this::processChange));
    this.maxEvents.setText(maxEvents != -1 ? Integer.toString(maxEvents) : "");
    eventBox.getChildren().add(this.maxEvents);
    VBox.setMargin(eventBox, margin);
    main.getChildren().add(eventBox);
  }

  /**
   * validates the input when the textField has any changes
   *
   * @param change the change made
   *
   * @return the changes if it is valid
   */
  private TextFormatter.Change processChange(TextFormatter.Change change) {
    String input = change.getControlNewText();
    if (input.matches("[0-9]*")) {
      return change;
    }
    return null;
  }

  /**
   *
   * @return the current Max Events provided
   */
  public int getMaxEvents() {
    String input = this.maxEvents.getText();
    int max = -1;
    if (input.isEmpty()) {
      return max;
    }
    try {
      max = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Invalid max events provided");
    }
    return max;
  }

  /**
   *
   * @return the current Max task provided
   */
  public int getMaxTasks() {
    String input = this.maxTasks.getText();
    int max = -1;
    if (input.isEmpty()) {
      return max;
    }
    try {
      max = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Invalid max tasks provided");
    }
    return max;
  }

}
