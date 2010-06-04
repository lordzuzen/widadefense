/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author ErNeSt
 */
public class UnitatEnemigaInvisible extends UnitatEnemigaAtkNormal {

    private boolean invisible = true;
    private boolean apareix = true;
    private Animation animation2;

    public UnitatEnemigaInvisible(int vida, Image[] frames, Image[] framesMort, int milisegons, Image[] frames2, double velocitat, int milisegonsAtck, double dany, Image[] frames3) {
        super(vida, frames, framesMort, milisegons, frames2, velocitat, milisegonsAtck, dany);
        animation2= new Animation(frames3,100);
    }
    public void canviShape(){
        shape=new Rectangle(posX+48, posY+12, animation.getWidth()-48, animation.getHeight()-12);
    }
    public void segonCanviShape() {
        super.setLocation(posX, posY);
    }
    @Override
    public void render(GameContainer gc, Graphics g) {

        if (!invisible) {

            if (apareix &&activat) {
                g.drawAnimation(atck, (posX + (shape.getWidth()) - atck.getWidth()), posY + shape.getHeight() - atck.getHeight());
                atck.stopAt(31);
            } else if (!apareix&&activat) {
                renderVida(gc, g);
                g.drawAnimation(animation, posX, posY);
            }
            else if(!activat){
                renderVida(gc, g);
                g.drawAnimation(animation2, posX, posY);
            }
        }
    }

    @Override
    public void update(int delta) {
        if(posX<=0-getWidth()/2){
            haArribat=true;
        }
        if (!activat) {
            posX -= velocitat * delta;
            setLocation(posX, posY);
        }
        if (activat) {
            invisible = false;
            if (atck.isStopped()) {
                apareix = false;
            }
        }

    }

    @Override
    public double getDany() {
        if (!mort && !invisible && !apareix) {
            return dany;
        } else {
            return 0;
        }
    }
    @Override
    public void impacte(double dany){
        if(!invisible && !apareix){
            super.impacte(dany);
        }
    }

    public boolean isInvisible() {
        return invisible;
    }
    
}
