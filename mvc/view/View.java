package mvc.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import javax.imageio.ImageIO;

import mvc.App;
import mvc.model.Model;
import mvc.sprite.Asteroid;

public class View extends Canvas {

  private static final long serialVersionUID = 1L;
  private static final double ASPECT_RATIO = 1; // width to height ratio
  private static final int HEIGHT = 600;
  private static final int WIDTH = 800;

  private BufferedImage img;
  private Rectangle displayRect = new Rectangle(0, 0, 0, 0);
  private Model model;
  private RenderingHints renderingHints;

  public View(App app) {
    model = app.model;
    renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);
    addComponentListener(app.controller);
    addMouseListener(app.controller);
  }

  public void noticeResize(Dimension updatedSize) {
    if (updatedSize.height * ASPECT_RATIO > updatedSize.width) { // use full width
      displayRect.width = updatedSize.width;
      displayRect.height = (int) (displayRect.width / ASPECT_RATIO);
      displayRect.x = 0;
      displayRect.y = (int) ((updatedSize.height - displayRect.width / ASPECT_RATIO) / 2);
    } else { // use full height
      displayRect.height = updatedSize.height;
      displayRect.width = (int) (displayRect.height * ASPECT_RATIO);
      renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      displayRect.x = (int) ((updatedSize.width - displayRect.height * ASPECT_RATIO) / 2);
      displayRect.y = 0;
    }
  }

  public void updateCycle() {
    repaint();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHints(renderingHints);
    g2d.setStroke(new BasicStroke(1));
    g2d.setColor(Color.WHITE);
    for (Asteroid asteroid : model.asteroids) {
      asteroid.updateView(g2d);
    }
    Toolkit.getDefaultToolkit().sync();
  }

}
