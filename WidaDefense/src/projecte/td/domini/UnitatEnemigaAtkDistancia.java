/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class UnitatEnemigaAtkDistancia extends UnitatEnemiga {

    int cadencia;
    private ProjectilEstatic projectil;
    private Timer timer;
    private boolean dispara = true;
    private float posXProj;
    private float posYProj;

    public UnitatEnemigaAtkDistancia(int vida, int cadencia, Image image, Image[] frames, ProjectilEstatic projectil, Image[] frames2, double velocitat, float posXProj, float posYProj) {
        super(vida, image, frames, frames2, velocitat);
        this.cadencia = cadencia;
        this.projectil = projectil;
        this.posXProj = posXProj;
        this.posYProj = posYProj;
        timer = new Timer(cadencia, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispara = true;

            }
        });
    }

    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(posX - projectil.getAnimation().getWidth() + posXProj, posY + posYProj);

    }

    public void activarDispars() {


        timer.start();
        super.activat = true;

    }

    public void haDisparat() {
        dispara = false;


    }

    public void desactivarDispars() {
        activat = false;
        timer.stop();

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
}
