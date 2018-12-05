import java.util.ArrayList;

/**
 * List of children and their spouses
 */
public class DisplayChildren extends DisplayPeople {
	private ArrayList<DisplayPeople> children;

	public DisplayChildren() {
		this.children = new ArrayList<>();
	}

	public void add(DisplayPeople person) {
		// If we already have the person we're looking for, do nothing
		for (DisplayPeople people : children) {
			if (person.equals(people)) {
				return;
			}
		}
		// If we're sure we don't already have them, add them
		children.add(person);
	}

	@Override
	public boolean contains(Person person) {
		for (DisplayPeople child : children) {
			if (!child.contains(person)) {
				return false;
			}
		}
		return true;
	}
}
