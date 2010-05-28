package projecte.td.estats;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class EstatEstadistiques extends BasicGameState {

    // Identificador del estat
    public static final int ID = 9;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per reinicar la wave
    private BotoMenu botoTornar;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    private Sound soClick;
    private Sound soOver;
    // Font que s'usa per renderitzar el text
    private Font font;
    private static int totalMorts;
    private static int totalUnitatsColocades;
    private static int totalBales;
    private static int totalGuanyades;
    private static int totalPerdudes;
    private static int totalDinersGuanyats;
    private static int totalAuresColocades;

    public int getID() {
        return ID;
    }

    /**
     * Aqui s'inicialitzen les variables necessaries per al correcte funcionament del estat
     * @param container
     * @param game
     * @throws SlickException
     */
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.state = game;
        this.container = container;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoXImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoXOverImage");
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
        font = ManagerRecursos.getFont("dejavuNormalFont");

    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        imatgeFons.draw(0, 0);
        botoTornar.render(container, g);
        g.setFont(font);
        g.drawString("Total Morts:" + totalMorts, 410, 240);
        g.drawString("Total Bales:" + totalBales, 410, 280);
        g.drawString("Total Guanyades:" + totalGuanyades, 410, 320);
        g.drawString("Total Perdudes:" + totalPerdudes, 410, 360);
        g.drawString("Total Diners:" + totalDinersGuanyats, 410, 400);
        g.drawString("Total Aures:" + totalAuresColocades, 410, 440);
        g.drawString("Total Unitats:" + totalUnitatsColocades, 410, 480);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotonsMenuNormal();
        afegirListeners();
        totalMorts = ManagerPerfil.getTotalMorts();
        totalBales = ManagerPerfil.getBales();
        totalGuanyades = ManagerPerfil.getGuanyades();
        totalPerdudes = ManagerPerfil.getPerdudes();
        totalDinersGuanyats = ManagerPerfil.getDiners();
        totalAuresColocades = ManagerPerfil.getAures();
        totalUnitatsColocades = ManagerPerfil.getUnitats();
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu tornar al menu principal
        botoTornar = new BotoMenu(container, imatgeBotoNormal, 750, 100);
        botoTornar.setMouseOverImage(imatgeBotoOver);
        botoTornar.setMouseDownSound(soClick);
        botoTornar.setMouseOverSound(soOver);
        botoTornar.setActiu(true);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoTornar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatDades.ID);
            }
        });
    }
}

