/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author media
 */
public class ProjectilMobil extends Projectil {

    private Image image;

    public ProjectilMobil(double dany, Image image) {
        super(dany);
        this.image = image;
        //this.shape = 
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
            posX += 2;
            shape.setLocation(posX, posY);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawImage(image, posX, posY);
    }

    @Override
    public Object clone() {
        return new ProjectilMobil(dany, image);
    }

    public Projectil cloneProjectil(){
        Projectil p=(Projectil) clone();
        p.setLocation(posX, posY);
        return p;
    }

    public void impacte() {
        this.mort = true;
    }
}
