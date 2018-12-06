import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements Refreshable {
	private Tree tree;
	private ArrayList<ArrayList<DisplayPeople>> nodes = new ArrayList<>();

	public Panel(Tree tree) {
		// TODO: remove next line and corresponding import
		setBackground(Color.GRAY);
		this.tree = tree;
		refresh();
	}

	public void refresh() {
		ArrayList<ArrayList<DisplayNode>> people = new ArrayList<>();
		nodes.clear();
		for (List<Person> generation : tree.all) {
			ArrayList<DisplayNode> displayGeneration = new ArrayList<>();
			for (Person person : generation) {
				displayGeneration.add(new DisplayNode(person, tree, this));
			}
			nodes.add(new ArrayList<>());
			people.add(displayGeneration);
		}
		link(people);
	}

	private void link(ArrayList<ArrayList<DisplayNode>> people) {
		// Iterate over generations, starting from youngest first
		for (int i = 0; i < people.size(); i++) {
			// Iterate over all people in the generation
			for (DisplayNode person : people.get(i)) {
				// Do parent-specific stuff
				if (person.getPerson().getMother() != null) {
					person.setParents(new DisplayParents());
					// Connect to parents
					for (DisplayNode parent : people.get(i + 1)) {
						if (parent.getPerson().equals(person.getPerson().getMother())) {
							if (parent.getPartnership() != null) {
								// Replace the empty object with the one that's already generated
								person.setParents(parent.getPartnership());
								break;
							}
							person.getParents().setMother(parent);
						}
						if (parent.getPerson().equals(person.getPerson().getFather())) {
							if (parent.getPartnership() != null) {
								// Replace the empty object with the one that's already generated
								person.setParents(parent.getPartnership());
								break;
							}
							person.getParents().setFather(parent);
						}
					}

					if (person.getParents().getChildren().isEmpty()) {
						// Add the list of children to the display if it is newly created
						nodes.get(i).add(person.getParents().getChildren());
					}

					if (person.getPerson().getSpouse() == null) {
						// Add this person to the children node
						person.getParents().getChildren().add(person);
					}
				}

				if (person.getPerson().getSpouse() != null) {
					// Only do work if this person hasn't already been added by the spouse
					if (person.getPartnership() == null) {
						// Find the spouse and add a parent node with this person and the spouse
						for (int j = 0; j < people.get(i).size(); j++) {
							if (people.get(i).get(j).getPerson().equals(person.getPerson().getSpouse())) {
								DisplayNode spouse = people.get(i).get(j);
								if (spouse.getPerson().getSex() == Person.MALE) {
									new DisplayParents(person, spouse);
								} else {
									new DisplayParents(spouse, person);
								}
								break;
							}
						}
					}
					if (!nodes.get(i).contains(person.getPartnership())) {
						if (person.getParents() != null) {
							person.getParents().getChildren().add(person.getPartnership());
						} else {
							nodes.get(i).add(person.getPartnership());
						}
					}
				} else if (person.getParents() == null) {
					nodes.get(i).add(person);
				}
			}
		}
	}
}
