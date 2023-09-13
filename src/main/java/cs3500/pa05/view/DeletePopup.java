package cs3500.pa05.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * represents the confirmation popup when deleting anything
 */
public class DeletePopup extends Stage {

  /**
   * sets the scene to the popup
   *
   * @param no button with functionality
   *
   * @param yes button with functionality
   */
  public DeletePopup(Button no, Button yes) {

    VBox main = this.layout(no, yes);
    main.setBackground(Background.fill(Color.valueOf("#faedcd")));
    this.setTitle("Delete Item");
    this.setScene(new Scene(main, 400, 100));
  }

  /**
   * layout of delete popup
   *
   * @param no button with functionality
   *
   * @param yes button with functionality
   *
   * @return the VBox containing the buttons and layout
   */
  private VBox layout(Button no, Button yes) {

    yes.setText("Yes");
    yes.setFont(ViewerPopup.bodyFont);
    HBox.setMargin(yes, new Insets(10, 40, 0, 10));
    HBox controls = new HBox();
    controls.getChildren().add(yes);

    no.setText("No");
    no.setFont(ViewerPopup.bodyFont);
    HBox.setMargin(no, new Insets(10, 10, 0, 40));
    controls.getChildren().add(no);

    Label msg = new Label("Permanently delete this item?");
    msg.setFont(ViewerPopup.titleFont);
    VBox.setMargin(msg, new Insets(20, 50, 10, 60));
    controls.setAlignment(Pos.CENTER);

    return new VBox(msg, controls);
  }
}
