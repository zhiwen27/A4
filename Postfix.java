import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.io.StringReader;

/** 
 * Class to interpret and compute the result of arithmetic expressions 
 * in POSTFIX format - 
 */
public class Postfix {

  /**
   * A function that does Postfix calculation
   * @param input input string
   * @return calculated double result
   */
  public static double run(String input){
    ArrayDeque<Object> inputQueue = Tokenizer.readTokens(input);
    ArrayDeque<Object> stack = new ArrayDeque<>();
    Iterator<Object> iterator = inputQueue.iterator();
    {
      while(iterator.hasNext()){
        Object o = iterator.next();
         if (o instanceof Double){
           stack.push(o); // push the numbers into the stack
         }
         if (o instanceof Character){
          // pop the last two elements and add them
          if (o.equals('+')){
            double last = (double)stack.pop();
            double first = (double)stack.pop();
            stack.push(last + first);
          }
          // pop the last two elements and multiply them
          else if (o.equals('*')){
            double last = (double)stack.pop();
            double first = (double)stack.pop();
            stack.push(last * first);
          }
          // pop the last two elements and subtract them (second last - last)
          else if (o.equals('-')){
            double last = (double)stack.pop();
            double first = (double)stack.pop();
            stack.push(first - last);
          }
          // pop the last two elements and devide them (second last / last)
          else if (o.equals('/')){
            double last = (double)stack.pop();
            double first = (double)stack.pop();
            stack.push(first / last);
          }
          // pop the last two elements and do (second last)^(last)
          else if (o.equals('^')){
            double last = (double)stack.pop();
            double first = (double)stack.pop();
            stack.push(Math.pow(first,last));
          }
        }
      }
    }
    return (double)stack.getFirst();
  }

  /** Run short test */
  public static void main(String[] args) {
    String input = "";
    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Postfix <expr>");
    } else {
      // Otherwise, echo what was read in for now
      Scanner scannerTest = new Scanner(new StringReader(args[0]));
      while (scannerTest.hasNext()) {
        input += scannerTest.next() + " ";
      }
      scannerTest.close();
    }
    System.err.println(Postfix.run(input));
  }
}