import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

/**
 * Main panel that renders the pedigree
 * @author Nicholas Carr
 */
public class Panel extends JPanel implements Refreshable {
	private Tree tree;
	private Inheritance inheritance;

	/**
	 * Constructor
	 * @param tree Pedigree to be drawn
	 * @param inheritance Type of inheritance of disease
	 * @see Inheritance
	 */
	public Panel(Tree tree, Inheritance inheritance) {
		this.inheritance = inheritance;
		this.tree = tree;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Custom Font
		try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("renogare.otf")));
        } catch (Exception e) {
        }
		
        setUIFont(new javax.swing.plaf.FontUIResource("renogare", Font.PLAIN, 48));
        
		refresh();
	}

	/**
	 * Rebuild the entire panel (usually in response to the tree changing)
	 * @see Refreshable
	 */
	public void refresh() {
		removeAll();
		DisplayGeneration lastGeneration = null;
		for (int generationNumber = tree.all.size() - 1; generationNumber >= 0; generationNumber--) {
			// TODO: export/import, colour person of interest
			lastGeneration = new DisplayGeneration(tree, inheritance, generationNumber, this, lastGeneration);
			add(lastGeneration);
		}

		 calculate();
		Person person = tree.initialPerson;
		if(person.getHomoUnaffected()!=null) {
			JLabel chance1 = new JLabel("Homozygous affected chance: " + person.getHomoAffected().getNumber() * 100 + "%");
			JLabel chance2 = new JLabel("Heterozygous chance: " + person.getHetero().getNumber() * 100 + "%");
			JLabel chance3 = new JLabel("Homozygous unaffected chance: " + person.getHomoUnaffected().getNumber() * 100 + "%");
			add(chance1);
			add(chance2);
			add(chance3);
		}else{
			JLabel chance1 = new JLabel("Invalid tree");
			add(chance1);
		}
		revalidate();
		repaint();
	}

	private boolean calculatePerson(Person person) {
		switch (inheritance) {
			case AUTOSOMAL_DOMINANT:
				return tree.autosomalDominant(person);
			case AUTOSOMAL_RECESSIVE:
				return tree.autosomalRecessive(person);
			case X_LINKED_DOMINANT:
				return tree.xLinkDominant(person);
			case X_LINKED_RECESSIVE:
				return tree.xLinkRecessive(person);
			case Y_LINKED:
				return tree.yLinked(person);
		}
		return false;
	}

	private boolean calculatePersonUp(Person person) {
		switch (inheritance) {
			case AUTOSOMAL_DOMINANT:
				return tree.autosomalDominantUp(person);
			case AUTOSOMAL_RECESSIVE:
				return tree.autosomalRecessiveUp(person);
			case X_LINKED_DOMINANT:
				return tree.xLinkDominantUp(person);
			case X_LINKED_RECESSIVE:
				return tree.xLinkRecessiveUp(person);
			case Y_LINKED:
				return tree.yLinkUp(person);
		}
		return false;
	}

	public boolean calculate() {
		tree.reset();
		for(int i = 0;i<tree.all.size();i++) {
			for(int j = 0;j<tree.all.get(i).size();j++) {
				if(!calculatePersonUp(tree.all.get(i).get(j))){
					System.out.println("error at (" + i + "," + j + ")");
					tree.reset();
					return false;
				}
			}
		}
		for(int i = tree.all.size()-1;i>=0;i--) {
			for(int j = 0;j<tree.all.get(i).size();j++) {
				if(!calculatePerson(tree.all.get(i).get(j))) {
					System.out.println("error at (" + i + "," + j + ")");
					tree.reset();
					return false;
				}
			}
		}
		return true;
	}
	
	//Load Custom Fonts
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}
