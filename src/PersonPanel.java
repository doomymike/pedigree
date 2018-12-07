import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonPanel extends JPopupMenu {
    public PersonPanel(Person person, Tree tree, Refreshable refreshable) {

        
        JButton addParentButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddParentButton.png"))));
        JButton addPartnerButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddPartnerButton.png"))));
        JButton addChildButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("AddChildButton.png"))));
  

        
        //Add AddParent button to panel

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
            public void mousePressed(java.awt.event.MouseEvent evt){
            }
            public void mouseClicked(MouseEvent e) {
                tree.addParents(person);
                refreshable.refresh();
                setVisible(false);
            }
        });

        add(addParentButton);
        
        //Add AddPartner button to panel

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
            public void mousePressed(java.awt.event.MouseEvent evt){
            }
            public void mouseClicked(MouseEvent e) {
                tree.addSpouse(person);
                refreshable.refresh();
                setVisible(false);
            }
        });

        add(addPartnerButton);
        
        
        //Add AddChild button to panel

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
            public void mousePressed(java.awt.event.MouseEvent evt){
            }
            public void mouseClicked(MouseEvent e) {
                tree.addChild(person);
                refreshable.refresh();
                setVisible(false);
            }
        });

        add(addChildButton);

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
