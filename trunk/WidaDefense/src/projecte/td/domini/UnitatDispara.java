/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class UnitatDispara extends UnitatAbstract{

    private int cadencia;
   
    private Projectil projectil;
    
    // Timer per controlar cada quants segons dispara la unitat
    private Timer timer;

    private boolean dispara=true;
    private boolean activat;
    private float posXProj;
    private float posYProj;

    public UnitatDispara(int vida, int cadencia, Image image
            , Image[] frames, Projectil projectil,float posXProj,float posYProj) {
        super(vida, image, frames);
        this.cadencia = cadencia;
        this.imageAura = ManagerRecursos.getImage("auraVelocitatImage");
        this.projectil = projectil;
        this.posXProj=posXProj;
        this.posYProj=posYProj;

        timer = new Timer(cadencia, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispara = true;
            }
        });
    }
    @Override
    public void setLocation(float posX, float posY){
        super.setLocation(posX, posY);
        projectil.setLocation(posX+animation.getWidth()+posXProj, posY+posYProj);

    }

    public void activarDispars() {


        timer.start();
        activat = true;

    }

    public void haDisparat() {
        dispara = false;

    }

    public void desactivarDispars() {
        activat = false;
        timer.stop();

    }

    public boolean estaActivat() {
        return activat;
    }

    public boolean estaDisparant() {
        return dispara;
    }

    protected void ajustarTimer() {
        timer.setDelay(cadencia);
    }

    public int getCadencia() {
        return cadencia;
    }

    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (auraActiva) {
            imageAura.setAlpha(0.15f);
            g.drawImage(aura.getImage(), posX, posY);
        }
        g.drawAnimation(animation, posX, posY);
    }

    @Override
    public void activarAura(Aura aura) {
        auraActiva = true;
        this.aura = aura;
        this.aura.setBo(this);
        this.aura.activarAura();
        auraActiva = true;
        ajustarTimer();
    }

    @Override
    public void desactivarAura() {
        auraActiva = false;
        aura.desactivarAura();
        aura = null;
        ajustarTimer();
    }

    @Override
    public boolean isAuraActiva() {
        return auraActiva;
    }

    @Override
    public void setAuraActiva(boolean auraActiva) {
        this.auraActiva = auraActiva;
    }

    public Projectil getProjectil() {
        return projectil.cloneProjectil();
    }

    public void setP2(Projectil p2) {
        this.projectil = p2;
    }
}
