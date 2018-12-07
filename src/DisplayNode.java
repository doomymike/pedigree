import java.awt.*;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisplayNode extends DisplayPeople {
	private final Person person;
	private DisplayParents parents;
	private DisplayParents partnership;

	public DisplayNode(Person person, Tree tree, Refreshable refreshable) {
		this.person = person;
		// TODO: turn this into square/circle
		if (person.getSex() == Person.MALE) {
			setBackground(Color.BLACK);
		} else {
			setBackground(Color.ORANGE);
		}
		setPreferredSize(new Dimension(100, 30));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonPanel popup = new PersonPanel(person, tree, refreshable);
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
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

	@Override
	public void draw() {
	}
}
