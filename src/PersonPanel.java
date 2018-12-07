import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PersonPanel extends JPopupMenu {
	public PersonPanel(Person person, Tree tree, Refreshable refreshable) {
		// TODO: don't show these components if they won't do anything
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
		addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				refreshable.repaint();
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {

			}
		});
	}
}
