import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Charu Mishra (cm2jk)
// Katie Freix (kef3dd)
// Lawson Kennan (lck8bq)
// Shreyas Hirway (sh4wk)

public class TicTacToeGame extends JFrame implements ActionListener {

	JFrame frame;
	JPanel panel = new JPanel();
	JPanel background = new JPanel();
	boolean turn = true;
	boolean playing = true;
	TicTacToe board;
	JButton[][] buttons = new JButton[3][3];
	String[][] fakeBoard = new String[3][3];

	ImageIcon X = new ImageIcon(this.getClass().getResource("/xo/X Image.png"), "X");
	ImageIcon O = new ImageIcon(this.getClass().getResource("/xo/O Image.png"), "O");

	int winner;

	public boolean getPlaying() {
		return this.playing;
	}

	public void setWinner(int b) {
		this.winner = b;
	}

	public void setTurn(boolean a) {
		this.turn = a;
	}

	public boolean getWinner() {
		int a = checkWin();

		Random r = new Random();
		int rand = (int) r.nextInt(3 - 1) + 1;
		if (a == 1) {
			return true;
		} else if (a == 2) {
			return false;
		} else if (rand == 1) {
			return true;
		} else
			return false;
	}

	public TicTacToeGame(int x, int y, TicTacToe board) {
		this.board = board;
		setTitle("Tic Tac Toe Game");
		setSize(600, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(true);
		setLocation(200, 200);

		this.panel.setLayout(new GridLayout(3, 3));
		this.panel.setSize(600, 600);
		this.panel.setBackground(Color.LIGHT_GRAY);

		this.background.setLayout(null);
		this.background.setBackground(Color.LIGHT_GRAY);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				this.panel.add(buttons[i][j]);
			}
		}

		getContentPane().add(this.panel);
		getContentPane().add(this.background);

		setVisible(true);
		// System.out.println(winner);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton button = (JButton) e.getSource();
		if (turn) {
			button.setIcon(X);
			button.setEnabled(false);
			button.setDisabledIcon(X);
			turn = false;
			checkWin();

		} else {
			button.setIcon(O);
			button.setEnabled(false);
			button.setDisabledIcon(O);
			turn = true;
			checkWin();

		}
		// prints out if someone won after the click
		// System.out.println(checkWin());
	}

	public static void main(String[] args) {

	}

	public int checkWin() {

		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[0][1].getIcon()) != null)
				&& (((ImageIcon) buttons[0][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("X"))) {

				// ///// trying to make game end when someone wins
				this.playing = false;
				dispose();
				this.winner = 1;
				System.out.println("X wins in row 1");

			}
		}
		if ((((ImageIcon) buttons[1][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[1][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;
				System.out.println("X wins in row 2");

			}
		}
		if ((((ImageIcon) buttons[2][0].getIcon()) != null) && (((ImageIcon) buttons[2][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;

			}
		}
		// X columns
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][0].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;

			}
		}
		if ((((ImageIcon) buttons[0][1].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][1].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;

			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][2].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;

			}
		}

		// O rows
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[0][1].getIcon()) != null)
				&& (((ImageIcon) buttons[0][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}
		if ((((ImageIcon) buttons[1][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[1][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}
		if ((((ImageIcon) buttons[2][0].getIcon()) != null) && (((ImageIcon) buttons[2][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}

		// O columns
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][0].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}
		if ((((ImageIcon) buttons[0][1].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][1].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][2].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}

		// X diagonals
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;

			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("X"))) {
				this.playing = false;
				dispose();
				this.winner = 1;

			}
		}

		// O diagonals
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("O"))) {
				this.playing = false;
				dispose();
				this.winner = 2;

			}
		}
		if (((ImageIcon) buttons[0][0].getIcon()) != null && ((ImageIcon) buttons[0][1].getIcon()) != null
				&& ((ImageIcon) buttons[0][2].getIcon()) != null && ((ImageIcon) buttons[1][0].getIcon()) != null
				&& ((ImageIcon) buttons[1][1].getIcon()) != null && ((ImageIcon) buttons[1][2].getIcon()) != null
				&& ((ImageIcon) buttons[2][0].getIcon()) != null && ((ImageIcon) buttons[2][1].getIcon()) != null
				&& ((ImageIcon) buttons[2][2].getIcon()) != null) {
			this.playing = false;
			dispose();
			Random random = new Random();
			int number = random.nextInt(2) + 1;
			this.winner = number;

		}

		this.board.handleAfterGameFinished(this, winner);
		return winner;

	}

}
