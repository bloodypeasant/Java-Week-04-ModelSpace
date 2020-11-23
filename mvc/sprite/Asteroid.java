package mvc.sprite;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;

public class Asteroid {

  final double MIN_POS_X = 0.0;
  final double MAX_POS_X = 800.0;
  final double MIN_POS_Y = 0.0;
  final double MAX_POS_Y = 600.0;
  final int MIN_VERTEX_TRIOS = 4;
  final int MAX_VERTEX_TRIOS = 8;
  final double MIN_RADIUS = 20.0;
  final double MAX_RADIUS = 50.0;
  final double INNER_FACTOR = 0.75;
  final double OUTER_FACTOR = 1.25;
  final double ANGLE_FACTOR = 0.25;
  final double MIN_SPEED = 0.1;
  final double MAX_SPEED = 0.2;
  final double MIN_SPIN = -0.001;
  final double MAX_SPIN = 0.001;

  private double midRadius;
  private double maxRadius;
  private int numVertices;
  private Point2D.Double pos;
  private Point2D.Double[] vertices;
  private Point2D.Double vel;
  private double angle;
  private double spin;

  public Asteroid() {
    angle = getRandomDouble(0, 2 * Math.PI);
    spin = getRandomDouble(MIN_SPIN, MAX_SPIN);
    pos = new Point2D.Double(getRandomDouble(MIN_POS_X, MAX_POS_X), getRandomDouble(MIN_POS_Y, MAX_POS_Y));
    double trajectory = getRandomDouble(0, 2 * Math.PI);
    double speed = getRandomDouble(MIN_SPEED, MAX_SPEED);
    vel = new Point2D.Double(speed * Math.cos(trajectory), speed * Math.sin(trajectory));
    numVertices = 3 * getRandomInt(MIN_VERTEX_TRIOS, MAX_VERTEX_TRIOS);
    vertices = new Point2D.Double[numVertices];
    midRadius = getRandomDouble(MIN_RADIUS, MAX_RADIUS);
    double angleStep = 2 * Math.PI / numVertices;
    maxRadius = 0;
    for (int i = 0; i < numVertices; ++i) {
      double a = angleStep * (i + getRandomDouble(-ANGLE_FACTOR, ANGLE_FACTOR));
      double r = midRadius * ((i % 3 == 0) ? getRandomDouble(INNER_FACTOR, 1.0) : getRandomDouble(1.0, OUTER_FACTOR));
      if (r > maxRadius) {
        maxRadius = r;
      }
      vertices[i] = new Point2D.Double(r * Math.cos(a), r * Math.sin(a));
    }
  }

  private double getRandomDouble(double min, double max) {
    return (min + Math.random() * (max - min));
  }

  private int getRandomInt(int min, int max) {
    return (int) (min + Math.random() * (max - min + 1));
  }

  public void updateView(Graphics2D g2d) {
    GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, numVertices);
    path.moveTo(vertices[0].x, vertices[0].y);
    for (int i = 1; i < numVertices; ++i) {
      path.lineTo(vertices[i].x, vertices[i].y);
    }
    path.closePath();
    AffineTransform transform = AffineTransform.getTranslateInstance(pos.x, pos.y);
    transform.rotate(angle);
    g2d.draw(transform.createTransformedShape(path));
  }

  public void updateModel(long timeChange) {
    angle += spin * timeChange;
    pos.x += vel.x * timeChange;
    pos.y += vel.y * timeChange;

    if (pos.x < -maxRadius) {
      pos.x = 800 + maxRadius;
    } else if (pos.x > 800 + maxRadius) {
      pos.x = -maxRadius;
    }
    if (pos.y < -maxRadius) {
      pos.y = 600 + maxRadius;
    } else if (pos.y > 600 + maxRadius) {
      pos.y = -maxRadius;
    }
  }

}
