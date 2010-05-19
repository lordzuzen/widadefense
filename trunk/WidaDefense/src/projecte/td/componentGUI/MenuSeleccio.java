package projecte.td.componentGUI;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;

/**
 * Aquesta classe s'utlitza com a suport de l'estat SeguentWave
 * Comprova quines unitats es poden seleccionar a la wave actual i quines unitats
 * ha seleccionat el jugador per començar a jugar la partida
 */
public class MenuSeleccio {

    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als diferents estats del joc
    private StateBasedGame state;
    // Unitats que es mostraran per poder seleccionar
    private String unitatsAMostrar;
    // Informacio referent a cada unitat
    private String informacio;
    // Posicio X on es començara a col·locar el primer boto de tria d'unitat
    private int posXVariable;
    // Posicio Y on es començara a col·locar el primer boto de tria d'unitat
    private int posYVariable;
    // Nombre de columnes que es volen mostrar en la tria d'unitats
    private int nColumnes = 4;
    // Indica si hi ha hagut algun canvi
    private boolean canvi;
    // Posicio X del menu
    int posX;
    // Posicio Y del menu
    int posY;
    // ArrayList on es guarden els botons de seleccio d'unitats
    private ArrayList<BotoSeleccio> botonsSeleccio;
    // ArrayList que s'utilitza per borrar les unitats necessaries de l'arraylist anterior
    private ArrayList<BotoSeleccio> botonsSeleccio2;
    // ArrayList on es guarden els botons de unitats ja triades
    private ArrayList<BotoSeleccio> botonsTriats;
    // ArrayList que s'utilitza per borrar les unitats necessaries de l'arraylist anterior
    private ArrayList<BotoSeleccio> botonsTriats2;

    public MenuSeleccio(GameContainer container, int x, int y) {
        this.container = container;
        posX = x;
        posY = y;
        posXVariable = posX + 60;
        posYVariable = posY + 60;
        botonsSeleccio = new ArrayList<BotoSeleccio>();
        botonsSeleccio2 = new ArrayList<BotoSeleccio>();
        botonsTriats = new ArrayList<BotoSeleccio>();
        botonsTriats2 = new ArrayList<BotoSeleccio>();
        unitatsAMostrar = ManagerPerfil.getUnitatsDisponibles();
        crearBotons();
        posicionarBotons();
    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer game, StateBasedGame state, int delta) {
        comprovarMoviments();
        if (canvi) {
            canvi = false;
            posicionarBotons();
        }
        int comptador = 0;
        for (BotoSeleccio b : botonsSeleccio) {
            if (b.isOver()) {
                informacio = b.getUnitat();
            } else {
                comptador++;
            }
        }
        if (comptador == botonsSeleccio.size()) {
            informacio = "";
        }
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer game, StateBasedGame state, Graphics g) {
        for (BotoSeleccio b : botonsSeleccio) {
            b.render(container, g);
        }
        for (BotoSeleccio b : botonsTriats) {
            b.render(container, g);
        }
        g.drawString(informacio, 200, 400);
        g.drawString("Wave: " + ManagerPerfil.getWave(), 500, 500);
    }

    /**
     * Comprova si s'ha de canviar alguna unitat d'una zona a una altra
     * (Zona de seleccio a zona d'unitat ja triada i viceversa)
     */
    private void comprovarMoviments() {
        for (BotoSeleccio b : botonsSeleccio) {
            if (b.isNotaCanvi()) {
                b.setNotaCanvi(false);
                botonsSeleccio2.add(b);
                botonsTriats.add(b);
                canvi = true;
            }
        }
        botonsSeleccio.removeAll(botonsSeleccio2);
        botonsSeleccio2.clear();
        for (BotoSeleccio b : botonsTriats) {
            if (b.isNotaCanvi()) {
                b.setNotaCanvi(false);
                botonsTriats2.add(b);
                botonsSeleccio.add(b);
                canvi = true;
            }
        }
        botonsTriats.removeAll(botonsTriats2);
        botonsTriats2.clear();

    }

    /**
     * Posiciona els botons a la posicio que els pertoca
     */
    private void posicionarBotons() {
        int columnes = 0;
        int files = 0;
        for (BotoSeleccio b : botonsSeleccio) {
            int posicioBotoX = (columnes * 70) + posXVariable;
            int posicioBotoY = (files * 100) + posYVariable;
            b.setLocation(posicioBotoX, posicioBotoY);
            if (columnes == nColumnes) {
                columnes = 0;
                files++;
            } else {
                columnes++;
            }
        }
        columnes = 0;
        files = 0;
        for (BotoSeleccio b : botonsTriats) {
            int posicioBotoX = (columnes * 70) + posXVariable + 400;
            int posicioBotoY = (files * 100) + posYVariable;
            b.setLocation(posicioBotoX, posicioBotoY);
            if (columnes == nColumnes) {
                columnes = 0;
                files++;
            } else {
                columnes++;
            }
        }
    }

    /**
     * Crea els botons necessaris
     */
    private void crearBotons() {
        String[] s = unitatsAMostrar.split("-");
        for (String text : s) {
            BotoSeleccio bs = new BotoSeleccio(container, ManagerRecursos.getImage("botoIngame" + text + "Image"),
                    0, 0, text);
            bs.addListener();
            botonsSeleccio.add(bs);
        }
    }

    /**
     * Retorna les unitats que ha seleccionat el jugador per jugar la wave seguent
     * @return
     */
    public String agafarUnitats() {
        String seleccio = "";
        for (BotoSeleccio b : botonsTriats) {
            seleccio += b.getUnitat() + "-";
        }
        if (seleccio.length() > 1) {
            return seleccio.substring(0,seleccio.length()-1);
        }
        return "";
    }

    /**
     * Comprova si s'ha seleccionat alguna unitat, sino s'ha seleccionat no deixa continuar
     * @return
     */
    public boolean unitatsNoNull() {
        if (agafarUnitats().length() > 1) {
            return true;
        }
        return false;
    }
}
