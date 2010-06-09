package projecte.td.factories;

import projecte.td.managers.ManagerRecursos;
import projecte.td.domini.*;
import projecte.td.utilitats.*;

/**
 * Aquesta classe factory serveix enemics o unitats del tipus que se li passi com a
 * parametre.
 * @author David Alvarez Palau i Ernest Daban Macià
 */
public class FactoriaUnitatsEnemics {

    // Arxiu de configuracio amb la informació de les unitats
    private static ArxiuConfiguracio unitats;

    /**
     * Crea una unitat i la retorna
     * @param tipus String amb el tipus d'unitat sol·licitat
     * @return UnitatAbstact amb la unitat sol·licitada
     */
    public static UnitatAbstract getUnitatBona(String tipus) {
        if (unitats == null) {
            unitats = Configuracio.getUnitats();
        }
        UnitatAbstract bo = null;
        // Unitat Pistoler
        if (tipus.equals("Pistoler")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaPistoler"),
                    unitats.getPropietatInt("cadenciaPistoler"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationPistoler")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), -2, 20,
                    ManagerRecursos.getSound("pistolerSound"));
        } // Unitat Escopeta
        else if (tipus.equals("Escopeta")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaEscopeta"),
                    unitats.getPropietatInt("cadenciaEscopeta"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscopeta")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("escopetaProjectilAnimation"),
                    100), -2, -5, ManagerRecursos.getSound("focSound"));
        } // Unitat MetralletaLleugera
        else if (tipus.equals("MetralletaLleugera")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaMetralletaLleugera"),
                    unitats.getPropietatInt("cadenciaMetralletaLleugera"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMetralletaLleugera")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")),
                    -2, 4, ManagerRecursos.getSound("pistolerSound"));
        } // Unitat Miner
        else if (tipus.equals("Miner")) {
            bo = new Miner(unitats.getPropietatInt("vidaMiner"), unitats.getPropietatInt("cadenciaMiner"),
                    unitats.getPropietatInt("capacitatMiner"),
                    ManagerRecursos.getImageArray("minerAnimation"), 
                    ManagerRecursos.getImageArray("sangAnimation"), 60, "Miner",
                    ManagerRecursos.getSound("minerSound"));
        } // Unitat Mag Vida
        else if (tipus.equals("MagVida")) {
            bo = new Miner(unitats.getPropietatInt("vidaMagVida"),
                    unitats.getPropietatInt("cadenciaMagVida"),
                    unitats.getPropietatInt("capacitatMagVida"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMagVida")), 
                    ManagerRecursos.getImageArray("sangAnimation"), 60, "MagVida",
                    ManagerRecursos.getSound("auraSound"));
        } // Unitat Mag Rapidesa
        else if (tipus.equals("MagRapidesa")) {
            bo = new Miner(unitats.getPropietatInt("vidaMagRapidesa"),
                    unitats.getPropietatInt("cadenciaMagRapidesa"),
                    unitats.getPropietatInt("capacitatMagRapidesa"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMagRapidesa")), 
                    ManagerRecursos.getImageArray("sangAnimation"), 60, "MagRapidesa",
                    ManagerRecursos.getSound("auraSound"));
        } // Unitat Escut
        else if (tipus.equals("Escut")) {
            bo = new UnitatAbstract(unitats.getPropietatInt("vidaEscut"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationEscut")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60);
        } // Unitat Bomba
        else if (tipus.equals("Bomba")) {
            bo = new Bomba(unitats.getPropietatInt("vidaBomba"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBomba")), 60,
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("bombaProjectilAnimation"), 100),
                    ManagerRecursos.getSound("bombaSound"));
        } // Unitat Caixa
        else if (tipus.equals("Caixa")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaCaixa"),
                    unitats.getPropietatInt("cadenciaCaixa"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationCaixa")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilMobil(25, ManagerRecursos.getImage("lleugerImage")), 10, 4,
                    ManagerRecursos.getSound("pistolerSound"));
        } // Unitat Foc
        else if (tipus.equals("Foc")) {
            bo = new UnitatDispara(unitats.getPropietatInt("vidaFoc"),
                    unitats.getPropietatInt("cadenciaFoc"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationFoc")),
                    ManagerRecursos.getImageArray("sangAnimation"), 60,
                    new ProjectilEstatic(0.25, ManagerRecursos.getImageArray("focProjectilAnimation"), 100),
                    -2, -5, ManagerRecursos.getSound("focSound"));
        } // Unitat Mina
        else if (tipus.equals("Mina")) {
            bo = new Mina(unitats.getPropietatInt("vidaMina"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMina")), 60,
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation"), 100),
                    ManagerRecursos.getSound("bombaSound"));
        } // Unitat Motorista
        else if (tipus.equals("Motorista")) {
            bo = new Motorista(unitats.getPropietatInt("vidaMotorista"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationMotorista")), 60,
                    new ProjectilAnimat(200,
                    ManagerRecursos.getImageArray("motoristaAnimation"), 100),
                    ManagerRecursos.getSound("pistolerSound"));
        } // Unitat BombaAerea
        else if (tipus.equals("BombaAerea")) {
            bo = new BombaAerea(unitats.getPropietatInt("vidaBombaAerea"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationBombaAerea")), 60,
                    new ProjectilEstatic(20, ManagerRecursos.getImageArray("minaProjectilAnimation"), 100),
                    ManagerRecursos.getSound("bombaSound"));
        } //Unitat Aigua
        else if (tipus.equals("Aigua")) {
            bo = new UnitatAigua(unitats.getPropietatInt("vidaAigua"),
                    ManagerRecursos.getImageArray(unitats.getPropietatString("animationAigua")),
                    ManagerRecursos.getImageArray("sangAnimation"), 90);
        }
        return bo;
    }

    /**
     * Crea un enemic i el retorna
     * @param tipus String amb el tipus d'enemic sol·licitat
     * @return UnitatAbstact amb l'enemic sol·licitada
     */
    public static UnitatAbstract getUnitatDolenta(String tipus) {
        UnitatAbstract dolent = null;
        if (tipus.equals("Momia")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000,
                    ManagerRecursos.getImageArray("momiaCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.10, ManagerRecursos.getImageArray("momiaProjectilAnimation"),
                    100), ManagerRecursos.getImageArray("momiaAtacaAnimation"), 0.030, 100, -2, 4);
        } else if (tipus.equals("Natiu")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("natiuCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("natiuAtacaAnimation"), 0.050, 100, 0.25);
        } else if (tipus.equals("Espasa")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("espasaCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("espasaAtacaAnimation"), 0.060, 100, 0.25);
        } else if (tipus.equals("Ufo")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000,
                    ManagerRecursos.getImageArray("ufoAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("ufoProjectilAnimation"),
                    100), ManagerRecursos.getImageArray("ufoAnimation"), 0.08, 100, -2, 20);
        } else if (tipus.equals("Cranc")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("crancCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("crancAtacaAnimation"), 0.020, 100, 1);
        } else if (tipus.equals("Robot")) {
            dolent = new UnitatEnemigaAtkDistanciaSalta(100, 2000,
                    ManagerRecursos.getImageArray("robotCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.50, ManagerRecursos.getImageArray("robotProjectilAnimation"), 100), 
                    ManagerRecursos.getImageArray("robotAtacaAnimation"), 0.030, 100, -2, 10,
                    ManagerRecursos.getImageArray("robotCanviAnimation"));
        } else if (tipus.equals("Cuc")) {
            dolent = new UnitatEnemigaAtkNormal(100,
                    ManagerRecursos.getImageArray("cucCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("cucAtacaAnimation"), 0.020, 100, 1);
        } else if (tipus.equals("Insecte")) {
            dolent = new UnitatEnemigaVola(100, ManagerRecursos.getImageArray("insecteAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60, 0.050, 0.010);
        }
        else if (tipus.equals("Gos")) {
            dolent = new UnitatEnemigaInvisible(100,
                    ManagerRecursos.getImageArray("gosAtacaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("gosAnimation"), 0.050, 30, 0.80,
                    ManagerRecursos.getImageArray("gosCaminaAnimation"),
                    ManagerRecursos.getImageArray("gosInvisibleAnimation"));
        }
        else if(tipus.equals("Zombie")) {
            dolent = new UnitatEnemigaExplosio(100,
                    ManagerRecursos.getImageArray("zombieCaminaAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    ManagerRecursos.getImageArray("zombieAtacaAnimation"), 0.010, 100, 1);
        }
        else if(tipus.equals("BolaNeu")) {
            dolent = new UnitatEnemigaBolaNeu(10000,
                    ManagerRecursos.getImageArray("bolaNeuAnimation"),
                    ManagerRecursos.getImageArray("sangDretaAnimation"), 60, 0.040, 10000);
        }
        else if (tipus.equals("Yeti")) {
            dolent = new UnitatEnemigaAtkDistancia(100, 2000,
                    ManagerRecursos.getImageArray("yetiCaminaAnimation"), ManagerRecursos.getImageArray("sangDretaAnimation"), 60,
                    new ProjectilEstatic(0.10, ManagerRecursos.getImageArray("yetiProjectilAnimation"), 100), ManagerRecursos.getImageArray("yetiAtacaAnimation"), 0.030, 100, -2, 4);
        }
        return dolent;
    }
}
