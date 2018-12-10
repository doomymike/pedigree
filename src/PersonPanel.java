import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Action panel that pops up when you click on a person
 * @author Nicholas Carr, Brian Zhang
 */
public class PersonPanel extends JPopupMenu {
    /**
     * Constructor
     * @param person Person to perform actions on
     * @param tree Pedigree containing the person
     * @param refreshable Component to refresh when the tree is modified
     */
    public PersonPanel(Person person, Tree tree, Refreshable refreshable) {
        //Add AddParent button to panel
        if (person.getMother() == null && person.getFather() == null) {
            JButton addParentButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddParentButton.png"))));
            addParentButton.setBorderPainted(false);
            addParentButton.setContentAreaFilled(false);

            //Custom button mouseListener
            addParentButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    addParentButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddParentButton.png"))));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    addParentButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddParentHover.png"))));
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                }

                public void mouseClicked(MouseEvent e) {
                    tree.addParents(person);
                    refreshable.refresh();
                    setVisible(false);
                }
            });

            add(addParentButton);
        }
        
        //Add AddPartner button to panel
        if (person.getSpouse() == null) {
            JButton addPartnerButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddPartnerButton.png"))));
            addPartnerButton.setBorderPainted(false);
            addPartnerButton.setContentAreaFilled(false);

            //Custom button mouseListener
            addPartnerButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    addPartnerButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddPartnerButton.png"))));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    addPartnerButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddPartnerHover.png"))));
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                }

                public void mouseClicked(MouseEvent e) {
                    tree.addSpouse(person);
                    refreshable.refresh();
                    setVisible(false);
                }
            });

            add(addPartnerButton);
        }
        
        //Add AddChild button to panel
        if (person.getSpouse() != null) {
            JButton addChildButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddChildButton.png"))));
            addChildButton.setBorderPainted(false);
            addChildButton.setContentAreaFilled(false);

            //Custom button mouseListener
            addChildButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    addChildButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddChildButton.png"))));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    addChildButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddChildHover.png"))));
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                }

                public void mouseClicked(MouseEvent e) {
                    tree.addChild(person);
                    refreshable.refresh();
                    setVisible(false);
                }
            });

            add(addChildButton);
        }

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
