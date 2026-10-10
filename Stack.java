import java.util.Scanner;

// Stack-based menu program simulating a pile of plates (0 = NULL)
public class Stack {
    public static void main(String[] args) {
        final int MAX_SIZE = 10; // fixed capacity
        int[] stack = new int[MAX_SIZE];
        
        // Explicitly initialize all elements to 0 (representing null/empty slots)
        for (int i = 0; i < MAX_SIZE; i++) {
            stack[i] = 0;
        }

        int top = -1; //-1 means empty
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("----- STACK MENU (0 = NULL) -----");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display");
            System.out.println("4. Size");
            System.out.println("5. isEmpty");
            System.out.println("6. isFull");
            System.out.println("7. Exit");
            System.out.println("---------------------------------");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                // 1. Push - add a new plate on top
                case 1:
                    if (top >= MAX_SIZE - 1) {
                        System.out.println("Overflow! Stack is full.");
                    } else {
                        System.out.print("Enter value to push (non-zero): ");
                        int value = sc.nextInt();
                        if (value == 0) {
                            System.out.println("0 is reserved as null/empty and cannot be pushed onto the stack.");
                            break;
                        }
                        top++;
                        stack[top] = value;
                        System.out.println("Value " + value + " pushed onto stack.");
                    }
                    break;

                // 2. Pop - remove and show the top plate, nullifying the slot
                case 2:
                    if (top == -1) {
                        System.out.println("Underflow! Stack is empty.");
                    } else {
                        int removed = stack[top];
                        stack[top] = 0; // Set the popped slot to null/0
                        top--;
                        System.out.println("Popped value: " + removed + " (slot nullified to 0).");
                    }
                    break;

                // 3. Display - show all elements including nullified 0 slots
                case 3:
                    System.out.print("Stack Array state (0 represents null): ");
                    for (int i = 0; i < MAX_SIZE; i++) {
                        System.out.print(stack[i] + " ");
                    }
                    System.out.println();
                    if (top != -1) {
                        System.out.println("Active Stack elements (top to bottom):");
                        for (int i = top; i >= 0; i--) {
                            System.out.println("Index " + i + ": " + stack[i]);
                        }
                    } else {
                        System.out.println("Stack is currently empty.");
                    }
                    break;

                // 4. Size - number of plates currently stacked
                case 4:
                    System.out.println("Current size: " + (top + 1) + " (Capacity: " + MAX_SIZE + ")");
                    break;

                // 5. isEmpty - check if stack has no plates
                case 5:
                    System.out.println(top == -1 ? "Stack is empty." : "Stack is not empty.");
                    break;

                // 6. isFull - check if stack has reached capacity
                case 6:
                    System.out.println(top == MAX_SIZE - 1 ? "Stack is full." : "Stack is not full.");
                    break;

                // 7. Exit - end the program
                case 7:
                    System.out.println("Exiting Stack program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        sc.close();
    }
}
