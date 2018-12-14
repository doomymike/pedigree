/*Pedigree Generator - Menu GUI
    IntroductionFrame.java
    JFrame handling the entry menu image for the program
    Author: Brian Zhang
    Course: ICS4UE
    Date: 12/06/18
 */

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IntroductionFrame extends JFrame implements KeyListener{
    //Reference panels and frames
    private MenuPanel panel;
    protected MenuFrame next;

    //Override KeyListener Methods (proceed to next frame upon keypressed)
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e){

    }
    public void keyPressed(KeyEvent e) {
        next = new MenuFrame();
        dispose();
    }

    //Base Constructor
    public IntroductionFrame(){
        //Base Dimensions
        setSize(1000,650);
        setLocationRelativeTo(null);
        setTitle("Pedigree Generator v0.0");

        //Decorations
        setResizable(true);
        setUndecorated(false);


        panel = new MenuPanel();
        panel.setLayout(null);

        //Initialize the Logo
        JLabel logo = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("MoshLogo.gif"))));
        JLabel dna = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("BlueRed.gif"))));
        JLabel press = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("ReUrban.png"))));
        JLabel license = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("CreatorLicense.png"))));

        //Manual label integration (setLocation and setSize)
        panel.add(logo);
        logo.setLocation(65,140);
        logo.setSize(840,82);

        panel.add(dna);
        dna.setLocation(360,20);
        dna.setSize(250,500);

        panel.add(press);
        press.setLocation(30,480);
        press.setSize(902,90);

        panel.add(license);
        license.setLocation(425,560);
        license.setSize(600,50);

        //Setup JFrame components
        add(panel);
        addKeyListener(this);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
