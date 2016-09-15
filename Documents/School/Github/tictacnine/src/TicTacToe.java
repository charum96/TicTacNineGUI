import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToe extends JFrame {
	JFrame frame;
	JFrame win = new JFrame();
	JPanel panel = new JPanel();
	JPanel background = new JPanel();
	JButton[][] buttons = new JButton[3][3];
	JButton exit = new JButton();
	JButton clear = new JButton();
	JButton restart = new JButton();
	JButton latestbutton;

	public JButton button;
	public TicTacToeGame that1;
	public TicTacToeGame that2;
	public TicTacToeGame that3;
	public TicTacToeGame that4;
	public TicTacToeGame that5;
	public TicTacToeGame that6;
	public TicTacToeGame that7;
	public TicTacToeGame that8;
	public TicTacToeGame that9;
	public int all = 0;

	public int p = 0;

	public ImageIcon X = new ImageIcon(this.getClass().getResource("/xo/X Image.png"), "X");
	public ImageIcon O = new ImageIcon(this.getClass().getResource("/xo/O Image.png"), "O");

	public int winner;
	public boolean playing;
	
	

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();

	}

	public TicTacToe() {
		setTitle("Tic Tac Nine Board");
		setSize(600, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(true);

		this.exit.setText("EXIT");
		this.exit.setBounds(500, 600, 90, 70);
		this.clear.setText("RESTART");
		this.clear.setBounds(20, 600, 90, 70);

		this.panel.setLayout(new GridLayout(3, 3));
		this.panel.setSize(600, 600);
		this.panel.setBackground(Color.LIGHT_GRAY);

		this.background.setLayout(null);
		this.background.setBackground(Color.LIGHT_GRAY);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();

				this.panel.add(buttons[i][j]);
			}
		}

		buttons[0][0].addActionListener(new HandleButtonPressedBoard1(this));
		buttons[0][1].addActionListener(new HandleButtonPressedBoard2(this));
		buttons[0][2].addActionListener(new HandleButtonPressedBoard3(this));
		buttons[1][0].addActionListener(new HandleButtonPressedBoard4(this));
		buttons[1][1].addActionListener(new HandleButtonPressedBoard5(this));
		buttons[1][2].addActionListener(new HandleButtonPressedBoard6(this));
		buttons[2][0].addActionListener(new HandleButtonPressedBoard7(this));
		buttons[2][1].addActionListener(new HandleButtonPressedBoard8(this));
		buttons[2][2].addActionListener(new HandleButtonPressedBoard9(this));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.panel.add(buttons[i][j]);
			}
		}

		add(this.exit);
		add(this.clear);
		add(this.panel);
		add(this.background);
		this.clear.addActionListener(new HandleClearPressed());
		this.exit.addActionListener(new HandleExitPressed());

		setVisible(true);

	}

	public void handleAfterGameFinished(TicTacToeGame a, int b) {
		if (b == 1) {
			latestbutton.setIcon(X);
			latestbutton.setDisabledIcon(X);
			latestbutton.setEnabled(false);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton || ((ImageIcon) buttons[i][j].getIcon()) == null ) {
						buttons[i][j].setEnabled(true);
					}
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] == latestbutton || ((ImageIcon) buttons[i][j].getIcon()) != null ) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			checkWin();
		}

		if (b == 2) {
			latestbutton.setIcon(O);
			latestbutton.setDisabledIcon(O);
			latestbutton.setEnabled(false);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton || ((ImageIcon) buttons[i][j].getIcon()) == null ) {
						buttons[i][j].setEnabled(true);
					}
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] == latestbutton || ((ImageIcon) buttons[i][j].getIcon()) != null ) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			checkWin();
		}
		
		

	}

	public boolean getButtonWinner() {
		if (latestbutton != null && ((ImageIcon) latestbutton.getIcon()).getDescription() != null
				&& ((ImageIcon) latestbutton.getIcon()).getDescription().equals("O")) {
			return false;
		} else {
			return true;
		}
	}

	class HandleExitPressed implements ActionListener {
		// @Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	class HandleClearPressed implements ActionListener {
		// @Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					buttons[i][j].setEnabled(true);
					buttons[i][j].setIcon(null);
					win.setVisible(false);
				}
			}
			latestbutton=null;
		}

	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard1 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard1(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[0][0] = (JButton) e.getSource();

			that1 = new TicTacToeGame(600, 200, board);
			that1.setTurn(getButtonWinner());
			buttons[0][0].setEnabled(false);
			latestbutton = buttons[0][0];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that1, that1.checkWin());

			// if(that1.checkWin()==1){all++; buttons[0][0].setIcon(X);}
			// if(that2.checkWin()==1){all++; buttons[0][1].setIcon(X);}
			// if(that3.checkWin()==1){all++; buttons[0][2].setIcon(X);}
			// if(that4.checkWin()==1){all++; buttons[1][0].setIcon(X);}
			// if(that5.checkWin()==1){all++; buttons[1][1].setIcon(X);}
			// if(that6.checkWin()==1){all++; buttons[1][2].setIcon(X);}
			// if(that7.checkWin()==1){all++; buttons[2][0].setIcon(X);}
			// if(that8.checkWin()==1){all++; buttons[2][1].setIcon(X);}
			// if(that9.checkWin()==1){all++; buttons[2][2].setIcon(X);}
			// if(that1.checkWin()==2){all++; buttons[0][0].setIcon(O);}
			// if(that2.checkWin()==2){all++; buttons[0][1].setIcon(O);}
			// if(that3.checkWin()==2){all++; buttons[0][2].setIcon(O);}
			// if(that4.checkWin()==2){all++; buttons[1][0].setIcon(O);}
			// if(that5.checkWin()==2){all++; buttons[1][1].setIcon(O);}
			// if(that6.checkWin()==2){all++; buttons[1][2].setIcon(O);}
			// if(that7.checkWin()==2){all++; buttons[2][0].setIcon(O);}
			// if(that8.checkWin()==2){all++; buttons[2][1].setIcon(O);}
			// if(that9.checkWin()==2){all++; buttons[2][2].setIcon(O);}

		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard2 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard2(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[0][1] = (JButton) e.getSource();
			that2 = new TicTacToeGame(600, 200, board);
			that2.setTurn(getButtonWinner());
			buttons[0][1].setEnabled(false);
			latestbutton = buttons[0][1];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that2, that2.checkWin());

			//
		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard3 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard3(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[0][2] = (JButton) e.getSource();
			that3 = new TicTacToeGame(600, 200, board);
			that3.setTurn(getButtonWinner());
			buttons[0][2].setEnabled(false);
			latestbutton = buttons[0][2];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that3, that3.checkWin());

		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard4 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard4(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[1][0] = (JButton) e.getSource();
			that4 = new TicTacToeGame(600, 200, board);
			that4.setTurn(getButtonWinner());
			buttons[1][0].setEnabled(false);
			latestbutton = buttons[1][0];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that4, that4.checkWin());

		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard5 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard5(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[1][1] = (JButton) e.getSource();
			that5 = new TicTacToeGame(600, 200, board);
			that5.setTurn(getButtonWinner());
			buttons[1][1].setEnabled(false);
			latestbutton = buttons[1][1];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that5, that5.checkWin());

		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard6 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard6(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[1][2] = (JButton) e.getSource();
			that6 = new TicTacToeGame(600, 200, board);
			that6.setTurn(getButtonWinner());
			buttons[1][2].setEnabled(false);
			latestbutton = buttons[1][2];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that6, that6.checkWin());
			//
		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard7 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard7(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[2][0] = (JButton) e.getSource();
			that7 = new TicTacToeGame(600, 200, board);
			that7.setTurn(getButtonWinner());
			buttons[2][0].setEnabled(false);
			latestbutton = buttons[2][0];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that7, that7.checkWin());

			//
		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard8 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard8(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[2][1] = (JButton) e.getSource();
			that8 = new TicTacToeGame(600, 200, board);
			that8.setTurn(getButtonWinner());
			buttons[2][1].setEnabled(false);
			latestbutton = buttons[2][1];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that8, that8.checkWin());
		}
	}

	@SuppressWarnings("serial")
	class HandleButtonPressedBoard9 extends JButton implements ActionListener {
		// @Override
		private TicTacToe board;

		public HandleButtonPressedBoard9(TicTacToe board) {
			this.board = board;
		}

		public void actionPerformed(ActionEvent e) {
			buttons[2][2] = (JButton) e.getSource();
			that9 = new TicTacToeGame(600, 200, board);
			that9.setTurn(getButtonWinner());
			buttons[2][2].setEnabled(false);
			latestbutton = buttons[2][2];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] != latestbutton) {
						buttons[i][j].setEnabled(false);
					}
				}
			}
			handleAfterGameFinished(that9, that9.checkWin());

		}
	}

	public int checkWin() {

		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[0][1].getIcon()) != null)
				&& (((ImageIcon) buttons[0][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("X"))) {

				// ///// trying to make game end when someone wins
				this.playing = false;

				this.winner = 1;
				// System.out.println("X wins in row 1");
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.orange);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[1][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[1][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;
				// System.out.println("X wins in row 2");
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[2][0].getIcon()) != null) && (((ImageIcon) buttons[2][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		// X columns
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][0].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[0][1].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][1].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][2].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}

		// O rows
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[0][1].getIcon()) != null)
				&& (((ImageIcon) buttons[0][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[1][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[1][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[2][0].getIcon()) != null) && (((ImageIcon) buttons[2][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}

		// O columns
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][0].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[0][1].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][1].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][1].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][2].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][2].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}

		// X diagonals
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("X"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("X"))) {
				this.playing = false;

				this.winner = 1;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.blue);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.ORANGE);
				JLabel text = new JLabel("Player X Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.ORANGE);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}

		// O diagonals
		if ((((ImageIcon) buttons[0][0].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][2].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][0].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][2].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if ((((ImageIcon) buttons[0][2].getIcon()) != null) && (((ImageIcon) buttons[1][1].getIcon()) != null)
				&& (((ImageIcon) buttons[2][0].getIcon()) != null)) {
			if ((((ImageIcon) buttons[0][2].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[1][1].getIcon()).getDescription().equals("O"))
					&& (((ImageIcon) buttons[2][0].getIcon()).getDescription().equals("O"))) {
				this.playing = false;

				this.winner = 2;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						buttons[i][j].setEnabled(false);
					}
				}
				win = new JFrame();
				win.setSize(500, 300);
				win.setLocation(50, 50);
				JPanel panel = new JPanel();
				panel.setSize(500, 150);
				panel.setBackground(Color.ORANGE);
				JLabel gameOver = new JLabel("Game Over");
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
				gameOver.setForeground(Color.blue);
				JLabel text = new JLabel("Player O Wins!");
				text.setHorizontalAlignment(SwingConstants.CENTER);
				text.setFont(text.getFont().deriveFont(64.0f));
				text.setForeground(Color.blue);
				panel.add(gameOver);
				panel.add(text);
				win.add(panel);
				win.setVisible(true);
				return winner;
			}
		}
		if (((ImageIcon) buttons[0][0].getIcon()) != null && ((ImageIcon) buttons[0][1].getIcon()) != null
				&& ((ImageIcon) buttons[0][2].getIcon()) != null && ((ImageIcon) buttons[1][0].getIcon()) != null
				&& ((ImageIcon) buttons[1][1].getIcon()) != null && ((ImageIcon) buttons[1][2].getIcon()) != null
				&& ((ImageIcon) buttons[2][0].getIcon()) != null && ((ImageIcon) buttons[2][1].getIcon()) != null
				&& ((ImageIcon) buttons[2][2].getIcon()) != null) {
			this.playing = false;
			this.winner = 3;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					buttons[i][j].setEnabled(false);
				}
			}
			win = new JFrame();
			win.setSize(500, 300);
			win.setLocation(50, 50);
			JPanel panel = new JPanel();
			panel.setSize(500, 150);
			panel.setBackground(Color.blue);
			JLabel gameOver = new JLabel("Players tied...");
			gameOver.setHorizontalAlignment(SwingConstants.CENTER);
			gameOver.setFont(gameOver.getFont().deriveFont(64.0f));
			gameOver.setForeground(Color.ORANGE);
			JLabel text = new JLabel("Click restart");
			JLabel text2 = new JLabel("to play again!");
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text2.setHorizontalAlignment(SwingConstants.CENTER);
			text.setFont(text.getFont().deriveFont(50.0f));
			text2.setFont(text2.getFont().deriveFont(50.0f));
			text.setForeground(Color.ORANGE);
			text2.setForeground(Color.ORANGE);
			
			panel.add(gameOver);
			panel.add(text);
			panel.add(text2);
			win.add(panel);
	
			win.setVisible(true);
		}

		return winner;

	}
	// change who goes first for each mini game
	// whoever wins small game goes first in the next game
	// make it pretty

}
