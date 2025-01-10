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
			
			con.println("Perfect Squares (Advanced) has been added to your test options!");
		}
		con.sleep(700);
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
		String strQuestions[][];
		
		if(chrChosen == 'B' || chrChosen == 'b'){
			TextInputFile binaryfile = new TextInputFile("Binary Math.txt");
			strChosenTest = "Binary Math";
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			binaryfile.close();
			
		}else if(chrChosen == 'P' || chrChosen == 'p'){
			TextInputFile pfsqfile = new TextInputFile("Perfect Squares.txt");
			strChosenTest = "Perfect Squares";  // ensure that this is the name being entered into method when inputing varable to method
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			pfsqfile.close();
			
		}else if(chrChosen == 'Q' || chrChosen == 'q'){
			TextInputFile quadfile = new TextInputFile("Quadratic.txt");
			strChosenTest = "Quadratic";
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			quadfile.close();			
			
		}else if(strName.equalsIgnoreCase("statitan") && strChosenTest.equalsIgnoreCase("Perfect Squares (Advanced)")){
			TextInputFile advpfsq = new TextInputFile("Perfect Squares (Advanced)");
			strChosenTest = "Perfect Squares (Advanced)";
			//send to method to load question
			strQuestions = questionload(strChosenTest);
			
			advpfsq.close();
			
		}else{
			System.out.println("invalid keyboard input");
		}
				
		
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
	
	//while(test.eof() == false){
		//strQuestion = test.readLine();
		//intNumQuest = intNumQuest + 1;      // <-- number of questions counter
		//strAns1 = test.readLine();
		//strAns2 = test.readLine();
		//strAns3 = test.readLine();
	//}
	//test.close();
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
			System.out.println(strLoadQuest[intRow][0] + "| " + strLoadQuest[intRow][1] + "| " + strLoadQuest[intRow][2] + "| "  + strLoadQuest[intRow][3] + "| " + strLoadQuest[intRow][4]);
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
		System.out.println(intNumQuest);
		
		return intNumQuest;
	}
	
	public static void sort(String strQuestions[][]){
		String strTempQuestion;
		String strTempA1;
		String strTempA2;
		String strTempA3;
		int intRow;
		int intRow2;
		int intQuestNum;
		
		//for(intRow2 = 0; intRow2 < 
		
	}
}
