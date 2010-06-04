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
 * @author media
 */
public class Mina extends UnitatAbstract implements InterficieBomba{

    private Projectil projectil;
    private boolean dispara;
    private Timer timer;
    private boolean activa;

    public Mina(int vida, Image[] frames,int milisegons, Projectil projectil) {
        super(vida, frames,milisegons);
        this.projectil = projectil;
        timer = new Timer(5000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                activa = true;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void setLocation(float posX, float posY) {
        super.setLocation(posX, posY);
        projectil.setLocation(0, 0);
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void impacte(double dany) {
        if (activa) {
            dispara = true;
            projectil.setLocation(posX - ((projectil.getWidth()-shape.getWidth())/2), posY +shape.getHeight()-projectil.getHeight()+5);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (!activa) {
            Image image = animation.getImage(0);
            g.drawImage(image, posX, posY);
        } else {
            g.drawAnimation(animation, posX, posY);
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
