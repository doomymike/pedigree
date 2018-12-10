import javax.swing.*;

/**
 * Data class representing the vertical line drawn under parents
 * @author Nicholas Carr
 */
public class ParentLine {
	private JComponent connector;
	private Person mother;

	/**
	 * Constructor
	 * @param connector Connector component
	 * @param mother Mother
	 */
	public ParentLine(JComponent connector, Person mother) {
		this.connector = connector;
		this.mother = mother;
	}

	/**
	 * Getter for position
	 * @return Horizontal pixel position
	 */
	public int getPosition() {
		return connector.getX() + 2;
	}

	/**
	 * Getter for vertical position of dash
	 * @return Horizontal pixel position
	 */
	public int getY() {
		return connector.getY() + 10;
	}

	/**
	 * Getter for mother
	 * @return Mother
	 */
	public Person getMother() {
		return mother;
	}
}
