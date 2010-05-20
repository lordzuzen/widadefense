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

    public static UnitatAbstract getUnitatBona(String tipus) {
        if (unitats == null) {
            unitats = Configuracio.getUnitats();
        }
        UnitatAbstract bo = null;
        // Unitat Pistoler
        if (tipus.equals("Pistoler")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaPistoler"), unitats.getPropietatInt("cadenciaPistoler"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imagePistoler")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationPistoler")),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } // Unitat Escopeta
        else if (tipus.equals("Escopeta")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaEscopeta"), unitats.getPropietatInt("cadenciaEscopeta"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageEscopeta")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscopeta")),
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("escopetaProjectilAnimation")), -2, -5);
        } // Unitat MetralletaLleugera
        else if (tipus.equals("MetralletaLleugera")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaMetralletaLleugera"), unitats.getPropietatInt("cadenciaMetralletaLleugera"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageMetralletaLleugera")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMetralletaLleugera")),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } // Unitat Miner
        else if (tipus.equals("Miner")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("minerImage"),
                    ManagerRecursos.getImageArray("minerAnimation"), "Miner");
        } // Unitat Mag Vida
        else if (tipus.equals("MagVida")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"), ManagerRecursos.getImage("magVidaImage"),
                    ManagerRecursos.getImageArray("magVidaAnimation"), "MagVida");
        } // Unitat Escut
        else if (tipus.equals("Escut")) {
            bo = new UnitatAbstract(unitats.getPropietatInt("vidaEscut"),
                    ManagerRecursos.getImage(unitats.getPropietatString("imageEscut")),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscut")));
        } // Unitat Bomba
        else if (tipus.equals("Bomba")) {
            bo = new Bomba(10000, ManagerRecursos.getImage("bombaImage"),
                    ManagerRecursos.getImageArray("bombaAnimation"),
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("bombaProjectilAnimation")));
        } // Unitat Caixa
        else if (tipus.equals("Caixa")) {
            bo = new UnitatDispara(300, 10000, ManagerRecursos.getImage("caixaImage"),
                    ManagerRecursos.getImageArray("caixaAnimation"),
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), 10, 4);
        } // Unitat Foc
        else if (tipus.equals("Foc")) {
            bo = new UnitatDispara(100, 4000, ManagerRecursos.getImage("focImage"),
                    ManagerRecursos.getImageArray("focAnimation"),
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
        UnitatAbstract dolent = null;
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("momiaCaminaAnimation"),
                    new ProjectilEstatic(0.10, ManagerRecursos.getImageArray("momiaProjectilAnimation")), ManagerRecursos.getImageArray("momiaAtacaAnimation"), 0.030, -2, 4);
        } else if (tipus.equals("Natiu")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("natiuCaminaAnimation"),
                    ManagerRecursos.getImageArray("natiuAtacaAnimation"), 0.050, 0.25);
        } else if (tipus.equals("Espasa")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("espasaCaminaAnimation"),
                    ManagerRecursos.getImageArray("espasaAtacaAnimation"), 0.060, 0.25);
        } else if (tipus.equals("Ufo")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("ufoAnimation"),
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("ufoProjectilAnimation")), ManagerRecursos.getImageArray("ufoAnimation"), 0.08, -2, 20);
        } else if (tipus.equals("Cranc")) {
            dolent = new UnitatEnemigaAtkNormal(100, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("crancCaminaAnimation"),
                    ManagerRecursos.getImageArray("crancAtacaAnimation"), 0.020, 1);
        } else if (tipus.equals("Robot")) {
            dolent = new UnitatEnemigaAtkDistanciaSalta(100, 2000, ManagerRecursos.getImage("pistolerImage"),
                    ManagerRecursos.getImageArray("robotCaminaAnimation"),
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("robotProjectilAnimation")), ManagerRecursos.getImageArray("robotAtacaAnimation"), 0.030, -2, 20, 2000);
        }
        return dolent;
    }
}
