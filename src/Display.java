
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * Main frame for the app
 * @author Nicholas Carr
 */
public class Display extends JFrame implements KeyListener{

	//Setup Back Frame
	protected MenuFrame next;
	
	/**
	 * Constructor
	 * @param tree Pedigree to be drawn
	 * @param inheritance Type of inheritance of disease
	 * @see Inheritance
	 */
	public Display(Tree tree, Inheritance inheritance) {

		add(new JScrollPane(new Panel(tree, inheritance)));
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pedigree Generator v0.0");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e)
	   {
	      // Ability to traverse backwards
	      if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
	      {
	    	  System.out.println("escape pressed");
	    	  next = new MenuFrame();
	          dispose();
	      }
	   }
	
	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e){}

}
