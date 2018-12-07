import javax.swing.*;

public class Display extends JFrame implements Refreshable {
	private Panel panel;

	public Display(Tree tree) {
		panel = new Panel(tree, this);
		add(new JScrollPane(panel));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}

	public void refresh() {
		panel.refresh();
	}
}
