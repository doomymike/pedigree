import javax.swing.JFrame;

public class Display extends JFrame {
	private Panel panel;

	public Display(Tree tree) {
		panel = new Panel(tree);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}

	public void refresh() {
		panel.refresh();
	}
}
