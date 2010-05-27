/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author wida47909974
 */
public class UnitatEnemigaAtkNormal extends UnitatEnemiga{

    double dany;

    public UnitatEnemigaAtkNormal(int vida, Image[] frames,Image[] framesMort,int milisegons, Image[] frames2, double velocitat,int milisegonsAtck,double dany) {
        super(vida, frames,framesMort,milisegons, frames2,velocitat,milisegonsAtck);
        this.dany=dany;
    }

    public UnitatEnemigaAtkNormal(int vida, Image[] frames, Image[] framesMort,int milisegons, double velocitat,double dany) {
        super(vida, frames, framesMort,milisegons, velocitat);
        this.dany=dany;
    }
    
    public boolean isActivat() {
        return activat;
    }

    public void setActivat(boolean activat) {
        this.activat = activat;
    }

    public double getDany() {
        return dany;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (activat) {
            float x=getPosX()-(atck.getWidth()-getWidth());
            g.drawAnimation(atck, x, posY);

        }
        else{
        g.drawAnimation(animation, posX, posY);
        }
    }

}
