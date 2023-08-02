package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String operation = "enc";
        int key = 0;
        String data = "";
        String in = "";
        String out = "";
        String algoChoice = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> operation = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]) % 26;
                case "-data" -> data = args[i + 1];
                case "-in" -> in = args[i + 1];
                case "-out" -> out = args[i + 1];
                case "-alg" -> algoChoice = args[i + 1];
            }
        }

        if (!in.equals("") && data.equals("")) {
            try {
                data = new String(Files.readAllBytes(Paths.get(in)));
            } catch (Exception e) {
                System.out.println("Error");
            }
        }

        String output = "";
        AlgoFactory algoFactory = new AlgoFactory();
        Algo algo = algoFactory.create(algoChoice);

        if (operation.equals("enc")) {
            output = algo.encode(data, key);
        } else if (operation.equals("dec")) {
            output = algo.decode(data, key);
        }

        if (!out.equals("")) {
            File outputFile = new File(out);
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                printWriter.print(output);
                return;
            } catch (IOException e) {
                System.out.print("Error");
            }
        }
        System.out.println(output);
    }
}

class AlgoFactory {
    Algo create(String type) {
        if (type.equals("unicode")) {
            return new UnicodeAlgo();
        }
        return new ShiftAlgo();
    }
}

abstract class Algo {
    abstract String encode(String data, int key);

    abstract String decode(String data, int key);
}

class ShiftAlgo extends Algo {
    String encode(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            int curr = data.charAt(i);
            char newChar = (char) curr;
            if (Character.isLetter(curr)) {
                boolean isUpperCase = Character.isUpperCase(curr);
                curr = Character.toLowerCase(curr) - 97;
                newChar = (char) ((curr + key) % 26);

                if (isUpperCase) {
                    newChar += 65;
                } else {
                    newChar += 97;
                }
            }
            output.append(newChar);
        }
        return new String(output);
    }

    String decode(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            int curr = data.charAt(i);
            char newChar = (char) curr;
            if (Character.isLetter(curr)) {
                boolean isUpperCase = Character.isUpperCase(curr);
                curr = Character.toLowerCase(curr) - 97;
                if (curr - key < 0) {
                    newChar = (char) (26 + (curr - key));
                } else {
                    newChar = (char) (curr - key);
                }

                if (isUpperCase) {
                    newChar += 65;
                } else {
                    newChar += 97;
                }
            }
            output.append(newChar);
        }
        return new String(output);
    }
}

class UnicodeAlgo extends Algo {
    String encode(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            int curr = data.charAt(i);
            char newChar = (char) (curr + key);
            output.append(newChar);
        }
        return new String(output);
    }

    String decode(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            int curr = data.charAt(i);
            char newChar = (char) (curr - key);
            output.append(newChar);
        }
        return new String(output);
    }
}

