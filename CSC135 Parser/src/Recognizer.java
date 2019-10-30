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
	
	public void parse(String input)
	{
		javaClass();
	}
	
	public void javaClass()
	{
		className();
		if()//token == X
		{
			className();
		}
		//B
		varList();
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
	}
	
	public void varDef()
	{
		type();
		varName();
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
		
	}
	
	public void ifStatemt()
	{
		
	}
	
	public void assignStatemt()
	{
		
	}
	
	public void mathExpr()
	{
		
	}
	
	public void factor()
	{
		
	}
	
	public void oprnd()
	{
		
	}
	
	public void getVarRef()
	{
		
	}
	
	public void whileStatemt()
	{
		
	}
	
	public void cond()
	{
		
	}
	
	public void operator()
	{
		
	}
	
	public void returnStatemt()
	{
		
	}
	
	public void methodCall()
	{
		
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
	
	public void printError()
	{
		System.out.println("Unexpected token \"" + getToken() + "\" at position " + index);
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
