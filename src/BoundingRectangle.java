import java.awt.*;

public class BoundingRectangle extends GameObject{
    private Rectangle boundingBox;



    private int spriteWidth;
    private int spriteHeight;

    //get dimensions for object being made
    public BoundingRectangle(int pXPos, int pYPos,
                             int pWidth, int pHeight)
    {
        xPos = pXPos;
        yPos = pYPos;
        spriteWidth = pWidth;
        spriteHeight = pHeight;

        boundingBox = new Rectangle(xPos, yPos, pWidth, pHeight);
    }


    //for updating location
    public void updateBoundingBox(int pX, int pY)
    {
        boundingBox.x=pX;
        boundingBox.y=pY;
    }
    @Override
    public void draw(Graphics g) {
        //draw the object
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(boundingBox);
    }


    //Accessors and Mutators
    public Rectangle getBoundingBox() {
        return boundingBox;
    }


    //these are unused
    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }
}
