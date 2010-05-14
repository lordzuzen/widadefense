/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.factories;

import projecte.td.managers.ManagerRecursos;
import perBorrar.Projectil;
import perBorrar.ProjectilDinamic;

/**
 *
 * @author media
 */
public class FactoriaProjectils {

    public static Projectil getProjectil(String tipus) {
        Projectil projectil = null;
        if (tipus.equals("Lleuger")) {
            projectil = new ProjectilDinamic(25, 1, ManagerRecursos.getImage("lleugerImage"));
        } else if (tipus.equals("")) {
        
        }
        return projectil;
    }

}
