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
public class UnitatEnemigaBolaNeu extends UnitatEnemigaAtkNormal {

    public UnitatEnemigaBolaNeu(int vida, Image[] frames, Image[] framesMort, int milisegons, double velocitat, double dany) {
        super(vida, frames, framesMort, milisegons, velocitat, dany);
    }

    @Override
    public void update(int delta) {
        if (posX <= 0 - getWidth() / 2) {
            haArribat = true;
        }
        posX -= velocitat * delta;
        setLocation(posX, posY);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        g.drawAnimation(animation, posX, posY);

    }

    public void rebreAigua(){
        impacte(vida);
    }
}
