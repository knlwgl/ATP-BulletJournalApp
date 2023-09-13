package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Model;
import cs3500.pa05.view.ChooseFilePopup;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.FileChooser;

/**
 * Represents the controller for handling files related to a bullet journal
 */
public class FileIoController {
  private final Scene main;
  private Path currentBujoPath;
  private Model data;

  /**
   * Constructs a file-related bullet journal controller
   *
   * @param main main scene of the bullet journal
   *
   * @param data model data of the bullet journal
   */
  FileIoController(Scene main, Model data) {
    this.main = main;
    this.data = data;
  }

  /**
   * Handles the creation of a new .bujo file
   *
   * @param popup a file picker popup window to retrieve path from (since new file,
   *              no path is retrieved)
   */
  @FXML
  protected void handleNewFile(ChooseFilePopup popup) {
    popup.close();
    this.main.getRoot().setDisable(false);
  }

  /**
   * Loads an existing .bujo file
   *
   * @param popup file picker popup window to retrieve path from
   * @return returns the loaded model
   */
  @FXML
  protected Model handleLoadFile(ChooseFilePopup popup) {
    FileChooser choose = new FileChooser();
    FileChooser.ExtensionFilter filter =
        new FileChooser.ExtensionFilter("Bullet Journal File (.bujo)", "*.bujo");

    choose.getExtensionFilters().add(filter);
    choose.setSelectedExtensionFilter(filter);

    File loaded = choose.showOpenDialog(popup);
    if (!Objects.isNull(loaded)) {
      this.currentBujoPath = loaded.toPath();
      popup.close();
      initModel();
      this.main.getRoot().setDisable(false);
    }
    return this.data;
  }

  /**
   * Initializes the model of the bullet journal
   */
  private void initModel() {
    //parser
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonParser parser =
          mapper.getFactory().createParser(new FileReader(currentBujoPath.toFile()));
      JsonNode data = parser.readValueAs(JsonNode.class);
      this.data = mapper.convertValue(data, BulletJournal.class);
    } catch (IOException e) {
      // parsing error
    }
  }

  /**
   * Handles saving a bullet journal to this controller's path
   */
  void handleSave() {
    if (Objects.isNull(this.currentBujoPath)) {
      FileChooser chooser = new FileChooser();
      FileChooser.ExtensionFilter filter =
          new FileChooser.ExtensionFilter("Bullet Journal File (.bujo)", "*.bujo");
      chooser.getExtensionFilters().add(filter);
      chooser.setSelectedExtensionFilter(filter);
      File saved = chooser.showSaveDialog(this.main.getWindow());
      if (!Objects.isNull(saved)) {
        this.currentBujoPath = saved.toPath();
        this.saveToFile();
      }
    } else {
      this.saveToFile();
    }
  }

  /**
   * Handles saving a bullet journal to the file specified by this controller's path
   */
  private void saveToFile() {
    try {
      ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
      JsonNode data = mapper.convertValue(
          new BulletJournal(this.data.getMaxTasks(), this.data.getMaxEvents(),
              this.data.getTasks(), this.data.getEvents()), JsonNode.class);
      if (!Objects.isNull(data)) {
        Files.writeString(this.currentBujoPath, data.toPrettyString());
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    } catch (IOException e) {
      throw new IllegalArgumentException("Given filepath cannot be written to");
    }
  }

  /**
   * Handles creating a new week in a bullet journal
   *
   * @return a model of a new week in a bullet journal
   */
  protected Model handleNewWeek() {
    handleSave();
    this.currentBujoPath = null;
    this.data = new BulletJournal();
    return this.data;
  }
}
