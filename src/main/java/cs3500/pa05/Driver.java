package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.ControllerImpl;
import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Model;
import cs3500.pa05.view.StartUpScreen;
import cs3500.pa05.view.View;
import cs3500.pa05.view.ViewImpl;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents a bullet journal application.
 */
public class Driver extends Application {

  /**
   * Starts the GUI for a game of bullet journal.
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    Model data = new BulletJournal();
    Controller ctrl = new ControllerImpl(data);
    View gui = new ViewImpl(ctrl);
    Stage splash = new StartUpScreen(400, 300);
    splash.show();
    PauseTransition delay = new PauseTransition(Duration.seconds(1.85));
    try {
      stage.setScene(gui.load());
      stage.setTitle("Bullet Journal");
      delay.setOnFinished(event -> {
        splash.close();
        stage.show();
        ctrl.run();
      });
      delay.play();
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Entry point for a bullet journal
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Application.launch();
  }
}