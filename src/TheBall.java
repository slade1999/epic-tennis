import java.awt.*;
import java.awt.image.BufferedImage;

public class TheBall extends GameObject {

    //create object to give net collision
    private BoundingRectangle collisionBall;
    public static final int BALL_SPEED = 10;
    public final static int TILE_SIZE = 33;
    private int subImageX, subImageY;

    public TheBall(){
        subImageX=0;
        subImageY=0;

        //path of the sprite
        loadSprite("H:\\Web development\\final_knepp\\src\\ball.png");


        //ball starting position
        xPos = 220;
        yPos = 700;
        visible = true;
    }
    public void UpdateCollisionBoxPosition()
    {

        collisionBall.updateBoundingBox(this.xPos, this.yPos);

    }

    @Override
    public void draw(Graphics g) {
        getSubSprite();
        //set ball position and size
        collisionBall = new BoundingRectangle(xPos, yPos, sprite.getWidth(), sprite.getHeight());
        g.drawImage(this.sprite, xPos, yPos, null);
        UpdateCollisionBoxPosition();
        collisionBall.draw(g);
    }
    public Rectangle getCollisionBox() {
        return collisionBall.getBoundingBox();
    }

    public  void getSubSprite()
    {
        //set size of sprite
        sprite = sprite.getSubimage(subImageX*TILE_SIZE, subImageY*TILE_SIZE, 33, 33);
    }

    //public int subImage(){
    //}
}
