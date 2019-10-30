import java.io.IOException;
import java.util.Scanner;

public class Recognizer 
{
	private static Recognizer recognizer;
	public static String input;
	private static int index;
	static int errorFlag = 0;
	
	private Recognizer()
	{
		index = 0;
	}
	
	public static Recognizer getRecognizer()
	{
		if(recognizer == null)
			recognizer = new Recognizer();
		
		return recognizer;
	}
	
	public void javaClass()
	{
		className();
		if(getToken() == 'X')
		{
			className();
		}
		match('B');
		varList();
		match(';');
		while(getToken() == 'P' || getToken() == 'V')
			method();
		match('E');
	}
	
	public void className()
	{
		if(getToken() == 'C' || getToken() == 'D')
			match(getToken());
		else
			error();
	}
	
	public void varList()
	{
		varDef();
		while(getToken() == ',')
			varDef();
	}
	
	public void varDef()
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
	
	public void type()
	{
		if ((getToken() == 'I') || (getToken() == 'S'))
			match(getToken()); 
		else 
			error();
	}
	
	public void varName()
	{
		letter();
		while(getToken() == 'Y' || getToken() == 'Z' || getToken() == '0' || getToken() == '1' || getToken() == '2' || getToken() == '3')
		{
			character();
		}
	}
	
	public void letter()
	{
		if ((getToken() == 'Y') || (getToken() == 'Z'))
			match(getToken()); 
		else 
			error();
	}
	
	public void character()
	{
		 if ((getToken() == '0') || (getToken() == '1') || (getToken() == '2') || (getToken() == '3'))
			 digit();
		 else 
			 letter(); 
	}
	
	public void digit()
	{
		if ((getToken() == '0') || (getToken() == '1') || (getToken() == '2' || (getToken() == '3')))  
			match(getToken());
		else error();
	}
	
	public void integer()
	{
		digit();
		while(getToken() == '0' || getToken() == '1' || getToken() == '2' || getToken() == '3')
			digit();
	}
	
	public void varRef()
	{
		if ((getToken() == 'J') || (getToken() == 'K'))
			match(getToken()); 
		else 
			error();
	}
	
	public void method()
	{
		accessor();
		type();
		methodName();
		match('(');
		if(getToken() == 'I' || getToken() == 'S')
			varList();
		match(')');
		match('B');
		while(getToken() == 'F' || getToken() == 'Y' || getToken() == 'Z' || getToken() == 'W' || getToken() == 'J' || getToken() == 'K');
			statemt();
		returnStatemt();
		match('E');
	}
	
	public void accessor()
	{
		if ((getToken() == 'P') || (getToken() == 'V'))
			match(getToken()); 
		else 
			error();
	}
	
	public void methodName()
	{
		if ((getToken() == 'M') || (getToken() == 'N'))
			match(getToken()); 
		else 
			error();
	}
	
	public void statemt()
	{
		if(getToken() == 'F')
			ifStatemt();
		else if(getToken() == 'Y' || getToken() == 'Z')
		{
			assignStatemt();
			match(';');
		}
		else if(getToken() == 'W')
			whileStatemt();
		else if(getToken() == 'J' || getToken() == 'K')
			methodCall();
		else
			error();
	}
	
	public void ifStatemt()
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
	
	public void assignStatemt()
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
	
	public void mathExpr()
	{
		factor();
		while(getToken() == '+')
			factor();
	}
	
	public void factor()
	{
		oprnd();
		while(getToken() == '*')
			oprnd();
	}
	
	public void oprnd()
	{
		if(getToken() == '0' || getToken() == '1' || getToken() == '2' || getToken() == '3')
			integer();
		else if(getToken() == 'Y' || getToken() == 'Z')
			varName();
		else if(getToken() == '(')
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
	
	public void getVarRef()
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
	
	public void whileStatemt()
	{
		match('W');
		cond();
		match('T');
		match('B');
		while(getToken() == 'F' || getToken() == 'Y' || getToken() == 'Z' || getToken() == 'W' || getToken() == 'J' || getToken() == 'K')
			statemt();
		match('E');
	}
	
	public void cond()
	{
		while(getToken() == '0' || getToken() == '1' || getToken() == '2' || getToken() == '3' || getToken() == 'Y' || getToken() == 'Z' || getToken() == '(' || getToken() == 'J' || getToken() == 'K')
		{
			oprnd();
			operator();
			oprnd();
		}
	}
	
	public void operator()
	{
		if ((getToken() == '<') || (getToken() == '=') || (getToken() == '>' || (getToken() == '!')))  
			match(getToken());
		else error();
	}
	
	public void returnStatemt()
	{
		match('R');
		varName();
		match(';');
	}
	
	public void methodCall()
	{
		varRef();
		match('.');
		methodName();
		match('(');
		if(getToken() == 'I' || getToken() == 'S')
			varList();
		match(')');
	}
	
	public void match(char T)
	{ if (T == getToken())
		  nextToken(); 
	  	else 
	  		error(); 
	}
	
	public char getToken()
	{
		return input.charAt(index);
	}
	
	public void nextToken()
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
