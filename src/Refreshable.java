/**
 * Callback methods for display components to be called after the tree is modified
 * @author Nicholas Carr
 */
public interface Refreshable {
	/**
	 * Callback
	 */
	void refresh();

	/**
	 * Basic Swing repaint method
	 */
	void repaint();
}
