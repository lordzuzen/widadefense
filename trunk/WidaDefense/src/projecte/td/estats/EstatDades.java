package projecte.td.estats;

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
public class EstatDades extends BasicGameState {

    // Identificador del estat
    public static final int ID = 8;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per reinicar la wave
    private BotoMenu botoDades;
    // Boto per tornar al menu principal
    private BotoMenu botoUnitats;
    // Boto per tornar al menu principal
    private BotoMenu botoEnemics;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    private Sound soClick;
    private Sound soOver;

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
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfil2OverImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfilNormalImage");
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");

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
        botoUnitats.render(container, g);
        botoDades.render(container, g);
        botoEnemics.render(container, g);
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
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu tornar a jugar wave
        botoEnemics = new BotoMenu(container, imatgeBotoNormal, 380, 350);
        botoEnemics.setMouseOverImage(imatgeBotoOver);
        botoEnemics.setMouseDownSound(soClick);
        botoEnemics.setMouseOverSound(soOver);
        botoEnemics.setActiu(true);
        // BotoMenu tornar al menu principal
        botoDades = new BotoMenu(container, imatgeBotoNormal, 380, 450);
        botoDades.setMouseOverImage(imatgeBotoOver);
        botoDades.setMouseDownSound(soClick);
        botoDades.setMouseOverSound(soOver);
        botoDades.setActiu(true);
        // BotoMenu per seguir jugant en la següent Wave
        botoUnitats = new BotoMenu(container, imatgeBotoNormal, 380, 550);
        botoUnitats.setMouseOverImage(imatgeBotoOver);
        botoUnitats.setMouseDownSound(soClick);
        botoUnitats.setMouseOverSound(soOver);
        botoUnitats.setActiu(true);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoUnitats.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatInfoUnitats.ID);
            }
        });
        botoDades.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatEstadistiques.ID);
            }
        });
        botoEnemics.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
    }
}
