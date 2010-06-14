/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 *
 * @author wida47909974
 */
public class UnitatEnemigaVola extends UnitatEnemigaAtkNormal {

    private float posYFinal;
    private boolean inici;
    private float posXVol;
    private int contadorSo;

    public UnitatEnemigaVola(int vida, Image[] frames, Image[] framesMort, int milisegons, double velocitat, double dany, Sound soAtck) {
        super(vida, frames, framesMort, milisegons, velocitat, dany, soAtck);
        calcularVol();

    }

    public void calcularVol() {
        posXVol = (int) (Math.random() * 700) + 200;
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
        if (posX <= 0 - getWidth() / 2) {
            haArribat = true;
        }
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
        if (activat) {
            if (!animation.isStopped() && !soAcabat && animation.getFrame() == 0) {
                soAtck.play();
                soAcabat = true;
            }

        }
        if(animation.getFrame()==animation.getFrameCount()-1 && contadorSo%20==0){
            soAcabat=false;
            
        }
        contadorSo++;
    }
}
