/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projecte.td.domini;


/**
 *
 * @author media
 */
public abstract class Projectil extends Entitat {

    protected double dany;

    public Projectil(double dany) {
        this.dany = dany;
    }

    public void setLocation(float posX, float posY){
        super.posX=posX;
        super.posY=posY;
    }
    public void update(int delta){

    }
    public abstract void impacte();

    public abstract Projectil cloneProjectil();

    // Getters i setters

    public double getDany() {
        return dany;
    }

    public void setDany(int dany) {
        this.dany = dany;
    }
}
