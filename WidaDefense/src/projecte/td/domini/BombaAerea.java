/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/**
 *
 * @author media
 */
public class BombaAerea extends UnitatAbstract implements InterficieBomba{

    private float posicioYExplosio;
    private Projectil projectil;
    private boolean dispara;
    private Sound sound;

    public BombaAerea(int vida, Image[] frames,int milisegons, Projectil projectil, Sound sound) {
        super(vida, frames,milisegons);
        this.projectil = projectil;
        this.sound = sound;
    }

    @Override
    public void setLocation(float posX, float posY) {
        posicioYExplosio = posY;
        System.out.println(posY+"see");
        super.setLocation(posX, -20);
        projectil.setLocation(0, 0);
    }

    @Override
    public void update(int delta) {
        float y = super.getPosY();
        y += 4;
        super.setLocation(posX, y);
        
        if (posY >= posicioYExplosio) {
            projectil.setLocation(posX - ((projectil.getWidth()-shape.getWidth())/2), posicioYExplosio +shape.getHeight()-projectil.getHeight()+5);
            dispara = true;
        }
    }

    @Override
    public void impacte(double dany) {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);

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

    public Sound getSound() {
        return sound;
    }
}
