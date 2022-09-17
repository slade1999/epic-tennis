import java.awt.*;

public class Opponent extends GameObject {
    //data members of opponent
    public static int PLAYER_SPEED = 7; //determines opponent speed
    public final static int TILE_SIZE = 120;//size of sprite
    private int subImageX, subImageY; //coordinates of subimage




    private BoundingRectangle collisionBox; //area of collision for opponent

    public Opponent(String fileName)
    {
        subImageX=0;
        subImageY=0;

        //path of the sprite
        loadSprite(fileName);

        //player starting position
        xPos = 660;
        yPos = 25;
        visible = true;

    }

    public void UpdateCollisionBoxPosition()
    {

        collisionBox.updateBoundingBox(this.xPos, this.yPos);

    }
    /***********************************************
     *
     * Overrriden function to force the player and other
     * objects to draw themselves on the screen
     * Have to pass in the graphics object associated with
     * the screen
     */
    public void draw(Graphics g) {
        getSubSprite();
        collisionBox = new BoundingRectangle(xPos, yPos, sprite.getWidth(), sprite.getHeight());
        // System.out.println("drawing Player");
        g.drawImage(this.sprite, xPos, yPos, null);
        collisionBox.draw(g);
    }
    public Rectangle getCollisionBox() {

        return collisionBox.getBoundingBox();
    }

    public  void getSubSprite()
    {
        //set size of sprite
        sprite = sprite.getSubimage(subImageX*TILE_SIZE, subImageY*TILE_SIZE, 141, 160);
    }
}


