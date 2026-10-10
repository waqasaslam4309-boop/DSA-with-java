import java.util.Scanner;

// Array-based menu program for managing book IDs on a library shelf
public class Array {
    static final int MAX_SIZE = 10;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] books = new int[MAX_SIZE];
        
        // Explicitly initialize all elements to 0 (representing null/empty slots)
        for (int i = 0; i < MAX_SIZE; i++) {
            books[i] = 0;
        }

        int size = 0;
        int choice;

        do {
            System.out.println("\n===== LIBRARY BOOK MANAGEMENT (0 = NULL) =====");
            System.out.println("1. Add value at end");
            System.out.println("2. Insert value at index");
            System.out.println("3. Fill the array");
            System.out.println("4. Delete last element");
            System.out.println("5. Delete by index");
            System.out.println("6. Display all elements");
            System.out.println("7. Search for a value");
            System.out.println("8. Get value at index");
            System.out.println("9. Update value");
            System.out.println("10. Show size");
            System.out.println("11. Exit");
            System.out.println("==============================================");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                // 1. Add book ID to end
                case 1:
                    if (size == MAX_SIZE) {
                        System.out.println("Array is full.");
                    } else {
                        System.out.print("Enter book ID (non-zero): ");
                        int val = input.nextInt();
                        if (val == 0) {
                            System.out.println("0 is reserved as null/empty and cannot be added as a book ID.");
                            break;
                        }
                        books[size] = val;
                        size++;
                        System.out.println("Book ID added successfully.");
                    }
                    break;

                // 2. Insert book ID at index
                case 2:
                    if (size == MAX_SIZE) {
                        System.out.println("Array is full.");
                    } else {
                        System.out.print("Enter index (0 to " + size + "): ");
                        int index = input.nextInt();
                        if (index < 0 || index > size) {
                            System.out.println("Invalid index.");
                        } else {
                            System.out.print("Enter book ID (non-zero): ");
                            int value = input.nextInt();
                            if (value == 0) {
                                System.out.println("0 is reserved as null/empty and cannot be added as a book ID.");
                                break;
                            }
                            for (int i = size; i > index; i--) {
                                books[i] = books[i - 1];
                            }
                            books[index] = value;
                            size++;
                            System.out.println("Value inserted successfully.");
                        }
                    }
                    break;

                // 3. Fill the shelf with multiple book IDs
                case 3:
                    if (size == MAX_SIZE) {
                        System.out.println("Array is already full.");
                    } else {
                        char again = 'y'; // Initialized to avoid the compilation error
                        do {
                            System.out.print("Enter book ID (non-zero): ");
                            int val = input.nextInt();
                            if (val == 0) {
                                System.out.println("0 is reserved as null/empty. Try again.");
                                continue;
                            }
                            books[size] = val;
                            size++;
                            if (size == MAX_SIZE) {
                                System.out.println("Array is now full.");
                                break;
                            }
                            System.out.print("Add another value? (y/n): ");
                            again = input.next().charAt(0);
                        } while (again == 'y' || again == 'Y');
                    }
                    break;

                // 4. Delete last book ID (sets it to 0 as null)
                case 4:
                    if (size == 0) {
                        System.out.println("Array is empty.");
                    } else {
                        books[size - 1] = 0; // Set to null/0
                        size--;
                        System.out.println("Last element deleted (set to null) successfully.");
                    }
                    break;

                // 5. Delete book ID by index (shifts and sets removed slot to 0)
                case 5:
                    if (size == 0) {
                        System.out.println("Array is empty.");
                    } else {
                        System.out.print("Enter index to delete: ");
                        int deleteIndex = input.nextInt();
                        if (deleteIndex < 0 || deleteIndex >= size) {
                            System.out.println("Invalid index.");
                        } else {
                            for (int i = deleteIndex; i < size - 1; i++) {
                                books[i] = books[i + 1];
                            }
                            books[size - 1] = 0; // Clear the trailing element as null
                            size--;
                            System.out.println("Element deleted and slot nullified successfully.");
                        }
                    }
                    break;

                // 6. Display all book IDs (skips or shows 0s depending on view; here we show active elements, or you can see 0s in empty slots)
                case 6:
                    System.out.print("Shelf Array state (0 represents null): ");
                    for (int i = 0; i < MAX_SIZE; i++) {
                        System.out.print(books[i] + " ");
                    }
                    System.out.println();
                    break;

                // 7. Search for a book ID
                case 7:
                    System.out.print("Enter book ID to search: ");
                    int searchValue = input.nextInt();
                    if (searchValue == 0) {
                        System.out.println("0 represents null/empty and cannot be searched as a valid book ID.");
                        break;
                    }
                    boolean found = false;
                    for (int i = 0; i < size; i++) {
                        if (books[i] == searchValue) {
                            System.out.println("Book ID found at index " + i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book ID does not exist.");
                    }
                    break;

                // 8. Get book ID at index
                case 8:
                    System.out.print("Enter index: ");
                    int getIndex = input.nextInt();
                    if (getIndex < 0 || getIndex >= MAX_SIZE) {
                        System.out.println("Invalid index.");
                    } else {
                        if (books[getIndex] == 0) {
                            System.out.println("Value at index " + getIndex + " is null (0).");
                        } else {
                            System.out.println("Value at index " + getIndex + " = " + books[getIndex]);
                        }
                    }
                    break;

                // 9. Replace/Update book ID at index
                case 9:
                    System.out.print("Enter index to update: ");
                    int updateIndex = input.nextInt();
                    if (updateIndex < 0 || updateIndex >= size) {
                        System.out.println("Invalid index or index points to an unallocated slot.");
                    } else {
                        System.out.print("Enter new book ID (non-zero): ");
                        int newVal = input.nextInt();
                        if (newVal == 0) {
                            System.out.println("To nullify an element, please use the delete options.");
                            break;
                        }
                        books[updateIndex] = newVal;
                        System.out.println("Value updated successfully.");
                    }
                    break;

                // 10. Show current size
                case 10:
                    System.out.println("Current active size: " + size);
                    break;

                // 11. Exit program
                case 11:
                    System.out.println("Program exited successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 11);

        input.close();
    }
}
