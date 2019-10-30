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
	
	private void parse(String input)
	{
		javaClass();
	}
	
	private void javaClass()
	{
		className();
		match('B');
		varList();
		while(getToken() == 'P') {
			method();
		}
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
		if ((getToken() == 'I') || (getToken() == 'S'))
		{
			type();
			varName();
		}
		else 
		{
			className();
			varRef();
		}
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
		 else 
			 letter(); 
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
		while ((getToken() == '0') || (getToken() == '1') || (getToken() == '2') || (getToken() == '3'))
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
		
	}
	
	private void ifStatemt()
	{
		
	}
	
	private void assignStatemt()
	{
		
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
		else
			methodCall();		
	}
	
	private void getVarRef()
	{
		if (getToken() == 'O')
		{
			match('O');
			className();
		}
		else
			methodCall();
	}
	
	private void whileStatemt()
	{
		if (getToken() == 'W') 
		{
			match('W');
			cond();
			match('T');
			match('B');
			statemt();
			match('E');
		}
		else
			error();
	}
	
	private void cond()
	{
		
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
		if (getToken() == '(') 
		{
			match('(');
			varList();
			match(')');
		}
	}
	
	private void match(char T)
	{ if (T == getToken())
		  nextToken(); 
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
