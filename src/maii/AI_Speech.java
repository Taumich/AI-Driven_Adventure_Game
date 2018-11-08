package maii;

public class AI_Speech {
	
	static boolean devMode = Main.devMode;
	
	public static String[] AI(String answer, int[] coordinates)
	{
		answer = answer.toLowerCase();
		answer = answer.replaceAll("[\\-\\+\\.\\^:,]","");
		String[] aword = answer.split("\\s+");
		
		//Local variables
		boolean equality = false,
				log = true;
		String[] respons = {"temp","x","x"};
		//AI response library
		
		String[][] responses[] = 
				{	
					  {	{"esa"},
						  	{"wrong","w"},
						  	{"right","correct","r"},
						  	{"retry","next","other","d"}
					},{	{"hello","hi"},
							{"Greetings!", "Hello good sir!"}
					},{	{"heyo","yo","wazaa","wazzapp"},
							{"Heyo!", "Bruh wazaaap!", "yo!"}
					},{	{"yes","y","positive","yez","ye"},
							{"That's great!","Amazing!","Splendid!","Excellent!","Superb!","Marvelous!"},
							{"+"}
					},{	{"why","how","how come","why so"},
							{"That's why!", "No idea", "Why you asking me?"},
							{"?"}
					},{	{"beautiful","pretty","amazing","lovely","splendid"},
							{"Yes you are! <3", "Aww thanks! <3", "You are so sweet! <3"}
					},{	{"crazy","stupid","mad","insane","dumb"},
							{"Oi you wanna fight?!", "Is that an insult?"}
					}
				},
						actions =
				{
						{"Took a step north",			"up","north"},
						{"Took a step south",			"down","south"},
						{"you can't return from here",	"return","back"},
						{"took a step east", 			"right","east"},
						{"Took a step west",			"left","west"}
				};
		
		Say("_____Testing action loop now_____", log && devMode);
		
		//Ask for Task
		int i=0;
		ActionLoops:
		for (String[] act : actions) {
			for (String syn : act) {
				for (String word : aword) {
					equality = new String(word).equals(syn);
					if(equality) {
						respons = WorldCode.World( (i-1)/2, coordinates);
						break ActionLoops;
					}
				}
			}
			i++;
		}
		
		Say("Action loop end\n_____Testing response loop now_____ ", log && devMode);
		
		//return result
		if (!equality)
		{	//Ask for Conversation
			i=0;
			for (String[][] speechtype : responses) {
				for(String speech : speechtype[0]) {
					for (String word : aword) {
						equality = new String(word).equals(speech);
						Say("Checking "+word+" equal to "+speech+" which is: "+equality+" "+i, log && devMode);
						if(equality) {
							Say("   Break", log && devMode);
							int mod = (Main.turn) % (responses[i][1].length);
							String res[] = {responses[i][1][mod],"x","x"};
							return res;
						}
					}
				}
				i++;
			}
		}
		
		return respons;
	}
	
	private static void Say(String print, boolean devMode) {
		if(devMode)
			System.out.println(print);
	}
}
