/*Pedigree Generator - Menu GUI
    MenuFrame.java
    JFrame handling the main menu, buttons, and options for the program
    Author: Brian Zhang
    Course: ICS4UE
    Date: 12/06/18
 */

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuFrame extends JFrame implements Runnable, KeyListener {
    //MenuPanel reference initialization
    private MenuPanel panel;
    protected JFrame next;
    protected IntroductionFrame nexto;

    //Mouse Position
    private int xPos = 0;
    private int yPos = 0;

    //Base constructor
    public MenuFrame(){
        //Base Dimensions
        setSize(1000,650);
        setLocationRelativeTo(null);
        setTitle("Pedigree Generator v0.0");

        //Decorations
        setResizable(true);
        setUndecorated(false);

        //Override mouseListener methods
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                xPos = e.getX();
                yPos = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt){
                setLocation(evt.getXOnScreen()-xPos, evt.getYOnScreen()-yPos);
            }
        });
        
        addKeyListener(this);
        
        panel = new MenuPanel();
        panel.setLayout(null);

        //Setup all buttons for pedigree options (AutoDom, AutoRec, XLinkDom, XLinkRec, YLink, Samples)
        JButton buttonOne = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoDomButton.png"))));
        JButton buttonTwo = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoRecButton.png"))));
        JButton buttonThree = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkDomButton.png"))));
        JButton buttonFour = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkRecButton.png"))));
        JButton buttonFive = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("YLinkButton.png"))));
        JButton buttonSix = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SamplesButton.png"))));

        //Add AutoDom button to panel
        panel.add(buttonOne);
        buttonOne.setLocation(50,50);
        buttonOne.setSize(450,160);
        buttonTwo.setBorderPainted(false);
        buttonOne.setContentAreaFilled(false);

        //Custom button mouseListener
        buttonOne.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonOne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoDomHover.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonOne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoDomButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonOne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoDomPress.png"))));
            }
            public void mouseClicked(MouseEvent e) {
            	next = new Display(new Tree(), Inheritance.AUTOSOMAL_DOMINANT);
                dispose();
            }
        });

        //Add AutoRec button to panel
        panel.add(buttonTwo);
        buttonTwo.setLocation(50,250);
        buttonTwo.setSize(450,160);
        buttonTwo.setBorder(BorderFactory.createEmptyBorder());
        buttonTwo.setContentAreaFilled(false);

        //Custom button mouseListener
        buttonTwo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonTwo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoRecHover.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonTwo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoRecButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonTwo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AutoRecPress.png"))));
            }
            public void mouseClicked(MouseEvent e) {
            	next = new Display(new Tree(), Inheritance.AUTOSOMAL_RECESSIVE);
                dispose();
            }
        });

        //Add XLinkDom button to panel
        panel.add(buttonThree);
        buttonThree.setLocation(50,450);
        buttonThree.setSize(450,160);
        buttonThree.setBorder(BorderFactory.createEmptyBorder());
        buttonThree.setContentAreaFilled(false);

        //Custom button mouseListener
        buttonThree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonThree.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkDomHover.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonThree.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkDomButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonThree.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkDomPress.png"))));
            }
            public void mouseClicked(MouseEvent e) {
            	next = new Display(new Tree(), Inheritance.X_LINKED_DOMINANT);
                dispose();
            }
        });

        //Add XLinkRec button to panel
        panel.add(buttonFour);
        buttonFour.setLocation(500,50);
        buttonFour.setSize(450,160);
        buttonFour.setBorder(BorderFactory.createEmptyBorder());
        buttonFour.setContentAreaFilled(false);

        //Custom button mouseListener
        buttonFour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonFour.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkRecHover.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonFour.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkRecButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonFour.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("XLinkRecPress.png"))));
            }
            public void mouseClicked(MouseEvent e) {
            	next = new Display(new Tree(), Inheritance.X_LINKED_RECESSIVE);
				dispose();
            }
        });

        //Add YLink button to panel
        panel.add(buttonFive);
        buttonFive.setLocation(500,250);
        buttonFive.setSize(450,160);
        buttonFive.setBorder(BorderFactory.createEmptyBorder());
        buttonFive.setContentAreaFilled(false);

        //Custom button mouseListener
        buttonFive.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonFive.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("YLinkHover.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonFive.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("YLinkButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonFive.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("YLinkPress.png"))));
            }
            public void mouseClicked(MouseEvent e) {
				next = new Display(new Tree(), Inheritance.Y_LINKED);
				dispose();
            }
        });

        panel.add(buttonSix);
        buttonSix.setLocation(500,450);
        buttonSix.setSize(450,160);
        buttonSix.setBorder(BorderFactory.createEmptyBorder());
        buttonSix.setContentAreaFilled(false);

      //Custom button mouseListener
        buttonSix.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonSix.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SamplesButton.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonSix.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SamplesHover.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonSix.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SamplesPress.png"))));
            }
            public void mouseClicked(MouseEvent e) {
				next = new SampleFrame();
				dispose();
            }
        });
        
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //BackButton
    public void keyPressed(KeyEvent e)
	   {
	      // Ability to traverse backwards
	      if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
	      {
	    	  System.out.println("escape pressed");
	    	  nexto = new IntroductionFrame();
	          dispose();
	      }
	   }
	
	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e){}
	
    public void run(){
        //Placeholder method
    }
}
