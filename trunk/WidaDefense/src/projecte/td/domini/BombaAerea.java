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
public class BombaAerea extends UnitatAbstract {

    private float posicioYExplosio;
    private Projectil projectil;
    private boolean dispara;

    public BombaAerea(int vida, Image[] frames,int milisegons, Projectil projectil) {
        super(vida, frames,milisegons);
        this.projectil = projectil;
    }

    @Override
    public void setLocation(float posX, float posY) {
        posicioYExplosio = posY;
        super.setLocation(posX, -20);
        projectil.setLocation(posX, -20);
    }

    @Override
    public void update(int delta) {
        float y = super.getPosY();
        y += 4;
        super.setLocation(posX, y);
        projectil.setLocation(posX - 40, y -130);
        if (posY > posicioYExplosio) {
            dispara = true;
        }
    }

    @Override
    public void impacte(double dany) {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
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
        return projectil;
    }

    public void setProjectil(Projectil projectil) {
        this.projectil = projectil.cloneProjectil();
    }
}
