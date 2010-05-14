/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package perBorrar;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public class ProjectilDinamic extends Projectil{

    public int velocitat;

    public ProjectilDinamic() {

    }

    public ProjectilDinamic(int dany, int velocitat, Image image) {
        super(dany, image);
        this.velocitat = velocitat;
    }

    public void render(Graphics g) {
        g.drawImage(image, posicioX, posicioY);
    }

    public void update(int delta) {
        posicioX += 0.5 * delta;
    }

    public float getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    @Override
    public ProjectilDinamic clone() {
        return new ProjectilDinamic(dany, velocitat, image); 
    }
}
