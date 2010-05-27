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

/**
 *
 * @author wida47909974
 */
public class UnitatEnemigaAtkDistanciaSalta extends UnitatEnemigaAtkDistancia {

    private Timer timer2;
    private boolean salta = false;
    private boolean haRebutImpacte;
    private int ySalt;
    private Animation salt;
    private boolean primeraMort;

    public UnitatEnemigaAtkDistanciaSalta(int vida, int cadencia, Image[] frames,Image[] framesMort,int milisegons, ProjectilEstatic projectil, Image[] frames2, double velocitat,int milisegonsAtck, float posXProj, float posYProj, Image[] frames3) {
        super(vida, cadencia, frames,framesMort,milisegons, projectil, frames2, velocitat,milisegonsAtck,posXProj, posYProj);
        salt = new Animation(frames3, 100);
    }

    @Override
    public void impacte(double dany) {
        super.impacte(dany);
        if (!haRebutImpacte) {
            haRebutImpacte = true;
            salta = true;
        }

    }

    public void haSaltat() {
        salta = false;
    }

    public boolean isSaltant() {
        return salta;
    }

    public void calculaSalt(int llargadaTotal) {
        ySalt = (int) (Math.random() * llargadaTotal);
    }

    public int getySalt() {
        return ySalt;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (activat) {
            g.drawAnimation(atck, posX, posY);

        } else if (salta) {
            g.drawAnimation(salt, posX, posY);
            salt.setLooping(false);
        } else {
            g.drawAnimation(animation, posX, posY);
        }
    }

    public boolean haFinalitzatAnimacio() {
        return salt.isStopped();
    }

    @Override
    public boolean isMort() {
        if(!primeraMort && mort){
            primeraMort=true;
            return true;
        }
        else{
        if (animation_mort != null) {
            if (mort && animation_mort.isStopped()) {
                return true;
            }
        }
        else{
            if(mort){
                return true;
            }
        }
        return false;
        }
    }
}
