import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements Refreshable {
	private Tree tree;
	private Refreshable frame;

	public Panel(Tree tree, Refreshable frame) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.tree = tree;
		this.frame = frame;
		refresh();
	}

	public void refresh() {
		removeAll();
		ArrayList<ArrayList<DisplayPerson>> people = new ArrayList<>();
		ArrayList<ArrayList<Person>> all = tree.all;
		for (int generationNumber = 0; generationNumber < all.size(); generationNumber++) {
			List<Person> generation = all.get(generationNumber);
			ArrayList<DisplayPerson> displayGeneration = new ArrayList<>();
			for (Person person : generation) {
				displayGeneration.add(new DisplayPerson(person, tree, frame));
			}
			people.add(displayGeneration);
		}
		draw(people);
		frame.revalidate();
		frame.repaint();
	}

	private void draw(ArrayList<ArrayList<DisplayPerson>> people) {
		for (ArrayList<DisplayPerson> generation : people) {
			DisplayGeneration displayGeneration = new DisplayGeneration();
			for (int number = 0; number < generation.size(); number++) {
				DisplayPerson person = generation.get(number);
				if (number > 0 && person.getSpouse() == generation.get(number - 1).getPerson()) {
					displayGeneration.add(new JLabel("-")); // TODO make less ew
				}
				displayGeneration.add(person);
			}
			add(displayGeneration);
		}
	}
}
