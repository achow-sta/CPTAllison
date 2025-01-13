import arc.*;
import java.awt.*;
import java.awt.image.*;

public class CPTAllisonTools{
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
			
			con.println();
			con.println("Perfect Squares (Advanced) has been added to your test options!");
			con.sleep(700);
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
			strChosenTest = "Binary Math";
			
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			return chrKeyIn;
			
		}else if(chrChosen == 'Q' || chrChosen == 'q'){
			strChosenTest = "Quadratic";
			
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			return chrKeyIn;
			
		}else if(strName.equalsIgnoreCase("statitan") && strChosenTest.equalsIgnoreCase("Perfect Squares (Advanced)")){
			strChosenTest = "Perfect Squares (Advanced)";
			
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			return chrKeyIn;
			
		}else if(chrChosen == 'P' || chrChosen == 'p'){
			strChosenTest = "Perfect Squares";  // ensure that this is the name being entered into method when inputing varable to method
			
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
			
			return chrKeyIn;
			
		}else{
			System.out.println("invalid keyboard input");
			return 'm';
		}
						
		
	}
	
	
	
	
	public static String[][] questionload(String strChosenTest){
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
		
		for(intRow2 = 0; intRow2 < intQuestNum; intRow2++){
			
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
		//for(intRow = 0; intRow < intQuestNum; intRow++){
			//System.out.print("Q" + intRow + ": ");
			//System.out.println(strQuestions[intRow][0] + " | " + strQuestions[intRow][1] + " | " + strQuestions[intRow][2] + " | " + strQuestions[intRow][3] + " |rand " + strQuestions[intRow][4]);
		//}
		
		return strQuestions;
	}
	
	
	
	
	
	public static char asking(String[][] strQuestions, String strChosenTest, Console con, String strName){
		int intNumQuest;
		int intRow;
		double dblPercent = 0;
		int intAnsCount = 0;
		String strAnswer;
		int intCorrect = 0;
		int intTries = 3;
		
		//get number of questions
		intNumQuest = CPTAllisonTools.questnum(strChosenTest);
		
		//print questions one by one
		for(intRow = 0; intRow < intNumQuest; intRow++){
			
			//print title bar 
			con.println("                              " + strName + "  |  " + strChosenTest + "  |  " + intCorrect + "/" + intRow + "   " + dblPercent + "%");
		
			//print questions one by one
			con.println("Q" + (intRow + 1) + ": " + strQuestions[intRow][0]);
			strAnswer = con.readLine();
			
			//correct scenario
			if(strAnswer.equalsIgnoreCase(strQuestions[intRow][1]) || strAnswer.equalsIgnoreCase(strQuestions[intRow][2]) || strAnswer.equalsIgnoreCase(strQuestions[intRow][3])){
				intCorrect = intCorrect + 1;
				//con.println("Correct!");
				//con.sleep(500);
			}else{
				//incorrect scenario, try again
				while(!strAnswer.equalsIgnoreCase(strQuestions[intRow][1]) && !strAnswer.equalsIgnoreCase(strQuestions[intRow][2]) && !strAnswer.equalsIgnoreCase(strQuestions[intRow][3])){
					intTries = intTries - 1;
					
					if(intTries > 0){
						con.println();
						con.println("Incorrect, try again... Tries Remaining: " + intTries);
						con.println();
						
						strAnswer = con.readLine();
						
					}else{
						con.println();
						con.println();
						con.println("Incorrect, answer was: " + strQuestions[intRow][1] + "   OR   " + strQuestions[intRow][2] + "   OR   " + strQuestions[intRow][3]);
						con.sleep(2000);
						
						strAnswer = strQuestions[intRow][1];
										
					}
										
				}
				
			}
			
			if(intTries > 0){
				con.println("Correct!");
				con.sleep(500);
			}	
			
			//calculate percent
			dblPercent = Math.round(( (intCorrect * 1.0) / ((intRow + 1)* 1.0) ) * 10000.0)/100.0;
			//con.println(dblPercent);
			
			con.clear();
			intTries = 3;
			
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
		con.println("You got " + intCorrect + "/" + intNumQuest + "  " + dblPercent + "%");
		
		//update score to highscores file
		TextOutputFile highscore = new TextOutputFile("highscores.txt", true);
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
		
		//remove advanced pfsq file from test.txt (when needed)
		if(strChosenTest.equals("Perfect Squares (Advanced)")){
			TextOutputFile remove = new TextOutputFile("test.txt");
			remove.println("Binary Math");
			remove.println("Perfect Squares");
			remove.println("Quadratic");
			remove.close();
		}
		
		//return to main menu
		con.println("Press (m) to return to Main Menu ");
		
		char chrKeyIn;
		chrKeyIn = con.getChar();
		
		
		while(chrKeyIn != 'm' || chrKeyIn != 'M'){
			System.out.println("invalid keyboard input");
			chrKeyIn = con.getChar();
		}
		if(chrKeyIn == 'H' || chrKeyIn == 'h'){
			return chrKeyIn;
		}
		
		return chrKeyIn;
	}
	
	public static void home(Console con){
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
	}
	
}
