/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class UnitatEnemiga extends UnitatAbstract{

    protected boolean activat;
    protected Animation atck;
    protected double velocitat;


    public UnitatEnemiga(int vida, Image image, Image[] frames,Image[] framesMort, Image[] frames2,double velocitat) {
        super(vida, image, frames,framesMort);
        atck=new Animation(frames2, 100);
        this.velocitat=velocitat;
    }
    public UnitatEnemiga(int vida, Image image, Image[] frames,Image[] framesMort,double velocitat) {
        super(vida, image, frames,framesMort);
        this.velocitat=velocitat;
    }
    @Override
    public void setLocation(float posX, float posY){
        super.setLocation(posX, posY);

    }

    public boolean estaActivat() {
        return activat;
    }

    @Override
    public void update(int delta){
        if(!activat){
        posX -= velocitat * delta;
        setLocation(posX, posY);
        }
    }
    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (activat) {
            g.drawAnimation(atck, posX, posY);
            
        }
        else{
        g.drawAnimation(animation, posX, posY);
        }
    }
    @Override
    public void renderMort(GameContainer gc, Graphics g) {
        if (mort && animation_mort != null) {
            g.drawAnimation(animation_mort, (posX + shape.getWidth() / 2)-animation_mort.getWidth(), posY);
            animation_mort.setLooping(false);
        }
    }

    

    


}
