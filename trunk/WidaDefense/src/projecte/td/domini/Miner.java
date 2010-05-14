/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import projecte.td.managers.ManagerContext;
import projecte.td.managers.ManagerDiners;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class Miner extends UnitatAbstract {

    private int cadencia;
    private int capacitat;
    private Timer timer;
    private Moneda moneda;
    private static ManagerDiners diners;

    public Miner(int vida, int cadencia, int capacitat, Image image, Image[] frames) {
        super(vida, image, frames);
        this.cadencia = cadencia;
        this.capacitat = capacitat;
        this.imageAura = ManagerRecursos.getImage("auraVelocitatImage");
        diners = ManagerContext.getDiners();
        activar();
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        renderVida(gc, g);
        if (auraActiva) {
            imageAura.setAlpha(0.15f);
            g.drawImage(aura.getImage(), posX, posY);
        }
        if (moneda != null) {
            moneda.render(gc, g);
        }
        g.drawAnimation(animation, posX, posY);
    }

    @Override
    public void update(int delta) {
        if (moneda != null) {
            moneda.update();
            if (moneda.isDesapareix()) {
                moneda = null;
            } else if (moneda.isActiu()) {
                moneda = null;
                diners.afegirDiners(capacitat);
            }
        }
    }

    public void activar() {
        timer = new Timer(15000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moneda = new Moneda(ManagerContext.getGui(), (int) posX + 10, (int) posY + 20);
            }
        });
        timer.start();
    }

    protected void ajustarTimer() {
        timer.setDelay(cadencia);
    }

    @Override
    public boolean isAuraActiva() {
        return auraActiva;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }
}
