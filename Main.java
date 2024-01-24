package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * This main method processes command-line arguments to configure encryption/decryption parameters and performs the corresponding operation.
     *
     * @param args The command-line arguments provided to the program.
     *             Supported options:
     *             -mode: Specifies the operation mode (enc/dec). Default is "enc".
     *             -key: Specifies the encryption/decryption key. Default is 0.
     *             -data: Specifies the input data to be encrypted/decrypted. Default is an empty string.
     *             -in: Specifies the input file containing data to be encrypted/decrypted.
     *             -out: Specifies the output file to save the result. Default is an empty string.
     *             -alg: Specifies the algorithm for encryption/decryption. Default is "shift".
     *
     * @throws RuntimeException If there is an error during file operations or an invalid argument is provided.
     */
    public static void main(String[] args) {
        String data = " ", inputFile = " ", outputFile = " ", mode = "enc", alg = "shift";
        int key = 0;
        for(int i=0; i< args.length; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> data = args[i + 1];
                case "-in" -> inputFile = args[i + 1];
                case "-out" -> outputFile = args[i + 1];
                case "-alg" -> alg = args[i + 1];
                default -> System.err.println("Unknown option: " + args[i]);
            }
        }

        if(!inputFile.equals(" ") && data.equals(" ")) {
            try {
                data = getDataFromFile(inputFile);
            } catch (FileNotFoundException e) {
                System.out.println("Error while reading from file");
                throw new RuntimeException(e);
            }
        }

        data = encryptDecrypt(data, key, mode, alg);

        try {
            printToFileOrConsole(data, outputFile);
        } catch (IOException e) {
            System.out.println("Error while writing to file");
            throw new RuntimeException(e);
        }

    }

    /**
     * Reads data from the specified input file and returns it as a String.
     *
     * @param inputFile The path to the input file from which data needs to be read.
     * @return A String containing the data read from the input file.
     * @throws FileNotFoundException If the specified input file is not found.
     */
    public static String getDataFromFile(String inputFile) throws FileNotFoundException {
        StringBuilder data = new StringBuilder();
        File file = new File(inputFile);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            data.append(scanner.nextLine());
        }
        return data.toString();
    }

    /**
     * Writes the provided data to the specified output file or prints it to the console if no output file is specified.
     *
     * @param data       The data to be written or printed.
     * @param outputFile The path to the output file. If set to " ", the data will be printed to the console.
     * @throws IOException If an error occurs during file writing operations.
     */
    public static void printToFileOrConsole(String data, String outputFile) throws IOException {
        if(outputFile.equals(" ")) {
            System.out.println(data);
        } else {
            File file = new File(outputFile);
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        }
    }

    /**
     * Encrypts or decrypts the provided data based on the specified parameters.
     *
     * @param data The input data to be encrypted or decrypted.
     * @param key  The encryption or decryption key.
     * @param mode The operation mode, either "enc" for encryption or "dec" for decryption.
     * @param alg  The algorithm to be used, either "shift" or "unicode".
     * @return The result of the encryption or decryption operation as a String.
     */
    public static String encryptDecrypt(String data, int key, String mode, String alg) {
        StringBuilder message = new StringBuilder();
        if(mode.equals("dec")) {
            key *= -1;
        }
        for(int i=0; i<data.length(); i++) {
            int c = data.charAt(i);
            if(alg.equals("unicode")) {
                message.append((char) (c + key));
            } else {
                if(c >= 97 && c <= 122) {
                    message.append((char)((c - 97 + key + 26)%26 + 97));
                } else if(c >= 65 && c <= 90){
                    message.append((char)((c - 65 + key + 26)%26 + 65));
                } else {
                    message.append((char)c);
                }
            }
        }
        return message.toString();
    }
}
