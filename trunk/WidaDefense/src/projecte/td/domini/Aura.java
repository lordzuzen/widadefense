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
public class Aura {

    private String tipus;
    private Image image;
    private int capacitat;
    UnitatDispara bo;
    Timer timer;

    public Aura(String tipus, Image image, int capacitat) {
        this.tipus = tipus;
        this.image = image;
        this.capacitat = capacitat;
    }

    public void activarAura() {
        if (tipus.equals("cadencia")) {
            bo.setCadencia(bo.getCadencia() / capacitat);
        } else if (tipus.equals("vida")) {
            timer = new Timer(2000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //bo.setVida(bo.getVida()+1);
            }
        });
        timer.start();
        }
    }

    public void desactivarAura() {
        if (tipus.equals("cadencia")) {
            bo.setCadencia(bo.getCadencia() * capacitat);
        } else if (tipus.equals("vida")) {
            timer.stop();
            timer = null;
        }
    }

    public UnitatDispara getBo() {
        return bo;
    }

    public void setBo(UnitatDispara bo) {
        this.bo = bo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
