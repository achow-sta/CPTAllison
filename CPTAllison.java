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
		con.println("                                        Enter a Key Letter to Begin");
	}
}
