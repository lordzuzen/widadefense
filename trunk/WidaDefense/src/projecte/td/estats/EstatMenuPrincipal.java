package projecte.td.estats;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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
public class EstatMenuPrincipal extends BasicGameState {

    // Identificador del estat
    public static final int ID = 3;
    // Contenidor del joc
    private GameContainer container;
    // Contenidor d'estats que s'usara per accedir als estats necessaris
    private StateBasedGame state;
    // Boto per continuar jugant des de l'ultim punt on es va deixar el joc
    private BotoMenu botoNovaPartida;
    // Boto per accedir al menu opcions
    private BotoMenu botoOpcions;
    // Boto per accedir a l'estat canviar perfil per canviar el perfil actiu d'usuari
    private BotoMenu botoCanviarPerfil;
    // Boto per sortir del joc
    private BotoMenu botoSortir;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia
    private boolean alphaBotonsIn;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia
    private boolean alphaBotonsOut;
    // ArrayList per moure tots els botons quan sigui necessari
    private ArrayList<BotoMenu> botons;
    // Comptador per realitzar moviment en els botons
    private int comptador;
    // Indica a quin estat s'ha de canviar
    private int canviAEstat;
    // Float per utilitzar en alpha bending
    private float transparencia;
    // Imatge del fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;

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
    public void init(GameContainer container, StateBasedGame state) {
        this.container = container;
        this.state = state;

    }

    /**
     * Es reinicia el valor de les variables usades en aquest estat
     */
    private void reiniciarVariables() {
        imatgeFons = ManagerRecursos.getImage("fonsMenuImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfilNormalImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfil2OverImage");
        botons = new ArrayList<BotoMenu>();
        canviAEstat = 0;
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu nova partida
        botoNovaPartida = new BotoMenu(container, imatgeBotoNormal, 150, 430);
        botoNovaPartida.setMouseOverImage(imatgeBotoOver);
        botons.add(botoNovaPartida);
        // BotoMenu menu opcions
        botoOpcions = new BotoMenu(container, imatgeBotoNormal, 600, 430);
        botoOpcions.setMouseOverImage(imatgeBotoOver);
        botons.add(botoOpcions);
        // BotoMenu canvi de perfil
        botoCanviarPerfil = new BotoMenu(container, imatgeBotoNormal, 150, 550);
        botoCanviarPerfil.setMouseOverImage(imatgeBotoOver);
        botons.add(botoCanviarPerfil);
        // BotoMenu Sortir del joc
        botoSortir = new BotoMenu(container, imatgeBotoNormal, 600, 550);
        botoSortir.setMouseOverImage(imatgeBotoOver);
        botons.add(botoSortir);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoNovaPartida.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                alphaBotonsOut = true;
                canviAEstat = EstatSeguentWave.ID;
            }
        });
        botoOpcions.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                alphaBotonsOut = true;
            }
        });
        botoCanviarPerfil.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                alphaBotonsOut = true;
                canviAEstat = EstatPerfil.ID;
            }
        });
        botoSortir.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatSortir.ID);
            }
        });
    }

    /**
     * El motor s'encarrega de cridar aquest metode, aqui s'actualitzaran dades de variables o objectes
     * que s'estiguin usant en aquest estat
     * @param container
     * @param game
     * @param delta
     * @throws SlickException
     */
    public void update(GameContainer game, StateBasedGame state, int delta) {
        if (alphaBotonsIn) {
            comptador += 50;
            if (comptador % 100 == 0) {
                transparencia += 0.05;
            }
            if (transparencia >= 1) {
                alphaBotonsIn = false;
                comptador = 0;
            }
        } else if (alphaBotonsOut) {
            comptador += 50;
            if (comptador % 100 == 0) {
                transparencia -= 0.05;
            }
            if (transparencia <= 0) {
                state.enterState(canviAEstat);
            }
        }
    }

    /**
     * Aquest metode s'usa per renderitzar o dibuixar en pantalla els elements que es vulguin
     * @param container
     * @param game
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer game, StateBasedGame state, Graphics g) {
        imatgeBotoNormal.setAlpha(transparencia);
        imatgeBotoOver.setAlpha(transparencia);
        imatgeFons.draw(0, 0);
        botoNovaPartida.render(container, g);
        botoOpcions.render(container, g);
        botoCanviarPerfil.render(container, g);
        botoSortir.render(container, g);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        reiniciarVariables();
        crearBotonsMenuNormal();
        afegirListeners();
        alphaBotonsIn = true;
    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        alphaBotonsOut = false;
        imatgeBotoNormal.setAlpha(1f);
        imatgeBotoOver.setAlpha(1f);
        comptador = 0;
    }
}
