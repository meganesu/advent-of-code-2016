/*
--- Day 2: Bathroom Security ---

You arrive at Easter Bunny Headquarters under cover of darkness. However, you left in such a rush that 
you forgot to use the bathroom! Fancy office buildings like this one usually have keypad locks on their 
bathrooms, so you search the front desk for the code.

"In order to improve security," the document you find says, "bathroom codes will no longer be written 
down. Instead, please memorize and follow the procedure below to access the bathrooms."

The document goes on to explain that each button to be pressed can be found by starting on the previous 
button and moving to adjacent buttons on the keypad: U moves up, D moves down, L moves left, and R moves 
right. Each line of instructions corresponds to one button, starting at the previous button (or, for the 
first line, the "5" button); press whatever button you're on at the end of each line. If a move doesn't 
lead to a button, ignore it.

You can't hold it much longer, so you decide to figure out the code as you walk to the bathroom. You 
picture a keypad like this:

1 2 3
4 5 6
7 8 9
Suppose your instructions are:

ULL
RRDDD
LURDL
UUUUD
* You start at "5" and move up (to "2"), left (to "1"), and left (you can't, and stay on "1"), so the first 
  button is 1.
* Starting from the previous button ("1"), you move right twice (to "3") and then down three times (stopping 
at "9" after two moves and ignoring the third), ending up with 9.
Continuing from "9", you move left, up, right, down, and left, ending with 8.
Finally, you move up four times (stopping at "2"), then down once, ending with 5.
So, in this example, the bathroom code is 1985.

Your puzzle input is the instructions from the document you found at the front desk. What is the bathroom code?

--- Part Two ---

You finally arrive at the bathroom (it's a several minute walk from the lobby so visitors can behold the many fancy conference rooms and water coolers on this floor) and go to punch in the code. Much to your bladder's dismay, the keypad is not at all like you imagined it. Instead, you are confronted with the result of hundreds of man-hours of bathroom-keypad-design meetings:

    1
  2 3 4
5 6 7 8 9
  A B C
    D
You still start at "5" and stop when you're at an edge, but given the same instructions as above, the outcome is very different:

You start at "5" and don't move at all (up and left are both edges), ending at 5.
Continuing from "5", you move right twice and down three times (through "6", "7", "B", "D", "D"), ending at D.
Then, from "D", you move five more times (through "D", "B", "C", "C", "B"), ending at B.
Finally, after five more moves, you end at 3.
So, given the actual keypad layout, the code would be 5DB3.

Using the same instructions in your puzzle input, what is the correct bathroom code?
*/

import java.io.*;

public class Day2 {

	public static void main(String[] args) {
		try {
			// Load directions from input file
			File f = new File("input2.txt");
			FileReader fReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fReader);
			
			/* Set up grid:
			 *    i=0 1 2 3 4
			 *      
			 * j=0      1
			 * j=1    2 3 4
			 * j=2  5 6 7 8 9
			 * j=3    A B C
			 * j=4      D
			 */
			char[][] grid = new char[5][5];
			for (int i = 0; i < 5; i++) { // i = col
				for (int j = 0; j < 5; j++) { // j = row
					grid[i][j] = 'X'; // Initialize all cells to empty
				}
			}
			grid[2][0] = '1';
			grid[1][1] = '2';
			grid[2][1] = '3';
			grid[3][1] = '4';
			grid[0][2] = '5';
			grid[1][2] = '6';
			grid[2][2] = '7';
			grid[3][2] = '8';
			grid[4][2] = '9';
			grid[1][3] = 'A';
			grid[2][3] = 'B';
			grid[3][3] = 'C';
			grid[2][4] = 'D';
			
			String code = ""; // Track the code sequence
			
			// Track the key you're currently on (starts at 5 for first instruction)
			int currCol = 0; // i
			int currRow = 2; // j
			
			// Parse the instructions
			String input;
			while ((input = reader.readLine()) != null) {
				// Parse a line of instructions
				
				// Iterate through the characters in the string
				for (int i=0; i < input.length(); i++) {
					char dir = input.charAt(i);
					
					if (dir == 'U') {
						// Decrease row (if you can)
						if (currRow > 0 && grid[currCol][currRow - 1] != 'X') {
							currRow--;
						}
						System.out.println("Moved UP.    Current location: " + grid[currCol][currRow] + " (" + currCol + "," + currRow + ")");
					}
					else if (dir == 'R') {
						// Increase column (if you can)
						if (currCol < 4 && grid[currCol + 1][currRow] != 'X') {
							currCol++;
						}
						System.out.println("Moved RIGHT. Current location: " + grid[currCol][currRow] + " (" + currCol + "," + currRow + ")");
					}
					else if (dir == 'D') {
						// Increase row (if you can)
						if (currRow < 4 && grid[currCol][currRow + 1] != 'X') {
							currRow++;
						}
						System.out.println("Moved DOWN.  Current location: " + grid[currCol][currRow] + " (" + currCol + "," + currRow + ")");
					}
					else if (dir == 'L') {
						// Decrease column (if you can)
						if (currCol > 0 && grid[currCol - 1][currRow] != 'X') {
							currCol--;
						}
						System.out.println("Moved LEFT.  Current location: " + grid[currCol][currRow] + " (" + currCol + "," + currRow + ")");
					}
				}
				
				// When you've finished parsing the line, add the value at your current location to bathroom code
				code = code.concat(String.valueOf(grid[currCol][currRow]));
				System.out.println("End of line. Next digit in code: " + grid[currCol][currRow]);
			}
			
			// Print the final bathroom code
			System.out.println("The bathroom code is: " + code);
			
			reader.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
