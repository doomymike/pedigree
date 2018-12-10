import javax.swing.*;
import java.util.ArrayList;

/**
 * A single row of people
 * @author Nicholas Carr
 */
public class DisplayGeneration extends JPanel {

	/**
	 * Constructor
	 * @param tree Pedigree to be rendered
	 * @param number Generation number in pedigree object
	 * @param refreshable Component to refresh when the tree is modified
	 */
	public DisplayGeneration(Tree tree, int number, Refreshable refreshable) {
		ArrayList<Person> data = tree.all.get(number);
		for (int i = 0; i < data.size(); i++) {
			Person person = data.get(i);
			if (i > 0 && person.getSpouse() == data.get(i - 1)) {
				add(new JLabel("-"));
			}
			add(new DisplayPerson(person, tree, refreshable));
		}
	}
}
