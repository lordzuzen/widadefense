/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package perBorrar;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author media
 */
public abstract class Projectil extends Entitat{

    int dany;
    Shape shape;

    public Projectil() {

    }
    
    public Projectil(int dany, Image image) {
        super(image);
        shape = new Rectangle(0, 0, image.getWidth(), image.getHeight());
        this.dany = dany;
    }

    public int getDany() {
        return dany;
    }

    public void setDany(int dany) {
        this.dany = dany;
    }
}
