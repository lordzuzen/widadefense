package projecte.td.componentIngame;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.componentIngame.*;
import projecte.td.estats.EstatGuanya;
import projecte.td.estats.EstatPerd;
import projecte.td.managers.ManagerRecursos;
import projecte.td.factories.FactoriaUnitats;
import projecte.td.managers.ManagerDiners;

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
    // area: area que conte el MenuIngame
    private Shape area;
    // botonsUnitat: arraylist on es guardaran els botonsUnitat de que disposa el menu
    private ArrayList<BotoIngame> botonsUnitat;
    // botoOpcions: para la partida i accedeix al menu
    private BotoMenu botoOpcions;
    // unitats: s'utilitza per inicialitzar correctament els botonsUnitat i les imatges corresponents
    private String unitats;
    // elementEsperant: indica quin element esta esperant per ser posicionat
    private String elementEsperant;
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
    public MenuIngame(GUIContext gui, int posX, int posY, Image image, String unitats
            , ManagerDiners md, StateBasedGame state) {
        super(gui);
        this.gui = gui;
        this.state = state;
        this.posXVariable = posX + 30;
        this.posYVariable = posY;
        this.image = image;
        area = new Rectangle(posX, posY, image.getWidth(), image.getHeight());
        this.unitats = unitats;
        this.md = md;
        init();
        crearBotonsUnitats();
        crearBotoMenu();
        afegirListener();
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
        int xp = (int) (area.getX() + ((getWidth() - image.getWidth()) / 2));
        int yp = (int) (area.getY() + ((getHeight() - image.getHeight()) / 2));
        g.drawImage(image, xp, yp);
        for (BotoIngame b : botonsUnitat) {
            b.render(gui, g);
        }
        botoOpcions.render(container, g);
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
                cost = b.getCost();
            }
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

        for (String s : st) {
            BotoIngame boto = new BotoIngame(gui, ManagerRecursos.getImage("botoIngame" + s + "Image"),
                    posXVariable, posYVariable, 200);
            posXVariable += 80;
            boto.addListener(s);
            botonsUnitat.add(boto);
        }
    }

    /**
     * Crea el boto per accedir al menu ingame
     */

    private void crearBotoMenu() {
        botoOpcions = new BotoMenu(container, ManagerRecursos.getImage("marcIngameImage"), 420, 300);
        botoOpcions.setLocation(900, 500);
        botoOpcions.setMouseOverImage(ManagerRecursos.getImage("marcSeleccionatIngameImage"));
    }

    private void afegirListener() {
        botoOpcions.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatGuanya.ID);
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
        md.restarDiners(cost);
        reiniciarBotons();
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
