package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.componentGUI.MenuSeleccio;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class EstatSeguentWave extends BasicGameState {

    // Identificador del estat
    public static int ID = 4;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als diferents estats del joc
    private StateBasedGame state;
    // Fons de pantalla
    private Image imatgeFons;
    // Objecte menu que comprova les unitats que es poden seleccionar i les que s'han seleccionat
    private MenuSeleccio ms;
    // Boto per accedir al seguent estat
    private BotoMenu botoContinuar;
    // Boto per tornar al menu principal
    private BotoMenu botoTornar;
    // Indica si s'ha apretat algun boto
    private boolean botoApretat;

    /**
     * BasicGameState ens obliga a implementar aquest metode
     * @return int amb l'ID de l'estat del joc
     */
    public int getID() {
        return ID;
    }

    /**
     * Aqui s'inicialitzen les variables necessaries per al correcte funcionament del estat
     * @param container
     * @param game
     * @throws SlickException
     */
    public void init(GameContainer container, StateBasedGame state) {
        this.container = container;
        this.state = state;
        imatgeFons = ManagerRecursos.getImage("fonsSeguentWaveImage");

    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        ms = new MenuSeleccio(container, 0, 0);
        crearBotons();
    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        ms = null;
        botoContinuar = null;
        botoApretat = false;
    }

    /**
     * Crea els botons necessaris per continuar o tornar enrere
     */
    private void crearBotons() {
        botoContinuar = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 500);
        botoContinuar.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        botoContinuar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (ms.unitatsNoNull()) {
                    botoApretat = true;
                }
            }
        });
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
        ms.update(game, state, delta);
        if (botoApretat) {
            ManagerPerfil.setUnitatsTriades(ms.agafarUnitats());
            state.enterState(5);
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
        imatgeFons.draw(0, 0);
        botoContinuar.render(container, g);
        ms.render(game, state, g);
    }
}
