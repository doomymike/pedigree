import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A single row of people
 * @author Nicholas Carr, Brian Zhang
 */
public class DisplayGeneration extends JPanel {
	private ArrayList<DisplayPerson> people = new ArrayList<>();
	private ArrayList<ParentLine> lines = new ArrayList<>();
	private DisplayGeneration previous;
	/**
	 * Constructor
	 * @param tree Pedigree to be rendered
	 * @param inheritance Type of inheritance of disease
	 * @param number Generation number in pedigree object
	 * @param refreshable Component to refresh when the tree is modified
	 * @param previous One generation older
	 * @see Inheritance
	 */
	public DisplayGeneration(Tree tree, Inheritance inheritance, int number, Refreshable refreshable, DisplayGeneration previous) {
		this.previous = previous;
		ArrayList<Person> data = tree.all.get(number);
		for (int i = 0; i < data.size(); i++) {
			Person person = data.get(i);
			if (i > 0 && person.getSpouse() == data.get(i - 1)) {
				JLabel connector = new JLabel("-");
				add(connector);
				if (!person.children.isEmpty()) {
					if (person.getSex() == Person.FEMALE) {
						lines.add(new ParentLine(connector, person));
					} else {
						lines.add(new ParentLine(connector, person.getSpouse()));
					}
				}
			}
			DisplayPerson displayPerson = new DisplayPerson(person, tree, inheritance, refreshable);
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
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(4));

		for (ParentLine line : lines) {
			g2.drawLine(line.getPosition(), line.getY(), line.getPosition(), g.getClipBounds().height);
		}

		ArrayList<DisplayChildren> groups = new ArrayList<>();
		for (int i = 0; i < people.size(); i++) {
			DisplayPerson person = people.get(i);
			if (person.getPerson().getMother() != null) {
				g2.drawLine(person.getX() + 25, 0, person.getX() + 25, person.getY()+50);
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
					vertical = line.getPosition();
					break;
				}
			}
			// vertical 0
			if (vertical < people.get(children.getMin()).getX() + 25) {
				g2.drawLine(vertical, 0, people.get(children.getMax()).getX() + 25, 0);
			} else if (vertical > people.get(children.getMax()).getX() + 25) {
				g2.drawLine(people.get(children.getMin()).getX() + 25, 0, vertical, 0);
			} else {
				g2.drawLine(people.get(children.getMin()).getX() + 25, 0, people.get(children.getMax()).getX() + 25, 0);
			}
		}
	}
}
