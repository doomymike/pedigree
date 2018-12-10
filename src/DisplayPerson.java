import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Symbol representing a person
 * @author Nicholas Carr
 */
public class DisplayPerson extends JPanel {
	private final Person person;
	private Tree tree;
	private final Inheritance inheritance;

	/**
	 * Constructor
	 * @param person Person to be drawn
	 * @param tree Pedigree containing the person
	 * @param refreshable Component to refresh when the tree is modified
	 */
	public DisplayPerson(Person person, Tree tree, Inheritance inheritance, Refreshable refreshable) {
		this.person = person;
		this.tree = tree;
		this.inheritance = inheritance;
		setPreferredSize(new Dimension(50, 50));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonPanel popup = new PersonPanel(person, tree, refreshable);
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * Getter for person data
	 * @return Person object
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Paints the symbol
	 * @param g Swing graphics object
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (tree.initialPerson == person) {
			g.setColor(Color.RED);
		}
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
}
