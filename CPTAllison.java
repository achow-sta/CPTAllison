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
		String strKeyIn = "";
		char chrKeyIn;
		
		con.print("                                        Enter a Key Letter to Begin: ");
		strKeyIn = con.readLine(); 
		chrKeyIn = strKeyIn.charAt(0);
		
		//con.println(strKeyIn);
		//con.println(chrKeyIn);
		
		
		if(chrKeyIn == 'p' || chrKeyIn == 'P'){
			con.clear();
			con.setBackgroundColor(Color.BLACK);
			playgame(con);
		}
		
		
	}
	public static void playgame(Console con){
		
		//ask for username
		String strName;
		
		con.print("Enter your Name: ");
		strName = con.readLine();
		
		//statitans username advantage
		if(strName.equalsIgnoreCase("statitan")){
			TextOutputFile testfile = new TextOutputFile("test.txt", true);
			testfile.println("Perfect Squares (Advanced)");			
			testfile.close(); //FIND WAY TO REMOVE ADVANCED PERFECT SQUARES AFTER USER COMPLETE TEST 
		}
		
		con.clear();
		
		//Entering Empty Lines (Formatting)
		int intEnterCount;
		for(intEnterCount = 0; intEnterCount <= 4; intEnterCount++){
			con.println();
		}
		
		con.println(" Welcome " + strName + "!");
		for(intEnterCount = 0; intEnterCount <= 4; intEnterCount++){
			con.println();
		}
		con.println("                                        Choose a test to complete: ");
		con.println();
		
		//Open test.txt file to read
		String strTest;
		TextInputFile testnames = new TextInputFile("test.txt");
		
		while(testnames.eof()==false){
			strTest = testnames.readLine();
			con.println("                                                 " + strTest);
			con.println();
			con.println();
		}
		testnames.close();
		
		//Ask user to choose test
		String strChosenTest;
		char chrChosen;
		
		con.print("Which test would you like to complete?    ");
		strChosenTest = con.readLine();
		chrChosen = strChosenTest.charAt(0);
		
		
		//Open Respective Test Files
		if(chrChosen == 'B' || chrChosen == 'b'){
			TextInputFile binaryfile = new TextInputFile("Binary Math.txt");
			//MAKE METHOD TO LOAD QUESTION
			
			
			
			binaryfile.close();
		}else if(chrChosen == 'P' || chrChosen == 'p'){
			TextInputFile pfsqfile = new TextInputFile("Perfect Squares.txt");
			//METHOD TO LOAD QUESTION
			pfsqfile.close();
		}else if(chrChosen == 'Q' || chrChosen == 'q'){
			TextInputFile quadfile = new TextInputFile("Quadratic.txt");
			//METHOD LOAD QUESITON
			quadfile.close();			
		}else{
			System.out.println("invalid keyboard input");
		}
				
		
	}
	
public static void question(String strQuestion[][]){
	
}
}
