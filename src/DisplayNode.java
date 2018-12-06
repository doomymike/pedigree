import java.awt.*;
import java.awt.Dimension;

public class DisplayNode extends DisplayPeople {
	private final Person person;
	private DisplayParents parents;
	private DisplayParents partnership;

	public DisplayNode(Person person, Tree tree, Refreshable refreshable) {
		this.person = person;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(100, 30));
	}

	public DisplayParents getParents() {
		return parents;
	}

	public Person getPerson() {
		return person;
	}

	public void setParents(DisplayParents parents) {
		this.parents = parents;
	}

	public void setPartnership(DisplayParents partnership) {
		this.partnership = partnership;
	}

	public DisplayParents getPartnership() {
		return partnership;
	}
}
