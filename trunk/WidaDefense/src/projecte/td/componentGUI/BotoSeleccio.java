/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.componentGUI;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author media
 */
public class BotoSeleccio extends BotoMenu {

    private String unitat;
    private boolean botoTriat;
    private boolean notaCanvi;
    private boolean mouseOver;

    public BotoSeleccio(GUIContext container, Image image, int x, int y, String unitat) {
        super(container, image, x, y);
        this.unitat = unitat;
    }

    public void addListener() {
        addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                botoTriat = !botoTriat;
                notaCanvi = true;
            }
        });
    }

     /**
     * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int, int, int, int)
     */
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        over = area.contains(newx, newy);
    }

    public boolean isBotoTriat() {
        return botoTriat;
    }

    public void setBotoTriat(boolean botoTriat) {
        this.botoTriat = botoTriat;
    }

    public boolean isNotaCanvi() {
        return notaCanvi;
    }

    public void setNotaCanvi(boolean notaCanvi) {
        this.notaCanvi = notaCanvi;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
}
