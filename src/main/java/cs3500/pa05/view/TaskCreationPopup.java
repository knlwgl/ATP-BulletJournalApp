package cs3500.pa05.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * represents the popup when creating a task
 */
public class TaskCreationPopup extends CreationPopup {

  /**
   * sets the scene and title to this popup
   *
   * @param save button with functionality
   */
  public TaskCreationPopup(Button save) {
    super();
    this.setTitle("Create Task");
    initSaveButton(save);
    this.setScene(new Scene(this.main, 300, 425));
  }
}
