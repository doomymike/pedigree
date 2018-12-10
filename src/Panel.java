import javax.swing.*;

/**
 * Main panel that renders the pedigree
 * @author Nicholas Carr
 */
public class Panel extends JPanel implements Refreshable {
	private Tree tree;
	private Inheritance inheritance;

	/**
	 * Constructor
	 * @param tree Pedigree to be drawn
	 * @param inheritance Type of inheritance of disease
	 * @see Inheritance
	 */
	public Panel(Tree tree, Inheritance inheritance) {
		this.inheritance = inheritance;
		this.tree = tree;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
			lastGeneration = new DisplayGeneration(tree, inheritance, generationNumber, this, lastGeneration);
			add(lastGeneration);
		}

		Person person = tree.initialPerson;
		switch (inheritance) {
			case AUTOSOMAL_DOMINANT:
				tree.autosomalDominant(person);
				break;
			case AUTOSOMAL_RECESSIVE:
				tree.autosomalRecessive(person);
				break;
			case X_LINKED_DOMINANT:
				tree.xLinkDominant(person);
				break;
			case X_LINKED_RECESSIVE:
				tree.xLinkRecessive(person);
				break;
			case Y_LINKED:
				tree.yLinked(person);
				break;
		}

		add(new JLabel("Homozygous affected chance: " + person.getHomoAffected().numerator + " / " + person.getHomoAffected().denominator));
		add(new JLabel("Heterozygous chance: " + person.getHetero().numerator + " / " + person.getHetero().denominator));
		add(new JLabel("Homozygous unaffected chance: " + person.getHomoUnaffected().numerator + " / " + person.getHomoUnaffected().denominator));
		revalidate();
		repaint();
	}
}
