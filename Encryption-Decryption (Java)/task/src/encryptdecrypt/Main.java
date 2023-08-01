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

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> operation = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> data = args[i + 1];
                case "-in" -> in = args[i + 1];
                case "-out" -> out = args[i + 1];
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
        if (operation.equals("enc")) {
            output = encode(data, key);
        } else if (operation.equals("dec")) {
            output = decode(data, key);
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

    private static String encode(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            int curr = data.charAt(i);
            char newChar = (char) (curr + key);
            output.append(newChar);
        }
        return new String(output);
    }

    private static String decode(String data, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            int curr = data.charAt(i);
            char newChar = (char) (curr - key);
            output.append(newChar);
        }
        return new String(output);
    }
}
