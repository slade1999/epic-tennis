import java.awt.*;

public class Player extends GameObject {

    //data members of player
    public static int PLAYER_SPEED = 7;
    public final static int TILE_SIZE = 120;
    private int subImageX, subImageY;




    private BoundingRectangle collisionBox;

    public Player()
    {
        subImageX=0;
        subImageY=0;

        //path of the sprite
        loadSprite("H:\\Web development\\final_knepp\\src\\jodyHead.png");

        //player starting position
        xPos = 220;
        yPos = 780;
        visible = true;

    }

    public void UpdateCollisionBoxPosition()
    {
        //set collision box to player position
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
    //get collision box for player
    public Rectangle getCollisionBox() {

        return collisionBox.getBoundingBox();
    }

    public  void getSubSprite()
    {
        //set size of sprite
        sprite = sprite.getSubimage(subImageX*TILE_SIZE, subImageY*TILE_SIZE, 141, 160);
    }
}
