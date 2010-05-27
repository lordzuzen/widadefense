/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.managers;

/**
 *
 * @author media
 */
public class InfoEnemicManager implements Comparable{

    private String tipusEnemic;
    private int tempsSortida;
    private int carril;

    public InfoEnemicManager(String tipusEnemic, int tempsSortida) {
        this.tipusEnemic = tipusEnemic;
        this.tempsSortida = tempsSortida;
    }

    public InfoEnemicManager(String tipusEnemic, int tempsSortida, int carril) {
        this.tipusEnemic = tipusEnemic;
        this.tempsSortida = tempsSortida;
        this.carril = carril;
    }

    public int compareTo(Object object) {
        InfoEnemicManager iem = (InfoEnemicManager) object;
        if (iem.getTempsSortida() < this.tempsSortida) {
            return 1;
        } else if (iem.getTempsSortida() == this.tempsSortida) {
            return 0;
        } else {
            return -1;
        }
    }

    public int getTempsSortida() {
        return tempsSortida;
    }

    public String getTipusEnemic() {
        return this.tipusEnemic;
    }

}
