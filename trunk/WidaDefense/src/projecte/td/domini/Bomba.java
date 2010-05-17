/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public class Bomba extends UnitatAbstract {

    private Projectil projectil;
    private boolean dispara;

    public Bomba(int vida, Image image, Image[] frames, Projectil projectil) {
        super(vida, image, frames);
        animation.setLooping(false);
        this.projectil = projectil;
    }

    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(0, posY);
    }

    @Override
    public void update(int delta) {
        if (!animation.isStopped()) {
            posX += 1;
        } else if (animation.isStopped()) {
            dispara = true;
        }
    }

    public void haDisparat() {
        dispara = false;
        mort = true;
    }

    public boolean isDispara() {
        return dispara;
    }

    public void setDispara(boolean dispara) {
        this.dispara = dispara;
    }

    public Projectil getProjectil() {
        return projectil;
    }

    public void setProjectil(Projectil projectil) {
        this.projectil = projectil.cloneProjectil();
    }
}
