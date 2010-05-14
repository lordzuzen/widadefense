/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

import javax.swing.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author media
 */
public class ProjectilEstatic extends Projectil {

    Animation animation;
    Image[] frames;

    public ProjectilEstatic(double dany, Image[] frames) {
        super(dany);
        this.frames = frames;
        this.animation = new Animation(frames, 100);
        this.animation.setLooping(false);
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
        return new ProjectilEstatic(dany, frames);
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
