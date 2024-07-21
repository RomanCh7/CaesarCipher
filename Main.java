import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true)  {
            System.out.println("Hello! Please,choose your action\n 1-encrypt\n2-decrypt\n3-exit");
            int choice = scanner.nextInt();
            String inputFileEncrypt = null;
            int keyIn = 0;
            String inputFileDecrypt = null;
            switch (choice) {
                case 1:
                    System.out.println("Type your key");
                    while (true) {
                        if (scanner.hasNextInt()) {
                            keyIn = scanner.nextInt();
                            if (keyIn > 1 && keyIn < ALPHABET.length) {
                                break;
                            } else {
                                System.out.println("Please,type a correct key");
                            }
                        } else {
                            System.out.println("Please,type a valid integer key");
                            scanner.next();
                        }
                    }

                    System.out.println("Type path of input file:");
                    while (true) {
                        inputFileEncrypt = scanner.next();
                        if (checkFileExist(inputFileEncrypt)) {
                            break;
                        } else {
                            System.out.println("Please,type the correct path of file");
                        }
                    }

                    System.out.println("Type path of output file:");
                    String outputFileEncrypt;
                    while (true) {
                        outputFileEncrypt = scanner.next();
                        if (checkFileExist(outputFileEncrypt)) {
                            break;
                        } else {
                            System.out.println("Please,type the correct path of file");
                        }
                    }
                    encrypt(inputFileEncrypt, outputFileEncrypt, keyIn);
                    break;

                case 2:
                    System.out.println("Type your key");
                    int keyOut = 0;
                    while (true) {
                        if (scanner.hasNextInt()) {
                            keyIn = scanner.nextInt();
                            if (keyOut > 1 && keyOut < ALPHABET.length) {
                                break;
                            } else {
                                System.out.println("Please,type a correct key");
                            }
                        } else {
                            System.out.println("Please,type a valid integer key");
                            scanner.next();
                        }
                    }
                    System.out.println("Type path of input file:");
                    while (true) {
                        inputFileDecrypt = scanner.next();
                        if (checkFileExist(inputFileDecrypt)) {
                            break;
                        } else {
                            System.out.println("Please,type the correct path of file");
                        }
                    }

                    System.out.println("Type path of output file:");
                    String outputFileDecrypt;
                    while (true) {
                        outputFileDecrypt = scanner.next();
                        if (checkFileExist(outputFileDecrypt)) {
                            break;
                        } else {
                            System.out.println("Please,type the correct path of file");
                        }
                    }
                    decrypt(inputFileDecrypt, outputFileDecrypt, keyOut);
                    break;
                case 3:
                    System.out.println("Have a nice day!!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Incorrect choose");
            }




        }

    }






    public static boolean checkFileExist (String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    private static void decrypt (String inputFileDecrypt, String outputFileDecrypt,int keyOut) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileDecrypt));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileDecrypt))) {
            int nextSymbol;
            while ((nextSymbol = bufferedReader.read()) != -1) {
                char nextChar = (char) nextSymbol;
                char newChar = moveWithKeyOut(nextChar, keyOut);
                bufferedWriter.write(newChar);
            }

        }

    }

    public static char moveWithKeyOut ( char ch, int keyOut){
        if (ch == ' ') {
            return ' ';
        } else {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (Character.toLowerCase(ch) == ALPHABET[i]) {
                    int newIndex = (i - keyOut) % ALPHABET.length;
                    return Character.isLowerCase(ch) ? ALPHABET[newIndex] : Character.toUpperCase(ALPHABET[newIndex]);
                }
            }
        }
        return ch;
    }

    private static void encrypt (String inputFileEncrypt, String outputFileEncrypt,int keyIn) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileEncrypt));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileEncrypt))) {
            int nextSymbol;
            while ((nextSymbol = bufferedReader.read()) != -1) {
                char nextChar = (char) nextSymbol;
                char newChar = moveWithKeyIn(nextChar, keyIn);
                bufferedWriter.write(newChar);
            }

        }
    }

    public static char moveWithKeyIn ( char ch, int keyIn){
        if (ch == ' ') {
            return ' ';
        } else {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (Character.toLowerCase(ch) == ALPHABET[i]) {
                    int newIndex = (i + keyIn) % ALPHABET.length;
                    return Character.isLowerCase(ch) ? ALPHABET[newIndex] : Character.toUpperCase(ALPHABET[newIndex]);
                }
            }
        }
        return ch;
    }
}