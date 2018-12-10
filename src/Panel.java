import javax.swing.*;

/**
 * Main panel that renders the pedigree
 * @author Nicholas Carr
 */
public class Panel extends JPanel implements Refreshable {
	private Tree tree;

	/**
	 * Constructor
	 * @param tree Pedigree to be drawn
	 */
	public Panel(Tree tree) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.tree = tree;
		refresh();
	}

	/**
	 * Rebuild the entire panel (usually in response to the tree changing)
	 * @see Refreshable
	 */
	public void refresh() {
		removeAll();
		DisplayGeneration lastGeneration = null;
		for (int generationNumber = tree.all.size() - 1; generationNumber >= 0; generationNumber--) {
			lastGeneration = new DisplayGeneration(tree, generationNumber, this, lastGeneration);
			add(lastGeneration);
		}
		revalidate();
		repaint();
	}
}
