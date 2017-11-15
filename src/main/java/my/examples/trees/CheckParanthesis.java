package my.examples.trees;

public class CheckParanthesis {

    Stack stack = new Stack(100);

    class Stack {
        int top = -1;
        char[] items;
        int maxElements;

        public Stack(int size) {
            items = new char[size];
            maxElements = size - 1;
        }

        public void push(char c) {
            if (top == maxElements) {
                System.out.println("Stack is full");
                // throw Full exception
            } else {
                items[++top] = c;
            }
        }

        public char pop() {
            if (top == -1) {
                System.out.println("UnderFlow error");
                return '\0';
            }
            char element = items[top];
            top--;
            return element;
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    public boolean isMatchingChar(char c1, char c2) {
        if (c1 == '{' && c2 == '}')
            return true;
        else if (c1 == '[' && c2 == ']')
            return true;
        else if (c1 == '(' && c2 == ')')
            return true;
        else
            return false;
    }

    public boolean checkForParans(char[] c) {
        for (int i=0; i<c.length; i++) {
            if (c[i] == '{' || c[i] == '[' || c[i] == '(')
                stack.push(c[i]);
            else if (c[i] == '}' || c[i] == ']' || c[i] == ')') {
                final boolean isMatch = isMatchingChar(stack.pop(), c[i]);
                if (!isMatch)
                    return false;
            } else {
                // skip
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        char exp[] = {'{','(',')','}','[',']'};
        CheckParanthesis cp = new CheckParanthesis();
        if (cp.checkForParans(exp))
            System.out.println("Balanced ");
        else
            System.out.println("Not Balanced ");


        System.out.println(118 % 16);
        System.out.println(118 & 15);

        System.out.println(1 << 4);
    }

}
