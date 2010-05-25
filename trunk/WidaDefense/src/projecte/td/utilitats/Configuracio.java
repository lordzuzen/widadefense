/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.utilitats;

import projecte.td.utilitats.ArxiuConfiguracio;

/**
 *
 * @author wida47645633
 */
public class Configuracio {

    private static ArxiuConfiguracio configuracio;
    private static ArxiuConfiguracio enemics;
    private static ArxiuConfiguracio recursos;
    private static ArxiuConfiguracio opcions;
    private static ArxiuConfiguracio unitats;
    private static ArxiuConfiguracio perfil1;
    private static ArxiuConfiguracio perfil2;
    private static ArxiuConfiguracio perfil3;
    private static ArxiuConfiguracio wave;

    static {
        configuracio = new ArxiuConfiguracio("config/td.cfg");
    }

    public static ArxiuConfiguracio getPerfil1() {
        if (perfil1 == null) {
            perfil1 = new ArxiuConfiguracio(configuracio.getPropietatString("perfil1"));
        }
        return perfil1;
    }

    public static ArxiuConfiguracio getPerfil2() {
        if (perfil2 == null) {
            perfil2 = new ArxiuConfiguracio(configuracio.getPropietatString("perfil2"));
        }
        return perfil2;
    }

    public static ArxiuConfiguracio getPerfil3() {
        if (perfil3 == null) {
            perfil3 = new ArxiuConfiguracio(configuracio.getPropietatString("perfil3"));
        }
        return perfil3;
    }

    public static ArxiuConfiguracio getWaves() {
        if (wave == null) {
            wave = new ArxiuConfiguracio(configuracio.getPropietatString("wave"));
        }
        return wave;
    }
    
    public static ArxiuConfiguracio getRecursos() {
        if (recursos == null) {
            recursos = new ArxiuConfiguracio(configuracio.getPropietatString("recursos"));
        }
        return recursos;
    }

    public static ArxiuConfiguracio getOpcions() {
        if (opcions == null) {
            opcions = new ArxiuConfiguracio(configuracio.getPropietatString("opcions"));
        }
        return opcions;
    }

    public static ArxiuConfiguracio getUnitats() {
        if (unitats == null) {
            unitats = new ArxiuConfiguracio(configuracio.getPropietatString("unitats"));
        }
        return unitats;
    }

    public static ArxiuConfiguracio getEnemics() {
        if (enemics == null) {
            enemics = new ArxiuConfiguracio(configuracio.getPropietatString("enemics"));
        }
        return enemics;
    }
}
