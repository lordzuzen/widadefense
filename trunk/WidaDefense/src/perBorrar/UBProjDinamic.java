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
import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public class UBProjDinamic extends UnitatBona {

    ProjectilDinamic projectil;
    boolean balaEnCarregador = false;

    public UBProjDinamic(int vida, int proteccio, int cadencia, Animation animacio,
            Image image, ProjectilDinamic projectil) {
        super(vida,proteccio,cadencia,animacio,image);
        this.projectil = projectil;
    }

    public void activar() {
        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                balaEnCarregador = true;
            }
        });
        timer.start();
    }

    @Override
    public Object clone() {
        UBProjDinamic ubpd = new UBProjDinamic(vida,proteccio,cadencia,animacio,image,projectil);
        return ubpd;
    }

    public void update(int delta) {
    }

    public void render(Graphics g) {
        if (animacio != null) {
            g.drawAnimation(animacio, posicioX, posicioY);
        }
    }

    public boolean isBalaEnCarregador() {
        return balaEnCarregador;
    }

    public void setBalaEnCarregador(boolean balaEnCarregador) {
        this.balaEnCarregador = balaEnCarregador;
    }

    public ProjectilDinamic getProjectil() {
        return projectil.clone();
    }

    public void setProjectil(ProjectilDinamic projectil) {
        this.projectil = projectil;
    }

    @Override
    public void setPosicioX(float posicioX) {
        this.posicioX = posicioX;
        projectil.setPosicioX(posicioX+10);
    }

    @Override
    public void setPosicioY(float posicioY) {
        this.posicioY = posicioY;
        projectil.setPosicioY(posicioY+10);
    }

}
