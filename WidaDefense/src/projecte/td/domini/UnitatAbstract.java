/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author media
 */
public class UnitatAbstract extends Entitat {

    // Vida de la unitat
    protected float vida;
    protected float vidaTotal;
    // Animacion de la unitat
    protected Animation animation;
    // Control d'aures
    Aura aura;
    protected float alphaAura = 1;
    protected Image imageAura;
    protected boolean auraActiva;

    /**
     *
     * @param vida
     * @param posX
     * @param posY
     * @param image
     * @param frames
     */
    public UnitatAbstract(int vida, Image image, Image[] frames) {
        this.vida = vida;
        this.vidaTotal = vida;
        this.animation = new Animation(frames, 60);

    }

    public void setLocation(float x, float y) {
        super.posX = x;
        super.posY = y;
        super.shape = new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());

    }

    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
        renderVida(gc,g);
    }

    public void impacte(double dany) {
        if (vida > 0) {
            vida -= dany;
        }
        if (vida <= 0) {
            mort = true;
        }
    }

    public void renderVida(GameContainer gc, Graphics g) {
        g.setColor(Color.red);
        g.fillRect(posX + 5, posY - 15, (vida / vidaTotal) * 30, 5);
        g.setColor(Color.white);
        g.drawRect(posX + 5, posY - 15, 30, 5);
    }

    public void update(int delta) {
    }

    public void activarAura(Aura aura) {
    }

    public void desactivarAura() {
    }

    public float getAlphaAura() {
        return alphaAura;
    }

    public void setAlphaAura(float alphaAura) {
        this.alphaAura = alphaAura;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Aura getAura() {
        return aura;
    }

    public void setAura(Aura aura) {
        this.aura = aura;
    }

    public boolean isAuraActiva() {
        return auraActiva;
    }

    public void setAuraActiva(boolean auraActiva) {
        this.auraActiva = auraActiva;
    }

    public Image getImageAura() {
        return imageAura;
    }

    public void setImageAura(Image imageAura) {
        this.imageAura = imageAura;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
