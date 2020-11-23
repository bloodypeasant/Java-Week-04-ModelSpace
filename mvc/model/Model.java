package mvc.model;

import java.util.ArrayList;

import mvc.sprite.Asteroid;

public class Model {

  private final int NUM_ASTEROIDS = 8;

  public ArrayList<Asteroid> asteroids = new ArrayList<>();

  public Model() {
    for (int i = 0; i < NUM_ASTEROIDS; ++i) {
      asteroids.add(new Asteroid());
    }
  }

  public void updateCycle(long timeChange) {
    for (Asteroid asteroid : asteroids) {
      asteroid.updateModel(timeChange);
    }
  }

}
