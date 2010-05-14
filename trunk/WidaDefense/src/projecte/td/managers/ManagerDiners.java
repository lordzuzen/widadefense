/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.managers;

/**
 *
 * @author media
 */
public class ManagerDiners {

    private int total;

    public ManagerDiners() {
        total = 10000;
    }

    public void afegirDiners(int quantitat) {
        total += quantitat;
    }

    public void restarDiners(int quantitat) {
        total -=quantitat;
    }

    public boolean suficientsDinerst(int quantitat) {
        if (total - quantitat >= 0) {
            return true;
        }
        return false;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
