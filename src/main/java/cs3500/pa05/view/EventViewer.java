package cs3500.pa05.view;

import cs3500.pa05.model.DayNight;
import cs3500.pa05.model.Time;
import java.time.DayOfWeek;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
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
 * represents the mini view of an event popup
 */
public class EventViewer extends ViewerPopup {
  private final Time startTime;
  private final int durationVal;
  private final ChoiceBox<Integer> startHour;
  private final ChoiceBox<Integer> startMinute;
  private final ChoiceBox<DayNight> dayNight;
  private final TextField duration;

  /**
   * set the scene to this
   *
   * @param view mode of this popup
   *
   * @param edit mode of this popup
   *
   * @param name of the current event it is representing
   *
   * @param description of the current event it is representing
   *
   * @param day of the current event it is representing
   *
   * @param startTime of the current event it is representing
   *
   * @param duration of the current event it is representing
   *
   * @param deletePopup to be shown if the user wants to delete this event
   *
   * @param save button with functionality
   */
  public EventViewer(ViewMode view, EditMode edit, String name, String description, DayOfWeek day,
                     Time startTime, int duration, DeletePopup deletePopup, Button save) {
    super(view, edit, name, description, day, deletePopup, save);

    this.startTime = startTime;
    this.durationVal = duration;
    this.startHour = new ChoiceBox<>();
    this.startMinute = new ChoiceBox<>();
    this.dayNight = new ChoiceBox<>();
    this.duration = new TextField();

    Insets margin = new Insets(10, 15, 0, 15);
    initView(margin);
    initEdit(margin);
    this.setScene(this.view);
  }

  /**
   * initializes the fields in the textFields and choiceBox when going to edit mode
   *
   * @param margin of the VBox to align labels
   */
  private void initEdit(Insets margin) {
    HBox timeBox = new HBox();

    Label timeLabel = new Label("Start Time: ");
    timeLabel.setFont(Font.font(titleFont.getFamily(), FontWeight.BOLD, titleFont.getSize() - 2));
    initTime();
    timeBox.getChildren().addAll(timeLabel, this.startHour, this.startMinute, this.dayNight);
    VBox.setMargin(timeBox, margin);

    HBox durationBox = new HBox();
    durationBox.setSpacing(10);
    VBox.setMargin(durationBox, margin);
    Label durationLabel = new Label("Duration (minutes): ");
    durationLabel.setFont(titleFont);
    durationBox.getChildren().addAll(durationLabel, this.duration);

    HBox buttons = this.initCancelAndSaveButtons(margin);
    this.edit.getMain().getChildren().addAll(timeBox, durationBox, buttons);
  }

  /**
   * initializes the field time of the event in the popup
   */
  private void initTime() {
    this.startHour.setItems(
        FXCollections.observableList(IntStream.rangeClosed(1, 12).boxed().toList()));
    this.startMinute.setItems(
        FXCollections.observableList(IntStream.rangeClosed(0, 59).boxed().toList()));
    this.dayNight.setItems(FXCollections.observableArrayList(DayNight.values()));

    startHour.setValue(startTime.getHour());
    startMinute.setValue(startTime.getMinute());
    dayNight.setValue(startTime.getZone());

    duration.setPrefWidth(50);
    duration.setTextFormatter(new TextFormatter<Integer>(this::processChange));
    duration.setText(Integer.toString(durationVal));
    duration.setFont(bodyFont);
  }

  /**
   * initializes the labels in the event in the mini view popup
   *
   * @param margin of the VBox
   */
  private void initView(Insets margin) {
    HBox timeBox = new HBox();
    timeBox.setSpacing(10);
    Label timeLabel = new Label("Start Time: ");
    timeLabel.setFont(titleFont);
    Label timeBody = new Label(startTime.toString());
    timeBody.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
    timeBox.getChildren().addAll(timeLabel, timeBody);
    VBox.setMargin(timeBox, margin);

    HBox durationBox = new HBox();
    durationBox.setSpacing(10);
    Label eventLabel = new Label("Duration (minutes): ");
    eventLabel.setFont(titleFont);
    Label eventBody = new Label(Integer.toString(durationVal));
    eventBody.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
    durationBox.getChildren().addAll(eventLabel, eventBody);
    VBox.setMargin(durationBox, new Insets(10, 15, 0, 15));

    this.view.getMain().getChildren().addAll(timeBox, durationBox);
    this.initViewerButtons();
  }

  /**
   * goes into edit mode
   */
  @Override
  public void goEditMode() {
    this.setScene(edit);
  }

  /**
   * exists out of edit mode and repopulates the fields when edit mode is cancelled
   */
  @Override
  public void outEditMode() {
    this.setScene(this.view);
    this.edit.reset();
    this.startHour.setValue(this.startTime.getHour());
    this.startMinute.setValue(this.startTime.getMinute());
    this.dayNight.setValue(this.startTime.getZone());
    this.duration.setText(Integer.toString(this.durationVal));
  }

  /**
   * validates the input on any changes
   *
   * @param change the change that was made
   *
   * @return the TextFormatter.Change if input is valid
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
   * @return the edited name
   */
  public String getName() {
    return this.edit.getName();
  }

  /**
   *
   * @return the edited description
   */
  public String getDescription() {
    return this.edit.getDescription();
  }

  /**
   *
   * @return the edited Day
   */
  public DayOfWeek getDay() {
    return this.edit.getDay();
  }

  /**
   *
   * @return the start time of the event that is in the mini popup in Time class
   */
  public Time getStartTime() {
    return new Time(this.startHour.getValue(), this.startMinute.getValue(),
        this.dayNight.getValue());
  }

  /**
   *
   * @return the duration of the event in the mini pop up
   */
  public int getDuration() {
    try {
      return Integer.parseInt(this.duration.getText());
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Duration is not a number.");
    }
  }
}
