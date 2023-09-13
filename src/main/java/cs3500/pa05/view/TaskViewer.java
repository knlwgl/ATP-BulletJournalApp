package cs3500.pa05.view;

import java.time.DayOfWeek;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * represents the mini viewer popup of a task
 */
public class TaskViewer extends ViewerPopup {
  private final boolean isComplete;
  private final CheckBox completeBody;

  /**
   * sets the scene and title of this popup
   *
   * @param view mode scene of the popup
   *
   * @param edit mode scene of the popup
   *
   * @param name of the current Task it is representing
   *
   * @param description of the current Task it is representing
   *
   * @param day of the current Task it is representing
   *
   * @param isComplete represents the current task if it is completed
   *
   * @param deletePopup to show if user decides to delete
   *
   * @param save button with functionality
   */
  public TaskViewer(ViewMode view, EditMode edit, String name, String description, DayOfWeek day,
                    boolean isComplete, DeletePopup deletePopup, Button save) {
    super(view, edit, name, description, day, deletePopup, save);

    this.isComplete = isComplete;
    this.completeBody = new CheckBox("Complete");

    Insets margin =  new Insets(10, 15, 0, 15);
    initView(margin);
    initEdit(margin);
    this.setScene(this.view);
  }

  /**
   * initializes the fields with the task current information during edit
   *
   * @param margin of the VBox
   */
  private void initEdit(Insets margin) {
    completeBody.setFont(titleFont);
    completeBody.setSelected(this.isComplete);
    VBox.setMargin(completeBody, margin);
    HBox buttons = this.initCancelAndSaveButtons(margin);
    this.edit.getMain().getChildren().addAll(completeBody, buttons);
  }

  /**
   * initializes the complete field and if the task is completed
   *
   * @param margin of the VBox
   */
  private void initView(Insets margin) {
    Label completeLabel = new Label("Completed?: ");
    completeLabel.setFont(titleFont);

    Label completeBody = new Label(this.isComplete ? "YES" : "NO");
    completeBody.setFont(bodyFont);

    HBox completeBox = new HBox();
    completeBox.setSpacing(10);
    completeBox.getChildren().addAll(completeLabel, completeBody);
    VBox.setMargin(completeBox, margin);
    this.view.getMain().getChildren().add(completeBox);
    this.initViewerButtons();
  }

  /**
   * sets the scene to edit mode
   */
  @Override
  public void goEditMode() {
    this.setScene(edit);
  }

  /**
   * sets the scene back to the mini viewer
   */
  @Override
  public void outEditMode() {
    this.setScene(this.view);
    this.edit.reset();
    this.completeBody.setSelected(isComplete);
  }

  /**
   *
   * @return the input in the name field during the edit
   */
  public String getName() {
    return this.edit.getName();
  }

  /**
   *
   * @return the input in the description field during the edit
   */
  public String getDescription() {
    return this.edit.getDescription();
  }

  /**
   *
   * @return the input of the day in the choice box field
   */
  public DayOfWeek getDay() {
    return this.edit.getDay();
  }

  /**
   *
   * @return the input of the checkBox after the edit
   */
  public boolean isComplete() {
    return this.completeBody.isSelected();
  }
}
