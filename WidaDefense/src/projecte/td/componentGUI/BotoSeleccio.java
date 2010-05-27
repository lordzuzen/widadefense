/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.componentGUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author media
 */
public class BotoSeleccio extends BotoMenu {

    private String unitat;
    private static Image imatgeCarta;
    private static Image imatgeCartaOver;
    private boolean botoTriat;
    private boolean notaCanvi;
    private boolean mouseOver;

    public BotoSeleccio(GUIContext container, Image image, int x, int y, String unitat) {
        super(container, image, x, y);
        area = new Rectangle(x, y, imatgeCarta.getWidth(), imatgeCarta.getHeight());
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
     * @see org.newdawn.slick.gui.AbstractComponent#render(org.newdawn.slick.gui.GUIContext,
     *      org.newdawn.slick.Graphics)
     */
    @Override
    public void render(GUIContext container, Graphics g) {
        if (imatgeActual != null) {
            if (!over || !actiu) {
                g.drawImage(imatgeCarta, area.getX(), area.getY());
            } else {
                g.drawImage(imatgeCartaOver, area.getX(), area.getY());
            }
            int xp = (int) (area.getX() + ((getWidth() - imatgeActual.getWidth()) / 2));
            int yp = (int) (area.getY() + ((getHeight() - imatgeActual.getHeight()) / 2));
            imatgeActual.draw(xp, yp, colorActual);
        } else {
            g.setColor(colorActual);
            g.fill(area);
        }
        updateImage();
    }

    @Override
    protected void updateImage() {
        if (!over) {
            imatgeActual = imatgeNormal;
            colorActual = colorNormal;
            state = NORMAL;
            noClick = false;
            reproduit = false;
        } else {
            if (click) {
                if ((state != MOUSE_CLICK) && (noClick)) {
                    if (soClick != null && actiu) {
                        soClick.play();
                    }
                    imatgeActual = mouseDownImage;
                    colorActual = colorMouseClick;
                    state = MOUSE_CLICK;
                    notifyListeners();
                    noClick = false;
                }
            } else {
                noClick = true;
                if (state != MOUSE_OVER) {
                    if (soOver != null) {
                        if (!reproduit && actiu) {
                            soOver.play();
                            reproduit = true;
                        }
                    }
                    if (actiu) {
                        imatgeActual = imatgeMouseOver;
                    }
                    colorActual = colorMouserOver;
                    state = MOUSE_OVER;
                }
            }
        }

        click = false;
        state = NORMAL;
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

    public static Image getImatgeCarta() {
        return imatgeCarta;
    }

    public static void setImatgeCarta(Image imatgeCarta) {
        BotoSeleccio.imatgeCarta = imatgeCarta;
    }

    public static Image getImatgeCartaOver() {
        return imatgeCartaOver;
    }

    public static void setImatgeCartaOver(Image imatgeCartaOver) {
        BotoSeleccio.imatgeCartaOver = imatgeCartaOver;
    }
}
