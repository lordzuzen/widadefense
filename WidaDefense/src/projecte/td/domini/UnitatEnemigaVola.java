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
 * @author wida47909974
 */
public class UnitatEnemigaVola extends UnitatEnemigaAtkNormal {

    private float posYFinal;
    private boolean inici;
    private float posXVol;

    public UnitatEnemigaVola(int vida, Image image, Image[] frames, Image[] framesMort, double velocitat,double dany) {
        super(vida, image, frames, framesMort, velocitat,dany);
        calcularVol();

    }

    public void calcularVol() {
        posXVol = (int) (Math.random() * 700)+200;
    }

    @Override
    public void setLocation(float posX, float posY) {
        if (!inici) {
            posYFinal = posY;
            inici = true;
            super.setLocation(posXVol, 0);
        } else {
            super.setLocation(posXVol, posY);
        }


    }

    @Override
    public void update(int delta) {
        if (!activat) {
            if (posYFinal > posY) {
                posY += velocitat * delta;
                setLocation(posXVol, posY);
            } else if (posYFinal <= posY) {
                posXVol -= velocitat * delta;
                setLocation(posXVol, posY);
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        g.drawAnimation(animation, posX, posY);
    }
}
