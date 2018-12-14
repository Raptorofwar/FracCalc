package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
		String command = "";
		while(true) {
	    	System.out.print("Calculate: ");
			command = input.nextLine();
			if(command.equals("quit")) {
				break;
			}
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
    	
    	//takes in an expression, produces an answer
    	
    	int posStartParen = -1;
    	int posFinParen = -1;
    	String[] expression = toImproperFrac(input.split(" "));
    	int position = 0;
    	do {
    		if(expression[position].equals("(")) {
    			posStartParen = position;
    		}else if(expression[position].equals(")")) {
    			posFinParen = position;
    		}
    		if(posStartParen >= 0 && posFinParen >= 0) {
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
    		position++;
    	}while(position < expression.length);
    	return toMixedNum(evaluate(expression));
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    
    public static String[] remove(String[] arr, int idx) {
    	
    	// removes element in array arr at index idx
    	
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
    	
    	//changes an array's mixed numbers fractions to improper fractions
    	
    	String[] improperFracs = new String[a.length];
		for(int i = 0; i<a.length; i++) {
			String term = a[i];
			int dividerPos = a[i].indexOf("_");
			if(dividerPos>=0) {
				int vinculum = a[i].indexOf("/");
				int whole = Integer.parseInt(a[i].substring(0, dividerPos));
				int denom = Integer.parseInt(a[i].substring(vinculum+1));
				int num = Integer.parseInt(a[i].substring(dividerPos+1, vinculum));
				if(whole<0) {
					num *= -1;
				}
				term = whole * denom + num + "/" + denom;
			}
			improperFracs[i]=term;
		}
		return improperFracs;
	}
    
    public static String operation(String a, String b, String c) {
		String evaluates = "";
		
		//Evaluates "a b c" where a and c are terms and b is an operation
		
		int numerA = 0;
		int denomA = 1;
		int numerC = 0;
		int denomC = 1;
		
		if(a.indexOf("/") >= 0) {
			numerA = Integer.parseInt(a.substring(0, a.indexOf("/")));
			denomA = Integer.parseInt(a.substring(a.indexOf("/")+1));
		}else {
			numerA = Integer.parseInt(a);
		}
    	
		if(c.indexOf("/") >= 0) {
			numerC = Integer.parseInt(c.substring(0, c.indexOf("/")));
			denomC = Integer.parseInt(c.substring(c.indexOf("/")+1));
		}else {
			numerC = Integer.parseInt(c);
		}
		
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
    
    public static String toMixedNum(String fraction) {
    	
    	//changes one improper fraction into a mixed number (among other things to
    	//refine the final answer)
    	
    	int num = Integer.parseInt(fraction.substring(0, fraction.indexOf("/")));
    	int denom = Integer.parseInt(fraction.substring(fraction.indexOf("/")+1));
    	int whole = num / denom;
    	int newNum = num % denom;
    	
    	if(gcf(newNum, denom) > 1) {
    		int comFac = gcf(newNum, denom);
    		newNum/=comFac;
    		denom/=comFac;
    	}
    	
    	// think about what you need to make a real fraction
    	
    	if(denom < 0) {
    		denom*=-1;
    		newNum*=-1;
    	}
    	
    	String mixedNum = "";
    	if(num == 0) {
    		mixedNum = "0";
    	}else if(whole == 0) {
    		mixedNum = newNum + "/" + denom;
		}else if(newNum == 0) {
			mixedNum = whole + "";
		}else if(whole < 0 && newNum < 0) {
			mixedNum = whole + "_" + newNum * -1 + "/" + denom;
		}else {
			mixedNum = whole + "_" + newNum + "/" + denom;
		}
    	return mixedNum;
    }
    
    public static String evaluate(String[] expression) {
    	
    	//takes in an expression in an array, returns the final result as an improper frac
    	
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

    public static int gcf(int x, int y) {
	
	//input two ints, returns greatest common factor
	
		int placeholder=y;
		int newx=x;
		int newy=y;
		while(newx!=0&&newy!=0) {
			placeholder=newx%newy;
			newx=newy;
	 		newy=placeholder;
		}
		if(newx==0) {
			return absValue(newy);
		}else {
		 	return absValue(newx);
		}
	}
    
    public static int absValue(int absolute) {
	
    	//returns absolute values of two ints
    	
    	if (absolute<0) {
    		return absolute*-1;
    	}else {
    		return absolute;
    	}
    }

}