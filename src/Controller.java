import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/* *****************************************************************************
 * Project: Nexus_GO_Game.java
 * File: Controller.java
 * Description: Controller class that bridges the gap between the Model and View.
 * Author: William Koppelberger
 * Date: 2/2/15
 * *****************************************************************************/
public class Controller {

	/* Instantiate the Model and View Objects */
	private Model gameModel;
	private View gameView;

	/* Constructor: [Model, View] */
	public Controller(Model gameModel, View gameView) {

		// Set Look&Feel to Nimbus style
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		// Initialize gameModel and gameView
		this.gameModel = gameModel;
		this.gameView = gameView;

		// Tells the View that if buttons are clicked, call actionPerformed
		this.gameView.addButtonListener(new ButtonListener());
		this.gameView.addMouseListener(new MouseHandler());
	}

	/* This method handles game interactions. Corresponds to View method */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JButton resetButton = gameView.getResetButton();
			
			if(e.getSource() == resetButton) {
				gameModel.reset();
				gameView.reset();
				gameView.displayGUIBoard();
			}
		}
	}
	
	/* This method handles game interactions. Corresponds to View method */
	private class MouseHandler implements MouseListener {

		//public void actionPerformed(ActionEvent e) {

		//}

		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int numRow = gameView.getNumRow();
			int numCol = gameView.getNumCol();
			JLabel[][] gameBoardButtons = gameView.getGameBoardButtons();
			char gameBoard[][] = gameModel.getBoard();
			JButton resetButton = gameView.getResetButton();

			// Update Model and View
			for (int r = 0; r < numRow; r++)
				for (int c = 0; c < numCol; c++)
					if (arg0.getSource() == gameBoardButtons[r][c]) {

						// Check with model if taken spot and update if can
						gameModel.updatePlayerTurn(r, c,
								gameModel.getPlayerTurn());

						// Update View
						gameView.setGameBoard(gameBoard);
						gameView.displayGUIBoard();

						// Check with model if isWinner
						if (gameModel.isWinner() == 'W')
							JOptionPane.showMessageDialog(null, "Player "
									+ gameModel.getPlayerTurn()
									+ " is the winner!!", "Winner!",
									JOptionPane.PLAIN_MESSAGE);

						if (gameModel.isWinner() == 'T')
							JOptionPane.showMessageDialog(null, "Tie Game!!",
									"Tie Game!", JOptionPane.PLAIN_MESSAGE);

					} else if (arg0.getSource() == resetButton) {
						gameModel.reset();
						gameView.displayGUIBoard();
					}
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}