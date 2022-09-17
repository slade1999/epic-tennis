import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class TennisGUI extends JFrame implements Runnable {
    private TennisGame tennisGame;  //creates the visuals for the game
    private GameKeyListener keyListener; //The listener for key presses
    private Player player; //creates player object
    private Opponent opponent;
    private ArrayList<GameObject> gameObjects; //all game objects except the player
    private Thread loop;
    private Random rand;
    int ballMove; //decides which way the ball will move
    String opponentHead; //chooses opponent sprite
    int cCount; //counts each collision
    int reverse; //affects the balls direction
    int level; //the current level
    volatile boolean bStop; //needed to stop loop

    JFrame f;
    JPanel pScore;
    JLabel lScoreP, lScoreC;
    int playerScore, opponentScore; //current score of players

    public TennisGUI(int pLevel) {
        //set up game gui
        f = new JFrame();
        f.setTitle("EPIC TENNIS");
        f.setSize(1280, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set up the score board
        playerScore = 0;
        opponentScore = 0;
        level = pLevel;
        //panels
        pScore = new JPanel();
        //labels
        lScoreP = new JLabel("<html>" +"You're current score: " + "<br />" + playerScore + "</html>"); //holds players score
        lScoreC = new JLabel("<html>" +"Opponent's current score: " + "<br />" + playerScore + "<html>"); //holds computers score
        //set Scoreboard style
        lScoreP.setFont(new Font("Courier New",Font.BOLD,18));
        lScoreC.setFont(new Font("Courier New",Font.BOLD,18));
        pScore.setLayout(new BorderLayout());
        pScore.add(lScoreC, BorderLayout.NORTH);
        pScore.add(lScoreP, BorderLayout.SOUTH);
        pScore.setBackground(Color.GRAY);

        tennisGame = new TennisGame(); //Instantiate the visuals
        f.add(tennisGame, BorderLayout.CENTER); //adds the visuals to the game
        f.add(pScore, BorderLayout.EAST);
        f.setVisible(true);

        keyListener = new GameKeyListener(); //instantiate the listener for key presses
        f.addKeyListener(keyListener); //add the key listener to the JFrame

        manageGame(); //call manageGame function
    }

    //manages all aspects of the game
    private void manageGame() {
        SoundTrack music = new SoundTrack(); //will play music
        player = new Player();//instantiate player class
        gameObjects = new ArrayList<GameObject>();
        cCount = 0;
        reverse = 1; //determine ball direction after wall collision
        tennisGame.setPlayerReferenceInScreen((Player) player);
        tennisGame.setGameObjectsReferenceInScreen(gameObjects);

        //decide which sprite to use for opponent
        opponentHead = "H:\\Web development\\final_knepp\\src\\kanyeHead.png";
        opponent = new Opponent(opponentHead);
        tennisGame.setOpponentReferenceInScreen((Opponent) opponent);

        gameObjects.add(new TheBall()); //add the ball to game
        rand = new Random();

    }

    //the loop allows the game to be played normally
    private void RunGame() {
        bStop = true;
        tennisGame.repaint();
        loop = new Thread(this);
        loop.start();
        ballMove = rand.nextInt(3);


    }

    @Override
    //allows all objects to move by being repositioned
    public void run() {
        //get the sprite for opponent
        //level 1 is kanye
        if (level == 1 && playerScore == 0 && opponentScore == 0){
            opponentHead = "H:\\Web development\\final_knepp\\src\\kanyeHead.png";
            Opponent.PLAYER_SPEED = 7; //opponent gets faster each round
            //message displaying current level
            JOptionPane.showMessageDialog(null, "<HTML>" + "LEVEL 1 - EASY" + "<br />" +
                                            "This is the quarter-finals." + "<br />" +
                                            "Opponent: Kanye West" + "</html>");
        }
        //level 2 is dr.phil
        if (level == 2 && playerScore == 0 && opponentScore == 0){
            opponentHead = "H:\\Web development\\final_knepp\\src\\philHead.png";
            Opponent.PLAYER_SPEED = 8;
            //message displaying current level
            JOptionPane.showMessageDialog(null, "<HTML>" + "LEVEL 2 - MEDIUM" + "<br />" +
                    "You have made it to the semi-finals." + "<br />" +
                    "Opponent: Dr. Phil" + "</html>");
        }
        //level 3 is trump
        if (level == 3 && playerScore == 0 && opponentScore == 0){
            opponentHead = "H:\\Web development\\final_knepp\\src\\trumpHead.png";
            Opponent.PLAYER_SPEED = 9;
            //message displaying current level
            JOptionPane.showMessageDialog(null, "<HTML>" + "LEVEL 3 - HARD" + "<br />" +
                    "This is the championship match. Good luck!" + "<br />" +
                    "Opponent: Donald Trump" + "</html>");
        }
        //if player wins
        if (level > 3)
        {
            JOptionPane.showMessageDialog(null, "<HTML>" + "CONGRATULATIONS" + "<br />" +
                    "Jody has become the Tennis Champion!" + "</html>");
            System.exit(1); //The game will end
        }
        //load current sprite and start loop
        opponent.loadSprite(opponentHead);
        tennisGame.repaint();
        bStop = true;
        while(bStop) {
            //DETERMINES BALL MOVEMENT---------------------------------
            for (GameObject anObject : gameObjects) {

                //if theBall was last touched by player
                if (anObject.getxPos() > 2 && anObject.getxPos() < 948) {
                    if (cCount % 2 == 0 && ballMove == 0) {
                        anObject.setyPos(anObject.getyPos() - TheBall.BALL_SPEED);
                        //multiplying by reverse ensures ball will not turn back around after x goes past 2
                        anObject.setxPos(anObject.getxPos() - (TheBall.BALL_SPEED * reverse));
                    }
                    if (cCount % 2 == 0 && ballMove == 1) {
                        anObject.setyPos(anObject.getyPos() - TheBall.BALL_SPEED);
                    }
                    if (cCount % 2 == 0 && ballMove == 2) {
                        anObject.setyPos(anObject.getyPos() - TheBall.BALL_SPEED);
                        anObject.setxPos(anObject.getxPos() + (TheBall.BALL_SPEED * reverse));
                    }
                    //if theBall was last touched by opponent
                    if (cCount % 2 != 0 && ballMove == 0) {
                        anObject.setyPos(anObject.getyPos() + TheBall.BALL_SPEED);
                        anObject.setxPos(anObject.getxPos() + (TheBall.BALL_SPEED * reverse));
                    }
                    if (cCount % 2 != 0 && ballMove == 1) {
                        anObject.setyPos(anObject.getyPos() + TheBall.BALL_SPEED);
                    }
                    if (cCount % 2 != 0 && ballMove == 2) {
                        anObject.setyPos(anObject.getyPos() + TheBall.BALL_SPEED);
                        anObject.setxPos(anObject.getxPos() - (TheBall.BALL_SPEED * reverse));
                    }
                }
                else if (anObject.getxPos() < 2 || anObject.getxPos() > 948) {
                    //FOR WALL COLLISION
                    //if player was last touched
                    if (cCount % 2 == 0 && anObject.getxPos() < 2) {
                        anObject.setxPos(anObject.getxPos() + TheBall.BALL_SPEED);
                        anObject.setyPos(anObject.getyPos() - TheBall.BALL_SPEED);
                        //make reverse negative to switch the direction the if statement takes the ball
                        reverse = reverse * (-1);
                    }
                    if (cCount % 2 == 0 && anObject.getxPos() > 948) {
                        anObject.setxPos(anObject.getxPos() - TheBall.BALL_SPEED);
                        anObject.setyPos(anObject.getyPos() - TheBall.BALL_SPEED);
                        reverse = reverse * (-1);
                    }
                    //if opponent was last touched
                    if (cCount % 2 != 0 && anObject.getxPos() < 2) {
                        anObject.setxPos(anObject.getxPos() + TheBall.BALL_SPEED);
                        anObject.setyPos(anObject.getyPos() + TheBall.BALL_SPEED);
                        reverse = reverse * (-1);
                    }
                    if (cCount % 2 != 0 && anObject.getxPos() > 948) {
                        anObject.setxPos(anObject.getxPos() - TheBall.BALL_SPEED);
                        anObject.setyPos(anObject.getyPos() + TheBall.BALL_SPEED);
                        reverse = reverse * (-1);
                    }
                }

                //IF BALL TOUCHES EITHER TOP OR BOTTOM----------------------
                if (anObject.getyPos() < 1) //if ball hits top
                {
                    playerScore += 1; //player gets 1 point
                    //reset the position of all objects
                    anObject.setxPos(220);
                    anObject.setyPos(700);
                    cCount = 0; //counter is reset
                    lScoreP.removeAll();
                    lScoreP.setText("<html>" + "You're current score: " + "<br />" +  playerScore + "</html>"); //update the score
                    player.setxPos(220);
                    player.setyPos(780);
                    opponent.setyPos(25);
                    opponent.setxPos(660);
                    loop.start();
                }
                if (anObject.getyPos() > 948) //if ball hits bottom
                {


                    opponentScore += 1; //opponent gets 1 point
                    //reset the position of all objects
                    anObject.setxPos(220);
                    anObject.setyPos(700);
                    cCount = 0; //counter is reset
                    lScoreC.removeAll();
                    lScoreC.setText("<html>" + "Opponent's current score: " + "<br />" + opponentScore + "</html>"); //update the score
                    player.setxPos(220);
                    player.setyPos(780);
                    opponent.setyPos(25);
                    opponent.setxPos(660);
                    loop.start();
                }

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkCollisions(); //check for collisions with ball, net or players
            checkOpponentMovement(); //chooses where opponent will move
            tennisGame.repaint();

            //for progressing to next level
            if (playerScore == 8){ //the amount of points needed to advance to next level
                level += 1;
                //reset scores
                playerScore = 0;
                opponentScore = 0;
                lScoreP.removeAll();
                lScoreP.setText("<html>" + "You're current score: " + "<br />" +  playerScore + "</html>"); //update the score
                lScoreC.removeAll();
                lScoreC.setText("<html>" + "Opponent's current score: " + "<br />" + opponentScore + "</html>"); //update the score
                bStop = false;
                run();
            }
            //if the opponent wins
            if (opponentScore == 8){
                JOptionPane.showMessageDialog(null, "<HTML>" + "GAME OVER" + "<br />" +
                        "Jody has lost and dishonored his family." + "<br />" +
                        "He will live the rest of his life as a failure." + "</html>");
                System.exit(1); //exits the program
            }
        }
    }

    //class for moving opponent
    private void checkOpponentMovement() {
        for (GameObject anObject : gameObjects) {
            //if the opponent needs to move right to get closer to ball
            if (opponent.getxPos() < anObject.getxPos() &&
                    opponent.getxPos() < 844 && opponent.getxPos() > 1){ //prevent ai from going off screen

                opponent.setxPos(opponent.getxPos() + Opponent.PLAYER_SPEED);
                //if the opponent needs to move right and is far from the ball
                if (anObject.getyPos() - opponent.getyPos() < 120 &&
                        opponent.getyPos() > 1 && opponent.getyPos() < 300){ //prevent ai from going off screen or past net

                    opponent.setyPos(opponent.getyPos() - Opponent.PLAYER_SPEED);
                }
                //if the ball is far lower than the opponent and the x value is close
                if (anObject.getxPos() - opponent.getxPos() < 15 && anObject.getyPos() - opponent.getyPos() > 50 &&
                        opponent.getyPos() > 1 && opponent.getyPos() < 300){ //prevent ai from going off screen or past net

                    opponent.setyPos(opponent.getyPos() + Opponent.PLAYER_SPEED);
                }
            }
            //if the opponent needs to move left to get closer to ball
            if (opponent.getxPos() > anObject.getxPos()){
                opponent.setxPos(opponent.getxPos() - Opponent.PLAYER_SPEED);
                //if the opponent needs to move left and is really far from the ball
                if (anObject.getyPos() - opponent.getyPos() < 120 &&
                        opponent.getyPos() > 1 && opponent.getyPos() < 300){ //prevent ai from going off screen

                    opponent.setyPos(opponent.getyPos() - Opponent.PLAYER_SPEED);
                }
                //if the ball is far lower than the opponent and the x value is close
                if (opponent.getxPos() - anObject.getxPos() < 15 && anObject.getyPos() - opponent.getyPos() > 50 &&
                        opponent.getyPos() > 1 && opponent.getyPos() < 300){ //prevent ai from going off screen

                    opponent.setyPos(opponent.getyPos() + Opponent.PLAYER_SPEED);
                }
            }
            if (anObject.getyPos() - opponent.getyPos() < 40 &&
                    opponent.getyPos() > 1 && opponent.getyPos() < 300){ //prevent ai from going off screen

                opponent.setyPos(opponent.getyPos() - Opponent.PLAYER_SPEED);
            }
        }
    }

    //listens for button presses
    private class GameKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        //when an arrow key is pressed. the players location will be set
        //a certain number of pixels in that direction, depending on his speed
        public void keyPressed(KeyEvent e) {

            //PRESS SPACE TO START THE LEVEL
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                RunGame();
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {//if right key selected
                player.setxPos(player.getxPos() + Player.PLAYER_SPEED);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {//if left key selected
                player.setxPos(player.getxPos() - Player.PLAYER_SPEED);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {//if up key selected
                player.setyPos(player.getyPos() - Player.PLAYER_SPEED);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {//if down key selected
                player.setyPos(player.getyPos() + Player.PLAYER_SPEED);
            }
            //prevent player from going off screen
            //sets x or y position one step in the opposite direction
            if (player.xPos < 1) {
                player.setxPos(player.getxPos() + Player.PLAYER_SPEED);
            }
            if (player.xPos > 844) {
                player.setxPos(player.getxPos() - Player.PLAYER_SPEED);
            }
            if (player.yPos < 1) {
                player.setyPos(player.getyPos() + Player.PLAYER_SPEED);
            }
            if (player.yPos > 798) {
                player.setyPos(player.getyPos() - Player.PLAYER_SPEED);
            }
            //prevent opponent from going off screen
            if (opponent.xPos < 1) {
                opponent.setxPos(opponent.getxPos() + Opponent.PLAYER_SPEED);
            }
            if (opponent.xPos > 844) {
                opponent.setxPos(opponent.getxPos() - Opponent.PLAYER_SPEED);
            }
            if (opponent.yPos < 1) {
                opponent.setyPos(opponent.getyPos() + Opponent.PLAYER_SPEED);
            }
            if (opponent.yPos > 798) {
                opponent.setyPos(opponent.getyPos() - Opponent.PLAYER_SPEED);
            }

            player.UpdateCollisionBoxPosition();
            checkCollisions();//check for collision with ball or net
            repaint();


        }


        @Override
        public void keyReleased(KeyEvent e) {

        }
    }



    public void checkCollisions() {
        //add ball to player inventory
        for (GameObject anObject : gameObjects) {
            TheBall theBall = (TheBall) anObject;
            //FOR WALL COLLISION
            //if player was last touched
            if (player.getCollisionBox().intersects(theBall.getCollisionBox())) //if player touches ball
            {
                cCount += 1; //will decide which way ball moves after collision
                hitBall(player.xPos, player.yPos, 1, theBall);
            }
            if (opponent.getCollisionBox().intersects(theBall.getCollisionBox())) //if opponent touches ball
            {
                cCount += 1;
                hitBall(opponent.xPos, opponent.yPos, -1, theBall);
            }
            theBall.UpdateCollisionBoxPosition();
        }

        //creates instance of TheNet and checks for player collision
        TheNet net = new TheNet();
        //if player touches net then he will be not be able to move up
        if (player.getCollisionBox().intersects(net.getCollisionBox())) {
            player.setyPos(player.getyPos() + Player.PLAYER_SPEED);
            player.UpdateCollisionBoxPosition();
        }
        //create collision for opponent with net
        if (opponent.getCollisionBox().intersects(net.getCollisionBox())) {
            opponent.setyPos(opponent.getyPos() - Opponent.PLAYER_SPEED);
            opponent.UpdateCollisionBoxPosition();
        }

    }

    //if player or opponent touch ball
    //negNum is used on opponent to make ball go opposite way
    public void hitBall(int pX, int pY, int negNum, TheBall theBall) {//will decide where ball goes after collision
        for (GameObject anObject : gameObjects) {
            //ball will randomly fly in one of 3 directions
            ballMove = rand.nextInt(3);
            //ball flies northwest
            if (ballMove == 0) {
                theBall.setxPos(theBall.getxPos() - (TheBall.BALL_SPEED * 3 * negNum));
                theBall.setyPos(theBall.getyPos() - (TheBall.BALL_SPEED * 3 * negNum));

                anObject.setyPos(anObject.getyPos() + 10);
            }
            //ball flies north
            if (ballMove == 1) {
                theBall.setyPos(theBall.getyPos() - (TheBall.BALL_SPEED * 5 * negNum));
                anObject.setyPos(anObject.getyPos() + 10);
            }
            //ball flies northeast
            if (ballMove == 2) {
                theBall.setxPos(theBall.getxPos() + (TheBall.BALL_SPEED * 3 * negNum));
                theBall.setyPos(theBall.getyPos() - (TheBall.BALL_SPEED * 3 * negNum));
                anObject.setyPos(anObject.getyPos() + 10);
            }
        }

    }
}
