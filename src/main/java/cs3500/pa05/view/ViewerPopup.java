package cs3500.pa05.view;

import java.time.DayOfWeek;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * represents the abstract class for mini view
 */
public abstract class ViewerPopup extends Stage {
  protected static final Font titleFont = Font.font("Courier New", FontWeight.EXTRA_BOLD, 16);
  protected static final Font bodyFont = Font.font("Courier New", FontWeight.NORMAL, 14);

  protected ViewMode view;
  protected EditMode edit;
  protected final DeletePopup deletePopup;
  protected final String name;
  protected final String description;
  protected final DayOfWeek day;

  protected final HBox buttonContainer;
  protected final Button close;
  protected final Button editButton;
  protected final Button delete;
  protected final Button saveButton;

  /**
   * initializes fields with respective FXML elements
   *
   * @param view mode of this scene
   *
   * @param edit mode of this scene
   *
   * @param name of the current block it is representing
   *
   * @param description of the current block it is representing
   *
   * @param day of the current block it is representing
   *
   * @param deletePopup shown if user wants to delete block
   *
   * @param save button with functionality
   */
  public ViewerPopup(ViewMode view, EditMode edit, String name, String description, DayOfWeek day,
                     DeletePopup deletePopup, Button save) {
    this.view = view;
    this.edit = edit;
    this.deletePopup = deletePopup;
    this.name = name;
    this.description = description;
    this.day = day;

    this.buttonContainer = new HBox();
    this.close = new Button("CLOSE");
    this.delete = new Button("DELETE");
    this.editButton = new Button("EDIT");
    this.saveButton = save;
  }

  /**
   * initializes the edit, delete and close buttons in each mini view
   */
  protected void initViewerButtons() {
    buttonContainer.setAlignment(Pos.CENTER_RIGHT);
    initEditButton(buttonContainer);
    initDeleteButton(buttonContainer);
    initCloseButton(buttonContainer);
    VBox.setMargin(buttonContainer, new Insets(10, 10, 20, 10));
    this.view.getMain().getChildren().add(buttonContainer);
  }

  /**
   * initializes the close button and its functionality
   *
   * @param parentBox of the button
   */
  protected void initCloseButton(HBox parentBox) {
    initButton(parentBox, close);
    close.setOnAction(event -> this.close());
  }

  /**
   * initializes the delete button and its functionality
   *
   * @param parentBox of the button
   */
  protected void initDeleteButton(HBox parentBox) {
    initButton(parentBox, delete);
    delete.setOnAction(event -> {
      deletePopup.show();
      this.close();
    });
  }

  /**
   * initializes the edit button and its functionality
   *
   * @param parentBox of the button
   */
  protected void initEditButton(HBox parentBox) {
    initButton(parentBox, editButton);
    editButton.setOnAction(event -> this.goEditMode());
  }

  /**
   * initializes the cancel and save buttons when in edit mode of mini viewer
   *
   * @param margin of the VBox
   *
   * @return the HBox containing both the cancel and save button with functionality
   */
  protected HBox initCancelAndSaveButtons(Insets margin) {
    Button cancelButton = new Button("CANCEL");
    cancelButton.setFont(bodyFont);
    cancelButton.setOnAction(event -> outEditMode());

    saveButton.setFont(bodyFont);
    HBox buttons = new HBox();
    buttons.setAlignment(Pos.CENTER_RIGHT);
    buttons.getChildren().addAll(saveButton, cancelButton);
    VBox.setMargin(buttons, margin);

    return buttons;
  }

  /**
   * abstract method for creating a button
   *
   * @param parentBox of the button
   *
   * @param button the button to create
   */
  private void initButton(HBox parentBox, Button button) {
    button.setFont(bodyFont);
    parentBox.getChildren().add(button);
    VBox.setMargin(parentBox, new Insets(10, 15, 0, 15));
  }

  /**
   * abstract method to go into edit mode from mini view
   */
  public abstract void goEditMode();

  /**
   * abstract method to go out of edit mode back to mini view
   */
  public abstract void outEditMode();
}