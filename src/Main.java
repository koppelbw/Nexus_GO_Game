import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/* *****************************************************************************
 * Project: Nexus_GO_Game.java
 * File: Main.java
 * Description: 
 * Author: William Koppelberger
 * Date: 2/2/15
 * *****************************************************************************/
public class Main {

	public static void main(String[] args) {

		/* Request user input for board size */
		int numRow;
		int numCol;
		JRadioButton small = new JRadioButton("Small");
		JRadioButton medium = new JRadioButton("Medium");
		JRadioButton large = new JRadioButton("Large");
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(small);
		buttons.add(medium);
		buttons.add(large);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Choose Map Size"));
		panel.add(small);
		panel.add(medium);
		panel.add(large);
		small.setSelected(true);

		// Request data from user
		int choice = JOptionPane.showOptionDialog(null, panel, "Nexus_Go",
				JOptionPane.OK_CANCEL_OPTION, 1, null, null, panel);

		// Exit program if user selects CANCEL or 'X' button
		if (choice == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
		if (choice == JOptionPane.CLOSED_OPTION) {
			System.exit(0);
		}

		// Small = 10x10; Medium: 15x15; Large: 20x20
		if (small.isSelected()) {
			numRow = 10;
			numCol = 10;
			medium.setSelected(false);
			large.setSelected(false);
		} else if (medium.isSelected()) {
			numRow = 15;
			numCol = 15;
			small.setSelected(false);
			large.setSelected(false);
		} else {
			numRow = 20;
			numCol = 20;
			small.setSelected(false);
			medium.setSelected(false);
		}

		/* Create MVC and run the program */
		Model gameModel = new Model(numRow, numCol);
		View gameView = new View(numRow, numCol);
		Controller gameController = new Controller(gameModel, gameView);

		gameView.setVisible(true);
	}
}