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

  /**
   * A function that does Infix calculation
   * @param inputLine input string
   * @return calculated double result
   */
  public static double run(String inputLine){
    // create a arraylist that contains all the possible operators
    ArrayList<Character> operator = new ArrayList<>(5);
    operator.add('+'); operator.add('-'); operator.add('*'); operator.add('/'); operator.add('^');
    // create a arraylist that contains the corresponding precedences for all the possible operators
    ArrayList<Integer> precedence = new ArrayList<>(5);
    precedence.add(0); precedence.add(0); precedence.add(1); precedence.add(1); precedence.add(2);

    // create two instances of ArrayDeque<Object>: one operate as a stack, and the other operate as a queue
    ArrayDeque<Object> outputQueue = new ArrayDeque<>();
    ArrayDeque<Object> stack = new ArrayDeque<>();

    ArrayDeque<Object> input = Tokenizer.readTokens(inputLine);
    Iterator<Object> iterator = input.iterator();{
      // while there are tokens to be read
      while(iterator.hasNext()){
        // read a token
        Object o = iterator.next();
        // if the token is a number, add it to the output queue
        if (o instanceof Double){
         outputQueue.add(o);
        }
        if (o instanceof Character){
          // if the token is an operator
          if ((operator.contains((Character)o))){
            // consider left associativity: while there is an operator token at the top of the stack,
            // and it has greater than or equal precedence than the queue operator
            if (!o.equals('^')){
              while ((!stack.isEmpty()) && (operator.contains((Character)stack.getFirst()) && (precedence.get(operator.indexOf(o)) <= precedence.get(operator.indexOf((Character)stack.getFirst()))))){
                outputQueue.add(stack.pop()); // pop the stack operator off the stack and add it to the output queue
              }
              stack.push(o); // when no more high-precedence stack operators remain, push the queue operator onto the stack
            }
            // consider right associativity: while there is an operator token at the top of the stack
            else if (o.equals('^')){
              // when the stack operator has greater than precedence than the queue operator (leave the equal precedence one on the stack)
              while ((!stack.isEmpty()) && (operator.contains((Character)stack.getFirst()) && (precedence.get(operator.indexOf(o)) < precedence.get(operator.indexOf((Character)stack.getFirst()))))) {
                outputQueue.add(stack.pop()); // pop the stack operator off the stack and add it to the output queue
              }
              stack.push(o); // when no more high-precedence stack operators remain, push the queue operator onto the stack
            }
          }
          // if the token is a left parenthesis, push it onto the stack
          if (o.equals('(')){
            stack.push(o);
          }
          // if the token is a right parenthesis
          if (o.equals(')')){
            // till the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue
            while(!stack.getFirst().equals('(')){
              outputQueue.add(stack.pop());;
            }
            // if the stack runs out without finding a left parenthesis, report mismatched parentheses
            if (stack.isEmpty()){
              System.out.println("Mismatched Parenthesis.");
            }
            // pop the left parenthesis from the stack (but not onto the output queue)
            else{
              stack.pop();
            }
          }
        }
      }
      // while there are still tokens in the stack
      while (!stack.isEmpty()){
        // if it is an operator, pop it onto the output queue
        if ((stack.getFirst().equals('(')) || (stack.getFirst().equals(')'))){
          System.out.println("Mismatched Parenthesis.");
          stack.pop();
        }
        // if the token on the top of the stack is a parenthesis, report mismatched parentheses
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
    // calculate desired output
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