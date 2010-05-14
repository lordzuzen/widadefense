/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.managers;

import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author media
 */
public class ManagerContext {

    private static GUIContext gui;
    private static StateBasedGame state;
    private static ManagerDiners diners;

    public static GUIContext getGui() {
        return gui;
    }

    public static void setGui(GUIContext gui) {
        ManagerContext.gui = gui;
    }

    public static StateBasedGame getState() {
        return state;
    }

    public static void setState(StateBasedGame state) {
        ManagerContext.state = state;
    }

    public static ManagerDiners getDiners() {
        return diners;
    }

    public static void setDiners(ManagerDiners diners) {
        ManagerContext.diners = diners;
    }
}
