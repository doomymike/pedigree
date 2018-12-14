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

public class SampleFrame extends JFrame implements Runnable {
    //MenuPanel reference initialization
    private MenuPanel panel;
    protected JFrame next;

    //Mouse Position
    private int xPos = 0;
    private int yPos = 0;

    //Base constructor
    public SampleFrame(){
        //Base Dimensions
        setSize(1000,650);
        setLocationRelativeTo(null);
        setTitle("Vote Tories Today!");

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

        panel = new MenuPanel();
        panel.setLayout(null);

        //Setup all buttons for pedigree options (AutoDom, AutoRec, XLinkDom, XLinkRec, YLink, Samples)
        JButton buttonOne = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoDomButton.png"))));
        JButton buttonTwo = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoRecButton.png"))));
        JButton buttonThree = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkDomButton.png"))));
        JButton buttonFour = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkRecButton.png"))));
        JButton buttonFive = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleYLinkButton.png"))));

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
                buttonOne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoDomButton.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonOne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoDomButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonOne.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoDomButton.png"))));
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    next = new Display(Export.takeIn("autodom.txt"), Inheritance.AUTOSOMAL_DOMINANT);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                buttonTwo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoRecButton.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonTwo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoRecButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonTwo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleAutoRecButton.png"))));
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    next = new Display(Export.takeIn("autorec.txt"), Inheritance.AUTOSOMAL_RECESSIVE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                buttonThree.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkDomButton.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonThree.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkDomButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonThree.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkDomButton.png"))));
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    next = new Display(Export.takeIn("xdom.txt"), Inheritance.X_LINKED_DOMINANT);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                buttonFour.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkRecButton.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonFour.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkRecButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonFour.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleXLinkRecButton.png"))));
            }
            public void mouseClicked(MouseEvent e) {
				try {
					next = new Display(Export.takeIn("xrec.txt"), Inheritance.X_LINKED_RECESSIVE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
                buttonFive.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleYLinkButton.png"))));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonFive.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleYLinkButton.png"))));
            }
            public void mousePressed(java.awt.event.MouseEvent evt){
                buttonFive.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("SampleYLinkButton.png"))));
            }
            public void mouseClicked(MouseEvent e) {
				try {
					next = new Display(Export.takeIn("ylink.txt"), Inheritance.Y_LINKED);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
            }
        });


        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void run(){
        //Placeholder method
    }
}
