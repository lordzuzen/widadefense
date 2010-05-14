/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perBorrar;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public abstract class Entitat {

    int id;
    float posicioX;
    float posicioY;
    Animation animacio;
    Image image;

    static int serie = 0;

    public Entitat() {
        id = serie;
        serie++;
    }

    public Entitat(Animation animacio, Image image) {
        this();
        this.animacio = animacio;
        this.image = image;
    }

    public Entitat(Image image) {
        this();
        this.image = image;
    }

    public Entitat(float posicioX, float posicioY, Animation animacio, Image image) {
        this();
        this.posicioX = posicioX;
        this.posicioY = posicioY;
        this.animacio = animacio;
        this.image = image;
    }

    public abstract void update(int delta);

    public abstract void render(Graphics g);

    public Animation getAnimacio() {
        return animacio;
    }

    public void setAnimacio(Animation animacio) {
        this.animacio = animacio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public float getPosicioX() {
        return posicioX;
    }

    public void setPosicioX(float posicioX) {
        this.posicioX = posicioX;
    }

    public float getPosicioY() {
        return posicioY;
    }

    public void setPosicioY(float posicioY) {
        this.posicioY = posicioY;
    }

    public static int getSerie() {
        return serie;
    }

    public static void setSerie(int serie) {
        Entitat.serie = serie;
    }
}
