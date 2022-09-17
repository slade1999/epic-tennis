import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TennisGame extends JPanel {
    //initialize elements
    private GameObject playerReference;
    private GameObject opponentReference;
    private ArrayList<GameObject> gameObjReference;

    public TennisGame() {
        this.setBackground(new Color(26,118,0)); //set dark green background color
    }

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        //create lines on tennis court
        g.fillRect(1, 480, 979, 20);//net

        g.fillRect(100, 750, 790, 5);//bottom middle line
        g.fillRect(100, 210, 790, 5);//top middle line
        g.fillRect(495, 210, 5, 540);//middle down line
        g.fillRect(1, 1, 979, 5);//highest line
        g.fillRect(1, 1, 5, 960);//leftist line
        g.fillRect(979, 1, 5, 960);//rightest line
        g.fillRect(1, 956, 979, 5);//lowest line
        g.fillRect(100, 1, 5, 960);//left inner border
        g.fillRect(885, 1, 5, 960);//right inner border

        //draw player in game
        if (playerReference != null) {
            playerReference.draw(g);
        }
        //draw opponent in game
        if (opponentReference != null) {
            opponentReference.draw(g);
        }

        //draws objects into game
        if (gameObjReference != null)
        {
            for(GameObject aGameObject : gameObjReference)
            {
                aGameObject.draw(g);
            }
        }
    }


    //We need to be able to
    public void setPlayerReferenceInScreen(GameObject pPlayer)
    {

        playerReference = pPlayer;
    }

    //We need to be able to
    public void setOpponentReferenceInScreen(GameObject pOpponent)
    {

        opponentReference = pOpponent;
    }

    //We need to be able to
    public void setGameObjectsReferenceInScreen(ArrayList<GameObject> pGameObjs)
    {

        gameObjReference= pGameObjs;
    }

}
