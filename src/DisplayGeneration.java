import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A single row of people
 * @author Nicholas Carr
 */
public class DisplayGeneration extends JPanel {
	private ArrayList<DisplayPerson> people = new ArrayList<>();
	private ArrayList<ParentLine> lines = new ArrayList<>();
	private DisplayGeneration previous;
	/**
	 * Constructor
	 * @param tree Pedigree to be rendered
	 * @param number Generation number in pedigree object
	 * @param refreshable Component to refresh when the tree is modified
	 */
	public DisplayGeneration(Tree tree, int number, Refreshable refreshable, DisplayGeneration previous) {
		this.previous = previous;
		ArrayList<Person> data = tree.all.get(number);
		for (int i = 0; i < data.size(); i++) {
			Person person = data.get(i);
			if (i > 0 && person.getSpouse() == data.get(i - 1)) {
				JLabel connector = new JLabel("-");
				add(connector);
				if (person.getSex() == Person.FEMALE) {
					lines.add(new ParentLine(connector, person));
				} else {
					lines.add(new ParentLine(connector, person.getSpouse()));
				}
			}
			DisplayPerson displayPerson = new DisplayPerson(person, tree, refreshable);
			add(displayPerson);
			people.add(displayPerson);
		}
	}

	/**
	 * Paints lines between parents and their children
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Component component : getComponents()) {
			if (component instanceof JLabel) {
				g.drawLine(component.getX() + 2, component.getY() + 10, component.getX() + 2, g.getClipBounds().height);
			}
		}

		ArrayList<DisplayChildren> groups = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			DisplayPerson person = people.get(i);
			if (person.getPerson().getMother() != null) {
				g.drawLine(person.getX() + 25, 0, person.getX() + 25, person.getY());
				// Set up the children groups so we can draw horizontal lines
				boolean found = false;
				for (DisplayChildren children : groups) {
					if (children.getMother() == person.getPerson().getMother()) {
						found = true;
						children.add(i);
					}
				}
				if (!found) {
					DisplayChildren children = new DisplayChildren(person.getPerson().getMother());
					children.add(i);
					groups.add(children);
				}
			}
		}
		// Draw the horizontal lines
		for (DisplayChildren children : groups) {
			int vertical = 0;
			for (ParentLine line : previous.lines) {
				if (line.getMother() == children.getMother()) {
					System.out.println("vertical is set");
					vertical = line.getPosition();
					break;
				}
			}
			// vertical 0
			if (vertical < people.get(children.getMin()).getX() + 25) {
				g.drawLine(vertical, 0, people.get(children.getMax()).getX() + 25, 0);
			} else if (vertical > people.get(children.getMax()).getX() + 25) {
				g.drawLine(people.get(children.getMin()).getX() + 25, 0, vertical, 0);
			} else {
				g.drawLine(people.get(children.getMin()).getX() + 25, 0, people.get(children.getMax()).getX() + 25, 0);
			}
		}
	}
}
