import javax.swing.*;

/**
 * Main frame for the app
 * @author Nicholas Carr
 */
public class Display extends JFrame {

	/**
	 * Constructor
	 * @param tree Pedigree to be drawn
	 * @param inheritance Type of inheritance of disease
	 * @see Inheritance
	 */
	public Display(Tree tree, Inheritance inheritance) {
		add(new JScrollPane(new Panel(tree, inheritance)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pedigree Generator v0.0");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}
}
