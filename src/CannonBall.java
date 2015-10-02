import java.util.Scanner;

/*
 *	  Jalal Khan and Peter Deneroff
 *    9/4/15
 *    
 *    A program that implements a recursive function
 *    to compute the number of cannon balls in
 *    a pyramid.
 */

public class CannonBall {


	public static void main(String[] args) {

		Scanner keyb = new Scanner(System.in);  // Keyboard scanner.
		int cannonBalls;                        // Number of cannonballs
		                                        //    in the pyramid.
		int layers;                             // Number of layers in
		                                        //    the pyramid.

		do {
			// Prompt for and read the number of layers of
			// cannonballs in the pyramid.

		    System.out.print("\nNumber of layers (-1 to quit): ");
		    layers = keyb.nextInt();

		    if (layers != -1) {
		    	cannonBalls = cannon(layers);  // Determine and report the
		    	                               // number of cannonballs.
		    	System.out.printf("There are %d cannonballs in the %d layers.\n",
		    			                                  cannonBalls, layers);
		    }
		} while (layers != -1);
	}


	public static int cannon(int layer)
	{
		//base case, the top of the pile has one cannonball
		if(layer == 1) {
			return 1;
		} else { //inductive step, add number of cannonballs in layer
			//which is that layer number squared.
			return (int) Math.pow(layer, 2) + cannon(layer-1);
		}
	}
}
