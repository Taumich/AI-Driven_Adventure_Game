package lektionsTest;
import java.util.*;

class Main{
	
	static boolean devMode = true;
	
	public static void main (String[] args)
	{
		//Lokala variabler
		boolean exit = false;
		int turn = 0;
		int[] coordinates = {2,2};
		
		Say("Welcome to Project P online! \nType something to interact");
		Scanner s = new Scanner(System.in);
		
		//Loop som kör RPG spelet
		while (exit == false)
		{
			Say("Coordinates: " + coordinates[0] +","+ coordinates[1], devMode);
			
			//answer
			String[] answer = AI(s.nextLine(), coordinates);
			
			if(new String(answer[1]).equals("x"))
			{
				
			}
			else if (new String(answer[2]).equals("x"))
			{
				
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
		boolean equality = false;
		String[] respons = {"","-","-"},
		
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
				message = {"missing action"};
		
		//Ask for Task
		for (int i=0; i <= (actions.length -1); i++) {
			equality = new String(answer).equals(actions[i]);
			
			i++;
			
			if(equality) {
				respons = World( (i-1)/2 , coordinates );
				break;
			}
		}
		
		//return result
		if (equality)
			message = respons;
		else
		{
			//Ask for Conversation
			for (int i=0; i <= (responses.length -1); i++) {
				equality = new String(answer).equals(responses[i]);
				
				i++;
				respons[0] = responses[i];
				if(equality)
					break;
			}
			if (equality)
				message = respons;
		}
		return message;
	}
	
	//world is used for movement vector calculations
	private static String[] World(int action, int[] coordinates)
	{
		//Local variables
		int[] 	nextVector 	= {0,0};
		
		if (action == 0) {
			nextVector[0] = 0; nextVector[1] = 1;
		}
		else if (action == 1) {
			nextVector[0] = 0; nextVector[1] = -1;
		}
		else if (action == 3) {
			nextVector[0] = 1; nextVector[1] = 0;
		}
		else if (action == 3) {
			nextVector[0] = -1; nextVector[1] = 0;
		}
		
		return Map(nextVector, coordinates);
	}
	
	//Map is used for storing map tiles and returning movement results
	private static String[] Map(int[] action, int[] coordinates)
	{
		String[] element = {"in an ocean", "on a beach", "in a djungle"};
		
		int[][] tile = {
				{0,0,0,0,0},
				{0,1,1,1,0},
				{0,1,2,1,0},
				{0,1,1,1,0},
				{0,0,0,0,0}
				};
		
		//bounds = true means inside of map
		boolean xBounds = coordinates[0]+action[0] <5 || coordinates[0]+action[0] >0;
		boolean yBounds = coordinates[1]+action[1] <5 || coordinates[1]+action[1] >0;
		
		if(xBounds && yBounds)
		{
			String[] respons = {
									element[ tile[( coordinates[0]+action[0] )][( coordinates[1]+action[1] )] ],
									coordinates[0]+action[0]+"",
									coordinates[1]+action[1]+""
								};
			return respons;
		} else if (!xBounds) {
			String[] respons = {"Error, illegal movement","x","0"};
			return respons;
		} else {
			String[] respons = {"Error, illegal movement","0","x"};
			return respons;
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