import java.awt.*;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisplayNode extends DisplayPeople {
	private final Person person;
	private DisplayParents parents;
	private DisplayParents partnership;

	public DisplayNode(Person person, Tree tree, Refreshable refreshable) {
		this.person = person;
		setPreferredSize(new Dimension(50, 50));
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (person.getSex() == Person.FEMALE) {
			if (person.isAffected()) {
				g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			} else {
				g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			}
		} else {
			if (person.isAffected()) {
				g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			} else {
				g.drawRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			}
		}
	}

	@Override
	public void draw() {
	}
}
