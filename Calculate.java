import java.util.Scanner;
import java.util.Iterator;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

/** 
 * Class to interpret and compute the result of arithmetic expressions 
 * in INFIX format - 
 */
public class Calculate {

  public static double run(String inputLine){
    ArrayList<Character> operator = new ArrayList<>(5);
    operator.add('+'); operator.add('-'); operator.add('*'); operator.add('/'); operator.add('^');
    ArrayList<Integer> precedence = new ArrayList<>(5);
    precedence.add(0); precedence.add(0); precedence.add(1); precedence.add(1); precedence.add(2);

    ArrayDeque<Object> outputQueue = new ArrayDeque<>();
    ArrayDeque<Object> stack = new ArrayDeque<>();

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
            if (stack.isEmpty()){
              System.out.println("Mismatched Parenthesis.");
            }
            else{
              stack.pop();
            }
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

    return Postfix.run(convertedInput);
  }

  /** Run short test */
  public static void main(String[] args) {
    String inputLine = "";
    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Postfix <expr>");
    } else {
      // Otherwise, echo what was read in for now
      Scanner scannerTest = new Scanner(new StringReader(args[0]));
      while (scannerTest.hasNext()) {
        inputLine += scannerTest.next() + " ";
      }
      scannerTest.close();
    }
    System.err.println(Calculate.run(inputLine));
  }
}