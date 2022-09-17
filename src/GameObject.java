import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class calculates data for all objects in the game
public abstract class GameObject {
    //x and y position of the game object
    protected int xPos;
    protected int yPos;
    protected boolean visible; //not used
    protected BufferedImage sprite; //will hold the sprite

    public GameObject() {
        //Default x and y position of the game Object
        xPos = 0;
        yPos = 0;
    }


    private GameObject(int pX, int pY) {
        //if x and y are known
        xPos = pX;
        xPos = pY;
    }

    //load image for sprite
    protected void loadSprite(String pFileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sprite = (BufferedImage) image;

    }

    //draw the sprite from x and y positions
    public abstract void draw(Graphics g);


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    //these are unused

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
}