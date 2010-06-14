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
import org.newdawn.slick.Sound;
import projecte.td.managers.ManagerContext;
import projecte.td.managers.ManagerDinersAures;
import projecte.td.managers.ManagerPerfil;

/**
 *
 * @author media
 */
public class Miner extends UnitatAbstract implements IAuraRapidesa {

    private int cadencia;
    private int capacitat;
    private Timer timer;
    private Moneda moneda;
    private static ManagerDinersAures diners;
    private String tipus;
    private Sound sound;
    private boolean haSonat;

    public Miner(int vida, int cadencia, int capacitat, Image[] frames, Image[] framesMort,
            int milisegons, String tipus, Sound sound) {
        super(vida, frames, framesMort, milisegons);
        this.cadencia = cadencia;
        this.capacitat = capacitat;
        this.tipus = tipus;
        this.sound = sound;
        diners = ManagerContext.getDiners();
        activar();

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
        g.drawAnimation(animation, posX, posY);
        if (moneda != null) {
            moneda.render(gc, g);
        }
    }

    @Override
    public void update(int delta) {
        if (moneda != null) {
            moneda.update();
            if (!haSonat) {
                sound.play(1, (float) ManagerPerfil.getVolumEfectes() / 100);
                haSonat = true;
            }
            if (moneda.isDesapareix()) {
                moneda = null;
            } else if (moneda.isActiu()) {
                if (tipus.equals("Miner")) {
                    diners.afegirDiners(capacitat);
                } else if (tipus.equals("MagVida") || tipus.equals("MagRapidesa")) {
                    diners.setTipusAuraEspera(tipus);
                }
                moneda = null;
            }
        }
    }

    public void activar() {
        timer = new Timer(cadencia, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                moneda = new Moneda(ManagerContext.getGui(), (int) posX, (int) posY, tipus);
                haSonat = false;
            }
        });
        timer.start();
    }

    @Override
    public boolean potEquiparAura(Aura aura) {
        if (aura.getTipus().equals("MagRapidesa") || aura.getTipus().equals("MagVida")) {
            return true;
        }
        return false;
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

    public int getCadencia() {
        return cadencia;
    }

    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    public void desactivarTimer() {
        if (timer != null) {
            if (timer.isRunning()) {
                timer.stop();
            }
        }
    }

    public Sound getSound() {
        return sound;
    }
}
