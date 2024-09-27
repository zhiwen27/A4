import java.util.Scanner;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

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

    ArrayList<Character> operator = new ArrayList<>(5);
    operator.add('+'); operator.add('-'); operator.add('*'); operator.add('/'); operator.add('^');
    ArrayList<Integer> precedence = new ArrayList<>(5);
    precedence.add(0); precedence.add(0); precedence.add(1); precedence.add(1); precedence.add(2);

    ArrayDeque<Object> outputQueue = new ArrayDeque<>();
    ArrayDeque<Object> stack = new ArrayDeque<>();

    Scanner scanner = new Scanner(System.in);
    String inputLine = scanner.nextLine();
    ArrayDeque<Object> input = Tokenizer.readTokens(inputLine);
    Iterator<Object> iterator = input.iterator();{
      while(iterator.hasNext()){
        Object o = iterator.next();
        if (o instanceof Double){
         outputQueue.add(o);
        }
        if (o instanceof Character){
          if ((operator.contains((Character)o))){
            if (!o.equals('^')){
              while ((!stack.isEmpty()) && (operator.contains((Character)stack.getFirst()) && (precedence.get(operator.indexOf(o)) <= precedence.get(operator.indexOf((Character)stack.getFirst()))))){
                outputQueue.add(stack.pop());
              }
              stack.push(o);
            }
            else if (o.equals('^')){
              while ((!stack.isEmpty()) && (operator.contains((Character)stack.getFirst()) && (precedence.get(operator.indexOf(o)) < precedence.get(operator.indexOf((Character)stack.getFirst()))))) {
                outputQueue.add(stack.pop());
              }
              stack.push(o);
            }
          }
          if (o.equals('(')){
            stack.push(o);
          }
          if (o.equals(')')){
            while(!stack.getFirst().equals('(')){
              outputQueue.add(stack.pop());;
            }
            stack.pop();
            // If stack runs out: report paren mismatch
          }
        }
      }
      while (!stack.isEmpty()){
        if ((stack.getFirst().equals('(')) || (stack.getFirst().equals(')'))){
          System.out.println("Mismatched Parenthesis.");
          stack.pop();
        }
        else if (operator.contains((Character)stack.getFirst())){
          outputQueue.add(stack.pop());
        }
      }
    }
    Iterator<Object> iterator2= outputQueue.iterator();
    String convertedInput= "";
    while(iterator2.hasNext()){
      convertedInput += iterator2.next() + " ";
    }
    System.out.println(Postfix.run(convertedInput));
    scanner.close();
  }
}