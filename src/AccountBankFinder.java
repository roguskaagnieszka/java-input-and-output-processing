import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AccountBankFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first three digits of your account number: ");
        String userInput = scanner.nextLine().trim();

        if (!isValidAccountPrefix(userInput)) {
            System.out.println("Invalid input. Please provide exactly three digits.");
            return;
        }

        try {
            String urlString = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";
            String fileContent = fetchFileFromURL(urlString);

            if (fileContent == null) {
                System.out.println("Could not fetch the file. Please try again later.");
                return;
            }

            Set<String> bankNames = findUniqueBankNames(fileContent, userInput);

            if (bankNames.isEmpty()) {
                System.out.println("Bank not found for the provided account prefix.");
            } else {
                System.out.println("Banks associated with the prefix " + userInput + ":");
                for (String bankName : bankNames) {
                    System.out.println(bankName);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static boolean isValidAccountPrefix(String input) {
        return input.length() == 3 && input.matches("\\d+");
    }

    private static String fetchFileFromURL(String urlString) {
        StringBuilder content = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != 200) {
                System.out.println("Error fetching the file from NBP. HTTP Response Code: " + connection.getResponseCode());
                return null;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Error while fetching file: " + e.getMessage());
            return null;
        }

        return content.toString();
    }

    private static Set<String> findUniqueBankNames(String fileContent, String accountPrefix) {
        String[] lines = fileContent.split("\n");
        Set<String> bankNames = new HashSet<>();

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith(accountPrefix)) {
                String[] parts = line.split("\t");
                if (parts.length >= 3) {
                    String bankName = parts[1].trim();
                    bankNames.add(bankName);
                }
            }
        }

        return bankNames;
    }
}
