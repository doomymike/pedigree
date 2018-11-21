import javax.swing.*;
import java.awt.*;

public class DisplayNode extends JPanel {
	public DisplayNode(Person person, Tree tree, Refreshable refreshable) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(100, 30));
	}
}
