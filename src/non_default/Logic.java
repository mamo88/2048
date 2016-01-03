package non_default;

import java.io.*;

import javax.swing.JOptionPane;


public class Logic {
	
	private Game game;
	private boolean boardChanged;
	
	public Game getGame(){ //getter
		return this.game;
	}	
	public boolean getChanged(){ //getter
		return this.boardChanged;
	}
	public void setChanged(boolean b){ //setter
		this.boardChanged=b;
	}
	
	public Logic(Game game){ //constructor
		this.game=game; 
		this.boardChanged=false;
	}	
	
	public void actionLeft(){
		for (int i=0; i<4; i=i+1){ //unite the relevant equal labels in the order from left to right
			for (int j=0; j<4; j=j+1){
				int tmp=j+1;
				while (tmp<4 && this.game.getBoard().getArray()[i][tmp]==0)
					tmp=tmp+1;
				if (tmp<4 && this.game.getBoard().getArray()[i][j]==this.game.getBoard().getArray()[i][tmp]){
					this.game.getBoard().getArray()[i][j]=(this.game.getBoard().getArray()[i][j])+(this.game.getBoard().getArray()[i][tmp]);
					this.game.getBoard().getArray()[i][tmp]=0;
					this.game.setScoreInt(this.game.getScoreInt()+this.game.getBoard().getArray()[i][j]);
					this.boardChanged=true;
				}
			}
		}
		for (int i=0; i<4; i=i+1){ //adjust the labels to the left
			for (int j=0; j<4; j=j+1){
				int tmp=j;
				while (tmp<4 && this.game.getBoard().getArray()[i][tmp]==0)
					tmp=tmp+1;
				if (tmp<4 && tmp!=j){
					this.game.getBoard().getArray()[i][j]=this.game.getBoard().getArray()[i][tmp];
					this.game.getBoard().getArray()[i][tmp]=0;
					this.boardChanged=true;
				}
			}
		}
		this.game.getBoard().reDrawBoard();
	}
	
	public void actionRight(){
		for (int i=0; i<4; i=i+1){ //unite the relevant equal labels in the order from right to left
			for (int j=3; j>=0; j=j-1){
				int tmp=j-1;
				while (tmp>=0 && this.game.getBoard().getArray()[i][tmp]==0)
					tmp=tmp-1;
				if (tmp>=0 && this.game.getBoard().getArray()[i][j]==this.game.getBoard().getArray()[i][tmp]){
					this.game.getBoard().getArray()[i][j]=(this.game.getBoard().getArray()[i][j])+(this.game.getBoard().getArray()[i][tmp]);
					this.game.getBoard().getArray()[i][tmp]=0;
					this.game.setScoreInt(this.game.getScoreInt()+this.game.getBoard().getArray()[i][j]);
					this.boardChanged=true;
				}
			}
		}
		for (int i=0; i<4; i=i+1){ //adjust the labels to the right
			for (int j=3; j>=0; j=j-1){
				int tmp=j;
				while (tmp>=0 && this.game.getBoard().getArray()[i][tmp]==0)
					tmp=tmp-1;
				if (tmp>=0 && tmp!=j){
					this.game.getBoard().getArray()[i][j]=this.game.getBoard().getArray()[i][tmp];
					this.game.getBoard().getArray()[i][tmp]=0;
					this.boardChanged=true;
				}
			}
		}
		this.game.getBoard().reDrawBoard();
	}

	public void actionUp(){
		for (int j=0; j<4; j=j+1){ //unite the relevant equal labels in the order from top to bottom
			for (int i=0; i<4; i=i+1){
				int tmp=i+1;
				while (tmp<4 && this.game.getBoard().getArray()[tmp][j]==0)
					tmp=tmp+1;
				if (tmp<4 && this.game.getBoard().getArray()[i][j]==this.game.getBoard().getArray()[tmp][j]){
					this.game.getBoard().getArray()[i][j]=(this.game.getBoard().getArray()[i][j])+(this.game.getBoard().getArray()[tmp][j]);
					this.game.getBoard().getArray()[tmp][j]=0;
					this.game.setScoreInt(this.game.getScoreInt()+this.game.getBoard().getArray()[i][j]);
					this.boardChanged=true;
				}
			}
		}
		for (int j=0; j<4; j=j+1){ //adjust the labels to the top
			for (int i=0; i<4; i=i+1){
				int tmp=i;
				while (tmp<4 && this.game.getBoard().getArray()[tmp][j]==0)
					tmp=tmp+1;
				if (tmp<4 && tmp!=i){
					this.game.getBoard().getArray()[i][j]=this.game.getBoard().getArray()[tmp][j];
					this.game.getBoard().getArray()[tmp][j]=0;
					this.boardChanged=true;
				}
			}
		}
		this.game.getBoard().reDrawBoard();
	}
	
	public void actionDown(){
		for (int j=0; j<4; j=j+1){ //unite the relevant equal labels in the order from the bottom up
			for (int i=3; i>=0; i=i-1){
				int tmp=i-1;
				while (tmp>=0 && this.game.getBoard().getArray()[tmp][j]==0)
					tmp=tmp-1;
				if (tmp>=0 && this.game.getBoard().getArray()[i][j]==this.game.getBoard().getArray()[tmp][j]){
					this.game.getBoard().getArray()[i][j]=(this.game.getBoard().getArray()[i][j])+(this.game.getBoard().getArray()[tmp][j]);
					this.game.getBoard().getArray()[tmp][j]=0;
					this.game.setScoreInt(this.game.getScoreInt()+this.game.getBoard().getArray()[i][j]);
					this.boardChanged=true;
				}
			}
		}
		for (int j=0; j<4; j=j+1){ //adjust the labels to the top
			for (int i=3; i>=0; i=i-1){
				int tmp=i;
				while (tmp>=0 && this.game.getBoard().getArray()[tmp][j]==0)
					tmp=tmp-1;
				if (tmp>=0 && tmp!=i){
					this.game.getBoard().getArray()[i][j]=this.game.getBoard().getArray()[tmp][j];
					this.game.getBoard().getArray()[tmp][j]=0;
					this.boardChanged=true;
				}
			}
		}
		this.game.getBoard().reDrawBoard();
	}
	
	public void endGame() throws IOException{
		JOptionPane.showMessageDialog(null, "You Lost :( press OK to try again"); //lose message
		if (this.game.getBest()[9][1]==null || this.game.getBest()[9][1]=="" || this.game.getScoreInt()>Integer.parseInt(this.game.getBest()[9][1])){ //new high score
			String name=JOptionPane.showInputDialog("Very well! you managed to enter the BestScores table, please enter your name:");
			if (name!=null && name.length()>0){ //name was entered
				this.game.getBest()[9][0]=name; //replace the new high score with last
				this.game.getBest()[9][1]=""+this.game.getScoreInt();
				sortByScore(this.game.getBest()); //insertion sort by score value
				Logic.writeToFile(this.game.getBest()); //rewrite the new best scores array to file
			}	
			else{ //name wasn't entered
				while (name==null || name.length()==0){
					name=JOptionPane.showInputDialog("You have entered an invalid name - please enter your name!");
					if (name!=null && name.length()>0){ //name was entered
						this.game.getBest()[9][0]=name; //replace the new high score with last
						this.game.getBest()[9][1]=""+this.game.getScoreInt();
						sortByScore(this.game.getBest()); //insertion sort by score value
						Logic.writeToFile(this.game.getBest()); //rewrite the new best scores array to file
					}	
				}
			}
		}
		this.game.getBoard().restartGame(); //draw a new board
		this.game.setScoreInt(0); //change score to 0
	}
	
	private static String[][] sortByScore(String[][] scores){ //insertion sort
		for (int i=0; i<10; i=i+1){
			if (scores[i][1]==null ||scores[i][1]=="")
				scores[i][1]="0";
		}
		for (int i=0; i<9; i=i+1){
			for (int j=i+1; j<10; j=j+1){
				if (Integer.parseInt(scores[i][1])<Integer.parseInt(scores[j][1])){
					String temp1 = scores[i][0];
					String temp2 = scores[i][1];
	                scores[i][0] = scores[j][0];
	                scores[i][1] = scores[j][1];
	                scores[j][0] = temp1;
	                scores[j][1] = temp2;  
				}
			}
		}
		for (int i=0; i<10; i=i+1){
			if (Integer.parseInt(scores[i][1])==0)
				scores[i][1]="";
		}
		return scores;
	}
	
	public static void writeToFile(String[][] scores) throws IOException{
		File file=new File("BestScores"); //create file (if not exists)
		PrintWriter pw = new PrintWriter("BestScores"); //clear the file
		pw.print(""); //""
		pw.close(); //""
		FileWriter writer=new FileWriter(file,true); //open writer
		for (int i=0; i<10; i=i+1){ //write the best scores array into the file
			writer.write(scores[i][0]+","+scores[i][1]); 
			writer.write("\n");
		}
		writer.close(); //close writer
	}
	public static void readFromFile(String[][] scores) throws IOException{
		try {
			FileInputStream fInput = new FileInputStream("BestScores");
			DataInputStream dInput=new DataInputStream(fInput); //""
			InputStreamReader iReader=new InputStreamReader(fInput); //""
			BufferedReader bReader = new BufferedReader(iReader); //""
			String string=bReader.readLine(); //set string to the first line in the file
			int i;
			for (i=0; string!=null && !(string.equals("null,null")) && !(string.equals(",")) && i<10; i=i+1){ //condition - line has "data"
				String[] splited=string.split(",");
				if (splited.length==2){
					scores[i][0]=splited[0]; //put the line from the file in the best scores array
					scores[i][1]=splited[1]; //""
				}
				string=bReader.readLine(); //read the next line
			}
			dInput.close(); //close stuff ???
			while (i<10){ //set the "no-data" lines to empty string (not null)
				scores[i][0]="";
				scores[i][1]="";
				i=i+1;
			}
		} 
		catch (FileNotFoundException e) {
			writeToFile(scores); //create file if doesn't exist yet	
		}
	}	
}
