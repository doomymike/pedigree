import javax.swing.JPanel;
import java.awt.Color;

public class Panel extends JPanel {
	private Tree tree;

	public Panel(Tree tree) {
		setBackground(Color.BLUE);
		this.tree = tree;
		refresh();
	}

	public void refresh() {
		// Hardcoded
		add(new DisplayNode(new Person(true)));
	}
}
