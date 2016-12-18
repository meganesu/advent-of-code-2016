/*
--- Day 1: No Time for a Taxicab ---

Santa's sleigh uses a very high-precision clock to guide its movements, and the clock's 
oscillator is regulated by stars. Unfortunately, the stars have been stolen... by the Easter 
Bunny. To save Christmas, Santa needs you to retrieve all fifty stars by December 25th.

Collect stars by solving puzzles. Two puzzles will be made available on each day in the 
advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle 
grants one star. Good luck!

You're air-dropped near Easter Bunny Headquarters in a city somewhere. "Near", unfortunately, 
is as close as you can get - the instructions on the Easter Bunny Recruiting Document the 
Elves intercepted start here, and nobody had time to work them out further.

The Document indicates that you should start at the given coordinates (where you just landed) 
and face North. Then, follow the provided sequence: either turn left (L) or right (R) 90 
degrees, then walk forward the given number of blocks, ending at a new intersection.

There's no time to follow such ridiculous instructions on foot, though, so you take a moment 
and work out the destination. Given that you can only walk on the street grid of the city, 
how far is the shortest path to the destination?

For example:

- Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
- R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
- R5, L5, R5, R3 leaves you 12 blocks away.

How many blocks away is Easter Bunny HQ?

--- Part Two ---

Then, you notice the instructions continue on the back of the Recruiting Document. Easter Bunny HQ is actually at the first location you visit twice.

For example, if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.

How many blocks away is the first location you visit twice?
*/

import java.io.*;
import java.util.*;

public class Day1 {
	public static void main(String args[]) {
		
		String[] instructions;
		HashSet<String> coordinates = new HashSet<String>();
		
		int heading = 0; // 0 - face N; 1 - E; 2 - S; 3 - W
		int xPos = 0;
		int yPos = 0;
		
		try {
			// Load directions from the input file into array
			File f = new File("input1.txt");
			FileReader fReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fReader);
			
			String input = reader.readLine();
			instructions = input.split(", ");

			reader.close();
			
			// Parse each instruction
			for (int i = 0; i < instructions.length; i++) {
				// Separate the instruction into direction (L/R) and magnitude (# blocks)
				String direction = instructions[i].substring(0, 1);
				String magnitude = instructions[i].substring(1);
				
				// Update heading based on direction
				if (direction.equals("R")) {
					heading = (heading + 1) % 4;
				}
				else { // direction.equals("L")
					heading = ((heading - 1) % 4 + 4) % 4;
				}
				
				// Update xPos and yPos based on heading
				if (heading == 0) {
					for (int j=0; j < Integer.parseInt(magnitude); j++) {
						String currLoc = xPos + " " + (yPos + j + 1);
						if (coordinates.contains(currLoc)) {
							System.out.println("Repeat at " + currLoc);
						}
						coordinates.add(currLoc);
					}
					yPos = yPos + Integer.parseInt(magnitude);
				}
				else if (heading == 1) {
					for (int j=0; j < Integer.parseInt(magnitude); j++) {
						String currLoc = (xPos + j + 1) + " " + yPos;
						if (coordinates.contains(currLoc)) {
							System.out.println("Repeat at " + currLoc);
						}
						coordinates.add(currLoc);
					}
					xPos = xPos + Integer.parseInt(magnitude);
				}
				else if (heading == 2) {
					for (int j=0; j < Integer.parseInt(magnitude); j++) {
						String currLoc = xPos + " " + (yPos - j - 1);
						if (coordinates.contains(currLoc)) {
							System.out.println("Repeat at " + currLoc);
						}
						coordinates.add(currLoc);
					}
					yPos = yPos - Integer.parseInt(magnitude);
				}
				else { // heading == 3
					for (int j=0; j < Integer.parseInt(magnitude); j++) {
						String currLoc = (xPos - j - 1) + " " + yPos;
						if (coordinates.contains(currLoc)) {
							System.out.println("Repeat at " + currLoc);
						}
						coordinates.add(currLoc);
					}
					xPos = xPos - Integer.parseInt(magnitude);
				}
				
				System.out.println(xPos + " " + yPos);

			}
			System.out.println("Num blocks: " + (Math.abs(xPos) + Math.abs(yPos)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
