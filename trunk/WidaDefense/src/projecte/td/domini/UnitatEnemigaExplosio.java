/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author wida47909974
 */
public class UnitatEnemigaExplosio extends UnitatEnemigaAtkNormal {

    boolean cremant;
    Timer timer;

    public UnitatEnemigaExplosio(int vida, Image[] frames, Image[] framesMort, int milisegons, Image[] frames2, double velocitat, int milisegonsAtck, double dany) {
        super(vida, frames, framesMort, milisegons, frames2, velocitat, milisegonsAtck, dany);
        timer = new Timer(6000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mort=true;
                timer.stop();
            }
        });
    }

    @Override
    public void update(int delta) {
        if(posX<=0-getWidth()/2){
            haArribat=true;
        }
        posX -= velocitat * delta;
        setLocation(posX, posY);
        if(cremant && !timer.isRunning() && !mort){
            timer.start();
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (activat || cremant) {
            cremant=true;
            g.drawAnimation(atck, posX, posY+animation.getHeight()-atck.getHeight());

        } else {
            g.drawAnimation(animation, posX, posY);
        }
    }
}
