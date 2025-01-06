import java.io.*;

public class TextFileTransformer {
    public static void copyFileWithModification(String sourceFile, String destinationFile) {
        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {

            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                if (byteData == ' ') {
                    outputStream.write('-');
                } else {
                    outputStream.write(byteData);
                }
            }

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred during file processing: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String sourceFile = "src/files/sourceFile.txt";
        String destinationFile = "src/files/destinationFile.txt";

        copyFileWithModification(sourceFile, destinationFile);
    }
}
