/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package perBorrar;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import javax.swing.Timer;

/**
 *
 * @author media
 */
public abstract class UnitatBona extends Entitat implements Cloneable{

    int vida;
    int proteccio;
    int cadencia;
    Millora millora;
    Timer timer;

    public UnitatBona() {

    }

    public UnitatBona(int vida, int proteccio, int cadencia, Animation animacio, Image image) {
        super(animacio,image);
        this.vida = vida;
        this.proteccio = proteccio;
        this.cadencia = cadencia;
    }

    public UnitatBona(int vida, int proteccio, int cadencia, float posicioX,
            float posicioY, Animation animacio, Image image) {
        super(posicioX, posicioY, animacio, image);
        this.vida = vida;
        this.proteccio = proteccio;
        this.cadencia= cadencia;
    }

    @Override
    public abstract Object clone();

    public int getCadencia() {
        return cadencia;
    }

    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    public Millora getMillora() {
        return millora;
    }

    public void setMillora(Millora millora) {
        this.millora = millora;
    }

    public int getProteccio() {
        return proteccio;
    }

    public void setProteccio(int proteccio) {
        this.proteccio = proteccio;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
