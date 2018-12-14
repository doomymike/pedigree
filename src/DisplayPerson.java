import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Symbol representing a person
 * @author Nicholas Carr, Brian Zhang
 */
public class DisplayPerson extends JPanel {
	private final Person person;
	private Tree tree;
	private BufferedImage square1;
	private BufferedImage square2;
	private BufferedImage circle2;
	private BufferedImage circle1;
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
		setPreferredSize(new Dimension(100, 100));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonPanel popup = new PersonPanel(person, tree, refreshable);
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		//Circle and Square import
		URL resource1 = getClass().getResource("SquareFill.png");
		URL resource2 = getClass().getResource("SquareLine.png");
		URL resource3 = getClass().getResource("CircleFill.png");
		URL resource4 = getClass().getResource("CircleLine.png");
		try {
			square1 = ImageIO.read(resource1);
			square2 = ImageIO.read(resource2);
			circle1 = ImageIO.read(resource3);
			circle2 = ImageIO.read(resource4);
		}catch(IOException e) {
		}
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
				g.drawImage(circle1,0,10,this);
			} else {
				g.drawImage(circle2,0,10,this);
			}
		} else {
			if (person.isAffected()) {
				g.drawImage(square1,0,10,this);
			} else {
				g.drawImage(square2,0,10,this);
			}
		}
	}
}
