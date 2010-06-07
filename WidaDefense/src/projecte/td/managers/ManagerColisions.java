/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.managers;

import projecte.td.domini.*;
import java.util.ArrayList;

/**
 *
 * @author wida47909974
 */
public class ManagerColisions {

    ArrayList[] arrays_enemics;
    ArrayList[] arrays_projectils_amics;
    ArrayList[] arrays_projectils_enemics;
    private boolean[] controlaFiles;
    private UnitatAbstract[][] unitatsAmigues;

    public ManagerColisions(ArrayList[] arrays_enemics, ArrayList[] arrays_projectils_amics, ArrayList[] arrays_projectils_enemics, boolean[] controlaFiles, UnitatAbstract[][] unitatsAmigues) {
        this.arrays_enemics = arrays_enemics;
        this.arrays_projectils_amics = arrays_projectils_amics;
        this.arrays_projectils_enemics = arrays_projectils_enemics;
        this.controlaFiles = controlaFiles;
        this.unitatsAmigues = unitatsAmigues;
    }

    public void comprovarColisions() {
        //Colisions Projectils Amics-Enemics
        for (int i = 0; i < controlaFiles.length; i++) {
            if (controlaFiles[i]) {
                for (Object ob1 : arrays_enemics[i]) {
                    UnitatAbstract enemic = (UnitatAbstract) ob1;
                    for (Object ob2 : arrays_projectils_amics[i]) {
                        Projectil p = (Projectil) ob2;
                        if (enemic instanceof UnitatEnemigaInvisible) {
                            UnitatEnemigaInvisible ui = (UnitatEnemigaInvisible) enemic;
                            ui.canviShape();
                            if (!ui.isInvisible() && ui.collideWith(p.getShape())) {
                                enemic.impacte(p.getDany());
                                p.impacte();
                            }
                            ui.segonCanviShape();
                        } else if (enemic.collideWith(p.getShape())) {
                            if (p instanceof ProjectilMobil) {
                                if (!p.isMort()) {
                                    enemic.impacte(p.getDany());
                                }
                            } else {
                                enemic.impacte(p.getDany());
                            }
                            p.impacte();
                        }
                    }
                }
            }
        }
        //Colisions Projectils Enemics-Amics
        for (int i = 0; i < controlaFiles.length; i++) {
            if (controlaFiles[i]) {
                for (int col = 0; col < unitatsAmigues[i].length; col++) {
                    if (unitatsAmigues[i][col] != null) {
                        for (Object ob : arrays_projectils_enemics[i]) {
                            Projectil p = (Projectil) ob;
                            if (unitatsAmigues[i][col].collideWith(p.getShape())) {
                                unitatsAmigues[i][col].impacte(p.getDany());
                                p.impacte();
                            }
                        }
                    }
                }
            }
        }

        //Colisions Enemics-Amics
        for (int i = 0; i < controlaFiles.length; i++) {
            if (!arrays_enemics[i].isEmpty()) {
                for (Object ob1 : arrays_enemics[i]) {
                    UnitatEnemiga enemic = (UnitatEnemiga) ob1;
                    for (UnitatAbstract unitatAmiga : unitatsAmigues[i]) {
                        if (unitatAmiga != null) {
                            if (unitatAmiga.collideWith(enemic.getShape()) && unitatAmiga.getPosX() <= enemic.getPosX()) {
                                if (enemic instanceof UnitatEnemigaAtkDistancia) {
                                    UnitatEnemigaAtkDistancia enemicD = (UnitatEnemigaAtkDistancia) enemic;
                                    enemicD.activarDispars();
                                } else if (enemic instanceof UnitatEnemigaAtkNormal) {
                                    UnitatEnemigaAtkNormal enemicN = (UnitatEnemigaAtkNormal) enemic;
                                    enemicN.setActivat(true);
                                    //Bola Neu
                                    if (enemic instanceof UnitatEnemigaBolaNeu) {
                                        if (unitatAmiga instanceof UnitatAigua) {
                                            UnitatEnemigaBolaNeu un = (UnitatEnemigaBolaNeu) enemic;
                                            un.rebreAigua();
                                        } else {
                                            unitatAmiga.impacte(enemicN.getDany());
                                        }
                                    } else {
                                        unitatAmiga.impacte(enemicN.getDany());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
