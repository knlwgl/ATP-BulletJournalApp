package cs3500.pa05.controller;

import cs3500.pa05.model.AbstractBlock;
import cs3500.pa05.model.EventBlock;
import cs3500.pa05.model.Model;
import cs3500.pa05.model.TaskBlock;
import cs3500.pa05.view.DeletePopup;
import cs3500.pa05.view.EditMode;
import cs3500.pa05.view.EventViewer;
import cs3500.pa05.view.TaskViewer;
import cs3500.pa05.view.ViewMode;
import cs3500.pa05.view.ViewerPopup;
import java.time.DayOfWeek;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Represents a controller of task/event blocks (i.e. task or event "block" on a week view)
 */
public class BlockUiController {
  private final VBox sundayEvents;
  private final VBox mondayEvents;
  private final VBox tuesdayEvents;
  private final VBox wednesdayEvents;
  private final VBox thursdayEvents;
  private final VBox fridayEvents;
  private final VBox saturdayEvents;

  private final VBox sundayTasks;
  private final VBox mondayTasks;
  private final VBox tuesdayTasks;
  private final VBox wednesdayTasks;
  private final VBox thursdayTasks;
  private final VBox fridayTasks;
  private final VBox saturdayTasks;

  private final VBox taskQueue;
  private final HBox eventOverview;
  private final HBox taskOverview;

  /**
   * Constructs a controller of task/event blocks
   *
   * @param sundayEvents    a VBox of Sunday events
   *
   * @param mondayEvents    a VBox of Monday events
   *
   * @param tuesdayEvents   a VBox of Tuesday events
   *
   * @param wednesdayEvents a VBox of Wednesday events
   *
   * @param thursdayEvents  a VBox of Thursday events
   *
   * @param fridayEvents    a VBox of Friday events
   *
   * @param saturdayEvents  a VBox of Saturday events
   *
   * @param sundayTasks     a VBox of Sunday tasks
   *
   * @param mondayTasks     a VBox of Monday tasks
   *
   * @param tuesdayTasks    a VBox of Tuesday tasks
   *
   * @param wednesdayTasks  a VBox of Wednesday tasks
   *
   * @param thursdayTasks   a VBox of Thursday tasks
   *
   * @param fridayTasks     a VBox of Friday tasks
   *
   * @param saturdayTasks   a VBox of Saturday tasks
   *
   * @param taskQueue       a VBox of all tasks in a queue
   *
   * @param eventOverview   an HBox of an overview of events
   *
   * @param taskOverview    an HBox of an overview of tasks
   */
  BlockUiController(VBox sundayEvents, VBox mondayEvents, VBox tuesdayEvents, VBox wednesdayEvents,
                    VBox thursdayEvents, VBox fridayEvents, VBox saturdayEvents, VBox sundayTasks,
                    VBox mondayTasks, VBox tuesdayTasks, VBox wednesdayTasks, VBox thursdayTasks,
                    VBox fridayTasks, VBox saturdayTasks, VBox taskQueue, HBox eventOverview,
                    HBox taskOverview) {
    this.sundayEvents = sundayEvents;
    this.mondayEvents = mondayEvents;
    this.tuesdayEvents = tuesdayEvents;
    this.wednesdayEvents = wednesdayEvents;
    this.thursdayEvents = thursdayEvents;
    this.fridayEvents = fridayEvents;
    this.saturdayEvents = saturdayEvents;
    this.sundayTasks = sundayTasks;
    this.mondayTasks = mondayTasks;
    this.tuesdayTasks = tuesdayTasks;
    this.wednesdayTasks = wednesdayTasks;
    this.thursdayTasks = thursdayTasks;
    this.fridayTasks = fridayTasks;
    this.saturdayTasks = saturdayTasks;
    this.taskQueue = taskQueue;
    this.eventOverview = eventOverview;
    this.taskOverview = taskOverview;
  }

  /**
   * Initializes the blocks for the week given the model data
   *
   * @param data model data containing existing week data
   */
  protected void initWeek(Model data) {
    for (DayOfWeek day : DayOfWeek.values()) {
      refreshTask(day, data, true);
      refreshEvent(day, data);
    }
    refreshTaskQueue(data);
    refreshOverview(data);
  }

  /**
   * Refreshes the tasks for the given day based on the given model data
   *
   * @param day  day of the week
   *
   * @param data model data
   *
   * @param refreshQueue true if the task queue should be refreshed
   */
  protected void refreshTask(DayOfWeek day, Model data, boolean refreshQueue) {
    // clear the respective task
    VBox toRefresh = getParentId(day, false);
    toRefresh.getChildren().clear();
    List<TaskBlock> tasksOfTheDay = data.getTasks().get(day);
    if (data.areMaxTasksReached(day)) {
      Label task = new Label("Daily task limit exceeded!");
      task.setFont(Font.font("Courier New", FontWeight.BOLD, 10));
      task.setTextFill(Color.CRIMSON);
      task.setWrapText(true);
      VBox.setMargin(task, new Insets(2, 0, 3, 3));
      toRefresh.getChildren().add(task);
    }
    for (TaskBlock task : tasksOfTheDay) {
      VBox taskBox = new VBox(new HBox());
      populateTaskBox(taskBox, task, data);
      toRefresh.getChildren().add(taskBox);
    }
    if (refreshQueue) {
      refreshTaskQueue(data);
    }
  }

  /**
   * Refreshes the events for the given day based on the given model data
   *
   * @param day  day of the week
   *
   * @param data model data
   */
  protected void refreshEvent(DayOfWeek day, Model data) {
    // clear the respective event
    VBox toRefresh = getParentId(day, true);
    toRefresh.getChildren().clear();
    if (data.areMaxEventsReached(day)) {
      Label event = new Label("Daily event limit exceeded!");
      event.setFont(Font.font("Courier New", FontWeight.BOLD, 10));
      event.setTextFill(Color.CRIMSON);
      event.setWrapText(true);
      VBox.setMargin(event, new Insets(2, 0, 3, 3));
      toRefresh.getChildren().add(event);
    }
    List<EventBlock> eventOfTheDay = data.getEvents().get(day);
    for (EventBlock event : eventOfTheDay) {
      VBox eventBox = new VBox(new HBox());
      populateEventBox(eventBox, event, data);
      toRefresh.getChildren().add(eventBox);
    }
  }

  /**
   * Refreshes the task queue based on the given model data
   *
   * @param data model data
   */
  private void refreshTaskQueue(Model data) {
    DayOfWeek[] days = DayOfWeek.values();

    this.taskQueue.getChildren().clear();

    // add tasks from Sunday - Saturday
    for (int i = 6; i < 13; i++) {
      // clear the respective task
      List<TaskBlock> tasksOfTheDay = data.getTasks().get(days[i % 7]);
      for (TaskBlock task : tasksOfTheDay) {
        VBox taskBox = new VBox(new HBox());
        taskBox.setBackground(Background.fill(Color.valueOf("#CEDFF0").brighter()));
        taskBox.setBorder(Border.stroke(Color.valueOf("#6198CF")));
        CheckBox completion = new CheckBox(task.getName());
        completion.setSelected(task.getIsCompleted());
        completion.setOnAction(event -> {
          completion.setSelected(task.toggleComplete());
          refreshTask(task.getDay(), data, true);
          refreshOverview(data);
        });
        VBox.setMargin(completion, new Insets(5, 5, 5, 5));
        completion.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        taskBox.getChildren().add(completion);
        taskQueue.getChildren().add(taskBox);
      }
    }
  }

  /**
   * Add event name and description from the given blockInfo to given VBox block with the
   * given margin
   *
   * @param block     VBox to populate with information
   *
   * @param blockInfo block with information to populate from
   *
   * @param margin    margin between information
   */
  private void populateNameDescr(VBox block, AbstractBlock blockInfo, Insets margin) {
    Label name = new Label(blockInfo.getName());
    name.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
    VBox.setMargin(name, margin);

    Label description = new Label(blockInfo.getDescription());
    description.setMaxHeight(12.0);
    description.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
    VBox.setMargin(description, margin);

    block.getChildren().addAll(name, description);
  }

  /**
   * Add task-related information from the given TaskBlock taskInfo to the task VBox, and refreshes
   * the bullet journal based on the given (updated) model data
   *
   * @param task     VBox representing a task to add info to
   *
   * @param taskInfo block containing information to add to the task
   *
   * @param data     model data
   */
  private void populateTaskBox(VBox task, TaskBlock taskInfo, Model data) {
    Insets margin = new Insets(5, 5, 5, 5);

    populateNameDescr(task, taskInfo, margin);

    CheckBox isCompleted = new CheckBox("Complete?");
    VBox.setMargin(isCompleted, margin);
    isCompleted.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
    isCompleted.setSelected(taskInfo.getIsCompleted());
    isCompleted.setOnAction(event -> {
      isCompleted.setSelected(taskInfo.toggleComplete());
      refreshTaskQueue(data);
      refreshOverview(data);
    });

    task.setBackground(Background.fill(Color.valueOf("#CEDFF0").brighter()));
    task.setBorder(Border.stroke(Color.valueOf("#6198CF").darker()));
    task.getChildren().add(isCompleted);

    task.setOnMouseReleased(e -> openMiniViewer(taskInfo, data));
  }

  /**
   * Add event-related information from the given EventBlock eventInfo to the event VBox, and
   * refreshes the bullet journal based on the given (updated) model data.
   *
   * @param event VBox representing an event to populate with information
   *
   * @param eventInfo    information to populate event with
   *
   * @param data     model data
   */
  private void populateEventBox(VBox event, EventBlock eventInfo, Model data) {
    Insets margin = new Insets(5, 5, 5, 5);

    populateNameDescr(event, eventInfo, margin);

    Label startTime = new Label("Start time: " + eventInfo.getStartTime().toString());
    startTime.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
    VBox.setMargin(startTime, margin);

    Label duration = new Label("Duration: " + eventInfo.getDuration() + " minutes");
    duration.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
    VBox.setMargin(duration, margin);

    event.setBackground(Background.fill(Color.valueOf("#CDC9ED").brighter()));

    event.setBorder(Border.stroke(Color.valueOf("#B7AED5").darker()));
    event.getChildren().addAll(startTime, duration);

    event.setOnMouseReleased(e -> openMiniViewer(eventInfo, data));
  }

  /**
   * If the given boolean isEvent is true, gets the VBox of events on the given day of the week;
   * otherwise gets the VBox of tasks on the given day of the week
   * Abstracted getting the right vbox
   *
   * @param day     day of the week
   *
   * @param isEvent true to get the VBox of events (false to get the VBox of tasks)
   *
   * @return the desired VBox
   */
  private VBox getParentId(DayOfWeek day, boolean isEvent) {
    VBox id = null;
    switch (day) {
      case SUNDAY -> id = isEvent ? this.sundayEvents : this.sundayTasks;
      case MONDAY -> id = isEvent ? this.mondayEvents : this.mondayTasks;
      case TUESDAY -> id = isEvent ? this.tuesdayEvents : this.tuesdayTasks;
      case WEDNESDAY -> id = isEvent ? this.wednesdayEvents : this.wednesdayTasks;
      case THURSDAY -> id = isEvent ? this.thursdayEvents : this.thursdayTasks;
      case FRIDAY -> id = isEvent ? this.fridayEvents : this.fridayTasks;
      case SATURDAY -> id = isEvent ? this.saturdayEvents : this.saturdayTasks;
      default -> { } // no other options
    }
    return id;
  }

  /**
   * Opens the mini-viewer of the given task block with the given model data
   *
   * @param task task to open a mini-viewer of
   *
   * @param data model data
   */
  @FXML
  private void openMiniViewer(TaskBlock task, Model data) {
    Button yes = new Button("yes");
    Button no = new Button("no");

    DeletePopup deletepopup = new DeletePopup(no, yes);
    yes.setOnAction(event -> {
      DayOfWeek toRefresh = task.getDay();
      data.removeTask(task);
      refreshTask(toRefresh, data, true);
      refreshOverview(data);
      deletepopup.close();
    });

    no.setOnAction(event -> deletepopup.close());
    Button save = new Button("SAVE");
    save.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));

    VBox main = new VBox();
    ViewMode view = new ViewMode(new ScrollPane(main), main, 300, 330,
        task.getName(), task.getDescription(), task.getDay());
    EditMode edit = new EditMode(new VBox(), 300, 525, task.getName(),
        task.getDescription(), task.getDay());
    TaskViewer viewer = new TaskViewer(view, edit, task.getName(), task.getDescription(),
        task.getDay(), task.getIsCompleted(), deletepopup, save);
    save.setOnAction(event -> updateTask(task, viewer, data));
    view.setOnKeyReleased(keyEvent -> this.viewShortcuts(keyEvent, viewer, deletepopup));
    edit.setOnKeyReleased(keyEvent -> this.editShortcuts(keyEvent, viewer, save));
    viewer.show();
  }

  /**
   * Handles opening a mini-viewer of the given event block
   *
   * @param eventBlock event block to open a mini viewer of
   *
   * @param data       model data
   */
  @FXML
  private void openMiniViewer(EventBlock eventBlock, Model data) {
    Button yes = new Button("yes");
    Button no = new Button("no");
    DeletePopup deletepopup = new DeletePopup(no, yes);
    yes.setOnAction(event -> {
      DayOfWeek toRefresh = eventBlock.getDay();
      data.removeEvent(eventBlock);
      refreshEvent(toRefresh, data);
      refreshOverview(data);
      deletepopup.close();
    });
    no.setOnAction(event -> deletepopup.close());

    // save button
    Button save = new Button("SAVE");
    VBox main = new VBox();
    ViewMode view = new ViewMode(new ScrollPane(main), main, 300, 330,
        eventBlock.getName(), eventBlock.getDescription(), eventBlock.getDay());
    EditMode edit = new EditMode(new VBox(), 300, 525, eventBlock.getName(),
        eventBlock.getDescription(), eventBlock.getDay());
    EventViewer viewer = new EventViewer(view, edit, eventBlock.getName(),
        eventBlock.getDescription(), eventBlock.getDay(), eventBlock.getStartTime(),
        eventBlock.getDuration(), deletepopup, save);
    //on save button press, get all and pass new information into
    save.setOnAction(event -> updateEvent(eventBlock, viewer, data));
    view.setOnKeyReleased(keyEvent -> this.viewShortcuts(keyEvent, viewer, deletepopup));
    edit.setOnKeyReleased(keyEvent -> this.editShortcuts(keyEvent, viewer, save));
    viewer.show();
  }

  /**
   * Updates the given task in the given task viewer with the given model data
   *
   * @param task   task to update
   *
   * @param viewer task view to update (visually)
   *
   * @param data   model data
   */
  private void updateTask(TaskBlock task, TaskViewer viewer, Model data) {
    task.setName(viewer.getName());
    task.setDesc(viewer.getDescription());
    if (task.getIsCompleted() != viewer.isComplete()) {
      task.toggleComplete();
    }

    DayOfWeek oldDay = task.getDay();
    task.setDay(viewer.getDay());
    if (!oldDay.equals(task.getDay())) {
      data.removeTask(task);
      data.addTask(task.getName(), task.getDescription(), task.getDay(),
          task.getIsCompleted());
    }
    refreshTask(oldDay, data, true);
    refreshTask(viewer.getDay(), data, true);
    viewer.close();
  }

  /**
   * Update the given event (and the display of the event) with the given model data
   *
   * @param event  event to update
   *
   * @param viewer event viewer to update
   *
   * @param data   model data
   */
  private void updateEvent(EventBlock event, EventViewer viewer, Model data) {
    // if task changed day, must reflect in data and on screen
    event.setName(viewer.getName());
    event.setDesc(viewer.getDescription());
    event.setStartTime(viewer.getStartTime());
    event.setDuration(viewer.getDuration());

    DayOfWeek oldDay = event.getDay();
    event.setDay(viewer.getDay());
    if (!oldDay.equals(event.getDay())) {
      data.removeEvent(event);
      data.addEvent(event.getName(), event.getDescription(), event.getDay(),
          event.getStartTime(), event.getDuration());
    }
    refreshEvent(oldDay, data);
    refreshEvent(event.getDay(), data);
    viewer.close();
  }

  /**
   * Handles keyboard shortcuts for view mode scene
   *
   * @param keyEvent given key pressed (in addition to shortcut button pressed)
   *
   * @param popup the viewer popup hosting the edit mode scene
   *
   * @param delete the popup for deleting the corresponding item
   */
  private void viewShortcuts(KeyEvent keyEvent, ViewerPopup popup, DeletePopup delete) {
    if (keyEvent.isShortcutDown() && !keyEvent.isShiftDown()) {
      switch (keyEvent.getText()) {
        case "e" -> popup.goEditMode(); // edit item
        case "w" -> popup.close(); // close window
        case "d" -> { // delete item
          delete.show();
          popup.close();
        }
        default -> {
        }
      }
    }
  }

  /**
   * Handles keyboard shortcuts for edit mode scene
   *
   * @param keyEvent given key pressed (in addition to shortcut button pressed)
   *
   * @param popup the viewer popup hosting the edit mode scene
   *
   * @param save the button for saving edits
   */
  private void editShortcuts(KeyEvent keyEvent, ViewerPopup popup, Button save) {
    if (keyEvent.isShortcutDown() && !keyEvent.isShiftDown()) {
      switch (keyEvent.getText()) {
        case "s" -> save.fire(); // save item
        case "w" -> popup.close(); // close window
        case "e" -> popup.outEditMode(); // view item
        default -> {
        }
      }
    }
  }

  /**
   * Initializes the week view based to the given model data
   *
   * @param data model data
   */
  protected void initOverview(Model data) {
    int noOfEvents = data.getAllEvents().size();
    Label eventCounter = new Label(Integer.toString(noOfEvents));
    this.eventOverview.getChildren().add(eventCounter);

    int noOfTasks = data.getAllTasks().size();
    int noOfCompletedTasks = data.getCompletedTasks().size();
    Label taskCounter = new Label(noOfCompletedTasks + " / " + noOfTasks);
    this.taskOverview.getChildren().add(taskCounter);
  }

  /**
   * Refreshes the weekly overview to the given model data
   *
   * @param data model data
   */
  protected void refreshOverview(Model data) {
    this.eventOverview.getChildren().remove(1);
    this.taskOverview.getChildren().remove(1);
    initOverview(data);
  }

  /**
   * Refreshes/updates the task queue based on the search bar contents
   *
   * @param toSearch message to search for
   *
   * @param data model data
   */
  protected void refreshSearch(String toSearch, Model data) {
    // retrieve the message
    if (toSearch.length() > 0) {
      List<TaskBlock> filtered = data.getAllTasks();
      filtered.removeIf(taskBlock -> !taskBlock.getName().toLowerCase().contains(
          toSearch.toLowerCase()));
      refreshTaskQueueFilter(filtered, data);
    } else {
      refreshTaskQueue(data);
    }
  }

  /**
   * Refreshes the task queue based on the given filtered list of tasks
   *
   * @param filteredList filtered list of tasks
   *
   * @param data model data
   */
  private void refreshTaskQueueFilter(List<TaskBlock> filteredList, Model data) {
    this.taskQueue.getChildren().clear();
    for (TaskBlock task : filteredList) {
      VBox taskBox = new VBox(new HBox());
      taskBox.setBackground(Background.fill(Color.valueOf("#CEDFF0").brighter()));
      taskBox.setBorder(Border.stroke(Color.valueOf("#6198CF")));
      CheckBox completion = new CheckBox(task.getName());
      completion.setSelected(task.getIsCompleted());
      completion.setOnAction(event -> {
        completion.setSelected(task.toggleComplete());
        refreshTask(task.getDay(), data, false);
        refreshOverview(data);
      });
      VBox.setMargin(completion, new Insets(5, 5, 5, 5));
      completion.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
      taskBox.getChildren().add(completion);
      taskQueue.getChildren().add(taskBox);
    }
  }
}
