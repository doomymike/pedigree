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
		for (int i = people.size() - 1; i >= 0; i--) {
			// Iterate over all people in the generation
			for (DisplayNode person : people.get(i)) {
				// Do parent-specific stuff
				if (person.getPerson().getMother() != null) {
					// Connect to mother if possible
					for (int j = 0; j < people.get(i + 1).size(); j++) {
						if (people.get(i + 1).get(j).contains(person.getPerson().getMother())) {
							person.setParents(new DisplayParents());
							person.getParents().setMother(people.get(i + 1).get(j));
							break;
						}
					}
					// Connect to father if possible
					for (int j = 0; j < people.get(i + 1).size(); j++) {
						if (people.get(i + 1).get(j).contains(person.getPerson().getFather())) {
							person.getParents().setFather(people.get(i + 1).get(j));
							break;
						}
					}

					if (person.getParents().getChildren() == null) {
						// Make a children node if the parent node doesn't have one yet
						DisplayChildren children = new DisplayChildren();
						nodes.get(i).add(children);
						person.getParents().setChildren(children);
					}

					if (person.getPerson().getSpouse() == null) {
						// Add this person to the children node
						person.getParents().getChildren().add(person);
					}
				}

				if (person.getPerson().getSpouse() != null) {
					// Find the spouse and add a parent node with this person and the spouse
					for (int j = 0; j < people.get(i).size(); j++) {
						// TODO: only check the mother or father since sex is already known, delete the useless contains method
						if (people.get(i).get(j).getPerson().equals(person.getPerson().getSpouse())) {
							DisplayNode spouse = people.get(i).get(j);
							DisplayParents partnership;
							if (spouse.getPerson().getSex() == Person.MALE) {
								partnership = new DisplayParents(person, spouse);
							} else {
								partnership = new DisplayParents(spouse, person);
							}
							person.setPartnership(partnership);
							spouse.setPartnership(partnership);
							break;
						}
					}
					if (person.getParents() != null) {
						person.getParents().getChildren().add(person.getPartnership());
					}
					nodes.get(i).add(person.getPartnership());
				} else if (person.getParents() == null) {
					nodes.get(i).add(person);
				}
			}
		}
	}
}
