import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); 
        
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Marks: ");
            int marks = scanner.nextInt();

            Socket socket = new Socket("localhost", 3000); 
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            writer.println(id + "," + name + "," + marks);
            System.out.println("Data sent successfully.");
            socket.close();
        } 
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } 
        finally {
            scanner.close();
        }
    }
}
