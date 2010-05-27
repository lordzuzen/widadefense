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
public class UnitatAbstract extends Entitat implements IAuraVida {

    // Vida de la unitat
    protected float vida;
    // Vida de la unitat al col·locar-la al tauler
    protected float vidaTotal;
    // Animacion de la unitat
    protected Animation animation;
    // Aura que pot equipar la unitat
    Aura aura;
    // Float per dibuixar l'aura
    protected float alphaAura = 1;
    // Indica si la unitat te una aura activa
    protected boolean auraActiva;
    protected Animation animation_mort;

    /**
     *
     * @param vida
     * @param posX
     * @param posY
     * @param image
     * @param frames
     */
    public UnitatAbstract(int vida, Image[] frames, Image[] framesMort,int milisegons) {
        this.vida = vida;
        this.vidaTotal = vida;
        this.animation = new Animation(frames, milisegons);
        this.animation_mort = new Animation(framesMort, 30);

    }

    public UnitatAbstract(int vida, Image[] frames,int milisegons) {
        this.vida = vida;
        this.vidaTotal = vida;
        this.animation = new Animation(frames, milisegons);

    }

    public void setLocation(float x, float y) {
        super.posX = x;
        super.posY = y;
        super.shape = new Rectangle(posX, posY, animation.getWidth(), animation.getHeight());

    }

    public void render(GameContainer gc, Graphics g) {
        g.drawAnimation(animation, posX, posY);
        renderVida(gc, g);

    }

    public void renderMort(GameContainer gc, Graphics g) {
        if (mort && animation_mort != null) {
            g.drawAnimation(animation_mort, posX + shape.getWidth() / 2, posY);
            animation_mort.setLooping(false);
        }
    }

    @Override
    public boolean isMort() {
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

    public void impacte(double dany) {
        if (vida > 0) {
            vida -= dany;
        }
        if (vida <= 0) {
            mort = true;
        }
    }

    public void renderVida(GameContainer gc, Graphics g) {
        if (vida > 0) {
            g.setColor(Color.red);
            g.fillRect(posX + 5, posY - 15, (vida / vidaTotal) * 30, 5);
        }
        if (auraActiva) {
            aura.getImage().setAlpha(0.15f);
            g.drawImage(aura.getImage(), posX, posY);
        }
        g.setColor(Color.white);
        g.drawRect(posX + 5, posY - 15, 30, 5);
    }

    public void update(int delta) {
    }

    @Override
    public void activarAura(Aura aura) {
        auraActiva = true;
        this.aura = aura;
        this.aura.setBo(this);
        this.aura.activarAura();
    }

    @Override
    public void desactivarAura() {
        auraActiva = false;
        aura.desactivarAura();
        aura = null;
    }

    public boolean potEquiparAura(Aura aura) {
        if (aura.getTipus().equals("MagVida")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAuraActiva() {
        return auraActiva;
    }

    @Override
    public void setAuraActiva(boolean auraActiva) {
        this.auraActiva = auraActiva;
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

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public float getVidaTotal() {
        return vidaTotal;
    }

    public void setVidaTotal(float vidaTotal) {
        this.vidaTotal = vidaTotal;
    }
}
