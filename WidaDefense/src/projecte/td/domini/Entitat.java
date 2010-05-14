/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author media
 */
public abstract class Entitat {

    float posX;
    float posY;
    boolean mort;
    Shape shape;

    public Entitat() {
     
    }

    public abstract void update(int delta);

    public abstract void render(GameContainer gc, Graphics g);

    public abstract void setLocation(float x,float y);

    public boolean collideWith(Shape s) {
        if (shape.intersects(s)) {
            return true;
        }
        return false;
    }

    // Getters i setters

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public float getHeight(){
        return shape.getHeight();
    }

    public float getWidth(){
        return shape.getWidth();
    }

}
