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
    UnitatAbstract bo;
    Timer timer;

    public Aura(String tipus, Image image, int capacitat) {
        this.tipus = tipus;
        this.image = image;
        this.capacitat = capacitat;
    }

    public void activarAura() {
        if (tipus.equals("MagRapidesa")) {
            if (bo instanceof Miner) {
                Miner miner = (Miner) bo;
                miner.setCadencia(miner.getCadencia() / capacitat);
                miner.ajustarTimer();
            } else if (bo instanceof UnitatDispara) {
                UnitatDispara ud = (UnitatDispara) bo;
                ud.setCadencia(ud.getCadencia() / capacitat);
                ud.ajustarTimer();
            }
        } else if (tipus.equals("MagVida")) {
            timer = new Timer(500, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (bo.getVida() <= bo.getVidaTotal() + 1) {
                        bo.setVida(bo.getVida() + 1);
                    }
                }
            });
            timer.start();
        }
    }

    public void desactivarAura() {
        if (tipus.equals("MagRapidesa")) {
            //bo.setCadencia(bo.getCadencia() * capacitat);
        } else if (tipus.equals("MagVida")) {
            timer.stop();
            timer = null;
        }
    }

    public UnitatAbstract getBo() {
        return bo;
    }

    public void setBo(UnitatAbstract bo) {
        this.bo = bo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
