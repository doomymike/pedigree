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
		setMother(mother);
		setFather(father);
	}

	public DisplayChildren getChildren() {
		return children;
	}

	public void setMother(DisplayNode mother) {
		this.mother = mother;
		mother.setPartnership(this);
	}

	public void setFather(DisplayNode father) {
		this.father = father;
		father.setPartnership(this);
	}

	public DisplayNode getMother() {
		return mother;
	}

	public DisplayNode getFather() {
		return father;
	}
}
