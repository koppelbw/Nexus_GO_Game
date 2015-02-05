import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/* *****************************************************************************
 * Project: Nexus_GO_Game.java
 * File: View.java
 * Description: The View class performs as the GUI
 * Author: William Koppelberger
 * Date: 2/2/15
 * *****************************************************************************/

//Panel with a circle drawn on it.
class CirclePanel extends JPanel {

	// This is Swing, so override paint*Component* - not paint
	protected void paintBlackMove(Graphics g, int r, int c) {
		// call super.paintComponent to get default Swing
		// painting behavior (opaque honored, etc.)
		super.paintComponent(g);
		int x = (65 * c) + 10;
		int y = (65 * r) + 30;
		 int width = 50;
		 int height = 50;
		 g.setColor(Color.BLACK);
		 g.fillArc(x, y, width, height, 0, 360);

//		BufferedImage tile;
//		try {
//			tile = ImageIO.read(getClass().getResource("/tile_BLACK_02.png"));
//			g.drawImage(tile, x, y, this);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	// This is Swing, so override paint*Component* - not paint
	protected void paintWhiteMove(Graphics g, int r, int c) {
		// call super.paintComponent to get default Swing
		// painting behavior (opaque honored, etc.)
		super.paintComponent(g);
		int x = (65 * c) + 10;
		int y = (65 * r) + 30;
		 int width = 50;
		 int height = 50;
		 g.setColor(Color.WHITE);
		 g.fillArc(x, y, width, height, 0, 360);

//		BufferedImage tile;
//		try {
//			tile = ImageIO.read(getClass().getResource("/tile_BLACK_02.png"));
//			g.drawImage(tile, x, y, this);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}


public class View extends JFrame {

	/* Instantiate GUI related variables */
	private JPanel gamePanel;
	private GridLayout gameLayout;
	// private JButton[][] gameBoardButtons;
	private JLabel[][] gameBoardLabels;

	private int numRow;
	private int numCol;
	private char[][] gameBoard;
	private JButton resetButton;

	private CirclePanel dot;

	/* Constructor */
	View(int row, int col) {

		numRow = row;
		numCol = col;
		// gameBoardButtons = new JButton[numRow][numCol];
		gameBoardLabels = new JLabel[numRow][numCol];

		gamePanel = new JPanel();
		gameLayout = new GridLayout(row + 1, col + 1);
		resetButton = new JButton("Reset");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650,715);
		this.setResizable(false);
		this.setTitle("Nexus GO");
		gamePanel.setLayout(gameLayout);

		// gamePanel.setBackground(new Color(0,0,0,64));
		ImageIcon tile = new ImageIcon(getClass().getResource("/tile_03.png"));

		dot = new CirclePanel();
		dot.setOpaque(false);

		// Create and insert JLabels
		for (int r = 0; r < numRow; r++)
			for (int c = 0; c < numCol; c++) {
				gameBoardLabels[r][c] = new JLabel(tile);
				gameBoardLabels[r][c].setBorder(BorderFactory
						.createEmptyBorder());
				gamePanel.add(gameBoardLabels[r][c]);
			}

		gamePanel.add(resetButton);

		this.add(dot);
		this.add(gamePanel);

	}

	/* Adds ButtonListeners to all buttons. Corresponds with the Controller */
	public void addButtonListener(ActionListener listener) {
		resetButton.addActionListener(listener);
	}

	/* Adds MouseListeners to all gameBoardLabels */
	public void addMouseListener(MouseListener listener) {

		for (int r = 0; r < numRow; r++)
			for (int c = 0; c < numCol; c++)
				gameBoardLabels[r][c].addMouseListener(listener);
	}

	/* Resets the GUI board tiles */
	public void reset() {
		dot.removeAll();
		this.validate();
		this.repaint();
	}

	/* Updates what the user sees */
	public void displayGUIBoard() {

		for (int r = 0; r < numRow; r++)
			for (int c = 0; c < numCol; c++) {
				// gameBoardLabels[r][c].setText("" + gameBoard[r][c]);
				if (gameBoard[r][c] == 'B')
					dot.paintBlackMove(getGraphics(), r, c);
				if (gameBoard[r][c] == 'W')
					dot.paintWhiteMove(getGraphics(), r, c);
			}
	}

	/* Getter and Setter methods */
	public int getNumRow() {
		return numRow;
	}

	public int getNumCol() {
		return numCol;
	}

	public JLabel[][] getGameBoardButtons() {
		return gameBoardLabels;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public void setGameBoard(char[][] gameBoard) {
		this.gameBoard = gameBoard;
	}
}