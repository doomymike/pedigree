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

		/* calculate();

		Person person = tree.initialPerson;
		add(new JLabel("Homozygous affected chance: " + person.getHomoAffected().numerator + " / " + person.getHomoAffected().denominator));
		add(new JLabel("Heterozygous chance: " + person.getHetero().numerator + " / " + person.getHetero().denominator));
		add(new JLabel("Homozygous unaffected chance: " + person.getHomoUnaffected().numerator + " / " + person.getHomoUnaffected().denominator)); */
		revalidate();
		repaint();
	}

	private boolean calculatePerson(Person person) {
		switch (inheritance) {
			case AUTOSOMAL_DOMINANT:
				return tree.autosomalDominant(person);
			case AUTOSOMAL_RECESSIVE:
				return tree.autosomalRecessive(person);
			case X_LINKED_DOMINANT:
				return tree.xLinkDominant(person);
			case X_LINKED_RECESSIVE:
				return tree.xLinkRecessive(person);
			case Y_LINKED:
				return tree.yLinked(person);
		}
		return false;
	}

	private boolean calculatePersonUp(Person person) {
		switch (inheritance) {
			case AUTOSOMAL_DOMINANT:
				return tree.autosomalDominantUp(person);
			case AUTOSOMAL_RECESSIVE:
				return tree.autosomalRecessiveUp(person);
			case X_LINKED_DOMINANT:
				return tree.xLinkDominantUp(person);
			case X_LINKED_RECESSIVE:
				return tree.xLinkRecessiveUp(person);
			case Y_LINKED:
				return tree.yLinkUp(person);
		}
		return false;
	}

	public boolean calculate() {
		for(int i = 1;i<tree.all.size();i++) {
			for(int j = 0;j<tree.all.get(i).size();j++) {
				if(!calculatePersonUp(tree.all.get(i).get(j))){
					System.out.println("error at (" + i + "," + j + ")");
					tree.reset();
					return false;
				}
			}
		}
		for(int i = tree.all.size()-1;i>=0;i--) {
			for(int j = 0;j<tree.all.get(i).size();j++) {
				if(!tree.autosomalRecessive(tree.all.get(i).get(j))) {
					System.out.println("error at (" + i + "," + j + ")");
					tree.reset();
					return false;
				}
			}
		}
		return true;
	}
}
