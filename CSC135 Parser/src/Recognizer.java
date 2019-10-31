/*Team TGIF 
 * Lucas Sheehy, Matthew Calderon
 * 
 * To use:
 * Run the program and enter a string with no whitespace ending in '$'
 * 
 * 
 * Test Cases:
<javaclass>, <classname>, <varlist>, <vardef>, <type>, <varname>, <letter>
CBIY;E$

<javaclass>, <classname>, <varlist>, <vardef>, <classname>, <classname>, <varref>
CBCJ;E$

Goes into method
<javaclass>, <classname>, <classname>, <varlist>, <vardef>, <vardef>, <method>, <accessor>, <type>, <methodname>, <returnstatement>,
 <varname>, <letter>
CXCBIY,CJ;PIM()BRY;EE$

Goes into varlist
<javaclass>, <classname>, <classname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <vardef>, <classname>, <varref>, <method>,
 <accessor>, <type>, <methodname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <char>, <letter>, <char>, <digit>,
  <returnstatement>, <varname>, <letter>
CXCBIY,CJ;PIM(SZY0)BRY;EE$

Goes into statemnt > ifstatemt > etc
<javaclass>, <classname>, <classname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <vardef>, <classname>, <varref>, <method>, 
<accessor>, <type>, <methodname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <char>, <letter>, <char>, <digit>, <statement>, 
<ifstatemnt>, <cond>, <oprnd>, <integer>, <digit>,  <operator>, <oprnd>, <integer>, <digit>, <returnstatement>, <varname>, <letter>
CXCBIY,CJ;PIM(SZY0)BF(0=0)TBERY;EE$

Goes into statemnt > assignstatemt first side > etc
<javaclass>, <classname>, <classname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <vardef>, <classname>, <varref>, <method>,
 <accessor>, <type>, <methodname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <char>, <letter>, <char>, <digit>, <statement>,
  <assignstatemnt>, <varref>, <getvarref>, <classname>, <returnstatement>, <varname>
CXCBIY,CJ;PIM(SZY0)BK=OC();RY;EE$

Goes into statemnt > assignstatemt second side > etc
<javaclass>, <classname>, <classname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <vardef>, <classname>, <varref>, <method>,
 <accessor>, <type>, <methodname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <char>, <letter>, <char>, <digit>, <statement>,
  <assignstatemnt>, <varname>, <letter>, <mathexpr>, <factor>, <oprnd>, <integer>, <digit>, <factor>, <oprnd>, <varname>, <letter>,
   <oprnd>, <methodcall>, <varref>, <methodname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <returnstatement>, <varname>
CXCBIY,CJ;PIM(SZY0)BY=0+Z*J.N(SZ);RY;EE$

Goes into statemnt > whilestatemt > etc
<javaclass>, <classname>, <classname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <vardef>, <classname>, <varref>, <method>, 
<accessor>, <type>, <methodname>, <varlist>, <vardef>, <type>, <varname>, <letter>, <char>, <letter>, <char>, <digit>, <statement>,
 <whilestatemt>, <cond>, <returnstatement>, <varname>
CXCBIY,CJ;PIM(SZY0)BW(0=0)TBERY;EE$
 */

import java.io.IOException;
import java.util.Scanner;

public class Recognizer 
{
	private static Recognizer recognizer;
	private static String input;
	private static int index;
	static int errorFlag = 0;
	
	private Recognizer()
	{
		index = 0;
	}
	
	private static Recognizer getRecognizer()
	{
		if(recognizer == null)
			recognizer = new Recognizer();
		
		return recognizer;
	}
	
	private void javaClass()
	{
		className();
		if(getToken() == 'X')
		{
			match('X');
			className();
		}
		match('B');
		varList();
		match(';');
		while(getToken() == 'P' || getToken() == 'V')
			method();
		match('E');
	}
	
	private void className()
	{
		if(getToken() == 'C' || getToken() == 'D')
			match(getToken());
		else
			error();
	}
	
	private void varList()
	{
		varDef();
		while (getToken() == ',')
		{
			match(',');
			varDef();
		}
	}
	
	private void varDef()
	{
		if(getToken() == 'I' || getToken() == 'S')
		{
			type();
			varName();
		}
		else if(getToken() == 'C' || getToken() == 'D')
		{
			className();
			varRef();
		}
		else
			error();
	}
	
	private void type()
	{
		if ((getToken() == 'I') || (getToken() == 'S'))
			match(getToken()); 
		else 
			error();
	}
	
	private void varName()
	{
		letter();
		while ((getToken() == '0') || (getToken() == '1') || (getToken() == '2') || (getToken() == '3') ||(getToken() == 'Y') || (getToken() == 'Z'))
				character();
	}
	
	private void letter()
	{
		if ((getToken() == 'Y') || (getToken() == 'Z'))
			match(getToken()); 
		else 
			error();
	}
	
	private void character()
	{
		 if ((getToken() == '0') || (getToken() == '1') || (getToken() == '2') || (getToken() == '3'))
			 digit();
		 else if((getToken() == 'Y') || (getToken() == 'Z'))
			 letter(); 
		 else
			 error();
	}
	
	private void digit()
	{
		if ((getToken() == '0') || (getToken() == '1') || (getToken() == '2' || (getToken() == '3')))  
			match(getToken());
		else error();
	}
	
	private void integer()
	{
		digit();
		while(getToken() == '0' || getToken() == '1' || getToken() == '2' || getToken() == '3')
			digit();
	}
	
	private void varRef()
	{
		if ((getToken() == 'J') || (getToken() == 'K'))
			match(getToken()); 
		else 
			error();
	}
	
	private void method()
	{
		accessor();
		type();
		methodName();
		match('(');
		if(getToken() == 'I' || getToken() == 'S' || getToken() == 'C' || getToken() == 'D')
			varList();
		match(')');
		match('B');
		while(getToken() == 'F' || getToken() == 'Y' || getToken() == 'Z' || getToken() == 'W' || getToken() == 'J' || getToken() == 'K')
			statemt();
		returnStatemt();
		match('E');
	}
	
	private void accessor()
	{
		if ((getToken() == 'P') || (getToken() == 'V'))
			match(getToken()); 
		else 
			error();
	}
	
	private void methodName()
	{
		if ((getToken() == 'M') || (getToken() == 'N'))
			match(getToken()); 
		else 
			error();
	}
	
	private void statemt()
	{
		if(getToken() == 'F')
			ifStatemt();
		else if(getToken() == 'Y' || getToken() == 'Z' || getToken() == 'J' || getToken() == 'K')
		{
			assignStatemt();
			match(';');
		}
		else if(getToken() == 'W')
			whileStatemt();
		else
			error();
	}
	
	private void ifStatemt()
	{
		match('F');
		cond();
		match('T');
		match('B');
		while(getToken() == 'F' || getToken() == 'Y' || getToken() == 'Z' || getToken() == 'W' || getToken() == 'J' || getToken() == 'K')
			statemt();
		match('E');
		if(getToken() == 'L')
		{
			match('L');
			match('B');
			while(getToken() == 'F' || getToken() == 'Y' || getToken() == 'Z' || getToken() == 'W' || getToken() == 'J' || getToken() == 'K')
				statemt();
			match('E');
		}
	}
	
	private void assignStatemt()
	{
		if(getToken() == 'Y' || getToken() == 'Z')
		{
			varName();
			match('=');
			mathExpr();
		}
		else if(getToken() == 'J' || getToken() == 'K')
		{
			varRef();
			match('=');
			getVarRef();
		}
		else
			error();
	}
	
	private void mathExpr()
	{
		factor();
		while (getToken() == '+') 
		{
			match('+');
			factor();
		}
			
	}
	
	private void factor()
	{
		oprnd();
		while (getToken() == '*')
		{
			match('*');
			oprnd();
		}
	}
	
	private void oprnd()
	{
		if((getToken() == '0') || (getToken() == '1') || (getToken() == '2') || (getToken() == '3'))
			integer();
		else if ((getToken() == 'Y') || (getToken() == 'Z'))
			varName();
		else if (getToken() == '(') 
		{
			match('(');
			mathExpr();
			match(')');
		}
		else if(getToken() == 'J' || getToken() == 'K')
			methodCall();
		else
			error();		
	}
	
	private void getVarRef()
	{
		if(getToken() == 'O')
		{
			match('O');
			className();
			match('(');
			match(')');
		}
		else if(getToken() == 'J' || getToken() == 'K')
			methodCall();
		else
			error();
	}
	
	private void whileStatemt()
	{
		match('W');
		cond();
		match('T');
		match('B');
		while(getToken() == 'F' || getToken() == 'Y' || getToken() == 'Z' || getToken() == 'W' || getToken() == 'J' || getToken() == 'K')
			statemt();
		match('E');
	}
	
	private void cond()
	{
		match('(');
		oprnd();
		operator();
		oprnd();
		match(')');
	}
	
	private void operator()
	{
		if ((getToken() == '<') || (getToken() == '=') || (getToken() == '>') || (getToken() == '!'))
			match(getToken()); 
		else 
			error();
	}
	
	private void returnStatemt()
	{
		match('R');
		varName();
		match(';');
	}
	
	private void methodCall()
	{
		varRef();
		match('.');
		methodName();
		match('(');
		if(getToken() == 'I' || getToken() == 'S')
			varList();
		match(')');
	}
	
	private void match(char T)
	{
		if (T == getToken())
		{
			nextToken();
		}
	  	else 
	  		error(); 
	}
	
	private char getToken()
	{
		return input.charAt(index);
	}
	
	private void nextToken()
	{
		if (index < (input.length()-1)) index++; 
	}
	
	private void error()
	{
	    System.out.println("Unexpected token \"" + getToken() + "\" at position " + index);
	    errorFlag = 1;
	    nextToken();
	}
	
	private void start()
	{
	    javaClass();
	    match('$');

	    if (errorFlag == 0)
	      System.out.println("legal." + "\n");
	    else
	      System.out.println("errors found." + "\n");
	}
	
	public static void main(String[] args) throws IOException
	{
		
		Scanner scanner = new Scanner(System.in);
		Recognizer recognizer = Recognizer.getRecognizer();
		
		System.out.println("Enter an input stream with $ at the end:\n");
		input = scanner.nextLine();
		
		recognizer.start();
		scanner.close();
	}
}
