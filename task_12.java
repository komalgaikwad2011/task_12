package komal1;

import java.io.*;

//Step 1: Design a Student class implementing Serializable
class Student1 implements Serializable {
 private static final long serialVersionUID = 1L; // Step 4: serialVersionUID is important for version control
 String name;
 int age;
 transient String password; // Step 5: Mark sensitive fields as transient

 public Student1(String name, int age, String password) {
     this.name = name;
     this.age = age;
     this.password = password;
 }

 @Override
 public String toString() {
     return "Student{name='" + name + "', age=" + age + ", password='" + password + "'}";
 }
}


public class task_12 {
 public static void main(String[] args) {
     // Create a Student object
     Student1 student = new Student1("John Doe", 20, "secret123");

     // Step 2: Store object state into a file using ObjectOutputStream
     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
         oos.writeObject(student);
         System.out.println("Student object serialized.");
     } catch (IOException e) {
         System.err.println("Serialization error: " + e.getMessage());
     }

     // Step 3: Retrieve object data back using ObjectInputStream
     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
         Student1 restoredStudent = (Student1) ois.readObject();
         // Step 6: Validate restored object data
         System.out.println("Deserialized Student: " + restoredStudent);
         System.out.println("Note: transient field 'password' is null after deserialization.");
     } catch (ClassNotFoundException e) {
         // Step 7: Handle ClassNotFoundException
         System.err.println("Class not found: " + e.getMessage());
     } catch (IOException e) {
         System.err.println("Deserialization error: " + e.getMessage());
     }
 }
}