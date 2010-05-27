package projecte.td.factories;

import projecte.td.managers.ManagerRecursos;
import projecte.td.domini.*;
import projecte.td.utilitats.*;

/**
 *
 * @author wida47645633
 */
public class FactoriaUnitats {

    private static ArxiuConfiguracio unitats;
    private static ArxiuConfiguracio enemics;

    public static UnitatAbstract getUnitatBona(String tipus) {
        if (unitats == null) {
            unitats = Configuracio.getUnitats();
        }
        UnitatAbstract bo = null;
        // Unitat Pistoler
        if (tipus.equals("Pistoler")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaPistoler"), unitats.getPropietatInt("cadenciaPistoler"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imagePistoler")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationPistoler")),ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } // Unitat Escopeta
        else if (tipus.equals("Escopeta")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaEscopeta"), unitats.getPropietatInt("cadenciaEscopeta"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageEscopeta")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscopeta")),ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("escopetaProjectilAnimation")), -2, -5);
        } // Unitat MetralletaLleugera
        else if (tipus.equals("MetralletaLleugera")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaMetralletaLleugera"), unitats.getPropietatInt("cadenciaMetralletaLleugera"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageMetralletaLleugera")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMetralletaLleugera")),ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } // Unitat Miner
        else if (tipus.equals("Miner")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("minerImage"),
                    ManagerRecursos.getImageArray("minerAnimation"),ManagerRecursos.getImageArray("sangAnimation"), "Miner");
        } // Unitat Mag Vida
        else if (tipus.equals("MagVida")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("magVidaImage"),
                    ManagerRecursos.getImageArray("magVidaAnimation"),ManagerRecursos.getImageArray("sangAnimation"), "MagVida");
        } // Unitat Mag Rapidesa
        else if (tipus.equals("MagRapidesa")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("magRapidesaImage"),
                    ManagerRecursos.getImageArray("magRapidesaAnimation"),ManagerRecursos.getImageArray("sangAnimation"), "MagRapidesa");
        } // Unitat Escut
        else if (tipus.equals("Escut")) {
            bo = new UnitatAbstract(unitats.getPropietatInt("vidaEscut"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageEscut")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscut")),ManagerRecursos.getImageArray("sangAnimation"));
        } // Unitat Bomba
        else if (tipus.equals("Bomba")) {
            bo = new Bomba(10000, ManagerRecursos.getImage("bombaImage"),
                    ManagerRecursos.getImageArray("bombaAnimation"),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("bombaProjectilAnimation")));
        } // Unitat Caixa
        else if (tipus.equals("Caixa")) {
            bo = new UnitatDispara(300, 10000, ManagerRecursos.getImage("caixaImage"),
                    ManagerRecursos.getImageArray("caixaAnimation"),ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), 10, 4);
        } // Unitat Foc
        else if (tipus.equals("Foc")) {
            bo = new UnitatDispara(100, 4000, ManagerRecursos.getImage("focImage"),
                    ManagerRecursos.getImageArray("focAnimation"),ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("focProjectilAnimation")), -2, -5);
        } // Unitat Mina
        else if (tipus.equals("Mina")) {
            bo = new Mina(200, ManagerRecursos.getImage("minaImage"),
                    ManagerRecursos.getImageArray("minaAnimation"),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation")));
        } // Unitat Motorista
        else if (tipus.equals("Motorista")) {
            bo = new Motorista(100, ManagerRecursos.getImage("motoristaImage"),
                    ManagerRecursos.getImageArray("motoristaAnimation"),
                    new ProjectilAnimat(200, ManagerRecursos.getImage("motoristaImage"),
                    ManagerRecursos.getImageArray("motoristaAnimation")));
        } // Unitat BombaAerea
        else if (tipus.equals("BombaAerea")) {
            bo = new BombaAerea(100, ManagerRecursos.getImage("bombaAereaImage"),
                    ManagerRecursos.getImageArray("bombaAereaAnimation"),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation")));
        }
        return bo;
    }

    public static UnitatAbstract getUnitatDolenta(String tipus) {
        if (enemics == null) {
            enemics = Configuracio.getEnemics();
        }
        UnitatAbstract dolent = null;
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(enemics.getPropietatInt("vidaMomia"), enemics.getPropietatInt("cadenciaMomia"),
                    ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("momiaCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    new ProjectilEstatic(enemics.getPropietatFloat("atacMomia"), ManagerRecursos.getImageArray("momiaProjectilAnimation")), 
                    ManagerRecursos.getImageArray("momiaAtacaAnimation"), enemics.getDoublePropietat("velocitatMomia"),
                    enemics.getPropietatFloat("projXMomia"),
                    enemics.getPropietatFloat("projXMomia"));
        } else if (tipus.equals("Natiu")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("natiuCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    ManagerRecursos.getImageArray("natiuAtacaAnimation"), 0.050, 0.25);
        } else if (tipus.equals("Espasa")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("espasaCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    ManagerRecursos.getImageArray("espasaAtacaAnimation"), 0.060, 0.25);
        } else if (tipus.equals("Ufo")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("ufoAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("ufoProjectilAnimation")), ManagerRecursos.getImageArray("ufoAnimation"), 0.08, -2, 20);
        } else if (tipus.equals("Cranc")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("crancCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    ManagerRecursos.getImageArray("crancAtacaAnimation"), 0.020, 1);
        } else if (tipus.equals("Robot")) {
            dolent = new UnitatEnemigaAtkDistanciaSalta(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("robotCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("robotProjectilAnimation")), ManagerRecursos.getImageArray("robotAtacaAnimation"), 0.030, -2, 20, ManagerRecursos.getImageArray("robotCanviAnimation"));
        }
        else if (tipus.equals("Cuc")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("cucCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    ManagerRecursos.getImageArray("cucAtacaAnimation"), 0.020, 1);
        }
        else if (tipus.equals("Insecte")) {
            dolent = new UnitatEnemigaVola(100, ManagerRecursos.getImage("pistolerImage"), ManagerRecursos.getImageArray("insecteAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"), 0.050,0.010);
        }
        return dolent;
    }
}
