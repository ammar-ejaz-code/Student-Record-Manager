import java.util.LinkedList;
import java.util.Scanner;

class Student {
    int ID;
    String name;
    String department; 

    public Student(int id, String name, String department) {
        this.ID = id;
        this.name = name; 
        this.department = department; 
    }

    @Override
    public String toString() {
        return "ID: " + ID + "; Name: " + name + "; Department: " + department;
    }
}

class HashTable {
    private LinkedList<Student>[] table; 
    private int size; 

    public HashTable(int size) {
        this.size = size; 
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>(); // Corrected: Used =
        }
    }

    private int hash(int id) {
        return id % size;
    }

    public void insert(Student student) { // Corrected: 'empty' to 'void'
        int index = hash(student.ID);
        table[index].add(student);
        System.out.println("Student added at index " + index);
    }

    public Student search(int id) {
        int index = hash(id);
        for (Student s : table[index]) {
            if (s.ID == id) {
                return s; // Corrected: 'back to s' to 'return s'
            }
        }
        return null;
    }

    public boolean delete(int id) {
        int index = hash(id);
        for (Student s : table[index]) {
            if (s.ID == id) {
                table[index].remove(s);
                return true;
            }
        }
        return false;
    }

    public void display() { // Corrected: Added ()
        System.out.println("\n--- Student Records ---");
        for (int i = 0; i < size; i++) { // Corrected: Added 'i++'
            System.out.print("Index " + i + ": ");
            if (table[i].isEmpty()) { // Corrected: Added () and {}
                System.out.println("Empty");
            } else { // Corrected: Used 'else {'
                for (Student s : table[i]) {
                    System.out.print(" -> " + s);
                }
                System.out.println(); // Added to move to the next line after an index is printed
            }
        }
    }
}


public class StudentRecordManager {
    public static void main(String[] args) {
        HashTable ht = new HashTable(10);
        Scanner sc = new Scanner(System.in); // Corrected: Proper type and declaration
        int choice; // Corrected: Proper declaration

        do {
            System.out.println("\n===== Student Record Manager Using Hashing =====");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student"); // Corrected: Proper println usage
            System.out.println("3. Delete Student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    Student s = new Student(id, name, dept);
                    ht.insert(s);
                    break; // Corrected: 'stop' to 'break'

                case 2:
                    System.out.print("Enter ID to search: "); // Corrected: Added print statement part
                    int sid = sc.nextInt();
                    Student result = ht.search(sid); // Corrected: Variable declaration
                    if (result != null) { // Corrected: Condition syntax
                        System.out.println("Found: " + result);
                    } else { // Corrected: 'Otherwise' to 'else'
                        System.out.println("Student not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    if (ht.delete(did)) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break; // Corrected: 'split' to 'break'

                case 4:
                    ht.display();
                    break; // Corrected: 'stop' to 'break'

                case 5:
                    System.out.println("Exiting program...");
                    break; // Corrected: 'rest' to 'break'

                default:
                    System.out.println("Invalid choice; try again.");
            }
        } while (choice != 5);
        sc.close(); // Corrected: Added method call
    }
}