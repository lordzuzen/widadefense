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

/**
 *
 * @author media
 */
public class ProjectilEstatic extends Projectil implements Cloneable{

    Animation animation;
    Image[] frames;
    int milisegons;

    public ProjectilEstatic(double dany, Image[] frames, int milisegons) {
        super(dany);
        this.frames = frames;
        this.animation = new Animation(frames, milisegons);
        this.animation.setLooping(false);
        this.milisegons=milisegons;
        //this.shape = new Rectangle(posX, posY, frames[0].getWidth(), frames[0].getHeight());
    }
    @Override
    public void setLocation(float posX,float posY){
        super.setLocation(posX, posY);
        super.shape=new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());
    }
    @Override
    public void update(int delta) {
        if (animation.isStopped()) {
            mort = true;
        }
    }

    public Object clone() {
        return new ProjectilEstatic(dany, frames,milisegons);
    }

    public Projectil cloneProjectil(){
        Projectil p=(Projectil) clone();
        p.setLocation(posX, posY);
        return p;
    }

    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
    }

    public void impacte() {
        //this.mort = true;
    }

    public Animation getAnimation() {
        return animation;
    }

    

}
