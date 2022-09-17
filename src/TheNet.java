import java.awt.*;

public class TheNet extends GameObject {

    //create object to give net collision
    private BoundingRectangle collisionNet;

    public TheNet(){
        //set position on top of net
        collisionNet = new BoundingRectangle(1, 480, 979, 20);
        visible = true;
    }
    public void UpdateCollisionBoxPosition()
    {

        collisionNet.updateBoundingBox(1, 480);

    }

    @Override
    public void draw(Graphics g) {
        getSubSprite();
        g.drawImage(null, 1, 480, null);
        UpdateCollisionBoxPosition();
        collisionNet.draw(g);//insert collisionNet into game
    }
    public Rectangle getCollisionBox() {
        return collisionNet.getBoundingBox();
    }

    public  void getSubSprite()
    {
        sprite = sprite.getSubimage(1, 480, 979, 20);
    }
}
