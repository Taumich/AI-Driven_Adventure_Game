package maii;

public class WorldCode {
	
	static boolean devMode = Main.devMode;
	
	//world is used for movement vector calculations
		public static String[] World(int action, int[] coordinates)
		{
			Say("_____Entered World Mode in World-Code_____",devMode);
			
			//Local variables
			int[] 	nextVector 	= {0,0};
			
			if (action == 0) {
				nextVector[0] = 0; nextVector[1] = 1;
			} else if (action == 1) {
				nextVector[0] = 0; nextVector[1] = -1;
			} else if (action == 3) {
				nextVector[0] = 1; nextVector[1] = 0;
			} else if (action == 4) {
				nextVector[0] = -1; nextVector[1] = 0;
			}
			Say("Trying to move by: "+nextVector[0]+","+nextVector[1], devMode);
			return Map(nextVector, coordinates);
		}
		
		//Map is used for storing map tiles and returning movement results
		private static String[] Map(int[] action, int[] coordinates)
		{
			
			String[] element = {"in an ocean", "on a beach", "in a jungle"};
			int[] 	newloc = {coordinates[0]+action[0], coordinates[1]+action[1]};
			boolean log = true;
			
			Say("_____Entered map code_____",devMode);
			
			// y-coordinates: "up" = right on code, 
			// x-coordinates: "right" = down on code
			
			int[][] tile = 	{
							{1,1,1,0,0},
							{1,1,1,0,0,1,0,1,2,1,2},
							{1,1,2,0,0,2}, //---> "up" = y-axis
							{0,0,2,0,0},
							{0,0,0,0,0}
							};
					//    |
					//    v
					// "right" = x-axis
			
			int[][] y_length = new int[5][2]; //definition: y-length [tile number in y-axis] [min-val "0" or max-val "1" from this tile]
			
			Say("  Initializing: tile loop 0 to "+ (tile.length-1), log && devMode);
			
			for (int i = 0; i < tile.length; i++)
			{
				//if tile not set:
				if (y_length[i][1] == 0)
				{
					//check if y-axis in range
					if (tile[i].length > newloc[1])
					{
						//find max-val
						for (int u = i; u <= tile.length; u++) //loops one too much for final break code.
						{
							if (u < tile.length && tile[u].length > newloc[1])
								Say("valid range at tile["+u+"].max="+(tile[u].length-1)+" newloc[1]="+newloc[1], log && devMode);
							else
							{
								//set max-val to tile-group
								for(int n=i; n < u; n++)
								{
									y_length[n][1] = u-1;
									y_length[n][0] = i;
									Say("Set y["+n+"]= {"+y_length[n][0]+"," +y_length[n][1]+"}", log && devMode);
								}
								break;
							}
						}
					} 
					else //if out of range:
						y_length[i][0] = y_length[i][1] = -1; 
				}
				
				Say("y_length["+i+"]= {"+ y_length[i][0] +","+ y_length[i][1]+"}", devMode);
			}
			
			Say("newLoc[0] = "+ newloc[0] ,log && devMode);
			//bounds = true means inside of map
			boolean xBounds = newloc[0] < (tile.length) && newloc[0] >0;
			boolean yBounds = true;
			if( newloc[0] < tile.length)
				yBounds = newloc[1] < tile[newloc[0]].length && newloc[1] >0;
			
			if(xBounds)
				Say("xy-tile quantities = {"+tile.length+","+tile[newloc[0]].length+"}", devMode);
			
			//Say("xBounds = " + xBounds + " yBounds = " + yBounds, log && devMode);
			
			if(xBounds && yBounds)
			{
				String[] respons = {
										element[ tile[( coordinates[0]+action[0] )][( coordinates[1]+action[1] )] ],
										coordinates[0]+action[0]+"",
										coordinates[1]+action[1]+""
									};
				return respons;
			} else if (!xBounds) {
				String[] respons = {"A force stops you from moving there","x","0"};
				return respons;
			} else {
				String[] respons = {"A force stops you from moving there","0","x"};
				return respons;
			}
		}
		
		/*private static void Say(String print) {
			System.out.println(print);
		}*/
		private static void Say(String print, boolean devMode) {
			if(devMode)
				System.out.println(print);
		}
	
}
