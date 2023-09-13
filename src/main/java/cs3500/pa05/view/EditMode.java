package cs3500.pa05.view;

import java.time.DayOfWeek;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * represents the popup while editing
 */
public class EditMode extends Scene {
  private final VBox main;
  private final String name;
  private final TextField nameBody;
  private final String desc;
  private final TextArea descBody;
  private final DayOfWeek day;
  private final ChoiceBox<DayOfWeek> dayBody;

  /**
   * initializes fields of edit mode
   *
   * @param root Vbox of the exit popup
   *
   * @param width of the edit popup
   *
   * @param height of the edit popup
   *
   * @param name of the current block popup is displaying
   *
   * @param description of the current block popup is displaying
   *
   * @param day of the current block popup is displaying
   */
  public EditMode(VBox root, double width, double height, String name, String description,
                  DayOfWeek day) {
    super(root, width, height);
    this.main = root;
    this.name = name;
    this.desc = description;
    this.day = day;
    this.main.setPrefSize(width, height);
    this.main.setSpacing(15);
    this.main.setBackground(Background.fill(Color.valueOf("#faedcd")));

    this.nameBody = new TextField(name);
    this.descBody = new TextArea(description);
    this.dayBody =
        new ChoiceBox<>(FXCollections.observableArrayList(DayOfWeek.values()));
    dayBody.setValue(day);

    initNameDayDesc(new Insets(10, 15, 0, 15));
  }

  /**
   * initializes the Name, Day Desc labels
   *
   * @param margin the margin of the VBox
   */
  private void initNameDayDesc(Insets margin) {
    Label nameLabel = new Label();
    nameLabel.setText("Name: ");
    nameLabel.setFont(ViewerPopup.titleFont);
    VBox.setMargin(nameLabel, margin);
    nameBody.setFont(ViewerPopup.bodyFont);
    VBox.setMargin(nameBody, new Insets(0, 15, 0, 15));
    VBox nameBox = new VBox(nameLabel, nameBody);
    this.main.getChildren().add(nameBox);

    Label descLabel = new Label();
    descLabel.setText("Description: ");
    descLabel.setFont(ViewerPopup.titleFont);
    VBox.setMargin(descLabel, margin);
    descBody.setFont(ViewerPopup.bodyFont);
    VBox.setMargin(descBody, new Insets(0, 15, 0, 15));
    VBox descBox = new VBox(descLabel, descBody);
    this.main.getChildren().add(descBox);

    HBox dayBox = new HBox();
    dayBox.setSpacing(10);
    Label dayLabel = new Label();
    dayLabel.setText("Day: ");
    dayLabel.setFont(ViewerPopup.titleFont);
    dayBox.getChildren().add(dayLabel);
    dayBox.getChildren().add(dayBody);
    VBox.setMargin(dayBox, margin);
    this.main.getChildren().add(dayBox);
  }

  /**
   *
   * @return the main VBox in the popup
   */
  public VBox getMain() {
    return this.main;
  }

  /**
   * resets the values of day description and name to the original value
   */
  public void reset() {
    this.nameBody.setText(this.name);
    this.descBody.setText(this.desc);
    this.dayBody.setValue(this.day);
  }

  /**
   *
   * @return the new input given beside the name label
   */
  public String getName() {
    return this.nameBody.getText();
  }

  /**
   *
   * @return the new input given beside the description label
   */
  public String getDescription() {
    return this.descBody.getText();
  }

  /**
   *
   * @return the new input chosen for choice box
   */
  public DayOfWeek getDay() {
    return this.dayBody.getValue();
  }
}
