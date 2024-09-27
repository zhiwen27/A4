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
    operator.add('+');
    operator.add('-');
    operator.add('*');
    operator.add('/');
    operator.add('^');
    ArrayList<Integer> precedence = new ArrayList<>(5);
    precedence.add(0);
    precedence.add(0);
    precedence.add(1);
    precedence.add(1);
    precedence.add(2);

    ArrayDeque<Object> outputQueue = new ArrayDeque<>();
    ArrayDeque<Object> stack = new ArrayDeque<>();

    Scanner scanner = new Scanner(System.in);
    String inputLine = scanner.nextLine();
    ArrayDeque<Object> input = Tokenizer.readTokens(inputLine);
    Iterator<Object> iterator = input.iterator();{
      while(iterator.hasNext()){
        Object o = iterator.next();
        if (o instanceof Double){
         outputQueue.push(o);
        }
        if (o instanceof Character){
          if ((!o.equals('(')) && (!o.equals(')'))){
            if (operator.contains((Character)o)){
              while ((!stack.isEmpty()) && (operator.contains(stack.getLast()))){
                if ((precedence.get(operator.indexOf(o)) <= precedence.get(operator.indexOf((Character)stack.getLast())))){
                  outputQueue.push(stack.pop());
                }
              }
              stack.push(o);
            }
            stack.push(o);
          }
          if (o.equals(')')){
            stack.push(o);
          }
//         if (o.equals('(')){
//           while(!stack.getLast().equals('(')){
//             outputQueue.push(stack.pop());;
//           }
//           stack.pop();
//         }
        }
      }
    }
    // should reverse it
    Iterator<Object> iterator2 = outputQueue.iterator();
    while(iterator2.hasNext()){
      Object o = iterator2.next();
      System.out.println(o);
    }
  }
}