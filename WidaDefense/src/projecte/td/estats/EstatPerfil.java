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
import projecte.td.utilitats.Configuracio;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;

/**
 * En aquest estat s'escollira el perfil d'usuari que es vol utilitzar
 * @author David Alvarez Palau
 */
public class EstatPerfil extends BasicGameState {

    // Identificador del BasicGameState
    public static final int ID = 2;
    // Contenidor del joc
    private GameContainer container;
    // Estat on es podra controlar l'acces als altres estats
    private StateBasedGame state;
    // Botons per triar el perfil corresponent
    // Boto Perfil1
    private BotoMenu botoPerfil1;
    // Boto Borrar Perfil 1
    private BotoMenu botoBorrar1;
    // Boto Perfil2
    private BotoMenu botoPerfil2;
    // Boto Borrar Perfil 2
    private BotoMenu botoBorrar2;
    // Boto Perfil3
    private BotoMenu botoPerfil3;
    // Boto Borrar Perfil 3
    private BotoMenu botoBorrar3;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia
    private boolean alphaBotonsIn;
    // Boolean que s'utilitza per comprovar si s'ha de realitzar transparencia 
    private boolean alphaBotonsOut;
    // Indica si els botons es poden renderitzar
    private boolean carregat;
    // Float per utilitzar en alpha bending
    private float transparencia;
    // Comptador per realitzar moviment en els botons
    private int comptador;
    // Imatge fons de pantalla
    private Image imatgeFons;
    // Imatge del boto normal (Sense mouse over)
    private Image imatgeBotoNormal;
    // Image del boto amb mouse over
    private Image imatgeBotoOver;
    // Image del boto per esborra perfils
    private Image imatgeBotoBorrar;
    private ArxiuConfiguracio perfils;

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
        imatgeBotoNormal = ManagerRecursos.getImage("botoPerfilNormalImage");
        imatgeBotoOver = ManagerRecursos.getImage("botoPerfil2OverImage");
        imatgeBotoBorrar = ManagerRecursos.getImage("botoXImage");
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
                state.enterState(EstatMenuPrincipal.ID);
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
        g.drawString("PERFILS", container.getWidth() / 2 - 50, container.getHeight() / 3 - 100);
        if (carregat) {
            imatgeBotoBorrar.setAlpha(transparencia);
            imatgeBotoNormal.setAlpha(transparencia);
            imatgeBotoOver.setAlpha(transparencia);
            botoPerfil1.render(game, g);
            botoPerfil2.render(game, g);
            botoPerfil3.render(game, g);
            botoBorrar1.render(game, g);
            botoBorrar2.render(game, g);
            botoBorrar3.render(game, g);
        }
    }

    /**
     * Crea els botons necessaris per al menu de perfils
     * @param container
     */
    private void crearBotons(GameContainer container) {
        // Crear els botons
        // Perfil 1
        botoPerfil1 = new BotoMenu(container, imatgeBotoNormal, 420, 300);
        botoPerfil1.setLocation(container.getWidth() / 2 - botoPerfil1.getWidth() / 2 - imatgeBotoBorrar.getWidth() / 2, 380);
        botoPerfil1.setMouseOverImage(imatgeBotoOver);
        botoBorrar1 = new BotoMenu(container, imatgeBotoBorrar, 420, 300);
        botoBorrar1.setLocation(container.getWidth() / 2 - botoBorrar1.getWidth() / 2 + 145, 379);
        // Perfil 2
        botoPerfil2 = new BotoMenu(container, imatgeBotoNormal, 420, 450);
        botoPerfil2.setLocation(container.getWidth() / 2 - botoPerfil2.getWidth() / 2 - imatgeBotoBorrar.getWidth() / 2, 480);
        botoPerfil2.setMouseOverImage(imatgeBotoOver);
        botoBorrar2 = new BotoMenu(container, imatgeBotoBorrar, 420, 300);
        botoBorrar2.setLocation(container.getWidth() / 2 - botoBorrar2.getWidth() / 2 + 145, 479);
        // Perfil 3
        botoPerfil3 = new BotoMenu(container, imatgeBotoNormal, 420, 600);
        botoPerfil3.setLocation(container.getWidth() / 2 - botoPerfil3.getWidth() / 2 - imatgeBotoBorrar.getWidth() / 2, 580);
        botoPerfil3.setMouseOverImage(imatgeBotoOver);
        botoBorrar3 = new BotoMenu(container, imatgeBotoBorrar, 420, 300);
        botoBorrar3.setLocation(container.getWidth() / 2 - botoBorrar3.getWidth() / 2 + 145, 579);
    }

    /**
     * Afegeix els escoltadors necessaris per aquests botons
     */
    private void afegirListeners() {

        // Listener BotoMenu Perfil 1
        botoPerfil1.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ManagerPerfil.init(1);
                alphaBotonsOut = true;
            }
        });
        // Listener BotoMenu Borrar 1
        botoBorrar1.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                perfils = Configuracio.getPerfil1();
                perfils.setPropietatInt("seguentWave", 1);
                perfils.guardar();
            }
        });

        // Listener BotoMenu Perfil 2
        botoPerfil2.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ManagerPerfil.init(2);
                alphaBotonsOut = true;
            }
        });
        // Listener BotoMenu Borrar 1
        botoBorrar2.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                perfils = Configuracio.getPerfil2();
                perfils.setPropietatInt("seguentWave", 1);
                perfils.guardar();
            }
        });

        // Listener BotoMenu Perfil 3
        botoPerfil3.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ManagerPerfil.init(3);
                alphaBotonsOut = true;
            }
        });
        // Listener BotoMenu Borrar 1
        botoBorrar3.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                perfils = Configuracio.getPerfil3();
                perfils.setPropietatInt("seguentWave", 1);
                perfils.guardar();
            }
        });
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotons(gc);
        carregat = true;
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
        carregat = false;
        comptador = 0;
        alphaBotonsOut = false;
        imatgeBotoBorrar.setAlpha(1f);
        imatgeBotoNormal.setAlpha(1f);
        imatgeBotoOver.setAlpha(1f);
    }
}
