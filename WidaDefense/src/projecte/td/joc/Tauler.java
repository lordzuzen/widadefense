/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.joc;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import projecte.td.domini.Bomba;
import projecte.td.managers.ManagerColisions;
import projecte.td.domini.Projectil;
import projecte.td.domini.ProjectilEstatic;
import projecte.td.domini.ProjectilMobil;
import projecte.td.domini.UnitatAbstract;
import projecte.td.domini.UnitatDispara;
import projecte.td.domini.UnitatEnemigaAtkDistancia;
import projecte.td.domini.UnitatEnemigaAtkNormal;

/**
 *
 * @author wida47909974
 */
public class Tauler {

    private int nFiles;
    private int nColumnes;
    private int nCeles;
    private int ampladaTotal;
    private int llargadaTotal;
    private double amplada;
    private double llargada;
    private boolean[][] clicades;
    private Rectangle[][] celes;
    private UnitatAbstract[][] unitatsAmigues;
    ArrayList<UnitatAbstract> unitatsAmigues_mortes;
    ArrayList[] arrays_enemics;
    ArrayList<UnitatAbstract> enemics_morts;
    ArrayList[] arrays_projectils_amics;
    ArrayList[] arrays_projectils_enemics;
    ArrayList<Projectil> projectils_finalitzats;
    private boolean[] controlaFiles;
    ArrayList<Explosio> explosions;
    ArrayList<Explosio> explosions_finalitzades;
    ManagerColisions mc;

    public Tauler(int nFiles, int nColumnes, int ampladaTotal, int llargadaTotal) {
        this.nFiles = nFiles;
        this.nColumnes = nColumnes;
        this.ampladaTotal = ampladaTotal;
        this.llargadaTotal = llargadaTotal;
        nCeles = nFiles * nColumnes;
        clicades = new boolean[nFiles][nColumnes];
        celes = new Rectangle[nFiles][nColumnes];
        amplada = ampladaTotal / nColumnes;
        llargada = llargadaTotal / nFiles;
        crearCeles();
        inicialitzar();

    }

    private void crearCeles() {
        int x = 0;
        int y = 0;
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                Rectangle r = new Rectangle(x, y, ampladaTotal / nColumnes, llargadaTotal / nFiles);
                celes[fil][col] = r;
                x += amplada;
            }
            x = 0;
            y += llargada;
        }
    }

    private void inicialitzar() {
        unitatsAmigues = new UnitatAbstract[nFiles][nColumnes];
        controlaFiles = new boolean[nFiles];
        arrays_enemics = new ArrayList[nFiles];
        arrays_projectils_amics = new ArrayList[nFiles];
        arrays_projectils_enemics = new ArrayList[nFiles];
        for (int i = 0; i < nFiles; i++) {
            ArrayList<UnitatAbstract> enemics = new ArrayList<UnitatAbstract>();
            arrays_enemics[i] = enemics;
            ArrayList<Projectil> projectils_amics = new ArrayList<Projectil>();
            arrays_projectils_amics[i] = projectils_amics;
            ArrayList<Projectil> projectils_enemics = new ArrayList<Projectil>();
            arrays_projectils_enemics[i] = projectils_enemics;
        }
        projectils_finalitzats = new ArrayList<Projectil>();
        explosions = new ArrayList<Explosio>();
        explosions_finalitzades = new ArrayList<Explosio>();
        enemics_morts = new ArrayList<UnitatAbstract>();
        unitatsAmigues_mortes = new ArrayList<UnitatAbstract>();
        mc = new ManagerColisions(arrays_enemics, arrays_projectils_amics, arrays_projectils_enemics, controlaFiles, unitatsAmigues);
    }

    public int[] mirarCoordenadesClick(int x, int y) {
        int[] posFC = new int[2];
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                if (celes[fil][col].contains(x, y)) {
                    posFC[0] = fil;
                    posFC[1] = col;
                }
            }
        }
        return posFC;
    }

    public boolean comprovarClick(int fil, int col) {
        if (!clicades[fil][col]) {
            return true;

        }
        return false;
    }

    public Rectangle prova(int a, int b) {
        return celes[a][b];
    }

    public void posicionaUnitatAmiga(int fil, int col, UnitatAbstract unitatAmiga) {
        clicades[fil][col] = true;
        //Posiciona unitat Amiga
        float Lx = (float) (celes[fil][col].getCenterX() - (double) unitatAmiga.getAnimation().getImage(0).getWidth() / 2);
        float Ly = (float) (celes[fil][col].getY() + llargada - unitatAmiga.getAnimation().getImage(0).getHeight());
        unitatAmiga.setLocation(Lx, Ly);
        unitatsAmigues[fil][col] = unitatAmiga;
    }

    public UnitatAbstract getUnitatAmiga(int x, int y) {
        int[] posFC = mirarCoordenadesClick(x, y);
        return unitatsAmigues[posFC[0]][posFC[1]];
    }

    public void eliminaUnitatAmiga(int fil, int col) {
        unitatsAmigues[fil][col] = null;
        clicades[fil][col] = false;
        stopAtacUnitatsEnemigues(fil);
    }

    public void posicionaUnitatEnemiga(int x, int y, UnitatAbstract enemic) {
        for (int fil = 0; fil < nFiles; fil++) {
            for (int col = 0; col < nColumnes; col++) {
                if (celes[fil][col].contains(x, y)) {
                    enemic.setLocation(ampladaTotal, (float) (celes[fil][col].getY() + llargada - enemic.getAnimation().getHeight()));
                    arrays_enemics[fil].add(enemic);
                    if (!controlaFiles[fil]) {
                        controlaFiles[fil] = true;
                    }

                }
            }
        }
    }

    public void stopUnitatsAmigues() {
        for (int i = 0; i < nFiles; i++) {
            if (arrays_enemics[i].isEmpty()) {
                controlaFiles[i] = false;
            }
        }
    }

    public void stopAtacUnitatsEnemigues(int fila) {
        for (Object en : arrays_enemics[fila]) {
            if (en instanceof UnitatEnemigaAtkDistancia) {
                UnitatEnemigaAtkDistancia enemic = (UnitatEnemigaAtkDistancia) en;
                enemic.desactivarDispars();
            } else if (en instanceof UnitatEnemigaAtkNormal) {
                UnitatEnemigaAtkNormal enemic = (UnitatEnemigaAtkNormal) en;
                enemic.setActivat(false);
            }

        }

    }

    private void dispararUnitatAmiga(UnitatAbstract ud, int numFila, Projectil p) {
        if (ud instanceof UnitatDispara) {
            UnitatDispara ud2 = (UnitatDispara) ud;
            if (ud2.estaDisparant()) {
                arrays_projectils_amics[numFila].add(p);
                ud2.haDisparat();
            }
            if (!ud2.estaActivat()) {
                ud2.activarDispars();
            }
        } else if (ud instanceof Bomba) {
            Bomba b = (Bomba) ud;
            if (b.isDispara()) {
                arrays_projectils_amics[numFila].add(p);
                b.haDisparat();
            }
        }
    }

    public void dispararUnitatsAmigues() {
        for (int i = 0; i < nFiles; i++) {
            for (UnitatAbstract t : unitatsAmigues[i]) {
                if (t instanceof Bomba) {
                    Bomba b = (Bomba) t;
                    dispararUnitatAmiga(b, i, b.getProjectil());
                } else {
                    if (controlaFiles[i]) {
                        if (t != null && t instanceof UnitatDispara) {
                            UnitatDispara ud = (UnitatDispara) t;
                            Projectil p = ud.getProjectil();
                            if (p instanceof ProjectilMobil) {
                                dispararUnitatAmiga(ud, i, p);
                            } else if (p instanceof ProjectilEstatic) {
                                if (!arrays_enemics[i].isEmpty()) {
                                    UnitatAbstract enemic = (UnitatAbstract) arrays_enemics[i].get(0);
                                    if (enemic.getPosX() <= ud.getPosX() + 150) {
                                        dispararUnitatAmiga(ud, i, p);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    public void disparaUnitatsEnemigues() {
        for (int i = 0; i < nFiles; i++) {
            if (controlaFiles[i]) {
                for (Object en : arrays_enemics[i]) {
                    if (en instanceof UnitatEnemigaAtkDistancia) {
                        UnitatEnemigaAtkDistancia enemic = (UnitatEnemigaAtkDistancia) en;
                        if (enemic.estaActivat() && enemic.estaDisparant()) {
                            arrays_projectils_enemics[i].add(enemic.getProjectil());
                            enemic.haDisparat();

                        }
                    }
                }
            }
        }
    }

    public void dibuixar(Graphics g, GameContainer gc) {
        for (int i = 0; i < nFiles; i++) {
            for (UnitatAbstract t : unitatsAmigues[i]) {
                if (t != null) {
                    t.render(gc, g);
                }
            }
            for (Object en : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) en;
                enemic.render(gc, g);

            }
            for (Object ob : arrays_projectils_amics[i]) {
                Projectil p = (Projectil) ob;
                p.render(gc, g);

            }
            for (Object ob : arrays_projectils_enemics[i]) {
                Projectil p = (Projectil) ob;
                p.render(gc, g);

            }
        }

        for (Explosio ex : explosions) {
            ex.dibuixar();
        }

    }

    public void finalitzarProjectils_Enemics() {
        for (int i = 0; i < nFiles; i++) {
            for (Projectil b : projectils_finalitzats) {
                arrays_projectils_amics[i].remove(b);
            }
            for (Projectil b : projectils_finalitzats) {
                arrays_projectils_enemics[i].remove(b);
            }
            for (UnitatAbstract en : enemics_morts) {
                arrays_enemics[i].remove(en);
            }


        }
    }

    public void acciona(int delta) {

        for (int i = 0; i < nFiles; i++) {
            for (Object en : arrays_enemics[i]) {
                UnitatAbstract enemic = (UnitatAbstract) en;
                enemic.update(delta);
                if (enemic.isMort()) {
                    enemics_morts.add(enemic);
                }

            }
            for (Object ob : arrays_projectils_amics[i]) {
                Projectil p = (Projectil) ob;
                if (p.isMort()) {
                    projectils_finalitzats.add(p);
                }
                p.update(delta);
            }
            for (Object ob : arrays_projectils_enemics[i]) {
                Projectil p = (Projectil) ob;
                if (p.isMort()) {
                    projectils_finalitzats.add(p);
                }
                p.update(delta);
            }
            for (int col = 0; col < nColumnes; col++) {
                if (unitatsAmigues[i][col] != null) {
                    unitatsAmigues[i][col].update(delta);
                    if (unitatsAmigues[i][col].isMort()) {
                        eliminaUnitatAmiga(i, col);
                    }
                }
            }



        }
        dispararUnitatsAmigues();
        stopUnitatsAmigues();
        finalitzarProjectils_Enemics();
        mc.comprovarColisions();
        disparaUnitatsEnemigues();

    }
}
