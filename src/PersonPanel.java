import javax.swing.*;

public class PersonPanel extends JPopupMenu {
	public PersonPanel(Person person, Tree tree, Refreshable refreshable) {
		JButton parentsButton = new JButton("Add Parents");
		parentsButton.addActionListener(e -> {
			tree.addParents(person);
			refreshable.refresh();
			this.setVisible(false);
		});
		add(parentsButton);
		JButton partnerButton = new JButton("Add Partner");
		partnerButton.addActionListener(e -> {
			tree.addSpouse(person);
			refreshable.refresh();
			this.setVisible(false);
		});
		add(partnerButton);
		JButton childButton = new JButton("Add Child");
		childButton.addActionListener(e -> {
			tree.addChild(person);
			refreshable.refresh();
			this.setVisible(false);
		});
		add(childButton);
	}
}
