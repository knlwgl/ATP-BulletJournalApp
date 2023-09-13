package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.view.ChooseFilePopup;
import cs3500.pa05.view.EventCreationPopup;
import cs3500.pa05.view.SettingsPopup;
import cs3500.pa05.view.TaskCreationPopup;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represents the controller for a bullet journal
 */
public class ControllerImpl implements Controller {
  private Model data;
  @FXML private Scene main;
  @FXML private MenuItem event;
  @FXML private MenuItem task;
  @FXML private Button settings;
  @FXML private Button quickSave;
  @FXML private MenuItem fileEvent;
  @FXML private MenuItem fileTask;
  @FXML private MenuItem fileWeek;
  @FXML private MenuItem fileSave;
  @FXML private MenuItem fileOpen;
  @FXML private MenuItem fileClear;
  @FXML private MenuItem menuSettings;

  @FXML private VBox sundayEvents;
  @FXML private VBox mondayEvents;
  @FXML private VBox tuesdayEvents;
  @FXML private VBox wednesdayEvents;
  @FXML private VBox thursdayEvents;
  @FXML private VBox fridayEvents;
  @FXML private VBox saturdayEvents;

  @FXML private VBox sundayTasks;
  @FXML private VBox mondayTasks;
  @FXML private VBox tuesdayTasks;
  @FXML private VBox wednesdayTasks;
  @FXML private VBox thursdayTasks;
  @FXML private VBox fridayTasks;
  @FXML private VBox saturdayTasks;

  @FXML private VBox taskQueue;
  @FXML private HBox eventOverview;
  @FXML private HBox taskOverview;
  @FXML private TextField searchBar;

  private FileIoController fileIo;
  private BlockUiController blockUi;

  /**
   * Constructs a bullet journal controller
   *
   * @param data bullet journal data
   */
  public ControllerImpl(Model data) {
    this.data = data;
  }

  /**
   * Initializes and runs a bullet journal
   */
  @Override
  @FXML
  public void run() {
    initSubControllers();
    // init getting/creation of new files
    requestBujo();
    // init handlers for event, task, settings and shortcuts
    initHandlers();
    blockUi.initOverview(this.data);
  }

  /**
   * Initializes sub-controllers to delegate controlling of specific aspects of the bullet journal
   */
  private void initSubControllers() {
    this.blockUi = new BlockUiController(this.sundayEvents, this.mondayEvents, this.tuesdayEvents,
        this.wednesdayEvents, this.thursdayEvents, this.fridayEvents, this.saturdayEvents,
        this.sundayTasks, this.mondayTasks, this.tuesdayTasks, this.wednesdayTasks,
        this.thursdayTasks, this.fridayTasks, this.saturdayTasks, this.taskQueue,
        this.eventOverview, this.taskOverview);
    this.fileIo = new FileIoController(this.main, this.data);
  }

  /**
   * Sets action events for many elements of the bullet journal (i.e. buttons, menu options, etc.)
   */
  @FXML
  private void initHandlers() {
    this.main.setOnKeyReleased(this::handleShortcut);
    this.event.setOnAction(event -> handleEventButton());
    this.task.setOnAction(event -> handleTaskButton());
    this.quickSave.setOnAction(event -> fileIo.handleSave());
    this.settings.setOnAction(event -> handleSettingsButton());
    this.fileEvent.setOnAction(event -> handleEventButton());
    this.fileTask.setOnAction(event -> handleTaskButton());
    this.fileWeek.setOnAction(event -> {
      this.data = fileIo.handleNewWeek();
      blockUi.initWeek(this.data);
    });
    this.fileOpen.setOnAction(event -> requestBujo());
    this.fileSave.setOnAction(event -> fileIo.handleSave());
    this.fileClear.setOnAction(event -> searchBar.setText(""));
    this.menuSettings.setOnAction(event -> handleSettingsButton());
    this.searchBar.textProperty().addListener(
        (observable, oldValue, newValue) -> this.blockUi.refreshSearch(newValue, this.data));
  }

  /**
   * Creates popup to load or create a new journal
   */
  private void requestBujo() {
    Button loadButton = new Button();
    Button newFileButton = new Button();
    ChooseFilePopup popup = new ChooseFilePopup(loadButton, newFileButton);

    popup.setOnCloseRequest(event -> this.main.getRoot().setDisable(false));
    loadButton.setOnAction(event -> {
      this.data = fileIo.handleLoadFile(popup);
      blockUi.initWeek(this.data);
    });
    newFileButton.setOnAction(event -> {
      fileIo.handleNewFile(popup);
      blockUi.initWeek(this.data);
    });

    this.main.getRoot().setDisable(true);
    popup.show();
  }

  /**
   * Handles creating a popup for creating an event with a save button
   */
  @FXML
  private void handleEventButton() {
    Button save = new Button();
    EventCreationPopup popup = new EventCreationPopup(save);
    popup.setOnCloseRequest(event -> this.main.getRoot().setDisable(false));
    save.setOnAction(event -> handleEventCreation(popup));
    this.main.getRoot().setDisable(true);
    popup.show();
  }

  /**
   * Handles adding input fields to the given event creation popup
   *
   * @param popup event creation popup
   */
  @FXML
  private void handleEventCreation(EventCreationPopup popup) {
    data.addEvent(popup.getName(), popup.getDescription(), popup.getDay(),
        popup.getStartTime(), popup.getDuration());
    blockUi.refreshEvent(popup.getDay(), this.data);
    blockUi.refreshOverview(this.data);
    popup.close();
    this.main.getRoot().setDisable(false);
  }

  /**
   * Handles creating a popup for creating a task with a save button
   */
  @FXML
  private void handleTaskButton() {
    Button save = new Button();
    TaskCreationPopup popup = new TaskCreationPopup(save);
    popup.setOnCloseRequest(event -> this.main.getRoot().setDisable(false));
    save.setOnAction(event -> handleTaskCreation(popup));
    this.main.getRoot().setDisable(true);
    popup.show();
  }

  /**
   * Handles adding input fields to the given task creation popup
   *
   * @param popup task creation popup
   */
  @FXML
  private void handleTaskCreation(TaskCreationPopup popup) {
    data.addTask(popup.getName(), popup.getDescription(), popup.getDay());
    blockUi.refreshTask(popup.getDay(), this.data, true);
    blockUi.refreshOverview(this.data);
    popup.close();
    this.main.getRoot().setDisable(false);
  }

  /**
   * Handles creating a settings button that opens a popup window
   */
  @FXML
  private void handleSettingsButton() {
    Button settings = new Button();
    SettingsPopup popup = new SettingsPopup(settings, this.data.getMaxEvents(),
        this.data.getMaxTasks());
    popup.setOnCloseRequest(event -> this.main.getRoot().setDisable(false));
    settings.setOnAction(event -> handleSaveSettings(popup));
    this.main.getRoot().setDisable(true);
    popup.show();
  }

  /**
   * Handles setting functionality (i.e. setting max daily tasks + events) to the given settings
   * popup window
   *
   * @param popup settings popup window
   */
  @FXML
  private void handleSaveSettings(SettingsPopup popup) {
    data.updateMaxEvents(popup.getMaxEvents());
    data.updateMaxTasks(popup.getMaxTasks());
    blockUi.initWeek(this.data);
    popup.close();
    this.main.getRoot().setDisable(false);
  }

  /**
   * Handles keyboard shortcuts
   *
   * @param keyEvent given key pressed (in addition to shortcut button pressed)
   */
  @FXML
  private void handleShortcut(KeyEvent keyEvent) {
    if (keyEvent.isShortcutDown() && !keyEvent.isShiftDown()) {
      switch (keyEvent.getText()) {
        case "e" -> handleEventButton(); // new event
        case "t" -> handleTaskButton(); // new task
        case "s" -> fileIo.handleSave(); // save to file
        case "o" -> requestBujo(); // request for another bujo
        case "n" -> {
          this.data = fileIo.handleNewWeek();
          blockUi.initWeek(this.data);
        }
        case "r" -> searchBar.setText("");
        default -> { } // no other options
      }
    }
  }
}
