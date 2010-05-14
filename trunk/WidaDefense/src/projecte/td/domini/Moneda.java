/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.domini;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class Moneda extends AbstractComponent {

    private Shape area;
    private Image imatgeMoneda;
    private int comptador;
    private float transparencia = 1;
    private boolean desapareix;
    private boolean actiu;

    public Moneda(GUIContext gui, int posX, int posY) {
        super(gui);
        imatgeMoneda = ManagerRecursos.getImage("botoIngameTapatImage");
        area = new Rectangle(posX, posY, imatgeMoneda.getWidth(), imatgeMoneda.getHeight());
        addListener();
    }

    public void update() {
        comptador++;
        if (comptador > 200) {
            transparencia -= 0.002;
        }
        if (transparencia <= 0) {
            desapareix = true;
        }
    }

    /**
     * Es renderitzen tots els components del boto
     * @param gc : context en el que s'ha de renderitzar
     * @param g : objecte grafics que s'utilitza per renderitzar imatges i animacions
     */
    public void render(GUIContext gc, Graphics g) {
        imatgeMoneda.setAlpha(transparencia);
        g.drawImage(imatgeMoneda, area.getX(), area.getY());
        imatgeMoneda.setAlpha(1f);
    }

    /**
     * Afegeix un listener a aquest boto
     * @param unitat
     */
    public void addListener() {
        addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                actiu = true;
            }
        });
    }

    /**
     * Comprova si el mouse clica aquest element
     * @param button : boto que s'ha clicat
     * @param mx : posicio X del mouse quan s'ha clicat
     * @param my : posicio Y del mouse quan s'ha clicat
     */
    @Override
    public void mousePressed(int button, int mx, int my) {
        if (area.contains(mx, my)) {
            notifyListeners();
        }
    }

    public void setLocation(int x, int y) {
        if (area != null) {
            area.setX(x);
            area.setY(y);
        }
    }

    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }

    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }

    public int getX() {
        return (int) area.getX();
    }

    public int getY() {
        return (int) area.getY();
    }

    public boolean isDesapareix() {
        return desapareix;
    }

    public void setDesapareix(boolean desapareix) {
        this.desapareix = desapareix;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public float getTransparencia() {
        return transparencia;
    }

    public void setTransparencia(float transparencia) {
        this.transparencia = transparencia;
    }
}
