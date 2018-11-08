package maii;
import java.util.*;

class Main{
	
	static boolean devMode = !true;
	static int turn = 0;
	
	public static void main (String[] args)
	{
		//Lokala variabler
		
		boolean exit = false;
		int[] coordinates = {2,2};
		
		//AI_Speech aispeech = new AI_Speech();
		//WorldCode worldcode = new WorldCode();
		
		Say("Welcome to Michael AI-initiative! \nType something to interact");
		Scanner s = new Scanner(System.in);
		
		//Loop som kör RPG spelet
		while (exit == false)
		{
			Say("Coordinates: " + coordinates[0] +","+ coordinates[1], devMode);
			
			//answer
			String[] answer = AI_Speech.AI(s.nextLine(), coordinates);
			
			if(new String(answer[0]).equals("x"))
			{
				Say("Lacking response because of: " + answer[0], devMode);
				answer[0] = "Sorry i don't understand that";
			}
			else if(new String(answer[1]).equals("x"))
			{
				Say("Didn't move because of: " + answer[1], devMode);
			}
			else if (new String(answer[2]).equals("x"))
			{
				Say("Didn't move because of: " + answer[2], devMode);
			}
			else
			{
				//new position
				coordinates[0] = Integer.valueOf(answer[1]);
				coordinates[1] = Integer.valueOf(answer[2]);
			}
			
			//say answer
			Say(answer[0]);
			
			turn ++;
			if (turn >= 10) {
				exit = true;
				Say("Game Over!"); //to delete
			}
		}	
	}

	private static void Say(String print) {
			System.out.println(print);
	}
	private static void Say(String print, boolean devMode) {
		if(devMode)
			System.out.println(print);
	}
}