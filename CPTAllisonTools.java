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
		int intTestNum = 0;
		TextInputFile testnames = new TextInputFile("test.txt");
		
		while(testnames.eof()== false){
			intTestNum = intTestNum + 1;
			strTest = testnames.readLine();
			con.println("                                               " + intTestNum + ". " + strTest);
			con.println();
			con.println();
			
		}
		//System.out.println(intTestNum);
		testnames.close();
		
		//load test names into a 1-d array
		String strTestNames[];
		strTestNames = new String[intTestNum];
		int intTestCount = 0;
		testnames = new TextInputFile("test.txt");
		
		
		while(testnames.eof() == false && intTestCount < intTestNum){
			strTestNames[intTestCount] = testnames.readLine();
			//System.out.println(intTestCount + ". " + strTestNames[intTestCount]);
			
			intTestCount = intTestCount + 1;
		}		
		testnames.close();
		
		
		
		//Ask user to choose test
		String strChosenTest;
		int intChosenTest;
		//char chrChosen;
		
		con.print("(Enter the number of the test would you like to complete:    ");
		intChosenTest = con.readInt();
		//chrChosen = strChosenTest.charAt(0);
		strChosenTest = strTestNames[intChosenTest - 1];
		
		con.clear();
		
		char chrKeyIn;
		//Open Respective Test Files
		String strQuestions[][];
		
		
		while(intChosenTest > intTestNum){
			System.out.println("invalid keyboard input");
			intChosenTest = con.readInt();
		}
		if(intChosenTest - 1 < intTestNum){
			strChosenTest = strTestNames[intChosenTest - 1];
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			//send to another method to sort randomizer
			strQuestions = sort(strQuestions, strChosenTest);
			
			//send to method to ask questions
			chrKeyIn = asking(strQuestions, strChosenTest, con, strName);
		}
		return 'm';
		
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
		String strQ;
		String strFileType;
		int intQLength;
		
		//get number of questions
		intNumQuest = questnum(strChosenTest);
		
		//print questions one by one
		for(intRow = 0; intRow < intNumQuest; intRow++){
			
			//print title bar 
			con.println("                              " + strName + "  |  " + strChosenTest + "  |  " + intCorrect + "/" + intRow + "   " + dblPercent + "%");
		
			//print questions one by one... if question has png/jpg/jpeg, draw the image instead
			strQ = strQuestions[intRow][0];
			intQLength = strQ.length();
			strFileType = strQ.substring(intQLength - 4, intQLength);
			//System.out.println(strFileType);
			
			if(strFileType.equals(".jpg") || strFileType.equals(".png") || strFileType.equals(".gif")){
				BufferedImage imgQuestion = con.loadImage(strQ);
				con.drawImage(imgQuestion, 700, 50);
				con.print("Answer: ");
			}else{
				con.println("Q" + (intRow + 1) + ": " + strQuestions[intRow][0]);
			}
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
			con.setBackgroundColor(Color.BLACK);
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
			TextOutputFile adding = new TextOutputFile("test.txt");
			adding.println("Binary Math");
			adding.println("Perfect Squares");
			adding.println("Quadratic");
			adding.println("Geometry");
			adding.close();
		}
		
		//return to main menu
		con.println("Press (m) to return to Main Menu ");
		
		char chrKeyIn;
		chrKeyIn = con.getChar();
		
		if(chrKeyIn == 'm' || chrKeyIn == 'M'){
			return chrKeyIn;
		}
		
		int intStop = 0;
		while(intStop == 0){
			if(chrKeyIn == 's' || chrKeyIn == 'S'){
				secret();
			}else if(chrKeyIn == 'm' || chrKeyIn == 'M'){
				intStop = 1;
			}else{
				System.out.println("invalid keyboard input");
			}
			chrKeyIn = con.getChar();
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
		for(intEnterCount = 0; intEnterCount <= 5; intEnterCount++){
			con.println();
		}
		
		//Load Image
		con.drawImage(imghello, 70, 80);
		
		con.println("                                                   Play");
		con.println();
		con.println("                                                Highscores");
		con.println();
		con.println("                                                   Help");
		con.println();
		con.println("                                                 Add Quiz");
		con.println();
		con.println("                                                   Quit");
		
		con.println();
		
		//Ask for what user wants to do:
		char chrKeyIn;
		
		con.print("                                        Enter a Key Letter to Begin: ");
		//strKeyIn = con.readLine(); 
		chrKeyIn = con.getChar();
	}
	
	public static void help(){
		Console con = new Console("AC Math Help Screen", 575, 800);
		
		con.println("Press:                                  ");
		con.println("	(p) to play                      ");
		con.println("                                         ");
		con.println("	(v) to view highscores           ");
		con.println("	                                 ");
		con.println("	(h) to open help menu            ");
		con.println("	                                 ");
		con.println("	(a) to add quiz                  ");
		con.println("	                                 ");
		con.println("	(m) to return to main menu       ");
		con.println("	                                 ");
		con.println("	(q) to quit                      ");
		con.println();
		con.println();
		con.println();
		con.println("Notes on Game:");
		con.println("	* you may answer the binary math test ");
		con.println("	  in BIN, HEX, or DEC");
		con.println("   * DO NOT put units in the geometry test"); 
		con.println("     answers");
		
	}
	
	public static void secret(){
		Console con = new Console("shh... it's a secret menu", 500, 420);
		
		con.println("!false");
		con.sleep(900);
		con.println("It’s funny because it’s true.");
		
		int intEnterCount;
		for(intEnterCount = 0; intEnterCount <= 4; intEnterCount++){
			con.println();
		}
				
		//to close window without closing game
		char chrKeyIn;
		con.println("Press 'c' to close secret menu");
		chrKeyIn = con.getChar();
		
		if(chrKeyIn == 'c'){
			con.closeWindow();
		}
		
	}
	
	
	public static char highscore(Console con){
		// Create array for bubble sort
		String strLeader[][];
		
		// Open highscore.txt file and count how many players
		TextInputFile scores = new TextInputFile("highscores.txt");
		
		String strName;
		String strTest;
		double dblScore;
		int intPlayerNum = 0;
		
		while(scores.eof() == false){
			strName = scores.readLine();
			strTest = scores.readLine();
			dblScore = scores.readDouble();
			
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
				if(Double.parseDouble(strLeader[intRow][2]) < Double.parseDouble(strLeader[intRow + 1][2])){
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
		
		if(chrKeyIn == 'm' || chrKeyIn == 'M'){
			return chrKeyIn;
		}
		
		while(chrKeyIn != 'm' || chrKeyIn != 'M'){
			if(chrKeyIn == 'h' || chrKeyIn == 'H'){
				help();
			}else if(chrKeyIn != 'm' || chrKeyIn != 'M'){
				System.out.println("invalid keyboard input");
			}
			chrKeyIn = con.getChar();
		}
		
		return chrKeyIn;			
		
	}
	
	public static char addquiz(Console con){
		char chrKeyIn;
		
		String strFileName;
		con.println("what is the name of the test file?");
		strFileName = con.readLine();
		TextOutputFile newquiz = new TextOutputFile(strFileName + ".txt");
		con.sleep(600);
		
		con.clear();
		con.println("Please enter the quiz in this format: ");
		con.println("Question: ");
		con.println("Possible Answer 1: ");
		con.println("Possible Answer 2: ");
		con.println("Possible Answer 3: ");
		con.println();
		con.println("When finished, enter \"done\"");
		con.println("Note: your questions must be more than 5 characters long");
		
		con.println("----------------------------------------------------------------------------------");
		con.println();
		
		String strInput = "";
		int intStop = 1;
		
		while(intStop == 1){
			con.print("Question: ");
			strInput = con.readLine();
									
			//exit loop if "done"
			if(strInput.equalsIgnoreCase("done")){
				newquiz.close();
				intStop = 0;
			}else{
				newquiz.println(strInput);
				
				con.print("Possible Answer 1: ");
				strInput = con.readLine();
				newquiz.println(strInput);
				
				con.print("Possible Answer 2: ");
				strInput = con.readLine();
				newquiz.println(strInput);
				
				con.print("Possible Answer 3: ");
				strInput = con.readLine();
				newquiz.println(strInput);
			}
			
			
		}
		
		if(strInput.equalsIgnoreCase("done")){
			con.clear();
			
			TextOutputFile addtestname = new TextOutputFile("test.txt",true);
			addtestname.println(strFileName);
			addtestname.close();
			
			con.println("Test Created!");
			newquiz.close();
		}
		
		//return to main menu
		con.println();
		con.println();
		con.println();
		con.println("Press 'm' to return to main menu");
		chrKeyIn = con.getChar();
		
		if(chrKeyIn == 'm' || chrKeyIn == 'M'){
			return chrKeyIn;			
		}else{
			while(chrKeyIn != 'm' || chrKeyIn != 'M'){
				if(chrKeyIn == 'h' || chrKeyIn == 'H'){
					help();
				}else{
					System.out.println("invalid keyboard input");
				}
				chrKeyIn = con.getChar();
			}
		}
			
		return chrKeyIn;
			
		
	}
	
}
