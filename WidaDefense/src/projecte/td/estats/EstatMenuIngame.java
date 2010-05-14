package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerRecursos;

/**
 *
 * @author media
 */
public class EstatMenuIngame extends BasicGameState {
     // Identificador de l'estat
    public static int ID = 6;
    // Contenidors del joc
    private GameContainer container;
    private StateBasedGame state;
    // Botons
    private BotoMenu botoReiniciar;
    private BotoMenu botoSortir;

    public int getID() {
        return ID;
    }

    public void init(GameContainer container, StateBasedGame state) {
        this.container = container;
        this.state = state;
        crearBotons();
        afegirListeners();
    }

    private void crearBotons() {
        // Crear els botons
        // BotoMenu Reiniciar, per reiniciar la wave actual
        botoReiniciar = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 200);
        botoReiniciar.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        // BotoMenu Sortir, surt al menu principal
        botoSortir = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 300);
        botoSortir.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        // BotoMenu Confirmar, confirma l'accio triada
        botoSortir = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 450);
        botoSortir.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        // BotoMenu Cancelar, cancel·la l'accio triada
        botoSortir = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 550);
        botoSortir.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
    }

    private void afegirListeners() {

        // Listener BotoMenu Si

        botoReiniciar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatInGame.ID);
            }
        });

        // Listener BotoMenu No

        botoSortir.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
    }

    public void update(GameContainer game, StateBasedGame state, int delta) {
    }

    public void render(GameContainer game, StateBasedGame state, Graphics g) {
        botoReiniciar.render(container, g);
        botoSortir.render(container, g);
    }
}
