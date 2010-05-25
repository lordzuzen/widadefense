package projecte.td.componentGUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

public class BotoMenu extends AbstractComponent {

    protected  static final int NORMAL = 1;
    protected static final int MOUSE_CLICK = 2;
    protected static final int MOUSE_OVER = 3;

    protected Image imatgeNormal;
    protected Image imatgeMouseOver;
    protected Image mouseDownImage;

    protected Color colorNormal = Color.white;
    protected Color colorMouserOver = Color.white;
    protected Color colorMouseClick = Color.white;

    protected Sound soOver;
    protected Sound soClick;
    protected Shape area;

    protected Image imatgeActual;
    protected Color colorActual;

    protected boolean over;
    protected boolean click;
    protected boolean noClick;

    protected int state = NORMAL;
    
    public BotoMenu(GUIContext container, Image image, int x, int y,
            ComponentListener listener) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
        addListener(listener);
    }

    public BotoMenu(GUIContext container, Image image, int x, int y) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
    }

    public BotoMenu(GUIContext container, Image image, int x, int y, int width,
            int height, ComponentListener listener) {
        this(container, image, x, y, width, height);
        addListener(listener);
    }

    public BotoMenu(GUIContext container, Image image, int x, int y, int width,
            int height) {
        this(container, image, new Rectangle(x, y, width, height));
    }

    public BotoMenu(GUIContext container, Image image, Shape shape) {
        super(container);

        area = shape;
        imatgeNormal = image;
        imatgeActual = image;
        imatgeMouseOver = image;
        mouseDownImage = image;

        colorActual = colorNormal;

        state = NORMAL;
        Input input = container.getInput();
        over = area.contains(input.getMouseX(), input.getMouseY());
        click = input.isMouseButtonDown(0);
        updateImage();
    }

    public void setLocation(int x, int y) {
        if (area != null) {
            area.setX(x);
            area.setY(y);
        }
    }

    public int getX() {
        return (int) area.getX();
    }

    /**
     * Returns the position in the Y coordinate
     *
     * @return y
     */
    public int getY() {
        return (int) area.getY();
    }

    /**
     * Set the normal color used on the image in the default state
     *
     * @param color
     *            The color to be used
     */
    public void setNormalColor(Color color) {
        colorNormal = color;
    }

    /**
     * Set the color to be used when the mouse is over the area
     *
     * @param color
     *            The color to be used when the mouse is over the area
     */
    public void setMouseOverColor(Color color) {
        colorMouserOver = color;
    }

    /**
     * Set the color to be used when the mouse is down the area
     *
     * @param color
     *            The color to be used when the mouse is down the area
     */
    public void setMouseDownColor(Color color) {
        colorMouseClick = color;
    }

    /**
     * Set the normal image used on the image in the default state
     *
     * @param image
     *            The image to be used
     */
    public void setNormalImage(Image image) {
        imatgeNormal = image;
    }

    /**
     * Set the image to be used when the mouse is over the area
     *
     * @param image
     *            The image to be used when the mouse is over the area
     */
    public void setMouseOverImage(Image image) {
        imatgeMouseOver = image;
    }

    /**
     * Set the image to be used when the mouse is down the area
     *
     * @param image
     *            The image to be used when the mouse is down the area
     */
    public void setMouseDownImage(Image image) {
        mouseDownImage = image;
    }

    /**
     * @see org.newdawn.slick.gui.AbstractComponent#render(org.newdawn.slick.gui.GUIContext,
     *      org.newdawn.slick.Graphics)
     */
    public void render(GUIContext container, Graphics g) {
        if (imatgeActual != null) {

            int xp = (int) (area.getX() + ((getWidth() - imatgeActual.getWidth()) / 2));
            int yp = (int) (area.getY() + ((getHeight() - imatgeActual.getHeight()) / 2));

            imatgeActual.draw(xp, yp, colorActual);
        } else {
            g.setColor(colorActual);
            g.fill(area);
        }
        updateImage();
    }

    /**
     * Update the current normalImage based on the mouse state
     */
    protected void updateImage() {
        if (!over) {
            imatgeActual = imatgeNormal;
            colorActual = colorNormal;
            state = NORMAL;
            noClick = false;
        } else {
            if (click) {
                if ((state != MOUSE_CLICK) && (noClick)) {
                    if (soClick != null) {
                        soClick.play();
                    }
                    imatgeActual = mouseDownImage;
                    colorActual = colorMouseClick;
                    state = MOUSE_CLICK;
                    notifyListeners();
                    noClick = false;
                }
            } else {
                noClick = true;
                if (state != MOUSE_OVER) {
                    if (soOver != null) {
                        soOver.play();
                    }
                    imatgeActual = imatgeMouseOver;
                    colorActual = colorMouserOver;
                    state = MOUSE_OVER;
                }
            }
        }

        click = false;
        state = NORMAL;
    }

    /**
     * Set the mouse over sound effect
     *
     * @param sound
     *            The mouse over sound effect
     */
    public void setMouseOverSound(Sound sound) {
        soOver = sound;
    }

    /**
     * Set the mouse down sound effect
     *
     * @param sound
     *            The mouse down sound effect
     */
    public void setMouseDownSound(Sound sound) {
        soClick = sound;
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int, int, int, int)
     */
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        over = area.contains(newx, newy);
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mousePressed(int, int, int)
     */
    public void mousePressed(int button, int mx, int my) {
        over = area.contains(mx, my);
        if (button == 0) {
            click = true;
        }
    }

    /**
     * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int, int, int)
     */
    public void mouseReleased(int button, int mx, int my) {
        over = area.contains(mx, my);
        if (button == 0) {
            click = false;
        }
    }

    /**
     * @see org.newdawn.slick.gui.AbstractComponent#getHeight()
     */
    public int getHeight() {
        return (int) (area.getMaxY() - area.getY());
    }

    /**
     * @see org.newdawn.slick.gui.AbstractComponent#getWidth()
     */
    public int getWidth() {
        return (int) (area.getMaxX() - area.getX());
    }

    public GUIContext getContainer() {
        return container;
    }

    public Image getImatgeActual() {
        return imatgeActual;
    }
}
