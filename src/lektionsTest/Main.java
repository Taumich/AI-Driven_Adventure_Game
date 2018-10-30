package lektionsTest;
import java.util.Scanner;

class Main{
	
	public static void main (String[] args)
	{
		//Lokala variabler
		boolean exit = false;
		int turn = 0;
		int[] coordinates = {2,2};
		
		Say("Welcome to Project P online! \nType something to interact");
		
		//Loop som kör RPG spelet hej 123
		while (exit == false)
		{
			
			Say("Coordinates: " + coordinates[0] +","+ coordinates[1]);
			Scanner s = new Scanner(System.in);
			
			//answer
			String[] answer = {AI(0,s.nextLine()), AI(1,s.nextLine()), AI(2,s.nextLine())};
			
			//new position
			//coordinates[0] = AI
			//coordinates[1] = AI
			
			//say answer
			Say(answer[0]);
			
			turn ++;
			if (turn >= 20) {
				exit = true;
				Say("Game Over!"); //to delete
			}
		}
	}
	
	private static String AI(int task, String answer) {
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
				respons[0] = actions[i] + "\n You are now " + World( (i-1)/2 );
				respons[1] = World(task, (i-1)/2 );
				respons[2] = World(task, (i-1)/2 );
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
		
		return message[task];
	}
	
	//world is used for movement vector calculations
	private static String[] World(int task, int action)
	{
		//Local variables
		int[] 	present 	= {2,2},
				nextVector 	= {0,0};
		
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
		
		return Map(task, motion);
	}
	
	//Map is used for storing map tiles and returning movement results
	private static String Map(int task, int action[])
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
			String[] response = {
									element[ tile[( action[0]+action[1] )][( action[2]+action[3] )] ],
									action[0]+action[1]+"",
									action[2]+action[3]+""
								};
			return response[task];
		} else {
			return "Error, illegal movement";
		}
	}
	


	private static void Say(String print) {
		System.out.println(print);
	}
}