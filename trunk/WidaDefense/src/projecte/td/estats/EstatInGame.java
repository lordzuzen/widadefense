/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentIngame.MenuIngame;
import projecte.td.joc.Tauler;
import projecte.td.factories.FactoriaUnitats;
import projecte.td.managers.ManagerRecursos;

import projecte.td.managers.ManagerDiners;
import projecte.td.domini.UnitatAbstract;
import projecte.td.managers.ManagerContext;
import projecte.td.managers.ManagerPerfil;

/**
 *
 * @author media
 */
public class EstatInGame extends BasicGameState {
    // Identificador del estat

    public static final int ID = 5;
    private GameContainer gc;
    private StateBasedGame state;
    Tauler p;
    MenuIngame mi;
    ManagerDiners md;
    String unitat = "null";

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
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.gc = container;
        this.state = game;
        FactoriaUnitats.creaHashMap();
    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
        Input input = gc.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && input.getMouseY() <= 600) {
            if (!unitat.equals("null")) {
                if (unitat.contains("Aura")) {
                } else {
                    UnitatAbstract u = null;
                    u = FactoriaUnitats.getUnitatBona(unitat);
                    p.posicionaUnitatAmiga(input.getMouseX(), input.getMouseY(), u);
                    unitat = "null";
                    mi.realitzaTransaccio();
                }
            }
        } else if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
            UnitatAbstract u = null;
            u = FactoriaUnitats.getUnitatDolenta("Natiu");
            p.posicionaUnitatEnemiga(input.getMouseX(), input.getMouseY(), u);
        }
        p.acciona(delta);
        mi.update();
        if (mi.isEnEspera()) {
            unitat = mi.getElementEsperant();
            mi.clear();
        }
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
        p.dibuixar(g, gc);
        mi.render(gc, g);
        g.drawString("Diners: " + md.getTotal() + "", 600, 600);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        p = new Tauler(6, 10, 1024, 600);
        md = new ManagerDiners();
        mi = new MenuIngame(gc, 0, 600, ManagerRecursos.getImage("contenidorIngameImage"), ManagerPerfil.getUnitatsTriades(), md, state);
        ManagerContext.setDiners(md);
    }
}
