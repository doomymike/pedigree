import javax.swing.*;

/**
 * Main frame for the app
 * @author Nicholas Carr
 */
public class Display extends JFrame {

	/**
	 * Constructor
	 * @param tree Pedigree to be drawn
	 */
	public Display(Tree tree) {
		add(new JScrollPane(new Panel(tree)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}
}
