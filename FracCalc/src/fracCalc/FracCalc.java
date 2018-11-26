package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
		String command = "";
		while(!command.equals("quit")) {
	    	System.out.print("Calculate: ");
			command = input.nextLine();
			System.out.println(produceAnswer(command));
		}
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    
    public static String produceAnswer(String input) { 
    	//absolutely useless
    	String[] statement = input.split(" ");
    	return statement[2];
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static int[] remove(int[] arr, int idx) {
		int[] removed = new int[arr.length-1];
		int count=0;
		for(int i=0; i<arr.length; i++) {
			if(i!=idx) {
				removed[count]=arr[i];
				count++;
			}
		}
		return removed;
	}
	
    public static String[] toImproperFrac(String[] a) {
    	String[] improperFracs = new String[a.length];
		for(int i = 0; i<a.length; i++) {
			String converted = a[i];
			int dividerPos = a[i].indexOf("_");
			int vinculum = a[i].indexOf("/");
			if(dividerPos>=0) {
				converted = Integer.parseInt(a[i].substring(0, dividerPos))
						* Integer.parseInt(a[i].substring(vinculum+1)) + "/" +
						a[i].substring(vinculum+1);
			}
			improperFracs[i]=converted;
		}
		return improperFracs;
	}
    
    public static String evaluate(String a, String b, String c) {
		String evaluates = "";
		
	}
    
}
