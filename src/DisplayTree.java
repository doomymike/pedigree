import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayTree extends JPanel {
	public DisplayTree(Person person, Tree tree, Refreshable refreshable) {
		// TODO: remove next line and corresponding import
		setBorder(BorderFactory.createLineBorder(Color.GREEN));

		add(new DisplayNode(person, tree, refreshable));
		if (person.getSpouse() != null) {
			add(new DisplayNode(person.getSpouse(), tree, refreshable));
		}

		ArrayList<Person> drawn = new ArrayList<>();
		for (Person child : person.children) {
			if (drawn.contains(child.getSpouse())) {
				continue;
			}
			drawn.add(person);
			add(new DisplayTree(child, tree, refreshable));
		}
	}
}
