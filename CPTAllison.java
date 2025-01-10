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
		
		while(chrKeyIn != 'q' || chrKeyIn != 'Q'){
			if(chrKeyIn == 'p' || chrKeyIn == 'P'){
				con.clear();
				con.setBackgroundColor(Color.BLACK);
				chrKeyIn = playgame(con);
			}
		}
		
		
	}
	public static char playgame(Console con){
		
		//ask for username
		String strName;
		
		con.print("Enter your Name: ");
		strName = con.readLine();
		
		//statitans username advantage
		if(strName.equalsIgnoreCase("statitan")){
			TextOutputFile testfile = new TextOutputFile("test.txt", true);
			testfile.println("Perfect Squares (Advanced)");			
			testfile.close(); //FIND WAY TO REMOVE ADVANCED PERFECT SQUARES AFTER USER COMPLETE TEST 
			
			con.sleep(700);
			con.println("Perfect Squares (Advanced) has been added to your test options!");
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
		
		con.clear();
		
		char chrKeyIn;
		//Open Respective Test Files
		String strQuestions[][];
		
		if(chrChosen == 'B' || chrChosen == 'b'){
			TextInputFile binaryfile = new TextInputFile("Binary Math.txt");
			strChosenTest = "Binary Math";
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			binaryfile.close();
			return chrKeyIn;
			
		}else if(chrChosen == 'P' || chrChosen == 'p'){
			TextInputFile pfsqfile = new TextInputFile("Perfect Squares.txt");
			strChosenTest = "Perfect Squares";  // ensure that this is the name being entered into method when inputing varable to method
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			pfsqfile.close();
			return chrKeyIn;
			
		}else if(chrChosen == 'Q' || chrChosen == 'q'){
			TextInputFile quadfile = new TextInputFile("Quadratic.txt");
			strChosenTest = "Quadratic";
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			quadfile.close();			
			return chrKeyIn;
			
		}else if(strName.equalsIgnoreCase("statitan") && strChosenTest.equalsIgnoreCase("Perfect Squares (Advanced)")){
			TextInputFile advpfsq = new TextInputFile("Perfect Squares (Advanced)");
			strChosenTest = "Perfect Squares (Advanced)";
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			advpfsq.close();
			return chrKeyIn;
		}else{
			System.out.println("invalid keyboard input");
			strChosenTest = con.readLine();
			chrChosen = strChosenTest.charAt(0);
		}
				
		return 'm';
	}
	
public static String[][] questionload(String strChosenTest){
	//String strQuestion;
	//String strAns1;
	//String strAns2;
	//String strAns3;
	int intRandom;
	int intRow;
	int intNumQuest = 0;  
	
	//Count how many questions
	TextInputFile test = new TextInputFile(strChosenTest + ".txt");
	intNumQuest = questnum(strChosenTest);
	
	//Create Array
	String strLoadQuest[][];
	strLoadQuest = new String[intNumQuest][5];
	
	//Load everything into array first
	test = new TextInputFile(strChosenTest + ".txt"); //<-- why don't I have to put in a while loop?
	while(test.eof() == false){
		for(intRow = 0; intRow <= intNumQuest - 1; intRow++){
			strLoadQuest[intRow][0] = test.readLine();
			strLoadQuest[intRow][1] = test.readLine();
			strLoadQuest[intRow][2] = test.readLine();
			strLoadQuest[intRow][3] = test.readLine();
			strLoadQuest[intRow][4] = randnum();
			//System.out.println(strLoadQuest[intRow][0] + "| " + strLoadQuest[intRow][1] + "| " + strLoadQuest[intRow][2] + "| "  + strLoadQuest[intRow][3] + "| " + strLoadQuest[intRow][4]);
		}	
	}
	test.close();
	
	return strLoadQuest;
	}
	
	public static String randnum(){
		int intRand;
		String strRand;
		
		intRand = (int)(Math.random() * 100 + 1);
		strRand = Integer.toString(intRand);
		
		return strRand;
	}
	public static int questnum(String strChosenTest){
		//Count how many questions
		String strQuestion;
		String strAns1;
		String strAns2;
		String strAns3;
		int intNumQuest = 0;  
		
		TextInputFile test = new TextInputFile(strChosenTest + ".txt");
		
		while(test.eof() == false){
			strQuestion = test.readLine();
			intNumQuest = intNumQuest + 1;      // <-- number of questions counter
			strAns1 = test.readLine();
			strAns2 = test.readLine();
			strAns3 = test.readLine();
		}
		test.close();
		//System.out.println(intNumQuest);
		
		return intNumQuest;
	}
	
	public static String[][] sort(String strQuestions[][], String strChosenTest){
		//Bubblesort randomizer column
		String strTempQuestion;
		String strTempA1;
		String strTempA2;
		String strTempA3;
		String strTempRand;
		int intRow;
		int intRow2;
		int intQuestNum;
		
		intQuestNum = questnum(strChosenTest);
		
		for(intRow2 = 0; intRow2 < intQuestNum - 1; intRow2++){
			
			for(intRow = 0; intRow < intQuestNum - 1 - intRow2; intRow++){
				if(Integer.parseInt(strQuestions[intRow][4]) > Integer.parseInt(strQuestions[intRow + 1][4])){
					//move row top row or row above into temp variable
					strTempQuestion = strQuestions[intRow][0];
					strTempA1 = strQuestions[intRow][1];
					strTempA2 = strQuestions[intRow][2];
					strTempA3 = strQuestions[intRow][3];
					strTempRand = strQuestions[intRow][4];
					
					//move row below into row above
					strQuestions[intRow][0] = strQuestions[intRow + 1][0];
					strQuestions[intRow][1] = strQuestions[intRow + 1][1];
					strQuestions[intRow][2] = strQuestions[intRow + 1][2];
					strQuestions[intRow][3] = strQuestions[intRow + 1][3];
					strQuestions[intRow][4] = strQuestions[intRow + 1][4];
					
					//move top row (data is in temp variable) down to bottom row
					strQuestions[intRow + 1][0] = strTempQuestion;
					strQuestions[intRow + 1][1] = strTempA1;
					strQuestions[intRow + 1][2] = strTempA2;
					strQuestions[intRow + 1][3] = strTempA3;
					strQuestions[intRow + 1][4] = strTempRand;
					
				}
			}
		}
		
		//system.out.println sorted version
		for(intRow = 0; intRow < intQuestNum - 1; intRow++){
			System.out.println(strQuestions[intRow][0] + " | " + strQuestions[intRow][1] + " | " + strQuestions[intRow][2] + " | " + strQuestions[intRow][3] + " |rand " + strQuestions[intRow][4]);
		}
		
		return strQuestions;
	}
	
	public static char asking(String[][] strQuestions, String strChosenTest, Console con, String strName){
		int intNumQuest;
		int intCount;
		double dblPercent = 0;
		int intAnsCount = 0;
		String strAnswer;
		int intCorrect = 0;
		
		//get number of questions
		intNumQuest = questnum(strChosenTest);
		
		//print questions one by one
		for(intCount = 0; intCount < intNumQuest - 1; intCount++){
			
			//print title bar 
			con.println("                              " + strName + "  |  " + strChosenTest + "  |  " + intCorrect + "/" + intNumQuest + "   " + dblPercent + "%");
		
			//print questions one by one
			con.println(strQuestions[intCount][0]);
			strAnswer = con.readLine();
			
			while(!strAnswer.equalsIgnoreCase(strQuestions[intCount][1]) && !strAnswer.equalsIgnoreCase(strQuestions[intCount][2]) && !strAnswer.equalsIgnoreCase(strQuestions[intCount][3])){
				con.println();
				con.println("Incorrect, try again...");
				con.println();
				intAnsCount = intAnsCount + 1;
				
				//ask question again
				con.println(strQuestions[intCount][0] + " ");
				strAnswer = con.readLine();
				
			}
			
			if(strAnswer.equalsIgnoreCase(strQuestions[intCount][1]) || strAnswer.equalsIgnoreCase(strQuestions[intCount][2]) || strAnswer.equalsIgnoreCase(strQuestions[intCount][3])){
				con.println("Correct!");
				con.sleep(500);
				
				
				//only add 1 to correct answers if took one time to correctly answer
				if(intAnsCount == 0){
					dblPercent = intCount + 1;
					dblPercent = Math.round(dblPercent /intNumQuest * 10000)/100;
					intCorrect = intCorrect + 1;
				}
				
				intAnsCount = 0;
				con.clear();
			}
		}
		
		//print test complete screen
		con.println(strChosenTest + " Complete!");
		con.println();
		
		if(dblPercent >= 80){
			con.println("Well Done " + strName + "!");
		}else{
			con.println("Keep Practicing " + strName + "!");
		}
		
		con.println();
		con.println("You got " + dblPercent + "%");
		
		//update score to highscores file
		TextOutputFile highscore = new TextOutputFile("highscores.txt");
		highscore.println(strName);
		highscore.println(strChosenTest);
		highscore.println(dblPercent);
		
		con.println();
		con.println("Score Updated to Highscores Leaderboard!");
		
		
		//enter empty lines (for formating)
		int intEnterCount;
		for(intEnterCount = 0; intEnterCount <= 4; intEnterCount++){
			con.println();
		}
		
		//return to main menu
		con.println("Press (m) to return to Main Menu ");
		
		char chrKeyIn;
		chrKeyIn = con.readChar();
		
		
		while(chrKeyIn != 'm' || chrKeyIn != 'M'){
			System.out.println("invalid keyboard input");
			chrKeyIn = con.readChar();
		}
		if(chrKeyIn == 'H' || chrKeyIn == 'h'){
			return chrKeyIn;
		}
		
		return chrKeyIn;
		
		
	}
	
}
