package projecte.td.factories;

import java.util.HashMap;
import projecte.td.managers.ManagerRecursos;
import projecte.td.domini.*;
import projecte.td.utilitats.*;

/**
 *
 * @author wida47645633
 */
public class FactoriaUnitats {

    private static ArxiuConfiguracio unitats;
    private static HashMap<String,UnitatAbstract> llistaUnitats;

    public static void creaHashMap() {
        if (unitats == null) {
            unitats = Configuracio.getUnitats();
        }
        llistaUnitats = new HashMap<String,UnitatAbstract>();
        UnitatAbstract ua = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("minerImage"),
                    ManagerRecursos.getImageArray("minerAnimation"));
        llistaUnitats.put("Miner", ua);

    }

    public static UnitatAbstract getUnitatBona(String tipus) {
        if (unitats == null) {
            unitats = Configuracio.getUnitats();
        }
        UnitatAbstract bo = null;
        if (tipus.equals("Pistoler")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaPistoler"), unitats.getPropietatInt("cadenciaPistoler"),
                    ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("pistolerAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } else if (tipus.equals("Escopeta")) {
            bo = new UnitatDispara(100, 3000, ManagerRecursos.getImage("escopetaImage"),
                    ManagerRecursos.getImageArray("escopetaAnimation"),
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("escopetaProjectilAnimation")), -2, -5);
        } else if (tipus.equals("MetralletaLleugera")) {
            bo = new UnitatDispara(100, 2000, ManagerRecursos.getImage("escopetaImage"),
                    ManagerRecursos.getImageArray("metralletaLleugeraAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } else if (tipus.equals("Miner")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("minerImage"),
                    ManagerRecursos.getImageArray("minerAnimation"));
        } else if (tipus.equals("Escut")) { 
            bo = new UnitatAbstract(100, ManagerRecursos.getImage("escutImage"),
                    ManagerRecursos.getImageArray("escutAnimation"));
        }
        return bo;
    }

    public static UnitatAbstract getUnitatDolenta(String tipus) {
        UnitatAbstract dolent = null;
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("momiaCaminaAnimation"),
                    new ProjectilEstatic(0.10, ManagerRecursos.getImageArray("momiaProjectilAnimation")), ManagerRecursos.getImageArray("momiaAtacaAnimation"), -2, 4);
        } else if (tipus.equals("Natiu")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("natiuCaminaAnimation"),
                    ManagerRecursos.getImageArray("natiuAtacaAnimation"), 0.25);
        }
        else if (tipus.equals("Espasa")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("espasaCaminaAnimation"),
                    ManagerRecursos.getImageArray("espasaAtacaAnimation"),0.25);
        }
        else if (tipus.equals("Ufo")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("ufoAnimation"),
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("ufoProjectilAnimation")),ManagerRecursos.getImageArray("ufoAnimation"),-2,20);
        }

        else if (tipus.equals("Cranc")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("crancCaminaAnimation"),
                    ManagerRecursos.getImageArray("crancAtacaAnimation"),1);
        }
        else if (tipus.equals("Robot")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("robotCaminaAnimation"),
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("robotProjectilAnimation")),ManagerRecursos.getImageArray("robotAtacaAnimation"),-2,20);
        }
        return dolent;
    }
}
