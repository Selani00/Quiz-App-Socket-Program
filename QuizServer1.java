import java.io.*;
import java.net.*;
import java.util.*;

public class QuizServer1 {
    private static Map<ClientHandler1, String[]> clientResponses = new HashMap<>();
    private static List<String> questions = new ArrayList<>();
    private static Map<String, String[]> answersMap = new HashMap<>();
    private static int nextClientId = 1;

    // static {
    // // Add questions to the list when the server starts
    // questions.add("Q1: What is 2 + 2?");
    // questions.add("Q2: What is the capital of France?");

    // // Add answers to the map
    // answersMap.put("Q1: What is 2 + 2?", new String[]{"A) 3", "B) 4", "C) 5", "D)
    // 6"});
    // answersMap.put("Q2: What is the capital of France?", new String[]{"A)
    // London", "B) Paris", "C) Berlin", "D) Madrid"});
    // }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4800);
            System.out.println("Quiz server started...");

            // Ask for the number of questions
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of questions: ");
            int numQuestions = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Receive questions and answers from the user
            for (int i = 0; i < numQuestions; i++) {
                System.out.print("Enter question " + (i + 1) + ": ");
                String question = scanner.nextLine();
                questions.add(question);

                String[] options = new String[4];
                for (int j = 0; j < options.length; j++) {
                    System.out.print("Enter option " + (char) ('A' + j) + ": ");
                    options[j] = scanner.nextLine();
                }
                answersMap.put(question, options);

            }
            //System.out.println("Questions and answers received successfully.");
            //System.out.println("Waiting for students to connect...");

            // Close scanner after receiving input
            scanner.close();

            // Start accepting client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandler1 clientHandler = new ClientHandler1(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<ClientHandler1, String[]> getClientResponses() {
        return clientResponses;
    }

    public static List<String> getQuestions() {
        return questions;
    }

    public static Map<String, String[]> getAnswersMap() {
        return answersMap;
    }

    public static synchronized int getNextClientId() {
        return nextClientId++;
    }
    public static void addClientResponse(ClientHandler1 clientHandler1, String[] responses) {
        clientResponses.put(clientHandler1, responses);
    }

    public static void printClientResponses() {
        for (Map.Entry<ClientHandler1, String[]> entry : clientResponses.entrySet()) {
            ClientHandler1 client = entry.getKey();
            String[] responses = entry.getValue();
            System.out.println("Client ID: " + client.getClientId());
            System.out.println("Client Name: " + client.getClientName());
            System.out.println("Response to Q1: " + responses[0]);
            System.out.println("Response to Q2: " + responses[1]);
            System.out.println("-----------------------------");
        }
    }
}

class ClientHandler1 implements Runnable {
    private int clientId;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;

    public ClientHandler1(Socket socket) {
        this.clientSocket = socket;
        this.clientId = QuizServer.getNextClientId();
        this.clientName = ""; // Initialize clientName
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Get client name
            out.println("Enter your name:");
            clientName = in.readLine();

            // Send questions one by one to client
            List<String> questions = QuizServer.getQuestions();
            Map<String, String[]> answersMap = QuizServer.getAnswersMap();

            String[] responses = new String[questions.size()]; // Initialize responses array

            for (int i = 0; i < questions.size(); i++) {
                String question = questions.get(i);
                out.println(question);
                String[] options = answersMap.get(question);
                for (String option : options) {
                    out.println(option);
                }

                // Get response from client
                out.println("Please enter your answer:");
                String answer = in.readLine();
                responses[i] = answer; // Store client's response in the array
            }

            QuizServer1.addClientResponse(this, responses); // Store client responses
            //System.out.println("Quiz completed by: " + clientName);
            clientSocket.close();
            QuizServer1.printClientResponses(); // Print the map after the conversation ends
        } catch (SocketException e) {
            System.out.println("Client disconnected unexpectedly: " + clientName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }
}
