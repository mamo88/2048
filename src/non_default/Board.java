package non_default;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class Board extends JPanel {
	
	private int[][] array;
	private JLabel[][] labels;
	private int graphics; //1/2/3
	protected boolean won;
	
	public int[][] getArray(){ //getters
		return this.array;
	}
	public JLabel[][] getLabels(){
		return this.labels;
	}
	public int getGraphic(){
		return this.graphics;
	}
	public void setGraphic(int g){ //setter
		this.graphics=g;
	}
	
	
	public Board(){ //constructor
		super(new GridLayout(4,4,15,15));
		JLabel label;
		array=new int[4][4];
		labels=new JLabel[4][4];
		graphics=1;
		won=false;
		for (int i=0; i<4; i=i+1){ //set the int array to 0. //create blank labels for the labels array
			for (int j=0; j<4; j=j+1){
				array[i][j]=0;
				label = new JLabel();
				Border border=BorderFactory.createLineBorder(Color.BLACK, 5, false);
				label.setBorder(border);
				label.setOpaque(true); //to enable setBackground
				label.setBackground(Color.WHITE);
				label.setFont(new Font("David", Font.BOLD, 50)); //set the text: font+size
				label.setHorizontalAlignment(getWidth()/2); //center the label
				label.setVerticalAlignment(getHeight()/2); //""
				this.add(label);
				labels[i][j]=label;
			}
		}
		this.randomDrawLabel(); //draw 2 random "2/4" labels to begin the game.
		this.randomDrawLabel();	//"
	}
	
	public void restartGame(){ //resets all the labels to 0 and the draws to random to begin a new game
		for (int i=0; i<4; i=i+1){
			for (int j=0; j<4; j=j+1){
				array[i][j]=0;
			}
		}
		this.won=false;
		this.randomDrawLabel();
		this.randomDrawLabel();
		this.reDrawBoard();
	}
	
	public void randomDrawLabel(){ //draw a new label to a random open cell (75%-2, 25%-4)
        if (this.isFull()) //don't draw if the board is full
        	return;
		JLabel label;
		int value;
		Color color;
		ImageIcon icon;
		int i=(int)(Math.random()*4);
		int j=(int)(Math.random()*4);
		while (array[i][j]!=0){ //loop to make sure the cell is empty
			i=(int)(Math.random()*4);
			j=(int)(Math.random()*4);
		}
		if (this.getGraphic()==1){ //case 1 draw numbers
			if ((int)(Math.random()*4)!=0){ //75% for value to get 2,
				value=2; 
				color=Color.BLUE;
			}
			else{ // 25% to get 4
				value=4; 
				color=Color.GREEN;
			}
			array[i][j]=value;
			label=labels[i][j];
			label.setBackground(color); //set color
			label.setText(""+value);
		}
		else if (this.getGraphic()==2){ //case 2 draw flags with numbers
			if ((int)(Math.random()*4)!=0){ //75% for value to get 2,
				value=2; 
				icon=new ImageIcon("Images//england2.gif");
			}
			else{ // 25% to get 4
				value=4; 
				icon=new ImageIcon("Images//france4.gif");
			}
			array[i][j]=value;
			label=labels[i][j];
			label.setIcon(icon); //set color
			label.setText("");
		}
		else if (this.getGraphic()==3){ //case 3 draw flags
			if ((int)(Math.random()*4)!=0){ //75% for value to get 2,
				value=2; 
				icon=new ImageIcon("Images//england.gif");
			}
			else{ // 25% to get 4
				value=4; 
				icon=new ImageIcon("Images//france.gif");
			}
			array[i][j]=value;
			label=labels[i][j];
			label.setIcon(icon); //set color
			label.setText("");
		}
	}
	
	public void reDrawBoard(){ //this function "draws" the board according to its current int array values and the current graphic value
		JLabel label;
		int value;
		Color color;
		for (int i=0; i<4; i=i+1){
			for (int j=0; j<4; j=j+1){
				color=Color.WHITE;;
				value=array[i][j];
				label=labels[i][j];
				if (this.getGraphic()==1){ //case 1 - numbers
					label.setIcon(null);
					if (value==2) color=Color.BLUE;
					else if (value==4) color=Color.GREEN;
					else if (value==8) color=Color.YELLOW;
					else if (value==16) color=Color.CYAN;
					else if (value==32) color=Color.RED;
					else if (value==64) color=Color.PINK;
					else if (value==128) color=Color.ORANGE;
					else if (value==256) color=new Color(122, 50, 200); //PURPLE
					else if (value==512) color=Color.MAGENTA;
					else if (value==1024) color=new Color(200, 120, 50); //BROWN
					else if (value==2048){
						color=new Color(20, 155, 55); //DARK_GREEN
						if (!won)
							JOptionPane.showMessageDialog(null, "CONGRATULATIONS! You Won the CUP! press OK to continue..");
						won=true;
					}
					if (value<10) label.setFont(new Font("David", Font.BOLD, 50)); //set the text: font+size
					else if (value<100) label.setFont(new Font("David", Font.BOLD, 40)); //set the text: font+size
					else if (value<1000) label.setFont(new Font("David", Font.BOLD, 35)); //set the text: font+size
					else if (value<10000) label.setFont(new Font("David", Font.BOLD, 28)); //set the text: font+size
					else label.setFont(new Font("David", Font.BOLD, 25)); //set the text: font+size
					label.setBackground(color); //set background white
					label.setText(""+value);
					if (value==0) label.setText(""); //case value 0 - don't show as text
				}
				else if (this.getGraphic()==2){ // case 2 flags with numbers
					if (value==2){
						ImageIcon icon=new ImageIcon("Images//england2.gif");
						label.setIcon(icon);
					}
					else if (value==4){
						ImageIcon icon=new ImageIcon("Images//france4.gif");
						label.setIcon(icon);
					}
					else if (value==8){
						ImageIcon icon=new ImageIcon("Images//uruguay8.gif");
						label.setIcon(icon);
					}
					else if (value==16){
						ImageIcon icon=new ImageIcon("Images//italy16.gif");
						label.setIcon(icon);
					}
					else if (value==32){
						ImageIcon icon=new ImageIcon("Images//argentina32.gif");
						label.setIcon(icon);
					}
					else if (value==64){
						ImageIcon icon=new ImageIcon("Images//portugal64.gif");
						label.setIcon(icon);
					}
					else if (value==128){
						ImageIcon icon=new ImageIcon("Images//holland128.gif");
						label.setIcon(icon);
					}
					else if (value==256){
						ImageIcon icon=new ImageIcon("Images//spain256.gif");
						label.setIcon(icon);
					}
					else if (value==512){
						ImageIcon icon=new ImageIcon("Images//germany512.gif");
						label.setIcon(icon);
					}
					else if (value==1024){
						ImageIcon icon=new ImageIcon("Images//brasil1024.gif");
						label.setIcon(icon);
					}
					else if (value==2048){
						ImageIcon icon=new ImageIcon("Images//cup2048.gif");
						label.setIcon(icon);
						if (!won)
							JOptionPane.showMessageDialog(null, "CONGRATULATIONS! You Won the CUP! press OK to continue..");
						won=true; 
					}
					else if (value>2048){ //after 2048 the graphical version will proceed with numbers :(
						label.setFont(new Font("David", Font.BOLD, 25)); //set the text: font+size
						label.setText(""+value);
					}
					label.setBackground(color); //set background white
					if (!(value>2048)) label.setText(""); //in the range 0-2048 don't ad text (just icon)
					if (value==0) label.setIcon(null); //case 0 - blank label
				}
				else if (this.getGraphic()==3){ // case 3 flags
					if (value==2){
						ImageIcon icon=new ImageIcon("Images//england.gif");
						label.setIcon(icon);
					}
					else if (value==4){
						ImageIcon icon=new ImageIcon("Images//france.gif");
						label.setIcon(icon);
					}
					else if (value==8){
						ImageIcon icon=new ImageIcon("Images//uruguay.gif");
						label.setIcon(icon);
					}
					else if (value==16){
						ImageIcon icon=new ImageIcon("Images//italy.gif");
						label.setIcon(icon);
					}
					else if (value==32){
						ImageIcon icon=new ImageIcon("Images//argentina.gif");
						label.setIcon(icon);
					}
					else if (value==64){
						ImageIcon icon=new ImageIcon("Images//portugal.gif");
						label.setIcon(icon);
					}
					else if (value==128){
						ImageIcon icon=new ImageIcon("Images//holland.gif");
						label.setIcon(icon);
					}
					else if (value==256){
						ImageIcon icon=new ImageIcon("Images//spain.gif");
						label.setIcon(icon);
					}
					else if (value==512){
						ImageIcon icon=new ImageIcon("Images//germany.gif");
						label.setIcon(icon);
					}
					else if (value==1024){
						ImageIcon icon=new ImageIcon("Images//brasil.gif");
						label.setIcon(icon);
					}
					else if (value==2048){
						ImageIcon icon=new ImageIcon("Images//cup.gif");
						label.setIcon(icon);
						if (!won)
							JOptionPane.showMessageDialog(null, "CONGRATULATIONS! You Won the CUP! press OK to continue..");
						won=true; 
					}
					else if (value>2048){ //after 2048 the graphical version will proceed with numbers :(
						label.setFont(new Font("David", Font.BOLD, 25)); //set the text: font+size
						label.setText(""+value);
					}
					label.setBackground(color); //set background white
					if (!(value>2048)) label.setText(""); //in the range 0-2048 don't ad text (just icon)
					if (value==0) label.setIcon(null); //case 0 - blank label
				}	
			}
		}
	}
	
	public boolean isFull(){ //returns boolean if the board is full after the random drop
		for (int i=0; i<4; i=i+1){
			for (int j=0; j<4; j=j+1){
				if (this.array[i][j]==0)
					return false;
			}
		}
		return true;
	}
	
	public boolean Lost(){ //returns boolean if there are no more possible moves (goes to this method only if the board is full)
		for (int i=0; i<4; i=i+1){
			for (int j=0; j<4; j=j+1){
				if (i-1>=0 && array[i-1][j]==array[i][j]) //case up
					return false;
				if (i+1<4 && array[i+1][j]==array[i][j]) //case down
					return false;
				if (j-1>=0 && array[i][j-1]==array[i][j]) //case left
					return false;
				if (j+1<4 && array[i][j+1]==array[i][j]) //case right
					return false;
			}
		}
		return true;
	}
}
