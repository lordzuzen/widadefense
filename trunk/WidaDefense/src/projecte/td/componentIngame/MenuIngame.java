package projecte.td.componentIngame;

import java.util.ArrayList;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.estats.EstatGuanya;
import projecte.td.managers.ManagerRecursos;
import projecte.td.factories.FactoriaUnitats;
import projecte.td.managers.ManagerDiners;
import projecte.td.managers.ManagerEnemics;
import projecte.td.managers.ManagerPerfil;

/**
 *
 * @author media
 */
public class MenuIngame extends AbstractComponent {

    // gui: context en el que es situara el MenuIngame
    private GUIContext gui;
    private StateBasedGame state;
    // image: imatge que representara el menu
    private Image image;
    private Image imatgeMoneda;
    private Image imatgeAura;
    private Image contenidor;
    // area: area que conte el MenuIngame
    private Shape area;
    // botonsUnitat: arraylist on es guardaran els botonsUnitat de que disposa el menu
    private ArrayList<BotoIngame> botonsUnitat;
    // botoAux : boto que s'utilitza per guarda la referencia a un boto 
    private BotoIngame botoAux;
    // botoOpcions: para la partida i accedeix al menu
    private BotoMenu botoOpcions;
    //botoPala
    private BotoIngame botoPala;
    // unitats: s'utilitza per inicialitzar correctament els botonsUnitat i les imatges corresponents
    private String unitats;
    // elementEsperant: indica quin element esta esperant per ser posicionat
    private String elementEsperant;
    private String auraDisponible;
    // enEspera: flag per comprovar si hi ha algun element en espera
    private boolean enEspera;
    // cost: variable auxiliar on es guarda el cost del element per posicionar
    private int cost;
    // posXVariable: posicio X inicial a partir de la qual es posicionen els botonsUnitat
    private int posXVariable;
    // posXVariable: posicio Y inicial a partir de la qual es posicionen els botonsUnitat
    private int posYVariable;
    // md:
    private ManagerDiners md;
    private Sound soClick;
    private Sound soOver;
    private Font font;
    private boolean haSonat;

    /**
     * Contenidor pel menu ingame del joc on es podran escollir els elements que es posicionaran en
     * pantalla
     * @param gui: Context on es situara el menu
     * @param posX: posicio en l'eix X del menu
     * @param posY: posicio en l'eix Y del menu
     * @param image: imatge que representara el menu
     * @param unitats: unitats que estaran disponibles per seleccionar
     * @param md: manager que controla els diners que te el jugador durant la partida
     */
    public MenuIngame(GUIContext gui, int posX, int posY, Image image, String unitats, ManagerDiners md, StateBasedGame state) {
        super(gui);
        this.gui = gui;
        this.state = state;
        this.posXVariable = posX + 30;
        this.posYVariable = posY;
        this.image = image;
        area = new Rectangle(posX, posY, image.getWidth(), image.getHeight());
        this.unitats = unitats;
        this.md = md;
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
        font = ManagerRecursos.getFont("dejavuNormalFont");
        imatgeMoneda = ManagerRecursos.getImage("monedaImage");
        init();
        crearBotonsUnitats();
        crearBotoMenu();
        afegirListener();
        crearBotoPala();
    }

    /**
     * Inicialitza els components essencials
     */
    private void init() {
        botonsUnitat = new ArrayList<BotoIngame>();
        elementEsperant = "null";
    }

    /**
     * Es renderitzen tots els components del boto
     * @param gc : context en el que s'ha de renderitzar
     * @param g : objecte grafics que s'utilitza per renderitzar imatges i animacions
     */
    public void render(GUIContext gc, Graphics g) {
        int xp = 0;
        int yp = 600;
        g.drawImage(image, xp, yp);
        for (BotoIngame b : botonsUnitat) {
            b.render(gui, g);
        }
        botoPala.render(gc, g);
        botoOpcions.render(container, g);
        g.setFont(font);
        g.drawImage(imatgeMoneda, 820, 625);
        g.drawString(md.getTotal() + "", 880, 625);
        if (auraDisponible.equals("MagVida")) {
            g.drawImage(imatgeAura, 830, 670);
        } else if (auraDisponible.equals("MagRapidesa")) {
            g.drawImage(imatgeAura, 830, 670);
        }
    }

    /**
     * Actualitza el menu i els botonsUnitat que conte aquest
     */
    public void update() {
        for (BotoIngame b : botonsUnitat) {
            b.update(md.getTotal());
            if (b.isClicat()) {
                elementEsperant = b.getAccio();
                enEspera = true;
                botoAux = b;
            }
        }
        botoPala.update(md.getTotal());
        if (botoPala.isClicat()) {

            elementEsperant = botoPala.getAccio();
            enEspera = true;
            botoAux = botoPala;
        }
        if (md.isAuraEnEspera()) {
            auraDisponible = md.getTipusAuraEspera();
            if (auraDisponible.equals("MagVida")) {
                imatgeAura = ManagerRecursos.getImage("monedaVidaImage");
            } else if (auraDisponible.equals("MagRapidesa")) {
                imatgeAura = ManagerRecursos.getImage("monedaRapidesaImage");
            }
        } else {
            auraDisponible = "null";
        }
    }

    /**
     * Crea els botonsUnitat necessaris per a cada menu
     */
    private void crearBotonsUnitats() {
        String[] st = unitats.split("-");
        BotoIngame.setImatgeSeleccionat(ManagerRecursos.getImage("marcSeleccionatIngameImage"));
        BotoIngame.setImatgeNoSeleccionat(ManagerRecursos.getImage("marcIngameImage"));
        BotoIngame.setImatgeTapada(ManagerRecursos.getImage("noSeleccionableIngameImage"));
        int comptador = 0;
        for (String s : st) {
            int posX = (comptador * 90) + 40;
            BotoIngame boto = new BotoIngame(gui, ManagerRecursos.getImage("carta" + s + "Image"),
                    posX, 640, 200, true);
            boto.addListener(s);
            botonsUnitat.add(boto);
            comptador++;
        }
    }

    private void crearBotoPala() {
        BotoIngame.setImatgeSeleccionat2(ManagerRecursos.getImage("botoPalaSeleccionatImage"));
        BotoIngame.setImatgeNoSeleccionat2(ManagerRecursos.getImage("cartaPalaImage"));
        botoPala = new BotoIngame(gui, ManagerRecursos.getImage("botoPalaImage"), 822, 661, 0, false);
        botoPala.addListener("pala");
    }

    /**
     * Crea el boto per accedir al menu ingame
     */
    private void crearBotoMenu() {
        botoOpcions = new BotoMenu(container, ManagerRecursos.getImage("botoPetitImage"), 420, 300);
        botoOpcions.setLocation(820, 705);
        botoOpcions.setMouseOverImage(ManagerRecursos.getImage("botoPetitOverImage"));
        botoOpcions.setImageText(ManagerRecursos.getImage("textMenuPetitImage"));
        botoOpcions.setMouseDownSound(soClick);
        botoOpcions.setMouseOverSound(soOver);
        botoOpcions.setActiu(true);
    }

    private void afegirListener() {
        botoOpcions.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatGuanya.ID);
                ManagerPerfil.sumaPerdudes();
                ManagerPerfil.guardarEstadistiques();
                ManagerEnemics.pararTimer();
            }
        });
    }

    /**
     * Reinicia l'estat de tots els botonsUnitat
     */
    public void reiniciarBotons() {
        for (BotoIngame b : botonsUnitat) {
            b.clear();
        }
        botoPala.clear();
    }

    /**
     * Reinicia algunes variables del menu
     */
    public void clear() {
        enEspera = false;
        elementEsperant = "null";
    }

    /**
     * Posiciona l'element al punt indicat
     * @param x : posicio en l'eix X
     * @param y: posicio en l'eix Y
     */
    public void setLocation(int x, int y) {
        if (area != null) {
            area.setX(x);
            area.setY(y);
        }
    }

    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }

    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }

    public int getX() {
        return (int) area.getX();
    }

    public int getY() {
        return (int) area.getY();
    }

    public void realitzaTransaccio() {
        md.restarDiners(botoAux.getCost());
        reiniciarBotons();
        botoAux.activarTimer();
        botoAux = null;
        soClick.play();
        ManagerPerfil.sumaUnitat();
    }

    public ArrayList<BotoIngame> getBotons() {
        return botonsUnitat;
    }

    public void setBotons(ArrayList<BotoIngame> botons) {
        this.botonsUnitat = botons;
    }

    public String getElementEsperant() {
        return elementEsperant;
    }

    public void setElementEsperant(String elementEsperant) {
        this.elementEsperant = elementEsperant;
    }

    public boolean isEnEspera() {
        return enEspera;
    }

    public void setEnEspera(boolean enEspera) {
        this.enEspera = enEspera;
    }

    public GUIContext getGui() {
        return gui;
    }

    public void setGui(GUIContext gui) {
        this.gui = gui;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUnitats() {
        return unitats;
    }

    public void setUnitats(String unitats) {
        this.unitats = unitats;
    }

    public Object getElement() {
        return FactoriaUnitats.getUnitatBona(elementEsperant);
    }
}
