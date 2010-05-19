/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public class Motorista extends UnitatAbstract {

    private Projectil projectil;
    private boolean dispara;

    public Motorista(int vida, Image image, Image[] frames, Projectil projectil) {
        super(vida, image, frames);
        this.projectil = projectil;
    }

    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(posX, posY);
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void impacte(double dany) {
        vida -=1;
        dispara = true;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        g.drawAnimation(animation, posX, posY);
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
        return projectil.cloneProjectil();
    }

    public void setProjectil(Projectil projectil) {
        this.projectil = projectil.cloneProjectil();
    }
}
