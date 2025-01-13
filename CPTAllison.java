import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTAllison{
	public static void main(String[] args){
		Console con = new Console("AC Math Training Program", 1280, 720);
		BufferedImage imghello = con.loadImage("wavingpika.gif");
		
		//Loop for entering empty lines
		int intEnterCount;
		for(intEnterCount = 0; intEnterCount <= 7; intEnterCount++){
			con.println();
		}
		
		
		con.println("                                    Welcome to the Math Training Game!");
		
		//More Empty Enter Lines
		for(intEnterCount = 0; intEnterCount <= 7; intEnterCount++){
			con.println();
		}
		
		//Load Image
		con.drawImage(imghello, 100, 100);
		
		con.println("                                                   Play");
		con.println();
		con.println("                                                Highscores");
		con.println();
		con.println("                                                   Help");
		con.println();
		con.println("                                                   Quit");
		
		con.println();
		
		//Ask for what user wants to do:
		char chrKeyIn;
		
		con.print("                                        Enter a Key Letter to Begin: ");
		//strKeyIn = con.readLine(); 
		chrKeyIn = con.getChar();
		
		//con.println(strKeyIn);
		//con.println(chrKeyIn);
		
		while(chrKeyIn != 'q' || chrKeyIn != 'Q'){
			if(chrKeyIn == 'p' || chrKeyIn == 'P'){  //playgame
				con.clear();
				con.setBackgroundColor(Color.BLACK);
				chrKeyIn = CPTAllisonTools.playgame(con);
				
				con.clear();
				
			}else if(chrKeyIn == 'v' || chrKeyIn == 'V'){ //highscore
				con.clear();
				con.setBackgroundColor(Color.BLACK);
				chrKeyIn = highscore(con);
				con.clear();
			}else if(chrKeyIn == 'h' || chrKeyIn == 'H'){ //help
				
			}else if(chrKeyIn == 's' || chrKeyIn == 'S'){ //secret menu
				
			}else if(chrKeyIn == 'a' || chrKeyIn == 'A'){ //add quiz function (NEED TO ASK)
			
			}else{
				System.out.println("invalid keyboard input");
			}
			chrKeyIn = con.getChar();
		}
		
		
	}
	public static char highscore(Console con){
		// Create array for bubble sort
		String strLeader[][];
		
		// Open highscore.txt file and count how many players
		TextInputFile scores = new TextInputFile("highscores.txt");
		
		String strName;
		String strTest;
		int intScore;
		int intPlayerNum = 0;
		
		while(scores.eof() == false){
			strName = scores.readLine();
			strTest = scores.readLine();
			intScore = scores.readInt();
			
			intPlayerNum = intPlayerNum + 1;
		}
		
		scores.close();
		
		// Load data into array
		int intRow;
		strLeader = new String[intPlayerNum][3];
		scores = new TextInputFile("highscores.txt");
		
		for(intRow = 0; intRow < intPlayerNum; intRow++){
			strLeader[intRow][0] = scores.readLine();
			strLeader[intRow][1] = scores.readLine();
			strLeader[intRow][2] = scores.readLine();
			
			//System.out.println(strLeader[intRow][0] + " | " + strLeader[intRow][1] + " | " + strLeader[intRow][2]);
		}
		scores.close();
		
		// Bubblesort
		String strTempName;
		String strTempTest;
		String strTempScore;
		int intRow2;
		
		for(intRow2 = 0; intRow2 < intPlayerNum; intRow2++){
			for(intRow = 0; intRow < intPlayerNum - 1 - intRow2; intRow++){
				if(Integer.parseInt(strLeader[intRow][2]) < Integer.parseInt(strLeader[intRow + 1][2])){
					//shift to temporary
					strTempName = strLeader[intRow][0];
					strTempTest = strLeader[intRow][1];
					strTempScore = strLeader[intRow][2];
					
					//move up
					strLeader[intRow][0] = strLeader[intRow + 1][0];
					strLeader[intRow][1] = strLeader[intRow + 1][1];
					strLeader[intRow][2] = strLeader[intRow + 1][2];
					
					//move temporary to row below
					strLeader[intRow + 1][0] = strTempName;
					strLeader[intRow + 1][1] = strTempTest;
					strLeader[intRow + 1][2] = strTempScore;
				}
			}
		}
		
		// Print sorted version
		con.println("       Name       |       Test Completed        |       Score       ");
		con.println("--------------------------------------------------------------------");
		
		for(intRow = 0; intRow < intPlayerNum; intRow++){
			con.println("       " + strLeader[intRow][0] + "      |     " + strLeader[intRow][1] + "       |     " + strLeader[intRow][2]);
		}
		
		//return to main menu
		char chrKeyIn;
				
		con.println();
		con.println();
		con.println();
		con.println();
		con.println();
		con.println("Press 'm' to return to main menu");
		chrKeyIn = con.getChar();
		
		
		while(chrKeyIn != 'm' || chrKeyIn != 'M'){
			if(chrKeyIn == 'h' || chrKeyIn == 'H'){
				//HELPSCREEN METHOD WHEN DONE
			}else{
			System.out.println("invalid keyboard input");
			}
			chrKeyIn = con.getChar();
		}
		
		return chrKeyIn;
	}
}
