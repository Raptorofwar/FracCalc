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
    	String whole1 = "0";
    	String num1 = "0";
    	String denom1 = "1";
    	String whole2 = "0";
    	String num2 = "0";
    	String denom2 = "1";
    	String first = statement[0];
    	String second = statement[2];
    	
    	int underscore1 = first.indexOf("_");
    	int slash1 = first.indexOf("/");
    	int underscore2 = second.indexOf("_");
    	int slash2 = second.indexOf("/");
    	
    	if(slash1>=0) {
    		denom1 = first.substring(slash1 + 1);
    		if(underscore1>=0) {
    			whole1 = first.substring(0, underscore1);
    			num1 = first.substring(underscore1 + 1, slash1);
    		}else {
    			num1 = first.substring(0, slash1);
    		}
    	}else {
    		whole1 = first;
    	}
    	
    	if(slash2>=0) {
    		denom2 = second.substring(slash2 + 1);
    		if(underscore2>=0) {
    			whole2 = second.substring(0, underscore2);
    			num2 = second.substring(underscore2 + 1, slash2);
    		}else {
    			num2 = second.substring(0, slash2);
    		}
    	}else {
    		whole2 = second;
    	}
    	
    	return "whole:" + whole2 + 
    			" numerator:" + num2 + 
    			" denominator:" + denom2;
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
			if(dividerPos>=0) {
				int vinculum = a[i].indexOf("/");
				converted = Integer.parseInt(a[i].substring(0, dividerPos))
						* Integer.parseInt(a[i].substring(vinculum+1)) + 
						Integer.parseInt(a[i].substring(dividerPos+1, vinculum)) +
						"/" +
						a[i].substring(vinculum+1);
			}
			improperFracs[i]=converted;
		}
		return improperFracs;
	}
    
    public static String operation(String a, String b, String c) {
		String evaluates = "";
		int numerA = Integer.parseInt(a.substring(0, a.indexOf("/")));
		int denomA = Integer.parseInt(a.substring(a.indexOf("/")+1));
		int numerC = Integer.parseInt(c.substring(0, c.indexOf("/")));
		int denomC = Integer.parseInt(c.substring(c.indexOf("/")+1));
		
		if(b.equals("*")){
			evaluates = numerA * numerC + "/" + denomA * denomC;
		}else if(b.equals("/")) {
			evaluates = numerA * denomC + "/" + denomA * numerC;
		}else if(b.equals("+")) {
			evaluates = (numerA * denomC + numerC * denomA) + "/" +  (denomA * denomC);
		}else if (b.equals("-")){
			evaluates = (numerA * denomC - numerC * denomA) + "/" +  (denomA * denomC);
		}
		return evaluates;
	}
    
    public static String toMixedNum(String num) {
    	int numerator = Integer.parseInt(num.substring(0, num.indexOf("/")));
    	int denominator = Integer.parseInt(num.substring(num.indexOf("/")+1));
    	return numerator / denominator + "_" + numerator % denominator + "/" + denominator;
    }
    
    public static String evaluate(String[] expression) {
    	
    }
}