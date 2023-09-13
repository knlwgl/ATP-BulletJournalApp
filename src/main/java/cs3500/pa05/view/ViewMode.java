package cs3500.pa05.view;

import java.time.DayOfWeek;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;

/**
 * represents the View mode in the mini view
 */
public class ViewMode extends Scene {
  private final VBox main;

  /**
   * sets the scene to the View Mode of a popup
   *
   * @param root scroll pane where all elements will be plastered on
   *
   * @param vbox parent VBox
   *
   * @param width of the VBox
   *
   * @param height of the VBox
   *
   * @param name of the current block it is representing
   *
   * @param description of the current block it is representing
   *
   * @param day of the current block it is representing
   */
  public ViewMode(ScrollPane root, VBox vbox, double width, double height, String name,
                  String description, DayOfWeek day) {
    super(root, width, height);

    this.main = vbox;
    this.main.setPrefSize(width, height);
    this.main.setSpacing(15);
    this.main.setBackground(Background.fill(Color.valueOf("#faedcd")));

    root.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    root.setFitToWidth(true);
    root.setBackground(Background.fill(Color.valueOf("#faedcd")));

    this.main.setFocusTraversable(true);

    Insets margin = new Insets(10, 15, 0, 15);

    initNameDescDay(name, description, day, margin);
  }

  /**
   * initializes the name, description and day of the view
   *
   * @param name to populate the name field
   *
   * @param description to populate the description field
   *
   * @param day to populate the day field
   *
   * @param margin of the VBox
   */
  private void initNameDescDay(String name, String description, DayOfWeek day, Insets margin) {
    Label nameLabel = new Label("Name: ");
    nameLabel.setFont(ViewerPopup.titleFont);
    VBox.setMargin(nameLabel, margin);

    Label nameBody = new Label(name);
    nameBody.setFont(ViewerPopup.bodyFont);
    VBox.setMargin(nameBody, new Insets(0, 15, 0, 15));

    VBox nameBox = new VBox(nameLabel, nameBody);
    this.main.getChildren().add(nameBox);

    Label descLabel = new Label("Description: ");
    descLabel.setFont(ViewerPopup.titleFont);
    VBox.setMargin(descLabel, margin);

    Label descBody = new Label(description);
    descBody.setFont(ViewerPopup.bodyFont);
    descBody.setWrapText(true);

    TextFlow desc = new TextFlow();
    desc.getChildren().add(descBody);
    VBox.setMargin(desc, new Insets(0, 15, 0, 15));

    VBox descBox = new VBox(descLabel, desc);
    this.main.getChildren().add(descBox);

    Label dayLabel = new Label("Day: ");
    dayLabel.setFont(ViewerPopup.titleFont);

    Label dayBody = new Label(day.toString());
    dayBody.setFont(ViewerPopup.bodyFont);

    HBox dayBox = new HBox();
    dayBox.setSpacing(10);
    dayBox.getChildren().addAll(dayLabel, dayBody);
    VBox.setMargin(dayBox, margin);
    this.main.getChildren().add(dayBox);
  }

  /**
   *
   * @return the main Vbox
   */
  public VBox getMain() {
    return this.main;
  }
}
