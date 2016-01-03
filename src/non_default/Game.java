package non_default;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Game extends JFrame implements ActionListener, KeyListener, MouseListener {
	
	private Board board;
	private JButton bestButton;
	private JButton switchButton;
	private JButton restartButton;
	private int scoreInt;
	private JLabel scoreLabel;
	private Logic logic;
	private String[][] bestScores;
	
	public Board getBoard(){ //getters
		return this.board;
	}
	public JButton getBestButton(){
		return this.bestButton;
	}
	public JButton getSwitchButton(){ 
		return this.switchButton;
	}
	public JButton getRestartButton(){
		return this.restartButton;
	}
	public int getScoreInt(){
		return this.scoreInt;
	}
	public JLabel getScoreLabel(){
		return this.scoreLabel;
	}
	public Logic getLogic(){
		return this.logic;
	}
	public String[][] getBest(){
		return this.bestScores;
	}
	public void setScoreInt(int score){ //setters
		this.scoreInt=score;
	}
	public void setScoreLabel(){ //setter without input. set the scoreLabel to the current score value.
		this.scoreLabel.setText("Score: "+this.scoreInt);
	}

	public Game() throws IOException{ //constructor
		super("2048 - WorldCup2014"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false);
		this.addKeyListener(this);
		JLabel back=new JLabel(new ImageIcon("Images//background2.gif"));
		this.setContentPane(back); //set the background image
		this.getContentPane().setLayout(new BorderLayout()); //set layout to BorderLayout (North for buttons, center for playing board)
		this.board=new Board(); //create the board 
		this.board.setBorder(new EmptyBorder(50, 340, 10, 10)); //set the place of the board in the grid
		bestButton = new JButton("Best Scores"); //create the buttons
		switchButton = new JButton("Switch Graphics"); //""
		restartButton = new JButton("Start Over"); //""
		scoreInt = 0;
		scoreLabel = new JLabel("Score: "+scoreInt); //create the score label with int value
		bestScores=new String[10][2]; //create 2D array that holds name and score of the best scores
		Logic.readFromFile(bestScores); //set the best scores array according to file
		scoreLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		scoreLabel.setFont(new Font("David", Font.BOLD, 18));
		scoreLabel.setOpaque(true); //to enable setBackground
		scoreLabel.setBackground(Color.WHITE);
		this.logic=new Logic(this); //create new Logic that will have dual connection with "this"
		bestButton.addActionListener(this); //attach an ActionListener (Logic)
		switchButton.addActionListener(this); //""
		restartButton.addActionListener(this); //""
		bestButton.setFocusable(false); //make sure the focus is always on the main window
		switchButton.setFocusable(false); //""
		restartButton.setFocusable(false); //""
		scoreLabel.addKeyListener(this);
		JPanel northPanel = new JPanel(); //create a sub-panel for the "northern" buttons (and label)
		northPanel.add(bestButton); //""
		northPanel.add(switchButton); //""
		northPanel.add(restartButton); //""
		northPanel.add(scoreLabel); //""
		northPanel.setBorder(new EmptyBorder(90, 340, 25, 10)); //place the northPanel on the grid
		northPanel.setBackground(new Color(0,0,0,0)); //set the background color to transparent
		board.setBackground(new Color(0,0,0,0)); //set the background color to transparent
		this.getContentPane().add(northPanel, BorderLayout.NORTH); //add the buttons to the "north"
		this.getContentPane().add(board, BorderLayout.CENTER); //add the board to the "center"
		this.addMouseListener(this); //only for repaint causes (if the window is dragged "out" of the screen)
		this.board.addMouseListener(this); //""
		northPanel.addMouseListener(this); //""
		this.setSize(700,500); //set standard size
		this.setVisible(true); //show the window
	}
	
	public void actionPerformed(ActionEvent e){ 
		if (e.getSource()==this.getBestButton()){ //open table with best scores
			try {
				Logic.readFromFile(this.bestScores);
				BestScores best=new BestScores(this); 
			} catch (IOException e1) {
				//will never get here
			}
		}
		if (e.getSource()==this.getSwitchButton()){  //switch the graphics 1/2/3
			if (this.getBoard().getGraphic()==1)
				this.getBoard().setGraphic(2);
			else if (this.getBoard().getGraphic()==2)
				this.getBoard().setGraphic(3);
			else if (this.getBoard().getGraphic()==3)
				this.getBoard().setGraphic(1);
			this.getBoard().reDrawBoard();
		}
		if (e.getSource()==this.getRestartButton()){ //restart the game 
			this.getBoard().restartGame();
			this.setScoreInt(0); //change score to 0
		}
		this.setScoreLabel();
		this.repaint(); //repaint after every event to prevent graphical "errors"
	}
	
	public void keyPressed(KeyEvent e) { //change the board according to arrow pressed + change the score
	    if (e.getKeyCode() == KeyEvent.VK_LEFT){
	    	this.logic.actionLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
        	this.logic.actionRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
        	this.logic.actionUp();        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
        	this.logic.actionDown();      
        }
    	if (this.logic.getChanged()==true)  //draw a new label only if the board has changed
    		this.getBoard().randomDrawLabel();
    	this.logic.setChanged(false);
    	if (this.getBoard().isFull()){ //check if board is full
			if (this.getBoard().Lost()){ //check if lost
				try {
					this.logic.endGame(); //go to function that ends the game in logic
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
        this.setScoreLabel(); //change the score to the updated scoreInt value
        this.repaint(); //repaint after every event to prevent graphical "errors"
	}
	
	public static void main(String args[]) throws IOException{ //MAIN
		Game game = new Game();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		//implement
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//implement
	}
	
	public void mouseClicked(MouseEvent e) { //repaint after every event to prevent graphical "errors"
		this.repaint();
		this.board.repaint();		
	}
	
	public void mouseEntered(MouseEvent e) { //""
		this.repaint();
		this.board.repaint();		
	}

	public void mouseExited(MouseEvent e) { //""
		this.repaint();
		this.board.repaint();		
	}

	public void mousePressed(MouseEvent e) { //""
		this.repaint();
		this.board.repaint();		
	}
	
	public void mouseReleased(MouseEvent e) { //""
		this.repaint();
		this.board.repaint();		
	}
}
