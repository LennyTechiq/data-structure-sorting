import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomFileGenerator {

    public static void main(String[] args) {
        try {
            // Generate 5 random files
            for (int i = 1; i <= 5; i++) {
                String filename = "Random" + i + ".txt";
                generateRandomFile(filename, 1000); // Adjust the number of integers as needed
                System.out.println("Generated " + filename);
            }
            System.out.println("Files generation complete.");
        } catch (IOException e) {
            System.err.println("Error generating files: " + e.getMessage());
        }
    }

    // Method to generate a random file with given filename and number of integers
    private static void generateRandomFile(String filename, int numIntegers) throws IOException {
        Random random = new Random();
        FileWriter writer = new FileWriter(filename);
        try {
            for (int i = 0; i < numIntegers; i++) {
                int randomNumber = random.nextInt(10000) + 1; // Adjust range as per your requirements
                writer.write(randomNumber + "\n");
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
