# A5 DIY Calculator

# Your readme should include the following information. Each student needs to submit all of this information themselves, even when pair programming. 

Group Member Name(s): None

Other collaborators you worked with, including TAs (and feel free to give a shoutout to anyone who was particularly helpful):
Jessica, Elaine

Any references used besides JavaDoc and course materials: References provided in Instructions.

Reflection on your experience with this assignment:
It's really interesting to learn about the postfix method and the shunting yard algorithm, also consider about right associativity compared with left associativity.

This assignment does lend itself to more visual prototyping, so please feel free to go this route. Are you including slides, pseudocode, comments, or something else?
Comments about the carrying out the algorithm are added aside the codes.

- For stacks, are you choosing to use addFirst or addLast to push? Based on this, should you use removeFirst or removeLast to pop?
For stacks, I used the push() and pop() methods in ArrayDeque class to treat it as a 'stack' in the code.
More generally, I think we should choose addFirst() to push and removeFirst() to pop for stacks.

What about for queues, which function will you use to add and which will you use to remove items?
For queues, I used add() method in ArrayDeque class to treat it as a 'queue' in the code.
More generally, I think we should choose addLast() to add and removeFirst() to remove item for queues.

- Look at the documentation for peek. What does this method return? What makes it different from removeFirst?
Peek() would retrieve the head of queue (treating deque as queues) or return null if deque is empty, but would not remove this element. The property of not actually removing the first element makes it different from removeFirst(), which would retrieve and remove the first element.

- Now look at getFirst and getLast. These will allow you to implement peek on the first or last element of your Deque. How do they differ from removeFirst and removeLast?
getFirst() and getLast() differ from removeFirst() and removeLast() by only retrieving the elements but not removing them.