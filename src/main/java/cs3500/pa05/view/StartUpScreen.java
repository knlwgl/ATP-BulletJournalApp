package cs3500.pa05.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * represents the splash art popup before bullet journal is shown
 */
public class StartUpScreen extends Stage {
  Font titleFont = Font.font("Courier New", FontWeight.BOLD, 24);

  /**
   * sets the scene to the start-up screen
   *
   * @param width of the screen
   *
   * @param height of the screen
   */
  public StartUpScreen(int width, int height) {
    VBox bigger = new VBox();
    HBox box = new HBox(bigger);
    bigger.setAlignment(Pos.CENTER);
    box.setAlignment(Pos.CENTER);
    box.setBackground(Background.fill(Color.valueOf("fefae0")));
    ImageView logo = new ImageView();
    InputStream image = null;
    try {
      image = new FileInputStream(Path.of("src/main/resources/logo.png").toFile());
    } catch (FileNotFoundException e) {
      // failed to load logo
    }
    if (!Objects.isNull(image)) {
      logo.setImage(new Image(image));
    }
    logo.setFitWidth(width - 40);
    logo.setFitHeight(height - 75);
    Label buJo = new Label("BULLET JOURNAL");
    buJo.setFont(titleFont);
    buJo.setTextFill(Color.valueOf("d4a373"));
    bigger.getChildren().addAll(logo, buJo);
    bigger.setSpacing(2);

    Scene main = new Scene(box, width, height);
    this.setScene(main);
  }
}
