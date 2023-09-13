package cs3500.pa05.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * represents the popup to either choose a new Bullet Journal or load a bujo file
 */
public class ChooseFilePopup extends Stage {
  protected static final Font titleFont = Font.font("Courier New", FontWeight.EXTRA_BOLD, 14);
  protected static final Font bodyFont = Font.font("Courier New", FontWeight.NORMAL, 14);

  /**
   * sets the title and sets the scene with the VBox
   *
   * @param load button with functionality
   *
   * @param newFile button with functionality
   */
  public ChooseFilePopup(Button load, Button newFile) {
    this.setTitle("Bullet Journal Launcher");

    VBox main = this.layout(load, newFile);
    main.setBackground(Background.fill(Color.valueOf("#faedcd")));

    this.setScene(new Scene(main, 400, 100));
  }

  /**
   * creates the layout of the popup
   *
   * @param load button
   *
   * @param newFile button
   *
   * @return the vbox containing the buttons and the layout of the popup
   */
  private VBox layout(Button load, Button newFile) {
    newFile.setText("New Journal");
    newFile.setFont(bodyFont);
    HBox.setMargin(newFile, new Insets(10, 10, 15, 45));
    HBox controls = new HBox();
    controls.getChildren().add(newFile);

    load.setText("Open File...");
    load.setFont(bodyFont);

    HBox.setMargin(load, new Insets(10, 10, 15, 70));
    controls.getChildren().add(load);

    Label msg = new Label("Please create a new bullet journal or open an existing one.");
    msg.setFont(titleFont);
    msg.setWrapText(true);
    msg.setTextAlignment(TextAlignment.CENTER);
    VBox.setMargin(msg, new Insets(10, 25, 5, 25));

    return new VBox(msg, controls);
  }

}
