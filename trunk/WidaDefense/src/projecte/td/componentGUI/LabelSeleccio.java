/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.componentGUI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author media
 */
public class LabelSeleccio {

    private Image imatgeCarta;
    private Image imatgeEnemic;
    private int posX;
    private int posY;

    public LabelSeleccio(Image imatgeCarta, Image imatgeEnemic) {
        this.imatgeCarta = imatgeCarta;
        this.imatgeEnemic = imatgeEnemic;
    }

    public void render(Graphics g) {
        g.drawImage(imatgeCarta, posX, posY);
        g.drawImage(imatgeEnemic, posX + 15, posY + 15);
    }

    public void setLocation(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
