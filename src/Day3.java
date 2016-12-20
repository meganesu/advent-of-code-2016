import java.io.*;

/*
--- Day 3: Squares With Three Sides ---

Now that you can think clearly, you move deeper into the labyrinth of hallways and office furniture 
that makes up this part of Easter Bunny HQ. This must be a graphic design department; the walls are 
covered in specifications for triangles.

Or are they?

The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of 
these aren't triangles. You can't help but mark the impossible ones.

In a valid triangle, the sum of any two sides must be larger than the remaining side. For example, 
the "triangle" given above is impossible, because 5 + 10 is not larger than 25.

In your puzzle input, how many of the listed triangles are possible?

*/
public class Day3 {

	public static void main(String[] args) {
		try {
			// Load triangles from input file
			File f = new File("input3.txt");
			FileReader fReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fReader);
			
			int numTriangles = 0; // count of legitimate triangles
			
			String input;
			// Parse each line in the input file
			while ((input = reader.readLine()) != null) {
				System.out.println(input);
				String[] sides = input.trim().split("\\s+");
				System.out.println(sides[0]);
				
				int a = Integer.valueOf(sides[0]);
				int b = Integer.valueOf(sides[1]);
				int c = Integer.valueOf(sides[2]);
				
				if (a + b <= c) {
					continue;
				}
				else if (b + c <= a) {
					continue;
				}
				else if (c + a <= b) {
					continue;
				}
				numTriangles++;
			}
			
			System.out.println(numTriangles);
			
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
