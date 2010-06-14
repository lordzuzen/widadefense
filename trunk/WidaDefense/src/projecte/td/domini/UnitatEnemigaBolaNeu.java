package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 * Classe UnitatEnemigaBolaNeu: Unitat enemiga Bola Neu.
 * @author Ernest Daban i David Alvarez
 */
public class UnitatEnemigaBolaNeu extends UnitatEnemigaAtkNormal {

    /**
     * Constructor UnitatEnemigaBolaNeu
     * @param vida
     * @param frames
     * @param framesMort
     * @param milisegons
     * @param velocitat
     * @param dany
     */
    public UnitatEnemigaBolaNeu(int vida, Image[] frames, Image[] framesMort, int milisegons, double velocitat, double dany, Sound soAtck) {
        super(vida, frames, framesMort, milisegons, velocitat, dany, soAtck);
    }

    /**
     * Actualitza la unitat
     * @param delta
     */
    @Override
    public void update(int delta) {
        if (posX <= 0 - getWidth() / 2) {
            haArribat = true;
        }
        posX -= velocitat * delta;
        setLocation(posX, posY);
    }

    /**
     * Dibuixa la unitat
     * @param gc GameContainer
     * @param g Graphics
     */
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
        if (animation.getFrame() == animation.getFrameCount() - 1) {
            soAcabat = false;

        }
    }

    /**
     * Rep impacte produït per l'aigua calenta
     */
    public void rebreAigua() {
        impacte(vida);
    }
}
