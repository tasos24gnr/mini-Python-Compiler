import java.io.*;
import tony.lexer.Lexer;
import tony.parser.Parser;
import tony.node.*;
import java.util.*;

public class ParserTest
{
  public static void main(String[] args)
  {
    try
    {
      Parser parser =
        new Parser(
        new Lexer(
        new PushbackReader(
        new FileReader(args[0].toString()), 1024)));

     Hashtable symtable =  new Hashtable();
     Start ast = parser.parse();
	 //ast.apply(new ASTPrinter());

     ast.apply(new Visitoras(symtable));
     /* Gia ton deutero visitor grapste thn entolh
      * ast.apply(new mysecondvisitor(symtable));
      */
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

