package maii;
import java.util.*;

class Main{
	
	static boolean devMode = true;
	
	public static void main (String[] args)
	{
		//Lokala variabler
		boolean exit = false;
		int turn = 0;
		int[] coordinates = {2,2};
		
		
		AI_Speech aispeech = new AI_Speech();
		//WorldCode worldcode = new WorldCode();
		
		
		Say("Welcome to Michael AI-initiative! \nType something to interact");
		Scanner s = new Scanner(System.in);
		
		//Loop som kör RPG spelet
		while (exit == false)
		{
			Say("Coordinates: " + coordinates[0] +","+ coordinates[1], devMode);
			
			//answer
			String[] answer = AI(s.nextLine(), coordinates);
			
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
			if (turn >= 20) {
				exit = true;
				Say("Game Over!"); //to delete
			}
		}	
	}
	
	
	private static String[] AI(String answer, int[] coordinates) {
		//Local variables
		boolean equality = false,
				log = !true;
		String[] respons = {"temp","x","x"},
		
		//AI response library
				responses = 
		{	
			"hey","wazapp",  "no plz","plz",  "bye","no way",  "why","that's why",  "beautiful","yes you are",
			"ask me","are you crazy?",  "start","start what? Say start game to play",  "yes","amazing!",  "no","how sad",  "plz", "precisely!",
			"are you smart?","very smart!",  "who are you?","I am your friend", "what's up?","not much bro", 
			"hello","hey hey hey!",  "wazapp","wazapp boi",  "wazapp boi","hey that's my line!",  "start game","intializing..."
		},
				actions =
		{
			"up","took a step forward",   "down","took a step back",  "return","you can't return from here",
			"right","you turn to the right",  "left","you turn to the left"
		},
				message = {"x","n","n"};
		
		Say("_____Testing action loop now_____ actionLenght: "+actions.length, log && devMode);
		
		//Ask for Task
		for (int i=0; i < actions.length-1; i++) {
			equality = new String(answer).equals(actions[i]);
			
			i++;
			
			if(equality) {
				Say("  action["+i+"] = (" + actions[0] + ") should be: " + actions[i-1], log && devMode);
				//respons = World( (i-1)/2 , coordinates );
				respons = WorldCode.World( (i-1)/2, coordinates);
				break;
			}
		}
		
		//return result
		if (equality)
			message = respons;
		else
		{
			Say("_____Testing response loop now_____ responsLength = "+ responses.length, log && devMode);
			//Ask for Conversation
			for (int i=0; i < (responses.length-1); i++) {
				Say("  Testing ["+i+"]: " + responses[i], log && devMode);
				equality = new String(answer).equals(responses[i]);
				
				i++;
				Say("  respons["+i+"] = (" + respons[0] + ") should become: " + responses[i], log && devMode);
				respons[0] = responses[i];
				Say("  respons["+i+"] = (" + respons[0] + ") should be: " + responses[i], log && devMode);
				if(equality) {
					Say("   Break", log && devMode);
					String res[] = {respons[0],"x","x"};
					return res;
				}
			}
			//if (equality) {}
		}
		
		//Say("message is now: " + message[0] +", "+ message[1] +", "+ message[2], log && devMode);
		return message;
	}

	private static void Say(String print) {
			System.out.println(print);
	}
	private static void Say(String print, boolean devMode) {
		if(devMode)
			System.out.println(print);
	}
}