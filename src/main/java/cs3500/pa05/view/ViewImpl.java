package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a view of a single week of a bullet journal
 */
public class ViewImpl implements View {

  FXMLLoader loader;

  /**
   * Constructs a week view
   *
   * @param controller controller of a week bullet journal
   */
  public ViewImpl(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("week.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a week layout
   *
   * @return the layout
   */
  @Override
  public Scene load() throws IllegalStateException {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }

}
