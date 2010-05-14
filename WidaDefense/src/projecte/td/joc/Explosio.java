/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.joc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author wida47909974
 */
public class Explosio {

    private Animation a;
    private float x;
    private float y;
    private double height;
    private double width;

    public Explosio(float x,float y) throws SlickException{
        Image c1= new Image("imatges/ex1.png");
        Image c2= new Image("imatges/ex2.png");
        Image c3= new Image("imatges/ex3.png");
        Image c4= new Image("imatges/ex4.png");
        Image c5= new Image("imatges/ex5.png");
        Image[] imatges = {c1,c2,c3,c4,c5};
        a= new Animation(imatges,200);
        this.x=x;
        this.y=y;
        height=a.getHeight();
        width=a.getWidth();
    }

    public Explosio()  throws SlickException{
        Image c1= new Image("imatges/ex1.png");
        Image c2= new Image("imatges/ex2.png");
        Image c3= new Image("imatges/ex3.png");
        Image c4= new Image("imatges/ex4.png");
        Image c5= new Image("imatges/ex5.png");
        Image[] imatges = {c1,c2,c3,c4,c5};
        a= new Animation(imatges,200);
        height=a.getHeight();
        width=a.getWidth();
    }

    public void dibuixar(){
        a.draw(x,y);
        a.setLooping(false);
    }

    public Animation getAnimation() {
        return a;
    }
    public boolean estaActiva(){
        if(!a.isStopped()){
            return true;
        }
        else{
            return false;
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    

}
