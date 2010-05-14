/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package perBorrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author wida47645633
 */
public class Miner extends UnitatBona {

    static String pathImage = "minerImage";
    static String pathAnimacio = "minerAnimation";

    public Miner() {
        super(100,0,1, new Animation(ManagerRecursos.getImageArray(pathAnimacio),50),ManagerRecursos.getImage(pathImage));
    }

    public void activar() {
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
        timer.start();
    }

    @Override
    public Object clone() {
        Miner min = new Miner();
        return min;
    }

    public void update(int delta) {

    }

    public void render(Graphics g) {
        if (animacio != null) {
            g.drawAnimation(animacio, posicioX, posicioY);
        }
    }
}
