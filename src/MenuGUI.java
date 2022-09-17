import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

public class MenuGUI {

    //initialize all elements
    JFrame f;
    JPanel pNorth, pMiddle, pSouth;
    JLabel lTitle, lInstructions;
    JButton bStart, bQuit;
    JRadioButton bCheat;
    int level = 1;

    public MenuGUI(){

        //declare elements
        f = new JFrame();
        //panels
        pNorth = new JPanel();
        pMiddle = new JPanel();
        pSouth = new JPanel();
        //labels
        lTitle = new JLabel("EPIC TENNIS");
        lInstructions = new JLabel("<html>" +"In this arcade style tennis game, you will play as Jody as he battles his way to" + "<br />" +
                                        "the tennis championship. You will always play on the bottom half of the court." + "<br />" +
                                        "Press space to begin the match. Use the arrow keys to move Jody around the screen" +" <br />" +
                                        "to hit the ball back to the opponents side of the court. " + "<br />" +
                                        "To score a point the ball must hit the back of the opponents court." + "<br />" +
                                        "It's glorified pong essentially. The match is over when someone scores 8 points." + "</html>");
        //buttons
        bStart = new JButton("START");
        bQuit = new JButton("QUIT");
        bCheat = new JRadioButton("CHEAT(skip levels)");

        //GUI for main menu
        f.setTitle("EPIC TENNIS"); //title of GUI
        f.setSize(800, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true); // set the window to be visible
        //action listeners
        bStart.addActionListener(new startGameListener());
        bQuit.addActionListener(new quitGameListener());

        //designing the gui
        lTitle.setFont(new Font("Courier New",Font.BOLD,50));
        pNorth.add(lTitle);
        pNorth.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));//makes panel background transparent

        lInstructions.setFont((new Font("Verdana", Font.PLAIN, 16)));
        pMiddle.add(lInstructions);
        pMiddle.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));

        pSouth.setLayout(new FlowLayout());
        pSouth.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);//puts buttons beside each other
        pSouth.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
        pSouth.add(bStart);
        pSouth.add(bQuit);
        pSouth.add(bCheat);
        //creates a background for jframe
        JLabel background=new JLabel(new ImageIcon("H:\\Web development\\final_knepp\\src\\background.jpg"));
        f.setContentPane(background);
        background.setLayout(new FlowLayout());
        //add panels to frame
        f.add(pNorth, BorderLayout.NORTH);
        f.add(pMiddle, BorderLayout.CENTER);
        f.add(pSouth, BorderLayout.SOUTH);
    }



    private class startGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            //make f invisible and start game GUI
            f.setVisible(false);//hides this frame
            //if the cheat button is selected go to final level
            if (bCheat.isSelected()){
                level = 3;
            }
            //opens the games GUI
            TennisGUI tennis = new TennisGUI(level); //must include cheat for cheating
        }
    }

    private class quitGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            //display message and exit program
            JOptionPane.showMessageDialog(null, "You have quit Epic Tennis.");
            System.exit(1);
        }
    }
}
