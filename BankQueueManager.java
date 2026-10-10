import java.util.Scanner;

// New class name replacing 'Node'
class CustomerNode {
    int tokenNumber;
    CustomerNode next;

    public CustomerNode(int tokenNumber) {
        this.tokenNumber = tokenNumber;
        this.next = null;
    }
}

// New class name replacing 'LinkedListQueue'
public class BankQueueManager {
    private CustomerNode front;
    private CustomerNode rear;
    private int count;

    public BankQueueManager() {
        this.front = null;
        this.rear = null;
        this.count = 0;
    }

    // Enqueue operation: Add integer token to the rear of the queue
    public void enqueueToken(int token) {
        CustomerNode newNode = new CustomerNode(token);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        count++;
    }

    // Dequeue operation: Remove and return the front integer token
    public int dequeueToken() {
        if (isEmpty()) {
            throw new IllegalStateException("The service queue is empty!");
        }
        int token = front.tokenNumber;
        front = front.next;
        count--;
        if (front == null) {
            rear = null; // Reset rear if queue becomes empty
        }
        return token;
    }

    // Peek operation: View front token without removing it
    public int peekNextToken() {
        if (isEmpty()) {
            throw new IllegalStateException("The service queue is empty!");
        }
        return front.tokenNumber;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Get current queue size
    public int getQueueSize() {
        return count;
    }

    // Main method and Interactive Console User Manual
    public static void main(String[] args) {
        BankQueueManager serviceQueue = new BankQueueManager();
        Scanner inputScanner = new Scanner(System.in);
        int menuChoice = -1;

        System.out.println("=================================================");
        System.out.println("   BANK SERVICE COUNTER QUEUE SYSTEM (FIFO)      ");
        System.out.println("=================================================");

        while (menuChoice != 0) {
            System.out.println("\n--- Service Menu (Integer Input Only) ---");
            System.out.println("1. Issue & Enqueue Token (enqueueToken)");
            System.out.println("2. Serve & Dequeue Customer (dequeueToken)");
            System.out.println("3. Peek Next Customer Token (peekNextToken)");
            System.out.println("4. Check if Queue is Empty (isEmpty)");
            System.out.println("5. Check Total Waiting Count (getQueueSize)");
            System.out.println("0. Exit Application");
            System.out.print("Enter your choice (0-5): ");

            if (inputScanner.hasNextInt()) {
                menuChoice = inputScanner.nextInt();
                switch (menuChoice) {
                    case 1:
                        System.out.print("Enter integer Token ID: ");
                        int tokenVal = inputScanner.nextInt();
                        serviceQueue.enqueueToken(tokenVal);
                        System.out.println("Token " + tokenVal + " successfully added to the queue.");
                        break;
                    case 2:
                        try {
                            int servedToken = serviceQueue.dequeueToken();
                            System.out.println("Now serving Token ID: " + servedToken);
                        } catch (IllegalStateException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            int nextToken = serviceQueue.peekNextToken();
                            System.out.println("Next up for service: Token ID " + nextToken);
                        } catch (IllegalStateException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Is queue empty? " + serviceQueue.isEmpty());
                        break;
                    case 5:
                        System.out.println("Total customers waiting: " + serviceQueue.getQueueSize());
                        break;
                    case 0:
                        System.out.println("Exiting application. Have a wonderful day!");
                        break;
                    default:
                        System.out.println("Invalid option! Please enter a number between 0 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                inputScanner.next(); // Clear invalid input token
            }
        }
        inputScanner.close();
    }
}
