package cs3500.pa05.view;

import cs3500.pa05.model.DayNight;
import cs3500.pa05.model.Time;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * represents the event creation popup when creating a new event
 */
public class EventCreationPopup extends CreationPopup {
  private final ChoiceBox<Integer> startHour;
  private final ChoiceBox<Integer> startMinute;
  private final ChoiceBox<DayNight> dayNight;
  private final TextField duration;

  /**
   * Initializes fields with respective FXML elements, set the title of the popup and stage
   *
   * @param save button with functionality
   */
  public EventCreationPopup(Button save) {
    super();
    this.setTitle("Create Event");
    this.startHour = new ChoiceBox<>();
    this.startMinute = new ChoiceBox<>();
    this.dayNight = new ChoiceBox<>();
    this.duration = new TextField();
    initTimeFields();
    initDuration();
    initSaveButton(save);
    this.setScene(new Scene(this.main, 300, 525));
  }

  /**
   * initializing time field in the event popup
   */
  private void initTimeFields() {
    Label timeLabel = new Label("Start Time: ");
    timeLabel.setFont(Font.font(titleFont.getFamily(), FontWeight.BOLD, 14));

    this.startHour.setItems(
        FXCollections.observableList(IntStream.rangeClosed(1, 12).boxed().toList()));
    this.startMinute.setItems(
        FXCollections.observableList(IntStream.rangeClosed(0, 59).boxed().toList()));
    this.dayNight.setItems(FXCollections.observableArrayList(DayNight.values()));

    HBox timeBox = new HBox();
    timeBox.getChildren().addAll(timeLabel, this.startHour, this.startMinute, this.dayNight);
    VBox.setMargin(timeBox, new Insets(10, 15, 0, 15));
    this.main.getChildren().add(timeBox);
  }

  /**
   * initializes the duration field in the popup
   */
  private void initDuration() {

    Label eventLabel = new Label("Duration (minutes): ");
    eventLabel.setFont(titleFont);

    this.duration.setPrefWidth(50);
    this.duration.setFont(bodyFont);
    this.duration.setTextFormatter(new TextFormatter<Integer>(this::processChange));

    HBox durationBox = new HBox();
    durationBox.setSpacing(10);
    durationBox.getChildren().addAll(eventLabel, duration);
    VBox.setMargin(durationBox, new Insets(10, 15, 0, 15));
    this.main.getChildren().add(durationBox);
  }

  /**
   * validates the changes that happen within a textField
   *
   * @param change the changes made
   *
   * @return the TextFormatter
   */
  private TextFormatter.Change processChange(TextFormatter.Change change) {
    String input = change.getText();
    if ((input.matches("[0-9]*") && this.duration.getText().length() < 4) || input.isEmpty()) {
      return change;
    }
    return null;
  }

  /**
   *
   * @return the time input represented by Time class
   */
  public Time getStartTime() {
    return new Time(this.startHour.getValue(), this.startMinute.getValue(),
        this.dayNight.getValue());
  }

  /**
   *
   * @return the input in duration
   */
  public int getDuration() {
    try {
      return Integer.parseInt(this.duration.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Duration is not a number.");
    }
  }
}
