package projecte.td.componentGUI;

import java.util.ArrayList;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;

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
    private int nColumnesMenu1 = 4;
    // Nombre de columnes que es volen mostrar en la tria d'unitats
    private int nColumnesMenu2 = 8;
    // Indica si hi ha hagut algun canvi
    private boolean canvi;
    private boolean mostrarInformacio;
    private boolean actiu;
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
    private ArrayList<LabelSeleccio> cartesEnemics;
    private ArxiuConfiguracio unitats;
    private ArxiuConfiguracio enemics;
    private Sound soClick;
    private Sound soOver;
    private Font font;

    public MenuSeleccio(GameContainer container, int x, int y) {
        this.container = container;
        posX = x;
        posY = y;
        posXVariable = posX + 40;
        posYVariable = posY + 220;
        botonsSeleccio = new ArrayList<BotoSeleccio>();
        botonsSeleccio2 = new ArrayList<BotoSeleccio>();
        botonsTriats = new ArrayList<BotoSeleccio>();
        botonsTriats2 = new ArrayList<BotoSeleccio>();
        cartesEnemics = new ArrayList<LabelSeleccio>();
        unitatsAMostrar = ManagerPerfil.getUnitatsDisponibles();
        unitats = Configuracio.getUnitats();
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
        font = ManagerRecursos.getFont("dejavuNormalFont");
        actiu = true;
        crearBotons();
        posicionarBotons();
        crearCartes();
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
                mostrarInformacio = true;
            } else {
                comptador++;
            }
        }
        if (comptador == botonsSeleccio.size()) {
            informacio = "";
            mostrarInformacio = false;
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
        if (actiu) {
            g.setFont(font);
            g.drawString("Wave: " + ManagerPerfil.getWave(), 730, 220);
        }
        for (LabelSeleccio ls : cartesEnemics) {
            ls.render(g);
        }
        if (mostrarInformacio && actiu) {
            g.drawString(informacio, 555, 500);
            int posicio = 540;
            String[] infoUnitat = retornaInformacioUnitat();
            for (String z : infoUnitat) {
                g.drawString(z, 555, posicio);
                posicio += 30;
            }
        }
    }

    private String[] retornaInformacioUnitat() {
        String[] infoUnitat = new String[unitats.getPropietatInt("totalInfo" + informacio)];
        for (int z = 0; z < infoUnitat.length; z++) {
            infoUnitat[z] = unitats.getPropietatString("informacio" + informacio + z);
        }
        return infoUnitat;
    }

    /**
     * Comprova si s'ha de canviar alguna unitat d'una zona a una altra
     * (Zona de seleccio a zona d'unitat ja triada i viceversa)
     */
    private void comprovarMoviments() {
        if (botonsTriats.size() < 8) {
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
        } else {
            for (BotoSeleccio b : botonsSeleccio) {
                if (b.isNotaCanvi()) {
                    b.setNotaCanvi(false);
                }
            }
        }
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
            int posicioBotoX = (columnes * 90) + posXVariable;
            int posicioBotoY = (files * 110) + posYVariable;
            b.setLocation(posicioBotoX, posicioBotoY);
            if (columnes == nColumnesMenu1) {
                columnes = 0;
                files++;
            } else {
                columnes++;
            }
        }
        columnes = 0;
        files = 0;
        for (BotoSeleccio b : botonsTriats) {
            int posicioBotoX = (columnes * 90) + 40;
            int posicioBotoY = (files * 110) + 40;
            b.setLocation(posicioBotoX, posicioBotoY);
            if (columnes == nColumnesMenu2) {
                columnes = 0;
                files++;
            } else {
                columnes++;
            }
        }
    }

    private void posicionaCartes() {
        int columnes = 0;
        int files = 0;
        for (LabelSeleccio ls : cartesEnemics) {
            int posicioBotoX = (columnes * 90) + 550;
            int posicioBotoY = (files * 110) + 270;
            ls.setLocation(posicioBotoX, posicioBotoY);
            if (columnes == 4) {
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
        BotoSeleccio.setImatgeCarta(ManagerRecursos.getImage("botoCartaImage"));
        BotoSeleccio.setImatgeCartaOver(ManagerRecursos.getImage("botoCartaOverImage"));
        for (String text : s) {
            BotoSeleccio bs = new BotoSeleccio(container, ManagerRecursos.getImage("carta" + text + "Image"),
                    0, 0, text);
            bs.addListener();
            bs.setMouseDownSound(soClick);
            bs.setMouseOverSound(soOver);
            bs.setActiu(true);
            botonsSeleccio.add(bs);
        }
    }

    private void crearCartes() {
        String[] enemicsImage = ManagerPerfil.getEnemicsWave().split("-");
        for (String z : enemicsImage) {
            LabelSeleccio ls = new LabelSeleccio(ManagerRecursos.getImage("botoCartaImage"),
                    ManagerRecursos.getImage("carta" + z + "Image"));
            cartesEnemics.add(ls);
        }
        posicionaCartes();
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
            return seleccio.substring(0, seleccio.length() - 1);
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

    public void moureBotons(int incrementY) {
        for (BotoSeleccio bs : botonsSeleccio) {
            bs.setLocation(bs.getX(), bs.getY() + incrementY);
        }
        for (BotoSeleccio bs : botonsTriats) {
            bs.setLocation(bs.getX(), bs.getY() + incrementY);
        }
        for (LabelSeleccio ls : cartesEnemics) {
            ls.setLocation(ls.getPosX(), ls.getPosY() + incrementY);
        }
    }

    public void silenciarBotons() {
        for (BotoSeleccio bs : botonsSeleccio) {
            bs.setActiu(false);
        }
        for (BotoSeleccio bs : botonsTriats) {
            bs.setActiu(false);
        }
        actiu = false;
    }
}
