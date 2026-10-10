import java.util.Scanner;

// Node class representing an individual freight train carriage with an integer ID
class CarriageNode {
    int carriageId;
    CarriageNode next;

    public CarriageNode(int carriageId) {
        this.carriageId = carriageId;
        this.next = null;
    }
}

// Linked List implementation for managing the train sequence
class TrainLinkedList {
    private CarriageNode head = null;
    private CarriageNode tail = null;
    private int size = 0;

    public TrainLinkedList() {}

    // 1. Attach cargo to the rear of the train
    public void addLast(int id) {
        CarriageNode newCarriage = new CarriageNode(id);
        if (this.head == null) {
            this.head = newCarriage;
            this.tail = newCarriage;
        } else {
            this.tail.next = newCarriage;
            this.tail = newCarriage;
        }
        ++this.size;
    }

    // 2. Attach priority carriage or engine right behind the front
    public void addFirst(int id) {
        CarriageNode newCarriage = new CarriageNode(id);
        newCarriage.next = this.head;
        this.head = newCarriage;
        if (this.tail == null) {
            this.tail = newCarriage;
        }
        ++this.size;
    }

    // 3. Insert carriage at a specific index position
    public void addAt(int index, int id) {
        if (index >= 0 && index <= this.size) {
            if (index == 0) {
                this.addFirst(id);
            } else if (index == this.size) {
                this.addLast(id);
            } else {
                CarriageNode newCarriage = new CarriageNode(id);
                CarriageNode curr = this.head;
                for (int i = 0; i < index - 1; ++i) {
                    curr = curr.next;
                }
                newCarriage.next = curr.next;
                curr.next = newCarriage;
                ++this.size;
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid position index!");
        }
    }

    // 4. Uncouple a carriage by searching its integer ID
    public boolean remove(int id) {
        if (this.head == null) {
            return false;
        } else if (this.head.carriageId == id) {
            this.head = this.head.next;
            if (this.head == null) {
                this.tail = null;
            }
            --this.size;
            return true;
        } else {
            for (CarriageNode curr = this.head; curr.next != null; curr = curr.next) {
                if (curr.next.carriageId == id) {
                    if (curr.next == this.tail) {
                        this.tail = curr;
                    }
                    curr.next = curr.next.next;
                    --this.size;
                    return true;
                }
            }
            return false;
        }
    }

    // 5. Uncouple a carriage by its index position
    public void removeAt(int index) {
        if (index >= 0 && index < this.size) {
            if (index == 0) {
                this.head = this.head.next;
                if (this.head == null) {
                    this.tail = null;
                }
                --this.size;
            } else {
                CarriageNode curr = this.head;
                for (int i = 0; i < index - 1; ++i) {
                    curr = curr.next;
                }
                if (curr.next == this.tail) {
                    this.tail = curr;
                }
                curr.next = curr.next.next;
                --this.size;
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid position index!");
        }
    }

    // 6. Inspect carriage ID at a specific index
    public int get(int index) {
        if (index >= 0 && index < this.size) {
            CarriageNode curr = this.head;
            for (int i = 0; i < index; ++i) {
                curr = curr.next;
            }
            return curr.carriageId;
        } else {
            throw new IndexOutOfBoundsException("Invalid position index!");
        }
    }

    // 7. Search if a carriage ID exists in the train
    public boolean contains(int id) {
        for (CarriageNode curr = this.head; curr != null; curr = curr.next) {
            if (curr.carriageId == id) {
                return true;
            }
        }
        return false;
    }

    // 8. Reverse the order of the train sequence
    public void reverse() {
        if (this.head != null && this.head.next != null) {
            CarriageNode prev = null;
            CarriageNode curr = this.head;
            CarriageNode nextNode;
            this.tail = this.head;
            while (curr != null) {
                nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }
            this.head = prev;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // 10. Display the full train manifest layout
    public void printTrain() {
        CarriageNode curr = this.head;
        StringBuilder sb = new StringBuilder();
        sb.append("Engine [HEAD] -> ");
        while (curr != null) {
            sb.append("[").append(curr.carriageId).append("]");
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        sb.append(" -> [TAIL]");
        System.out.println(sb.toString());
    }
}

// Interactive Console User Manual Application
public class TrainManagementSystem {
    public static void main(String[] args) {
        TrainLinkedList train = new TrainLinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("=================================================");
        System.out.println("   FREIGHT TRAIN CARRIAGE MANAGEMENT SYSTEM      ");
        System.out.println("=================================================");

        while (choice != 0) {
            System.out.println("\n--- Train Control Panel (Integer Inputs Only) ---");
            System.out.println("1. Attach Carriage to Rear (addLast)");
            System.out.println("2. Attach Carriage to Front (addFirst)");
            System.out.println("3. Insert Carriage at Position Index (addAt)");
            System.out.println("4. Uncouple Carriage by ID (remove)");
            System.out.println("5. Uncouple Carriage by Index (removeAt)");
            System.out.println("6. Inspect Carriage ID at Index (get)");
            System.out.println("7. Search Manifest for Carriage ID (contains)");
            System.out.println("8. Reverse Train Orientation (reverse)");
            System.out.println("9. Check Train Status (Size & IsEmpty)");
            System.out.println("10. Display Full Train Manifest (printTrain)");
            System.out.println("0. Disconnect and Exit");
            System.out.print("Enter your choice (0-10): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter integer Carriage ID to add at rear: ");
                        train.addLast(scanner.nextInt());
                        System.out.println("Carriage attached successfully to the rear.");
                        break;
                    case 2:
                        System.out.print("Enter integer Carriage ID to add at front: ");
                        train.addFirst(scanner.nextInt());
                        System.out.println("Carriage attached successfully to the front.");
                        break;
                    case 3:
                        System.out.print("Enter index position (0 to " + train.size() + "): ");
                        int idx = scanner.nextInt();
                        System.out.print("Enter integer Carriage ID: ");
                        int id = scanner.nextInt();
                        try {
                            train.addAt(idx, id);
                            System.out.println("Carriage inserted at index " + idx + ".");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter integer Carriage ID to uncouple: ");
                        int targetId = scanner.nextInt();
                        if (train.remove(targetId)) {
                            System.out.println("Carriage ID " + targetId + " uncoupled successfully.");
                        } else {
                            System.out.println("Carriage ID not found on the train.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter index position to uncouple: ");
                        try {
                            int remIdx = scanner.nextInt();
                            train.removeAt(remIdx);
                            System.out.println("Carriage at index " + remIdx + " uncoupled.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.print("Enter index position to inspect: ");
                        try {
                            int getIdx = scanner.nextInt();
                            System.out.println("Carriage ID at index " + getIdx + " is: " + train.get(getIdx));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 7:
                        System.out.print("Enter integer Carriage ID to search: ");
                        int searchId = scanner.nextInt();
                        if (train.contains(searchId)) {
                            System.out.println("Confirmed: Carriage ID " + searchId + " is part of the train.");
                        } else {
                            System.out.println("Alert: Carriage ID " + searchId + " is NOT on the train.");
                        }
                        break;
                    case 8:
                        train.reverse();
                        System.out.println("Train orientation has been reversed!");
                        break;
                    case 9:
                        System.out.println("Total Carriages on Train: " + train.size());
                        System.out.println("Is Train Empty? " + train.isEmpty());
                        break;
                    case 10:
                        System.out.print("Current Train Layout: ");
                        train.printTrain();
                        break;
                    case 0:
                        System.out.println("Disconnecting control system. Safe travels!");
                        break;
                    default:
                        System.out.println("Invalid option! Please enter a number between 0 and 10.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer choice.");
                scanner.next(); // Clear invalid input token
            }
        }
        scanner.close();
    }
}