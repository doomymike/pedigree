import javax.swing.*;

public class PersonPanel extends JPanel {
	public PersonPanel(Person person, Tree tree, Refreshable refreshable) {
		JButton parentsButton = new JButton("Add Parents");
		parentsButton.addActionListener(e -> {
			tree.addParents(person);
			refreshable.refresh();
		});
		add(parentsButton);
		JButton partnerButton = new JButton("Add Partner");
		partnerButton.addActionListener(e -> {
			tree.addSpouse(person);
			refreshable.refresh();
		});
		add(partnerButton);
		JButton childButton = new JButton("Add Child");
		childButton.addActionListener(e -> {
			tree.addChild(person);
			refreshable.refresh();
		});
		add(childButton);
	}
}
