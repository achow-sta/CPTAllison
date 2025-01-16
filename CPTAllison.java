// Creator: Allison Chow
// Date Created: Jan 14, 2025
// Version 1.1.2

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
		
		if(chrKeyIn == 'q' || chrKeyIn == 'Q'){
			con.clear();
			con.setBackgroundColor(Color.BLACK);
			con.println("Thanks for playing!");
			con.sleep(500);
			con.closeWindow();
		}else{
			
		}
		
		if(chrKeyIn != 'q' || chrKeyIn != 'Q'){
			while(chrKeyIn != 'q' || chrKeyIn != 'Q'){
				if(chrKeyIn == 'p' || chrKeyIn == 'P'){  //playgame
					con.clear();
					con.setBackgroundColor(Color.BLACK);
					chrKeyIn = CPTAllisonTools.playgame(con);
					
					//return to main menu
					con.clear();
					CPTAllisonTools.home(con);
					
				}else if(chrKeyIn == 'v' || chrKeyIn == 'V'){ //highscore
					con.clear();
					con.setBackgroundColor(Color.BLACK);
					chrKeyIn = CPTAllisonTools.highscore(con);
					
					//return to main menu
					con.clear();
					CPTAllisonTools.home(con);
					
				}else if(chrKeyIn == 'h' || chrKeyIn == 'H'){ //help
					CPTAllisonTools.help();
					
				}else if(chrKeyIn == 's' || chrKeyIn == 'S'){ //secret menu
					CPTAllisonTools.secret();
					
				}else if(chrKeyIn == 'a' || chrKeyIn == 'A'){ //add quiz function 
					con.clear();
					con.setBackgroundColor(Color.BLACK);
					
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
					
					con.println("----------------------------------------------------------------------------------");
					con.println();
					
					String strInput = "";
					if(!strInput.equalsIgnoreCase("done")){
						while(!strInput.equalsIgnoreCase("done")){
							con.print("Question: ");
							strInput = con.readLine();
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
						strInput = "done";
					}else{
						con.clear();
						con.println("Test Created!");
						newquiz.close();
						
						con.println();
						con.println();
						con.println();
						con.println("Press 'm' to return to main menu");
						//chrKeyIn = con.getChar();
					}
					
				}else{
					System.out.println("invalid keyboard input");
				}
				chrKeyIn = con.getChar();
			}
		}else{
			con.clear();
			con.setBackgroundColor(Color.BLACK);
			con.println("Thanks for playing!");
			con.sleep(500);
			con.closeWindow();
		}
		
		
	}
	
	
	
}
