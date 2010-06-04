/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class ProjectilAnimat extends Projectil {

    private Animation animation;
    private int milisegons;
    
    public ProjectilAnimat(double dany, Image[] frames,int milisegons) {
        super(dany);
        this.milisegons=milisegons;
        this.animation = new Animation(frames, milisegons);
    }

    @Override
    public void setLocation(float posX,float posY){
        super.posX=posX;
        super.posY=posY;
        super.shape=new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());
    }

    @Override
    public void update(int delta) {
        if (posX > 1024) {
            mort = true;
        } else {
            posX += 0.4*delta;
            shape.setLocation(posX, posY);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
    }

    @Override
    public Object clone() {
        return new ProjectilAnimat(dany, ManagerRecursos.getImageArray("motoristaAnimation"),milisegons);
    }

    @Override
    public Projectil cloneProjectil(){
        Projectil p=(Projectil) clone();
        p.setLocation(posX, posY);
        return p;
    }

    @Override
    public void impacte() {
    }

}
