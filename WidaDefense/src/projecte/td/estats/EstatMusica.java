package projecte.td.estats;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import projecte.td.componentGUI.BotoMenu;
import projecte.td.managers.ManagerPerfil;
import projecte.td.managers.ManagerRecursos;
import projecte.td.utilitats.ArxiuConfiguracio;
import projecte.td.utilitats.ReproductorMusica;

/**
 * En aquest estat es carreguen els recursos tot mostrant un fons de pantalla i una barra progressiva
 * @author David Alvarez Palau
 */
public class EstatMusica extends BasicGameState {

    // Identificador de l'estat
    public static final int ID = 16;
    // Contenidor del joc
    private GameContainer game;
    private StateBasedGame state;
    // Imatge fons de pantalla
    private int volumEfectes;
    private int volumMusica;
    private Image imatgeFons;
    private BotoMenu botoEnrere;
    private BotoMenu botoPlay;
    private BotoMenu botoStop;
    private BotoMenu botoTornar;
    private BotoMenu botoEndavant;
    private BotoMenu botoSumaEfectes;
    private BotoMenu botoRestaEfectes;
    private BotoMenu botoSumaMusica;
    private BotoMenu botoRestaMusica;
    private Sound soClick;
    private Sound soOver;

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
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = container;
        this.state = game;
        imatgeFons = ManagerRecursos.getImage("fonsSelectorImage");
        soClick = ManagerRecursos.getSound("clickSound");
        soOver = ManagerRecursos.getSound("overSound");
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
        imatgeFons.draw(0, 0, container.getWidth(), container.getHeight());
        botoEndavant.render(container, g);
        botoEnrere.render(container, g);
        botoStop.render(container, g);
        botoTornar.render(container, g);
        botoRestaEfectes.render(container, g);
        botoSumaEfectes.render(container, g);
        botoRestaMusica.render(container, g);
        botoSumaMusica.render(container, g);
        g.drawString("Volum Musica: " + volumMusica, 430, 400);
        g.drawString("Volum Efectes: " + volumEfectes, 430, 470);
    }

    /**
     * Es fa un override del metode enter, el qual es crida cada vegada que s'entra a l'estat
     * @param gc : context on es situa l'estat actual
     * @param state : estat actual
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame state) {
        crearBotons();
        volumEfectes = ManagerPerfil.getVolumEfectes();
        volumMusica = ManagerPerfil.getVolumMusica();
    }

    private void crearBotons() {
        botoEnrere = new BotoMenu(game, ManagerRecursos.getImage("botoLastTrackImage"), 359, 257);
        botoEnrere.setMouseOverImage(ManagerRecursos.getImage("botoLastTrackImage"));
        botoEnrere.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ReproductorMusica.prev();
            }
        });
        botoEnrere.setMouseDownSound(soClick);
        botoEnrere.setMouseOverSound(soOver);
        botoEnrere.setActiu(true);

        botoStop = new BotoMenu(game, ManagerRecursos.getImage("botoLastTrackImage"), 479, 257);
        botoStop.setMouseOverImage(ManagerRecursos.getImage("botoLastTrackImage"));
        botoStop.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ReproductorMusica.stop();
            }
        });
        botoStop.setMouseDownSound(soClick);
        botoStop.setMouseOverSound(soOver);
        botoStop.setActiu(true);

        botoEndavant = new BotoMenu(game, ManagerRecursos.getImage("botoLastTrackImage"), 599, 257);
        botoEndavant.setMouseOverImage(ManagerRecursos.getImage("botoLastTrackImage"));
        botoEndavant.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                ReproductorMusica.next();
            }
        });
        botoEndavant.setMouseDownSound(soClick);
        botoEndavant.setMouseOverSound(soOver);
        botoEndavant.setActiu(true);

        botoTornar = new BotoMenu(game, ManagerRecursos.getImage("botoPerfilNormalImage"), 380, 560);
        botoTornar.setMouseOverImage(ManagerRecursos.getImage("botoPerfil2OverImage"));
        botoTornar.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                state.enterState(EstatMenuPrincipal.ID, new FadeOutTransition(), new FadeInTransition());
            }
        });
        botoTornar.setMouseDownSound(soClick);
        botoTornar.setMouseOverSound(soOver);
        botoTornar.setActiu(true);

        botoRestaEfectes = new BotoMenu(game, ManagerRecursos.getImage("botoEnrereImage"), 390, 470);
        botoRestaEfectes.setMouseOverImage(ManagerRecursos.getImage("botoEnrereOverImage"));
        botoRestaEfectes.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumEfectes >= 10) {
                    volumEfectes += 10;
                    game.setMusicVolume(volumEfectes);
                    ReproductorMusica.update(game);
                }
            }
        });
        botoRestaEfectes.setMouseDownSound(soClick);
        botoRestaEfectes.setMouseOverSound(soOver);
        botoRestaEfectes.setActiu(true);

        botoSumaEfectes = new BotoMenu(game, ManagerRecursos.getImage("botoEndavantImage"), 600, 470);
        botoSumaEfectes.setMouseOverImage(ManagerRecursos.getImage("botoEndavantOverImage"));
        botoSumaEfectes.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumEfectes <= 90) {
                    volumEfectes += 10;
                    game.setMusicVolume(volumEfectes);
                }
            }
        });
        botoSumaEfectes.setMouseDownSound(soClick);
        botoSumaEfectes.setMouseOverSound(soOver);
        botoSumaEfectes.setActiu(true);

        botoRestaMusica = new BotoMenu(game, ManagerRecursos.getImage("botoEnrereImage"), 390, 400);
        botoRestaMusica.setMouseOverImage(ManagerRecursos.getImage("botoEnrereOverImage"));
        botoRestaMusica.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumMusica > 10) {
                    volumMusica -= 10;
                    //game.setMusicVolume(volumMusica / 100f);
                    ManagerPerfil.setVolumMusica(volumMusica);
                    ManagerPerfil.guardarValorsMusica();
                    ReproductorMusica.setVolumMusic();
                    ReproductorMusica.update(game);
                }
            }
        });
        botoRestaMusica.setMouseDownSound(soClick);
        botoRestaMusica.setMouseOverSound(soOver);
        botoRestaMusica.setActiu(true);

        botoSumaMusica = new BotoMenu(game, ManagerRecursos.getImage("botoEndavantImage"), 600, 400);
        botoSumaMusica.setMouseOverImage(ManagerRecursos.getImage("botoEndavantOverImage"));
        botoSumaMusica.addListener(new ComponentListener() {

            public void componentActivated(AbstractComponent comp) {
                if (volumMusica < 100) {
                    volumMusica += 10;
                    //game.setMusicVolume(volumMusica / 100f);
                    ManagerPerfil.setVolumMusica(volumMusica);
                    ManagerPerfil.guardarValorsMusica();
                    ReproductorMusica.setVolumMusic();
                    ReproductorMusica.update(game);
                }
            }
        });
        botoSumaMusica.setMouseDownSound(soClick);
        botoSumaMusica.setMouseOverSound(soOver);
        botoSumaMusica.setActiu(true);
    }
}