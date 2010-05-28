/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
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
    public static final int ID = 12;
    // Contenidors del joc
    private GameContainer container;
    private StateBasedGame state;
    // Botons
    private BotoMenu botoSi;
    private BotoMenu botoNo;
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoXNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoXOver;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoVNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoVOver;
    private Image textSortida;
    private Sound soClick;
    private Sound soOver;

    public int getID() {
        return ID;
    }

    public void init(GameContainer container, StateBasedGame state) {
        this.container = container;
        this.state = state;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        imatgeBotoXNormal = ManagerRecursos.getImage("botoXImage");
        imatgeBotoXOver = ManagerRecursos.getImage("botoXOverImage");
        imatgeBotoVNormal = ManagerRecursos.getImage("botoVImage");
        imatgeBotoVOver = ManagerRecursos.getImage("botoVOverImage");
        textSortida = ManagerRecursos.getImage("textSortidaImage");
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
        crearBotons();
        afegirListeners();
    }

    private void crearBotons() {
        // Crear els botons
        // BotoMenu Si, per quan es vol sortir del joc
        botoSi = new BotoMenu(container, imatgeBotoVNormal, 425, 400);
        botoSi.setMouseOverImage(imatgeBotoVOver);
        botoSi.setMouseDownSound(soClick);
        botoSi.setMouseOverSound(soOver);
        botoSi.setActiu(true);
        // BotoMenu No, per continuar jugant
        botoNo = new BotoMenu(container, imatgeBotoXNormal, 525, 400);
        botoNo.setMouseOverImage(imatgeBotoXOver);
        botoNo.setMouseDownSound(soClick);
        botoNo.setMouseOverSound(soOver);
        botoNo.setActiu(true);
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
        imatgeFons.draw(0, 0);
        textSortida.draw(250, 280);
        botoSi.render(container, g);
        botoNo.render(container, g);
    }
}


