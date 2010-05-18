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
    // Flags per comprovar si s'ha de realitzar alguna accio
    private boolean moureBotons;
    // ArrayList per moure tots els botons quan sigui necessari
    private ArrayList<BotoMenu> botons;
    // Indica a quin estat s'ha de canviar
    private int canviAEstat;
    // Variable per comprovar si els botons han sortit de la pantalla
    private int botonsFora;
    // Imatge del fons de pantalla
    private Image imatgeFons;

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
        botons = new ArrayList<BotoMenu>();
        moureBotons = false;
        botonsFora = 0;
        canviAEstat = 0;
    }

    /**
     * En aquest metode es creen els botons que es mostraran en el menu principal
     * A través del manager de recursos assignem una imatge i una posicio als botons
     */
    private void crearBotonsMenuNormal() {
        // BotoMenu nova partida
        botoNovaPartida = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 150);
        botoNovaPartida.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        botons.add(botoNovaPartida);
        // BotoMenu menu opcions
        botoOpcions = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 250);
        botoOpcions.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        botons.add(botoOpcions);
        // BotoMenu canvi de perfil
        botoCanviarPerfil = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 350);
        botoCanviarPerfil.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        botons.add(botoCanviarPerfil);
        // BotoMenu Sortir del joc
        botoSortir = new BotoMenu(container, ManagerRecursos.getImage("botoPerfilImage"), 420, 450);
        botoSortir.setMouseOverImage(ManagerRecursos.getImage("botoPerfilOverImage"));
        botons.add(botoSortir);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoNovaPartida.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                moureBotons = true;
                canviAEstat = EstatSeguentWave.ID;
            }
        });
        botoOpcions.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                moureBotons = true;
            }
        });
        botoCanviarPerfil.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                moureBotons = true;
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
        if (moureBotons) {
            botonsFora = 0;
            for (BotoMenu b : botons) {
                b.setLocation(b.getX(), b.getY() - delta);
                if (b.getY() < -150) {
                    botonsFora++;
                }
            }
            if (botonsFora == 4) {
                moureBotons = false;
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
    }

    /**
     * Es fa un override del metode leave, el qual es crida cada vegada que es surt de l'estat
     * @param gc
     * @param state
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame state) {
        botoCanviarPerfil = null;
        botoNovaPartida = null;
        botoOpcions = null;
        botoSortir = null;
        botons = null;
        moureBotons = false;
    }
}
