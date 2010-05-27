package projecte.td.managers;

import projecte.td.utilitats.*;
import projecte.td.utilitats.Configuracio;

/**
 *
 * @author media
 */
public class ManagerPerfil {

    // Perfil del jugador que s'ha triat per jugar
    private static int perfilTriat;
    // Wave a partir de la qual el jugador començara a jugar
    private static int wave;
    // Total de temps que durara la wave
    private static int tempsTotal;
    // Total d'enemics que apareixeran
    private static int enemicsTotals;
    // Unitats disponibles per triar en la wave actual
    private static String unitatsDisponibles;
    // Enemics que sortiran en la wave
    private static String enemicsWave;
    // Unitats que ha triat el jugador per jugar la wave
    private static String unitatsTriades;
    private static String enemicsSeguentWave;
    // Dades del perfil triat
    private static ArxiuConfiguracio dadesPerfil;
    // Dades de la wave actual
    private static ArxiuConfiguracio dadesWave;
    // Indica si el jugador ha aconseguit superar una wave
    private static boolean canviWave;

    /**
     * Inicialitza les variables i recursos necessaris per usar la classe
     * @param perfil
     */
    public static void init(int perfil) {
        perfilTriat = perfil;
        if (perfilTriat == 1) {
            dadesPerfil = Configuracio.getPerfil1();
        } else if (perfilTriat == 2) {
            dadesPerfil = Configuracio.getPerfil2();
        } else if (perfilTriat == 3) {
            dadesPerfil = Configuracio.getPerfil3();
        }
        dadesWave = Configuracio.getWaves();
        wave = dadesPerfil.getPropietatInt("seguentWave");
        assignarPropietats();
    }

    /**
     * Quan un jugador supera una wave s'escriuen les dades necessaries
     */
    public static void passaASeguentWave() {
        wave++;
        dadesPerfil.setPropietatInt("seguentWave", wave);
        dadesPerfil.guardar();
        canviWave = true;
    }

    /**
     * Assigna les unitats disponibles que tindra el jugador per escollir en la pantalla seguent wave
     */
    private static void assignarPropietats() {
        unitatsDisponibles = dadesWave.getPropietatString("unitatsDisponibles" + wave);
        tempsTotal = dadesWave.getPropietatInt("temps"+wave);
        enemicsTotals = dadesWave.getPropietatInt("nombreEnemics"+wave);
        enemicsWave = retornaEnemics();
    }

    private static String retornaEnemics() {
        String enemics = "";
        String[] unitatsEnemigues = dadesWave.getPropietatString("enemicsWave"+wave).split("-");
        for (String z : unitatsEnemigues) {
            enemics += z.split(":")[0] + "-";
        }
        return enemics.substring(0, enemics.length()-1);
    }

    /**
     * Retorna les unitats disponibles
     * @return
     */
    public static String getUnitatsDisponibles() {
        if (canviWave) {
            assignarPropietats();
            canviWave = false;
        }
        return unitatsDisponibles;
    }

    public static String getEnemicsWave() {
        if (canviWave) {
            assignarPropietats();
            canviWave = false;
        }
        return enemicsWave;
    }

    // Getters i setters
    
    public static void setUnitatsTriades(String triades) {
        unitatsTriades = triades;
    }

    public static String getUnitatsTriades() {
        return unitatsTriades;
    }

    public static int getWave() {
        return wave;
    }

    public static void setWave(int wave) {
        ManagerPerfil.wave = wave;
    }

    public static int getEnemicsTotals() {
        return enemicsTotals;
    }

    public static void setEnemicsTotals(int enemicsTotals) {
        ManagerPerfil.enemicsTotals = enemicsTotals;
    }

    public static int getTempsTotal() {
        return tempsTotal;
    }

    public static void setTempsTotal(int tempsTotal) {
        ManagerPerfil.tempsTotal = tempsTotal;
    }
}
