package projecte.td.estats;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ReproductorMusica;

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
    private BotoMenu botoMusica;
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
    // Imatge del text Jugar del boto
    private Image imatgeTextJugar;
    // Imatge del text Perfil del boto
    private Image imatgeTextPerfil;
    // Imatge del text Sortir del boto
    private Image imatgeTextSortir;
    // So del click que fan els botons al fer un mouse clicked
    private Sound soClick;
    // So del mouse que fan els botons pel mouse over
    private Sound soOver;
    private Music music;

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
        imatgeFons = ManagerRecursos.getImage("fonsMenuImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfilNormalImage");
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfil2OverImage");
        imatgeTextJugar = ManagerRecursos.getImage("textJugarImage");
        imatgeTextPerfil = ManagerRecursos.getImage("textPerfilImage");
        imatgeTextSortir = ManagerRecursos.getImage("textSortirImage");
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
        music = ManagerRecursos.getMusic("menuMusic");
    }

    /**
     * Es reinicia el valor de les variables usades en aquest estat
     */
    private void reiniciarVariables() {
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
        botoNovaPartida.setImageText(imatgeTextJugar);
        botoNovaPartida.setMouseDownSound(soClick);
        botoNovaPartida.setMouseOverSound(soOver);
        botons.add(botoNovaPartida);
        // BotoMenu menu opcions
        botoOpcions = new BotoMenu(container, imatgeBotoNormal, 600, 430);
        botoOpcions.setMouseOverImage(imatgeBotoOver);
        botoOpcions.setImageText(imatgeTextPerfil);
        botoOpcions.setMouseDownSound(soClick);
        botoOpcions.setMouseOverSound(soOver);
        botons.add(botoOpcions);
        // BotoMenu canvi de perfil
        botoCanviarPerfil = new BotoMenu(container, imatgeBotoNormal, 150, 550);
        botoCanviarPerfil.setMouseOverImage(imatgeBotoOver);
        botoCanviarPerfil.setImageText(imatgeTextPerfil);
        botoCanviarPerfil.setMouseDownSound(soClick);
        botoCanviarPerfil.setMouseOverSound(soOver);
        botons.add(botoCanviarPerfil);
        // BotoMenu Sortir del joc
        botoSortir = new BotoMenu(container, imatgeBotoNormal, 600, 550);
        botoSortir.setMouseOverImage(imatgeBotoOver);
        botoSortir.setImageText(imatgeTextSortir);
        botoSortir.setMouseDownSound(soClick);
        botoSortir.setMouseOverSound(soOver);
        botons.add(botoSortir);
        // BotoMenu Sortir del joc
        botoMusica = new BotoMenu(container, imatgeBotoNormal, 100, 100);
        botoMusica.setMouseOverImage(imatgeBotoOver);
        botoMusica.setImageText(imatgeTextSortir);
        botoMusica.setMouseDownSound(soClick);
        botoMusica.setMouseOverSound(soOver);
        botons.add(botoMusica);
    }

    /**
     * S'afegeixen els listeners que faran accionar els botons
     */
    private void afegirListeners() {
        botoNovaPartida.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    alphaBotonsOut = true;
                    canviAEstat = EstatSeguentWave.ID;
                }
            }
        });
        botoOpcions.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    alphaBotonsOut = true;
                    canviAEstat = EstatDades.ID;
                }
            }
        });
        botoCanviarPerfil.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    alphaBotonsOut = true;
                    canviAEstat = EstatPerfil.ID;
                }
            }
        });
        botoSortir.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    state.enterState(EstatSortir.ID);
                }
            }
        });
        botoMusica.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (!alphaBotonsIn && !alphaBotonsOut) {
                    state.enterState(EstatMusica.ID);
                }
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
        // Si s'esta entrant a l'estat o se n'esta sortint es dura a terme
        // l'efecte de transparencia
        if (alphaBotonsIn) {
            comptador += 50;
            if (comptador % 100 == 0) {
                transparencia += 0.05;
            }
            if (transparencia >= 1) {
                //music.play();
                ReproductorMusica.setVolumMusic();
                ReproductorMusica.last();
                botoNovaPartida.setActiu(true);
                botoOpcions.setActiu(true);
                botoCanviarPerfil.setActiu(true);
                botoSortir.setActiu(true);
                botoMusica.setActiu(true);
                alphaBotonsIn = false;
                comptador = 0;
            }
        } else if (alphaBotonsOut) {
            botoNovaPartida.setActiu(false);
            botoOpcions.setActiu(false);
            botoCanviarPerfil.setActiu(false);
            botoSortir.setActiu(false);
            botoMusica.setActiu(false);
            comptador += 50;
            if (comptador % 100 == 0) {
                transparencia -= 0.05;
            }
            if (transparencia <= 0) {
                state.enterState(canviAEstat);
            }
        }
        ReproductorMusica.update(container);
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
        imatgeTextJugar.setAlpha(transparencia);
        imatgeTextPerfil.setAlpha(transparencia);
        imatgeTextSortir.setAlpha(transparencia);
        imatgeFons.draw(0, 0);
        botoNovaPartida.render(container, g);
        botoOpcions.render(container, g);
        botoCanviarPerfil.render(container, g);
        botoSortir.render(container, g);
        botoMusica.render(container, g);
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
        imatgeTextJugar.setAlpha(1f);
        imatgeTextPerfil.setAlpha(1f);
        imatgeTextSortir.setAlpha(1f);
        comptador = 0;
    }
}
