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
public class ProjectilAnimat extends ProjectilMobil {

    private Animation animation;
    
    public ProjectilAnimat(double dany, Image image, Image[] frames) {
        super(dany, image);
        this.animation = new Animation(frames, 20);
    }

    @Override
    public void setLocation(float posX,float posY){
        super.setLocation(posX, posY);
        super.shape=new Rectangle(posX, posY, image.getWidth(), image.getHeight());
    }

    @Override
    public void update(int delta) {
        if (posX > 1024) {
            mort = true;
        } else {
            posX += 4;
            shape.setLocation(posX, posY);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
    }

    @Override
    public Object clone() {
        return new ProjectilAnimat(dany, image, ManagerRecursos.getImageArray("motoristaAnimation"));
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
