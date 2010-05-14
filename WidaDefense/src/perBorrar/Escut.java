/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package perBorrar;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author wida47645633
 */
public class Escut extends UnitatBona {

    static String pathImage = "escutImage";
    static String pathAnimacio = "escutAnimation";

    public Escut() {
        super(100,100,0, new Animation(ManagerRecursos.getImageArray(pathAnimacio),120),ManagerRecursos.getImage(pathImage));
    }

    public void activar() {
    }

    @Override
    public Object clone() {
        Escut esc = new Escut();
        return esc;
    }

    public void update(int delta) {

    }

    public void render(Graphics g) {
        if (animacio != null) {
            g.drawAnimation(animacio, posicioX, posicioY);
        }
    }
}

