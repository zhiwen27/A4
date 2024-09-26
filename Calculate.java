import java.util.Scanner;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.StringReader;
import java.util.ArrayDeque;

/** 
 * Class to interpret and compute the result of arithmetic expressions 
 * in INFIX format - 
 */
public class Calculate {

  /** Run short test */
  public static void main(String[] args) {
    // if (args.length == 0) {
    //   // If no arguments passed, print instructions
    //   System.err.println("Usage:  java Calculate <expr>");
    // } else {
    //   // Otherwise, echo what was read in for now
    //   Scanner input = new Scanner(new StringReader(args[0]));
    //   while (input.hasNext()) {
    //     System.out.println(input.next());
    //   }
    // }

    Hashtable<Character,Integer> precedence= new Hashtable<>(4);
    precedence.put('+', 0);
    precedence.put('-', 0);
    precedence.put('*', 1);
    precedence.put('/', 1);
    precedence.put('^', 2);

    ArrayDeque<Object> outputQueue = new ArrayDeque<>();
    ArrayDeque<Object> stack = new ArrayDeque<>();

    Scanner scanner = new Scanner(System.in);
    ArrayDeque<Object> input = new ArrayDeque<>();
    Iterator<Object> iterator = input.iterator();{
      while(iterator.hasNext()){
        Object o = iterator.next();
        if (o instanceof Double){
          outputQueue.push(o);
        }
        if (o instanceof Character){
          Object temp = o;
          while ((!stack.isEmpty()) && (precedence.get(temp) < precedence.get(stack.getLast()))){
            outputQueue.push(stack.pop());
            temp = stack.getLast();
          }
          stack.push(o);
        }
        if (o.equals(')')){
          stack.push(o);
        }
        if (o.equals('(')){
          if (!stack.getFirst().equals('(')){
            throw new RuntimeException("Mismatched parentheses.");
          }
          while(!stack.getLast().equals('(')){
            outputQueue.push(stack.pop());;
          }
          stack.pop();
        }
      }
    }
    while (!stack.isEmpty()){
      if (stack.getLast().equals('(') || stack.getLast().equals(')')){
        throw new RuntimeException("Mismatched parentheses.");
      }
      else{
        outputQueue.push(stack.pop());
      }
    }
    double result = Postfix.run(null)
  }
}