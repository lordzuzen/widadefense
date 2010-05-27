package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.Configuracio;

/**
 *
 * @author media
 */
public class EstatGuanya extends BasicGameState {

    // Identificador del estat
    public static final int ID = 7;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per reinicar la wave
    private BotoMenu botoReiniciarWave;
    // Boto per tornar al menu principal
    private BotoMenu botoMenuPrincipal;
    // Boto per tornar al menu principal
    private BotoMenu botoSeguentWave;
    // Arxiu per veure si es poden triar noves unitats en la seguent wave
    private ArxiuConfiguracio waves;
    // Informacio referent a les noves unitats que es podran utilitzar
    private String informacioNovesUnitats;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeCarta;
    // Image del boto amb mouse over
    private Image imatgePersonatge;
    // Boolean per comprovar si s'ha de mostrar una unitat nova
    private boolean unitatNova;

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
        waves = Configuracio.getWaves();
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfilNormalImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfil2OverImage");
        imatgeCarta = ManagerRecursos.getImage("botoCartaImage");
        
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
        botoMenuPrincipal.render(container, g);
        botoReiniciarWave.render(container, g);
        botoSeguentWave.render(container, g);
        if (unitatNova) {
            g.drawString("Has Guanyat!!!", 420, 120);
            imatgeCarta.draw(400, 190);
            imatgePersonatge.draw(420, 210);
            g.drawString(informacioNovesUnitats, 400, 160);
        }
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
        informacioNovesUnitats = waves.getPropietatString("unitatNova" + ManagerPerfil.getWave());
        imatgePersonatge = ManagerRecursos.getImage("carta" + informacioNovesUnitats + "Image");
        unitatNova = false;
        if (!informacioNovesUnitats.equals("null")) {
            unitatNova = true;
        }
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu tornar a jugar wave
        botoSeguentWave = new BotoMenu(container, imatgeBotoNormal, 380, 350);
        botoSeguentWave.setMouseOverImage(imatgeBotoOver);
        // BotoMenu tornar al menu principal
        botoReiniciarWave = new BotoMenu(container, imatgeBotoNormal, 380, 450);
        botoReiniciarWave.setMouseOverImage(imatgeBotoOver);
        // BotoMenu per seguir jugant en la següent Wave
        botoMenuPrincipal = new BotoMenu(container, imatgeBotoNormal, 380, 550);
        botoMenuPrincipal.setMouseOverImage(imatgeBotoOver);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoReiniciarWave.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatSeguentWave.ID);
            }
        });
        botoMenuPrincipal.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID);
            }
        });
        botoSeguentWave.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ManagerPerfil.passaASeguentWave();
                state.enterState(EstatSeguentWave.ID);
            }
        });
    }
}
