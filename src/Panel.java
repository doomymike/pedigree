import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;

public class Panel extends JPanel implements Refreshable {
	private Tree tree;

	public Panel(Tree tree) {
		// TODO: remove next line and corresponding import
		setBackground(Color.GRAY);
		this.tree = tree;
		refresh();
	}

	public void refresh() {
		// Iterate through oldest ancestors
		ArrayList<Person> drawn = new ArrayList<>();
		for (Person person : tree.all.get(tree.all.size() - 1)) {
			if (drawn.contains(person.getSpouse())) {
				continue;
			}
			drawn.add(person);
			add(new DisplayTree(person, tree, this));
		}
	}
}
