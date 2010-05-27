/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import projecte.td.domini.UnitatAbstract;
import projecte.td.factories.FactoriaUnitats;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;

/**
 *
 * @author media
 */
public class ManagerEnemics {

    // ID de la wave actual
    private static int waveActual;
    // Temps total que ha de durar la wave
    private static int tempsTotal;
    private static int tempsAnterior;
    // Arxiu amb les caracteristiques de la wave actual
    private static ArxiuConfiguracio propietatsWave;
    // Arxiu amb les caracteristiques dels enemics
    private static ArxiuConfiguracio enemics;
    // Enemics que apareixeran en la wave
    private static String enemicsWave;
    // Llista de tots els enemics que sortiran
    private static List llistaEnemics;
    private static int[] carrils;
    private static Timer timer;
    private static boolean unitatEsperant;
    private static boolean inici;
    private static String unitatEnEspera;

    public static void iniciaWave(int wave) {
        waveActual = wave;
        propietatsWave = Configuracio.getWaves();
        enemics = Configuracio.getEnemics();
        enemicsWave = propietatsWave.getPropietatString("enemicsWave" + waveActual);
        tempsTotal = propietatsWave.getPropietatInt("temps" + waveActual);
        llistaEnemics = new ArrayList();
        carrils = new int[6];
        tempsAnterior = 0;
        inici = true;
        carregaEnemics();
    }

    private static void carregaEnemics() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        String[] unitats = enemicsWave.split("-");
        for (String z : unitats) {
            String[] informacio = z.split(":");
            int totalUnitats = Integer.parseInt(informacio[1]);
            int comptador = 0;
            for (int k = 0; k < totalUnitats; k++) {
                if (k == 0) {
                    llistaEnemics.add(new InfoEnemicManager(informacio[0], tempsTotal / totalUnitats));
                    comptador += tempsTotal / totalUnitats;
                } else {
                    int tempsUnitat = comptador + (tempsTotal / totalUnitats);
                    llistaEnemics.add(new InfoEnemicManager(informacio[0], tempsUnitat));
                    comptador += tempsTotal / totalUnitats;
                }
            }
        }
        ordenarArray();
        print();
    }

    public static void ordenarArray() {
        Collections.sort(llistaEnemics);
    }

    private static void print() {
        for (Object o : llistaEnemics) {
            InfoEnemicManager e = (InfoEnemicManager) o;
            System.out.println(e.getTipusEnemic() + "-" + e.getTempsSortida());
        }
    }

    public static boolean isEnEspera() {
        return unitatEsperant;
    }

    public static UnitatAbstract getUnitat() {
        unitatEsperant = false;
        return FactoriaUnitats.getUnitatDolenta(unitatEnEspera);
    }

    public static void crearTimer(int temps) {
        timer = new Timer(temps, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inici) {
                    inici = false;
                } else {
                    if (llistaEnemics.size() >= 1) {
                        ajustarTimer();
                        unitatEsperant = true;
                    } else {
                        timer.stop();
                    }
                }
            }
        });
        timer.start();
    }

    private static void ajustarTimer() {
        InfoEnemicManager iem = (InfoEnemicManager) llistaEnemics.get(0);
        timer.setDelay(iem.getTempsSortida() - tempsAnterior);
        unitatEnEspera = iem.getTipusEnemic();
        tempsAnterior = iem.getTempsSortida();
        llistaEnemics.remove(iem);
    }

    public static int triarCarril() {
        int random = (int) (Math.random() * 600);
        return random;
    }

    public static boolean fidelaWave() {
        if (llistaEnemics.size() == 0) {
            return true;
        }
        return false;
    }
}
