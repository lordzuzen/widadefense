/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

/**
 *
 * @author media
 */
public interface IAuraRapidesa {

    public boolean potEquiparAura(Aura aura);

    public void activarAura(Aura aura);

    public void desactivarAura();

    public boolean isAuraActiva();

    public void setAuraActiva(boolean activa);

}
