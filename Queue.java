import java.util.Scanner;

// Queue-based menu program simulating bank token numbers (0 = NULL)
public class Queue {
    public static void main(String[] args) {
        final int MAX_SIZE = 10; // fixed capacity
        int[] queue = new int[MAX_SIZE];
        
        // Explicitly initialize all elements to 0 (representing null/empty slots)
        for (int i = 0; i < MAX_SIZE; i++) {
            queue[i] = 0;
        }

        int front = 0;
        int rear = -1;
        int count = 0; // number of elements currently in queue
        
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("----- QUEUE MENU (0 = NULL) -----");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display");
            System.out.println("4. Size");
            System.out.println("5. isEmpty");
            System.out.println("6. isFull");
            System.out.println("7. Exit");
            System.out.println("---------------------------------");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                // 1. Enqueue - add a new token to the back of the queue
                case 1:
                    if (count >= MAX_SIZE) {
                        System.out.println("Overflow! Queue is full.");
                    } else {
                        System.out.print("Enter token number to enqueue (non-zero): ");
                        int value = sc.nextInt();
                        if (value == 0) {
                            System.out.println("0 is reserved as null/empty and cannot be enqueued.");
                            break;
                        }
                        rear = (rear + 1) % MAX_SIZE; // wrap around if needed
                        queue[rear] = value;
                        count++;
                        System.out.println("Token " + value + " enqueued.");
                    }
                    break;

                // 2. Dequeue - remove and show the token at the front, nullifying the slot
                case 2:
                    if (count == 0) {
                        System.out.println("Underflow! Queue is empty.");
                    } else {
                        int removed = queue[front];
                        queue[front] = 0; // Set the dequeued slot to null/0
                        front = (front + 1) % MAX_SIZE; // wrap around if needed
                        count--;
                        System.out.println("Dequeued token: " + removed + " (slot nullified to 0).");
                    }
                    break;

                // 3. Display - show all elements including nullified 0 slots and active queue order
                case 3:
                    System.out.print("Queue Array state (0 represents null): ");
                    for (int i = 0; i < MAX_SIZE; i++) {
                        System.out.print(queue[i] + " ");
                    }
                    System.out.println();

                    if (count > 0) {
                        System.out.println("Active Queue elements (front to rear):");
                        for (int i = 0; i < count; i++) {
                            int idx = (front + i) % MAX_SIZE;
                            System.out.println("Position " + i + " (Index " + idx + "): " + queue[idx]);
                        }
                    } else {
                        System.out.println("Queue is currently empty.");
                    }
                    break;

                // 4. Size - number of tokens currently waiting
                case 4:
                    System.out.println("Current size: " + count + " (Capacity: " + MAX_SIZE + ")");
                    break;

                // 5. isEmpty - check if queue has no tokens
                case 5:
                    System.out.println(count == 0 ? "Queue is empty." : "Queue is not empty.");
                    break;

                // 6. isFull - check if queue has reached capacity
                case 6:
                    System.out.println(count == MAX_SIZE ? "Queue is full." : "Queue is not full.");
                    break;

                // 7. Exit - end the program
                case 7:
                    System.out.println("Exiting Queue program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        sc.close();
    }
}
