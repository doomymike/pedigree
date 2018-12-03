import javax.swing.*;

/**
 * Abstract class for things that can be in DisplayChildren
 */
public abstract class DisplayPeople extends JPanel {
	public abstract boolean contains(Person person);
}
