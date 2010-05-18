/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class EstatSortir extends BasicGameState {

    // Identificador de l'estat
    public static final int ID = 9;
    // Contenidors del joc
    private GameContainer container;
    private StateBasedGame state;
    // Botons
    private BotoMenu botoSi;
    private BotoMenu botoNo;

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
        // BotoMenu Si, per quan es vol sortir del joc
        botoSi = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 300);
        botoSi.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        // BotoMenu No, per continuar jugant
        botoNo = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 450);
        botoNo.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
    }

    private void afegirListeners() {

        // Listener BotoMenu Si

        botoSi.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                container.exit();
            }
        });

        // Listener BotoMenu No

        botoNo.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
    }

    public void update(GameContainer game, StateBasedGame state, int delta) {
    }

    public void render(GameContainer game, StateBasedGame state, Graphics g) {
        botoSi.render(container, g);
        botoNo.render(container, g);
    }
}


