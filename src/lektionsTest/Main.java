package lektionsTest;
import java.util.Scanner;

class Main{
	
	public static int[] coordinates = {2,2};
	
	public static void main (String[] args)
	{
		//Lokala variabler
		boolean exit = false;
		int turn = 0;
		
		Say("Welcome to Project P online! \nType something to interact");
		
		//Loop som kör RPG spelet
		while (exit == false)
		{
			
			Scanner s = new Scanner(System.in);
			
			Say(AI(s.nextLine()));
			
			turn ++;
			if (turn >= 20) {
				exit = true;
				Say("Game Over!"); //to delete
			}
		}
	}
	
	//world is used for movement vector calculations
	private static String World(int action)
	{
		int[] present = coordinates;
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
		
		//Local variable for movement response
		int[] motion = { nextVector[0], present[0], nextVector[1], present[1] };
		
		return Map(motion);
	}
	
	//Map is used for storing map tiles and returning movement results
	private static String Map(int action[])
	{
		String[] element = {"in an ocean", "on a beach", "in a djungle"};
		
		int[][] tile = {
				{0,0,0,0,0},
				{0,1,1,1,0},
				{0,1,2,1,0},
				{0,1,1,1,0},
				{0,0,0,0,0}
				};
		
		if(action[0]+action[1] <5 && action[2]+action[3] <5)
		{
			coordinates[0] = action[1];
			coordinates[1] = action[3];
			return element[ tile[( action[0]+action[1] )][( action[2]+action[3] )] ];
		} else {
			return "Error, illegal movement";
		}
	}
	
	private static String AI(String answer) {
		//Local variables
		boolean equality = false;
		String respons = "";
		
		//AI response library
		String[] responses = 
		{	
			"hey","wazapp",  "no plz","plz",  "bye","no way",  "why","that's why",  "beautiful","yes you are",
			"ask me","are you crazy?",  "start","start what? Say start game to play",  "yes","amazing!",  "no","how sad",  "plz", "precisely!",
			"are you smart?","very smart!",  "who are you?","I am your friend", "what's up?","not much bro", 
			"hello","hey hey hey!",  "wazapp","wazapp boi",  "wazapp boi","hey that's my line!",  "start game","intializing..."
		};
		
		String[] actions =
		{
			"up","took a step forward",   "down","took a step back",  "return","you can't return from here",
			"right","you turn to the right",  "left","you turn to the left"
		};
		
		//Ask for Task
		for (int i=0; i <= (actions.length -1); i++) {
			
			equality = new String(answer).equals(actions[i]);
			
			i++;
			
			if(equality) {
				respons = actions[i] + "\n You are now " + World( (i-1)/2 );
				break;
			}
				
		}
		
		//return result
		if (equality)
			return respons;
		else
		{
			//Ask for Conversation
			for (int i=0; i <= (responses.length -1); i++) {
				
				equality = new String(answer).equals(responses[i]);
				
				i++;
				respons = responses[i];
				
				if(equality)
					break;
			}
			
			if (equality)
				return respons;
			else
				return "missing answer to command";
		}
	}

	private static void Say(String print) {
		System.out.println(print);
	}
}