package mvc.controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mvc.App;

public class Controller implements ComponentListener, MouseListener {

  private App app;

  public Controller(App app) {
    this.app = app;
  }

  @Override
  public void componentHidden(ComponentEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void componentMoved(ComponentEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void componentResized(ComponentEvent e) {
    app.view.noticeResize(e.getComponent().getSize());
  }

  @Override
  public void componentShown(ComponentEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    app.toggleFS();
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub
  }

}
