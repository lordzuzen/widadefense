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
    private boolean auraEnEspera;
    private String tipusAuraEspera;

    public ManagerDiners() {
        total = 600;
    }

    public void afegirDiners(int quantitat) {
        total += quantitat;
        ManagerPerfil.sumaDiners(quantitat);
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

    public void clearAures() {
        auraEnEspera = false;
        tipusAuraEspera = "null";
    }

    public boolean isAuraEnEspera() {
        return auraEnEspera;
    }

    public String getTipusAuraEspera() {
        return tipusAuraEspera;
    }

    public void setTipusAuraEspera(String tipusAuraEspera) {
        auraEnEspera = true;
        this.tipusAuraEspera = tipusAuraEspera;
    }

}
