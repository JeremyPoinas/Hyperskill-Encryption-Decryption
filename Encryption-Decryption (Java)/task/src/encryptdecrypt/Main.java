package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String alphabet2 = "zyxwvutsrqponmlkjihgfedcba";
        String input = "we found a treasure!";
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr < 'a' || curr > 'z') {
                output.append(curr);
            } else {
                char newChar = alphabet2.charAt((int) curr - 97);
                output.append(newChar);
            }
        }
        System.out.println(output);
    }
}
