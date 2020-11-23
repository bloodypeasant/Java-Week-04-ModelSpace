package mvc;

import javax.swing.JFrame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import mvc.controller.Controller;
import mvc.model.Model;
import mvc.view.View;

public class App extends JFrame implements Runnable {

  private static final long serialVersionUID = 1L;
  private final int DELAY = 25;

  private GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
  private GraphicsDevice device = environment.getDefaultScreenDevice();
  private boolean isFullScreenAble = device.isFullScreenSupported();
  private boolean isFullScreenNow = false;

  public Controller controller = new Controller(this);
  public Model model = new Model();
  public View view = new View(this);
  private Thread thread = new Thread(this);
  private boolean isRunning = true;

  public App() {
    add(view);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(true);
    setTitle("Lesson");
    setVisible(true);
    thread.start();
  }

  public void toggleFS() {
    if (isFullScreenNow) {
      isFullScreenNow = false;
      device.setFullScreenWindow(null);
    } else if (isFullScreenAble) {
      isFullScreenNow = true;
      device.setFullScreenWindow(this);
    } else {
      System.out.println("Fullscreen mode is not supported.");
    }
  }

  public static void make() {
    new App();
  }

  @Override
  public void run() {
    long timePrior = System.currentTimeMillis();
    long timeChange = 0;
    long timeSleep;

    while (isRunning) {
      model.updateCycle(DELAY);
      view.updateCycle();
      timeChange = System.currentTimeMillis() - timePrior;
      timeSleep = DELAY - timeChange;
      if (timeSleep < 2) {
        timeSleep = 2;
      }
      try {
        Thread.sleep(timeSleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      timePrior = System.currentTimeMillis();
    }
  }

}
