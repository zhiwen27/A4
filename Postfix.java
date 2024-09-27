import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.io.StringReader;

/** 
 * Class to interpret and compute the result of arithmetic expressions 
 * in POSTFIX format - 
 */
public class Postfix {

  public static double run(String input){
    ArrayDeque<Object> inputQueue = Tokenizer.readTokens(input);
    ArrayDeque<Object> stack = new ArrayDeque<>();
    Iterator<Object> iterator = inputQueue.iterator();
    {
      while(iterator.hasNext()){
        Object o = iterator.next();
         if (o instanceof Double){
           stack.push(o);
         }
         if (o instanceof Character){
           if (o.equals('+')){
             double last = (double)stack.pop();
             double first = (double)stack.pop();
             stack.push(last + first);
           }
           else if (o.equals('*')){
             double last = (double)stack.pop();
             double first = (double)stack.pop();
             stack.push(last * first);
           }
           else if (o.equals('-')){
             double last = (double)stack.pop();
             double first = (double)stack.pop();
             stack.push(last - first);
           }
           else if (o.equals('/')){
             double last = (double)stack.pop();
             double first = (double)stack.pop();
             stack.push(last / first);
           }
         }
      }
    }
    return (double)stack.getFirst();
  }

  /** Run short test */
  public static void main(String[] args) {
    // if (args.length == 0) {
    //   // If no arguments passed, print instructions
    //   System.err.println("Usage:  java Postfix <expr>");
    // } else {
    //   // Otherwise, echo what was read in for now
    //   Scanner scannerTest = new Scanner(new StringReader(args[0]));
    //   while (scannerTest.hasNext()) {
    //     System.out.println(scannerTest.next());
    //   }
    //   scannerTest.close();
    // }
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    System.err.println(Postfix.run(input));
    sc.close();
  }
}