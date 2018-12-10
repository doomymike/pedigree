import java.util.ArrayList;

/**
 * Contains a list of indices of people who have the same mother
 * @see DisplayGeneration
 * @author Nicholas Carr
 */
public class DisplayChildren {
	private Person mother;
	private ArrayList<Integer> indices;

	/**
	 * Constructor
	 * @param mother Mother
	 */
	public DisplayChildren(Person mother) {
		this.mother = mother;
		indices = new ArrayList<>();
	}

	/**
	 * Add an index
	 * @param index Index of person within generation
	 */
	public void add(int index) {
		indices.add(index);
	}

	/**
	 * Minimum index
	 * @return Minimum index
	 */
	public int getMin() {
		return indices.get(0);
	}

	/**
	 * Maximum index
	 * @return Maximum index
	 */
	public int getMax() {
		return indices.get(indices.size() - 1);
	}

	/**
	 * Getter for mother
	 * @return Mother
	 */
	public Person getMother() {
		return mother;
	}
}
