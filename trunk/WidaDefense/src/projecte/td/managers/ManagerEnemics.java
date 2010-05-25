/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.managers;

import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;

/**
 *
 * @author media
 */
public class ManagerEnemics {

    private static int waveActual;
    private static ArxiuConfiguracio propietatsWave;
    private static String enemicsWave;

    public static void iniciaWave(int wave) {
        waveActual = wave;
        propietatsWave = Configuracio.getWaves();
        enemicsWave = propietatsWave.getPropietatString("enemicsWave" + waveActual);
    }

}
