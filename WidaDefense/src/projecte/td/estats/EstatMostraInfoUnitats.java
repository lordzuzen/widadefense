package projecte.td.estats;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;
import projecte.td.utilitats.ReproductorMusica;

/**
 *
 * @author media
 */
public class EstatMostraInfoUnitats extends BasicGameState {

    // Identificador del estat
    public static final int ID = 11;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per reinicar la wave
    private BotoMenu botoTornar;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    private Image labelFonsNegre;
    private Animation animation;
    private Sound soClick;
    private Sound soOver;
    private ArxiuConfiguracio unitats;
    private String unitatTriada;
    private String vida;
    private String capacitat;
    private String cadencia;
    private String informacio;
    // Font que s'usa per renderitzar el text
    private Font font;

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
        this.state = game;
        this.container = container;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoXImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoXOverImage");
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
        labelFonsNegre = ManagerRecursos.getImage("fonsNegrePetitImage");
        font = ManagerRecursos.getFont("dejavuNormalFont");

    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        ReproductorMusica.update(container);
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        imatgeFons.draw(0, 0);
        labelFonsNegre.draw(435, 123);
        botoTornar.render(container, g);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(unitatTriada, 425, 330);
        g.drawString("Vida: " + vida, 425, 380);
        g.drawString("Cadencia: " + cadencia, 425, 430);
        g.drawString("Capacitat:  " + capacitat, 425, 480);
        //String[] text = informacio.split("-");
        //int posicio = 530;
        /**for (String z : text) {
            g.drawString(z, 325, posicio);
            posicio += 40;
        }**/
        int posX = 435 + labelFonsNegre.getWidth() / 2 - animation.getImage(0).getWidth() / 2;
        int posY = 123 + labelFonsNegre.getHeight() / 2 - animation.getImage(0).getHeight() / 2;
        g.drawAnimation(animation, posX, posY);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotonsMenuNormal();
        afegirListeners();
        unitats = Configuracio.getUnitats();
        assignarPropietats();
    }

    private void assignarPropietats() {
        unitatTriada = ManagerPerfil.getInformacioUnitat();
        vida = unitats.getPropietatString("infoVida" + unitatTriada);
        cadencia = unitats.getPropietatString("infoCadencia" + unitatTriada);
        capacitat = unitats.getPropietatString("infoCapacitat" + unitatTriada);
        animation = new Animation(ManagerRecursos.getImageArray(unitats.getPropietatString("animation" + unitatTriada)), 80);
        int info = unitats.getPropietatInt("totalInfo" + unitatTriada);
        informacio = "";
        for (int z = 0; z < info; z++) {
            informacio += unitats.getPropietatString("informacio" + unitatTriada + z) + "-";
        }
        informacio = informacio.substring(0, informacio.length() - 1);
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu tornar al menu principal
        botoTornar = new BotoMenu(container, imatgeBotoNormal, 746, 104);
        botoTornar.setMouseOverImage(imatgeBotoOver);
        botoTornar.setMouseDownSound(soClick);
        botoTornar.setMouseOverSound(soOver);
        botoTornar.setActiu(true);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoTornar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatInfoUnitats.ID);
            }
        });
    }
}
