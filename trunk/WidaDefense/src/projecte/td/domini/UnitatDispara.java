/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public class UnitatDispara extends UnitatAbstract implements IAuraRapidesa {

    private int cadencia;
    private Projectil projectil;
    // Timer per controlar cada quants segons dispara la unitat
    private Timer timer;
    private boolean dispara = true;
    private boolean activat;
    private float posXProj;
    private float posYProj;

    public UnitatDispara(int vida, int cadencia, Image[] frames, Image[] framesMort, int milisegons, Projectil projectil, float posXProj, float posYProj) {
        super(vida, frames, framesMort, milisegons);
        this.cadencia = cadencia;
        this.projectil = projectil;
        this.posXProj = posXProj;
        this.posYProj = posYProj;

        timer = new Timer(cadencia, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!mort) {
                    dispara = true;
                }
            }
        });
    }

    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(posX + animation.getWidth() + posXProj, posY + posYProj);

    }

    @Override
    public boolean potEquiparAura(Aura aura) {
        if (aura.getTipus().equals("MagRapidesa") || aura.getTipus().equals("MagVida")) {
            return true;
        }
        return false;
    }

    public void activarDispars() {


        timer.start();
        activat = true;

    }

    public void haDisparat() {
        dispara = false;

    }

    public void desactivarDispars() {
        activat = false;
        timer.stop();

    }

    public boolean estaActivat() {
        return activat;
    }

    public boolean estaDisparant() {
        return dispara;
    }

    protected void ajustarTimer() {
        timer.setDelay(cadencia);
    }

    public int getCadencia() {
        return cadencia;
    }

    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    public Projectil getProjectil() {
        return projectil.cloneProjectil();
    }

    public void setP2(Projectil p2) {
        this.projectil = p2;
    }
}
