package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        String input = scanner.nextLine();
        int key = scanner.nextInt();
        String output = "";

        if (operation.equals("enc")) {
            output = encode(input, key);
        } else if (operation.equals("dec")) {
            output = decode(input, key);
        }
        System.out.println(output);
    }

    private static String encode(String input, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int curr = input.charAt(i);
            char newChar = (char) (curr + key);
            output.append(newChar);
        }
        return new String(output);
    }

    private static String decode(String input, int key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int curr = input.charAt(i);
            char newChar = (char) (curr - key);
            output.append(newChar);
        }
        return new String(output);
    }
}
