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
				log = true;
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
				message = {"missing action"};
		
		Say("_____Testing action loop now_____", log && devMode);
		
		//Ask for Task
		for (int i=0; i <= (actions.length -1); i++) {
			equality = new String(answer).equals(actions[i]);
			
			i++;
			
			if(equality) {
				Say("  respons[0] = (" + actions[0] + ") should be: " + actions[i-1], log && devMode);
				respons = World( (i-1)/2 , coordinates );
				break;
			}
		}
		
		//return result
		if (equality)
			message = respons;
		else
		{
			Say("_____Testing response loop now_____", log && devMode);
			//Ask for Conversation
			for (int i=0; i <= (responses.length -1); i++) {
				Say("  Testing: " + responses[i], log && devMode);
				equality = new String(answer).equals(responses[i]);
				
				i++;
				Say("  respons[0] = (" + respons[0] + ") should become: " + responses[i], log && devMode);
				respons[0] = responses[i];
				Say("  respons[0] = (" + respons[0] + ") should be: " + responses[i], log && devMode);
				if(equality) {
					Say("   Break", log && devMode);
					String res[] = {respons[0],"x","x"};
					return res;
				}
			}
			//if (equality) {}
		}
		Say("message is now: " + message[0] +", "+ message[1] +", "+ message[2], log && devMode);
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
		else if (action == 4) {
			nextVector[0] = -1; nextVector[1] = 0;
		}
		
		return Map(nextVector, coordinates);
	}
	
	//Map is used for storing map tiles and returning movement results
	private static String[] Map(int[] action, int[] coordinates)
	{
		
		String[] element = {"in an ocean", "on a beach", "in a djungle"};
		int[] 	newloc = {coordinates[0]+action[0], coordinates[1]+action[1]};
		boolean log = true;
		
		Say("____Entered map", log && devMode);
		
		// y-coordinates: "up" = right on code, 
		// x-coordinates: "right" = down on code
		int[][] tile = 	{
						{1,1,1,0,0},
						{1,1,1,0,0,0},
						{1,1,2,0,0,0}, //---> "up" = y-axis
						{0,0,2,0,0},
						{0,0,0,0,0}
						};
				//    |
				//    v
				// "right" = x-axis
		
		int[][] y_length = new int[5][2]; //definition: y-length [tile number in y-axis] [min-val "0" or max-val "1" from this tile]
		
		Say("tile loop 0 to "+ (tile.length-1), log && devMode);
		
		for (int i = 0; i <= tile.length -1; i++) {
			int 	minVal = -1,
					maxVal = -1;
			
			if (y_length[i][1] == 0)
			{
				//check if in range
				if (tile[i].length >= newloc[1]) {
					
					//set min-val
					if (i==0)
						y_length[i][0] = i;
					else if (y_length[i-1][0] == -1)
						y_length[i][0] = y_length[i-1][0];
					
					//find max-val from min-tile
					for (int u = i; u <= tile.length; u++) {
						Say("i="+i+" u="+u,log && devMode);
						
						if (tile[u].length >= newloc[1]) {
							Say("found valid at: "+u, log && devMode);
							y_length[u][0] = minVal; //min
						}
						else {
							//set max-val to tile-group
							for(int n=i; n < u; n++) {
								Say("Setting y_length["+n+"][1] to "+(u-1));
								y_length[n][1] = u-1;
							}
							break;
						}
					}
				}
				y_length[i][0] = minVal; //min
				y_length[i][1] = maxVal; //max newloc[0]
			}
			Say("min: "+ y_length[i][0] +", max: "+ y_length[i][1]);
		}
		
		//bounds = true means inside of map
		Say("x.length = " + tile.length, log && devMode);
		Say("y.length = " + tile[newloc[0]].length, log && devMode);
		boolean xBounds = newloc[0] <tile.length && newloc[0] >-1;
		boolean yBounds = newloc[1] <tile[newloc[0]].length && newloc[1] >-1;
		
		//Say("xBounds = " + xBounds + " yBounds = " + yBounds, devMode);
		
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