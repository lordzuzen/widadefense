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
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationPistoler")), ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } // Unitat Escopeta
        else if (tipus.equals("Escopeta")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaEscopeta"), unitats.getPropietatInt("cadenciaEscopeta"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscopeta")), ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("escopetaProjectilAnimation"), 100), -2, -5);
        } // Unitat MetralletaLleugera
        else if (tipus.equals("MetralletaLleugera")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaMetralletaLleugera"), unitats.getPropietatInt("cadenciaMetralletaLleugera"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMetralletaLleugera")), ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 4);
        } // Unitat Miner
        else if (tipus.equals("Miner")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"),
                    ManagerRecursos.getImageArray("minerAnimation"), ManagerRecursos.getImageArray("sangAnimation"), 60, "Miner");
        } // Unitat Mag Vida
        else if (tipus.equals("MagVida")) {
            bo = new Miner(unitats.getPropietatInt("vidaMagVida"), unitats.getPropietatInt("cadenciaMagVida"),
                    unitats.getPropietatInt("capacitatMagVida"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMagVida")), 
                    ManagerRecursos.getImageArray("sangAnimation"), 60, "MagVida");
        } // Unitat Mag Rapidesa
        else if (tipus.equals("MagRapidesa")) {
            bo = new Miner(unitats.getPropietatInt("vidaMagRapidesa"), unitats.getPropietatInt("cadenciaMagRapidesa"),
                    unitats.getPropietatInt("capacitatMagRapidesa"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMagRapidesa")), 
                    ManagerRecursos.getImageArray("sangAnimation"), 60, "MagRapidesa");
        } // Unitat Escut
        else if (tipus.equals("Escut")) {
            bo = new UnitatAbstract(unitats.getPropietatInt("vidaEscut"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscut")), ManagerRecursos.getImageArray("sangAnimation"), 60);
        } // Unitat Bomba
        else if (tipus.equals("Bomba")) {
            bo = new Bomba(unitats.getPropietatInt("vidaBomba"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBomba")), 60,
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("bombaProjectilAnimation"), 100));
        } // Unitat Caixa
        else if (tipus.equals("Caixa")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaCaixa"), unitats.getPropietatInt("cadenciaCaixa"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationCaixa")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), 10, 4);
        } // Unitat Foc
        else if (tipus.equals("Foc")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaFoc"), unitats.getPropietatInt("cadenciaFoc"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationFoc")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("focProjectilAnimation"), 100), -2, -5);
        } // Unitat Mina
        else if (tipus.equals("Mina")) {
            bo = new Mina(unitats.getPropietatInt("vidaMina"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMina")), 60,
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation"), 100));
        } // Unitat Motorista
        else if (tipus.equals("Motorista")) {
            bo = new Motorista(unitats.getPropietatInt("vidaMotorista"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMotorista")), 60,
                    new ProjectilAnimat(200,
                    ManagerRecursos.getImageArray("motoristaAnimation"), 100));
        } // Unitat BombaAerea
        else if (tipus.equals("BombaAerea")) {
            bo = new BombaAerea(unitats.getPropietatInt("vidaBombaAerea"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBombaAerea")), 60,
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation"), 100));
        }
        return bo;
    }

    public static UnitatAbstract getUnitatDolenta(String tipus) {
        UnitatAbstract dolent = null;
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000,
                    ManagerRecursos.getImageArray("momiaCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.10, ManagerRecursos.getImageArray("momiaProjectilAnimation"), 100), ManagerRecursos.getImageArray("momiaAtacaAnimation"), 0.030, 100, -2, 4);
        } else if (tipus.equals("Natiu")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("natiuCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("natiuAtacaAnimation"), 0.050, 100, 0.25);
        } else if (tipus.equals("Espasa")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("espasaCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("espasaAtacaAnimation"), 0.060, 100, 0.25);
        } else if (tipus.equals("Ufo")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000,
                    ManagerRecursos.getImageArray("ufoAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("ufoProjectilAnimation"), 100), ManagerRecursos.getImageArray("ufoAnimation"), 0.08, 100, -2, 20);
        } else if (tipus.equals("Cranc")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("crancCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("crancAtacaAnimation"), 0.020, 100, 1);
        } else if (tipus.equals("Robot")) {
            dolent = new UnitatEnemigaAtkDistanciaSalta(100, 2000,
                    ManagerRecursos.getImageArray("robotCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("robotProjectilAnimation"), 100), ManagerRecursos.getImageArray("robotAtacaAnimation"), 0.030, 100, -2, 20, ManagerRecursos.getImageArray("robotCanviAnimation"));
        } else if (tipus.equals("Cuc")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("cucCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("cucAtacaAnimation"), 0.020, 100, 1);
        } else if (tipus.equals("Insecte")) {
            dolent = new UnitatEnemigaVola(100, ManagerRecursos.getImageArray("insecteAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60, 0.050, 0.010);
        }
        return dolent;
    }
}
