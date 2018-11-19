import javax.swing.*;

public class PersonPanel extends JPanel {
	private Person person;
	private Tree tree;
	private Panel panel;

	public PersonPanel(Person p, Tree t, Panel panel) {
		person = p;
		tree = t;
		this.panel = panel;
		JButton parentsButton = new JButton("Add Parents");
		parentsButton.addActionListener(e -> {
			tree.addParents(person);
			panel.refresh();
		});
		add(parentsButton);
		JButton partnerButton = new JButton("Add Partner");
		partnerButton.addActionListener(e -> {
			tree.addSpouse(person);
			panel.refresh();
		});
		add(partnerButton);
		JButton childButton = new JButton("Add Child");
		childButton.addActionListener(e -> {
			tree.addChild(person);
			panel.refresh();
		});
		add(childButton);
	}
}
