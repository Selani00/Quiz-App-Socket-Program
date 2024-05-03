import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class QuizClient1 {
    public static void main(String[] args) {
        new QuizClient1().startClient();
    }

    public void startClient() {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Quiz Server");

            // Start a thread to listen for messages from the server
            new Thread(() -> {
                try {
                    while (true) {
                        Object receivedObject = inputStream.readObject();
                        if (receivedObject instanceof String) {
                            String message = (String) receivedObject;
                            System.out.println("Received from server: " + message);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    // Handle the end of the stream or any cleanup if necessary
                }
            }).start();

            // Start a thread to receive questions from the server
            new Thread(() -> {
                try {
                    while (true) {
                        Object receivedObject = inputStream.readObject();
                        if (receivedObject instanceof String) {
                            String question = (String) receivedObject;
                            System.out.println(question);

                            System.out.print("Your answer: ");
                            String answer = scanner.nextLine();
                            outputStream.writeObject(answer);
                            outputStream.flush();
                        }
                    }
                } catch (EOFException e) {
                    System.out.println("Server closed the connection. Exiting...");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    // Handle the end of the stream or any cleanup if necessary
                }
            }).start();

            // Wait for questions and send answers to the server
            while (true) {
                // Do other client-related tasks here if needed
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


