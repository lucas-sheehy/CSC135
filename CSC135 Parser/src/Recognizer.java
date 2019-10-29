import java.util.Scanner;

public class Recognizer 
{
	private static Recognizer recognizer;
	public static String input;
	private static int index;
	
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
			nextToken();
		else
			printError();
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
		//I OR S
	}
	
	public void varName()
	{
		
	}
	
	public void letter()
	{
		
	}
	
	public void character()
	{
		
	}
	
	public void digit()
	{
		
	}
	
	public void integer()
	{
		
	}
	
	public void varRef()
	{
		
	}
	
	public void method()
	{
		
	}
	
	public void accessor()
	{
		
	}
	
	public void methodName()
	{
		
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
	
	public char getToken()
	{
		return input.charAt(index);
	}
	
	public void nextToken()
	{
		index++;
	}
	
	public void printError()
	{
		System.out.println("Unexpected token \"" + getToken() + "\" at position " + index);
	}
	
	public static void main(String[] args) 
	{
		
		Scanner scanner = new Scanner(System.in);
		Recognizer recognizer = Recognizer.getRecognizer();
		
		System.out.println("Enter an input stream with $ at the end:\n");
		input = scanner.nextLine();
		
		recognizer.parse(input);
		scanner.close();
	}
}
