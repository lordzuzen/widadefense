/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.managers;

import projecte.td.utilitats.*;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import projecte.td.utilitats.Configuracio;

/**
 *
 * @author wida47645633
 */
public class ManagerRecursos {

    private static Map<String, Sound> sons = new HashMap<String, Sound>();
    private static Map<String, Music> musica = new HashMap<String, Music>();
    private static Map<String, Image> imatges = new HashMap<String, Image>();
    private static Map<String, Image[]> animacions = new HashMap<String, Image[]>();
    private static Map<String, Font> fonts = new HashMap<String, Font>();
    private static ArxiuConfiguracio recursos;

    public static void init() {
        carregaObjectesJoc();
    }

    private static void carregaObjectesJoc() {
        recursos = Configuracio.getRecursos();
        Set<Object> claus = recursos.getTotesPropietats();
        for (Object objecte : claus) {
            String clau = (String) objecte;
            try {
                crearObjectes(clau);
            } catch (Exception ex) {
                throw new RuntimeException("No s'ha pogut crear el recurs : " + clau);
            }
        }
    }

    private static void crearObjectes(String clau) throws Exception {
        String valor = recursos.getPropietatString(clau);
        if (clau.endsWith("Sound")) {
            sons.put(clau, new Sound(valor));
        } else if (clau.endsWith("Music")) {
            musica.put(clau, new Music(valor, true));
        } else if (clau.endsWith("Image")) {
            imatges.put(clau, new Image(valor));
        } else if (clau.endsWith("Animation")) {
            animacions.put(clau, convertirImatgeArray(valor));
        }
    }

    private static Image[] convertirImatgeArray(String valor) throws Exception{
        String[] frames = valor.split("-");
        Image[] image = new Image[frames.length];
        for (int z = 0; z < frames.length; z++) {
            image[z] = new Image(frames[z]);
        }
        return image;
    }

    public static Image getImage(String name) {
        return imatges.get(name);
    }

    public static Image[] getImageArray(String name) {
        return animacions.get(name);
    }
}
