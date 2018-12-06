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

	public boolean isEmpty() {
		return children.isEmpty();
	}
}
