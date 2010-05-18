/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.newdawn.slick.Image;

/**
 *
 * @author wida47909974
 */
public class UnitatEnemigaAtkDistanciaSalta extends UnitatEnemigaAtkDistancia{
    
    private Timer timer2;
    private int cadenciaSalts;
    private boolean salta=false;
    private int ySalt;

    public UnitatEnemigaAtkDistanciaSalta(int vida, int cadencia, Image image, Image[] frames, ProjectilEstatic projectil, Image[] frames2, double velocitat, float posXProj, float posYProj,int cadenciaSalts) {
        super(vida, cadencia, image, frames, projectil, frames2, velocitat, posXProj, posYProj);
         timer2 = new Timer(cadenciaSalts, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                salta=true;

            }
        });
        this.cadenciaSalts=cadenciaSalts;
        timer2.start();
    }

    public void haSaltat(){
        salta=false;
    }
    public boolean isSaltant(){
        return salta;
    }
    public void calculaSalt(int llargadaTotal){
        ySalt=(int)(Math.random()*llargadaTotal);        
    }

    public int getySalt() {
        return ySalt;
    }


}
