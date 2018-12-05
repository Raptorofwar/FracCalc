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
    	int posStartParen = -1;
    	int posFinParen = -1;
    	String[] expression = input.split(" ");
    	int position = 0;
    	do {
    		if(expression[position].equals("(")) {
    			posStartParen = position;
    		}else if(expression[position].equals("")) {
    			posFinParen = position;
    		}
    		if(posStartParen >= 0 || posFinParen >= 0) {
    			int length = posFinParen - posStartParen - 1;
    			String[] miniArray = new String[length];
    			for(int i = posStartParen + 1; i < posFinParen; i++) {
    				miniArray[i - posStartParen - 1] = expression[i];
    			}
    			expression[posStartParen] = evaluate(miniArray);
    			for(int i = posStartParen+1; i <= posFinParen; i++) {
    				remove(expression, posStartParen+1);
    			}
    			position = 0;
    			posStartParen = -1;
    			posFinParen = -1;
    		}
    	}while(position < expression.length);
    	return toMixedNum(evaluate(expression));
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static String[] remove(String[] arr, int idx) {
		String[] removed = new String[arr.length-1];
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
		
		//convert to improper fracs? fix below
    	
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
    	String[] evaluating = expression;
    	for(int i = 0; i<evaluating.length; i++) {
    		if(evaluating[i].equals("*")||evaluating[i].equals("/")) {
    			evaluating[i-1] = operation(evaluating[i-1], evaluating[i], evaluating[i+1]);
    			evaluating = remove(evaluating, i);
    			evaluating = remove(evaluating, i);
    			i = 0;
    		}
    	}
    	for(int i = 0; i<evaluating.length; i++) {
    		if(evaluating[i].equals("-")||evaluating[i].equals("+")) {
    			evaluating[i-1] = operation(evaluating[i-1], evaluating[i], evaluating[i+1]);
    			evaluating = remove(evaluating, i);
    			evaluating = remove(evaluating, i);
    			i = 0;
    		}
    	}
    	return evaluating[0];
    }
}