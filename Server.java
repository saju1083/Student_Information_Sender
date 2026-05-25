import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            System.out.println("Server is running... Waiting for student data.");
            
            HashMap<Integer, Integer[]> studentMarksMap = new HashMap<>();
            List<String> namesList = new ArrayList<>();

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                String data = reader.readLine();
                if (data != null) {
                    
                    String[] parts = data.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int marks = Integer.parseInt(parts[2]);

                    studentMarksMap.put(id, new Integer[]{marks});
                    namesList.add(name);

                    System.out.println("\n--- Current Student Records ---");
                    String[] namesArray = namesList.toArray(new String[0]);
                    int index = 0;
                    for (Integer studentId : studentMarksMap.keySet()) {
                        System.out.println("ID: " + studentId + 
                                           " | Name: " + namesArray[index] + 
                                           " | Marks: " + studentMarksMap.get(studentId)[0]);
                        index++;
                    }
                }
                socket.close();
            }
        } 
          catch (IOException e) {
            e.printStackTrace();
        }
    }
}
