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
            bo = new Bomba(unitats.getPropietatInt("vidaBomba"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageBomba")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBomba")),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("bombaProjectilAnimation")));
        } // Unitat Caixa
        else if (tipus.equals("Caixa")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaCaixa"), unitats.getPropietatInt("cadenciaCaixa"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageCaixa")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationCaixa")),
                    ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), 10, 4);
        } // Unitat Foc
        else if (tipus.equals("Foc")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaFoc"), unitats.getPropietatInt("cadenciaFoc"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageFoc")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationFoc")),
                    ManagerRecursos.getImageArray("sangAnimation"),
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("focProjectilAnimation")), -2, -5);
        } // Unitat Mina
        else if (tipus.equals("Mina")) {
            bo = new Mina(unitats.getPropietatInt("vidaMina"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageMina")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMina")),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation")));
        } // Unitat Motorista
        else if (tipus.equals("Motorista")) {
            bo = new Motorista(unitats.getPropietatInt("vidaMotorista"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageMotorista")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMotorista")),
                    new ProjectilAnimat(200, ManagerRecursos.getImage("motoristaImage"),
                    ManagerRecursos.getImageArray("motoristaAnimation")));
        } // Unitat BombaAerea
        else if (tipus.equals("BombaAerea")) {
            bo = new BombaAerea(unitats.getPropietatInt("vidaBombaAerea"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageBombaAerea")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBombaAerea")),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation")));
        }
        return bo;
    }

    public static UnitatAbstract getUnitatDolenta(String tipus) {
        UnitatAbstract dolent = null;
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("momiaCaminaAnimation"),ManagerRecursos.getImageArray("sangDretaAnimation"),
                    new ProjectilEstatic(0.10, ManagerRecursos.getImageArray("momiaProjectilAnimation")), ManagerRecursos.getImageArray("momiaAtacaAnimation"), 0.030, -2, 4);
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
