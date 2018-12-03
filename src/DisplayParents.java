/**
 * Class containing a couple, regardless of whether they may or may not have children
 */
public class DisplayParents extends DisplayPeople {
	private DisplayChildren children;
	private DisplayNode mother;
	private DisplayNode father;

	public DisplayParents() {
		this.children = new DisplayChildren();
	}

	public DisplayParents(DisplayNode mother, DisplayNode father) {
		this();
		this.mother = mother;
		this.father = father;
	}

	public void setChildren(DisplayChildren children) {
		this.children = children;
	}

	public DisplayChildren getChildren() {
		return children;
	}

	@Override
	public boolean contains(Person person) {
		return mother.contains(person) || father.contains(person);
	}

	public void setMother(DisplayNode mother) {
		this.mother = mother;
	}

	public void setFather(DisplayNode father) {
		this.father = father;
	}

	public DisplayNode getMother() {
		return mother;
	}

	public DisplayNode getFather() {
		return father;
	}
}
