package cs3500.pa05.view;


import java.time.DayOfWeek;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * abstract class abstracting the creation of task/events
 */
public abstract class CreationPopup extends Stage {
  protected final VBox main;
  protected final TextField name;
  protected final TextArea description;
  protected final ChoiceBox<DayOfWeek> day;

  protected static final Font titleFont = Font.font("Courier New", FontWeight.EXTRA_BOLD, 16);
  protected static final Font bodyFont = Font.font("Courier New", FontWeight.NORMAL, 14);

  /**
   * initializes fields with respective FXML elements and constants
   */
  public CreationPopup() {
    this.main = new VBox();
    this.main.setBackground(Background.fill(Color.valueOf("#faedcd")));
    this.main.setPrefSize(300, 500);
    this.main.setSpacing(15);

    this.name = new TextField();
    this.name.setFont(bodyFont);
    this.description = new TextArea();
    this.description.setFont(bodyFont);
    this.day = new ChoiceBox<>();
    initNameDescDay(new Insets(10, 15, 0, 15));
  }

  /**
   * initializes the creation popup with Name Description and Day
   *
   * @param margin in the VBox containing the elements
   */
  private void initNameDescDay(Insets margin) {
    Label nameLabel = new Label();
    nameLabel.setText("Name: ");
    nameLabel.setFont(titleFont);
    VBox.setMargin(nameLabel, margin);
    this.main.getChildren().add(nameLabel);

    HBox nameBox = new HBox();
    nameBox.getChildren().add(this.name);
    VBox.setMargin(nameBox, new Insets(0, 15, 0, 15));
    this.main.getChildren().add(nameBox);

    Label descLabel = new Label();
    descLabel.setText("Description: ");
    descLabel.setFont(titleFont);
    VBox.setMargin(descLabel, margin);
    this.main.getChildren().add(descLabel);

    HBox descBox = new HBox();
    descBox.getChildren().add(this.description);
    VBox.setMargin(descBox, new Insets(0, 15, 0, 15));
    this.main.getChildren().add(descBox);

    HBox dayBox = new HBox();
    dayBox.setSpacing(10);
    Label dayLabel = new Label();
    dayLabel.setText("Day: ");
    dayLabel.setFont(titleFont);
    dayBox.getChildren().add(dayLabel);
    this.day.setItems(FXCollections.observableArrayList(DayOfWeek.values()));
    dayBox.getChildren().add(this.day);
    VBox.setMargin(dayBox, margin);
    this.main.getChildren().add(dayBox);
  }

  /**
   * sets the save button layout in the VBox
   *
   * @param save button that already has handles save on action
   */
  protected void initSaveButton(Button save) {
    HBox saveBox = new HBox();
    saveBox.setAlignment(Pos.CENTER_RIGHT);
    save.setText("CREATE");
    saveBox.getChildren().add(save);
    VBox.setMargin(saveBox, new Insets(10, 15, 0, 15));
    this.main.getChildren().add(saveBox);
  }

  /**
   *
   * @return the text in the name textField
   */
  public String getName() {
    return this.name.getText();
  }

  /**
   *
   * @return the text in the description textField
   */
  public String getDescription() {
    return this.description.getText();
  }

  /**
   *
   * @return the day in the choiceBox
   */
  public DayOfWeek getDay() {
    return this.day.getValue();
  }
}
