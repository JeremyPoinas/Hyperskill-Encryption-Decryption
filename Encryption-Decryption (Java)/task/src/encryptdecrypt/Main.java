package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String input = scanner.nextLine();
        int key = scanner.nextInt();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr < 'a' || curr > 'z') {
                output.append(curr);
            } else {
                char newChar = alphabet.charAt((alphabet.indexOf(curr) + key) % 26);
                output.append(newChar);
            }
        }
        System.out.println(output);
    }
}
