
import java.util.Scanner;

public class RailFenceCipher {

    public static String encrypt(String plaintext, int numRails) {
        if (numRails <= 1) {
            return plaintext;
        }

        StringBuilder[] rail = new StringBuilder[numRails];
        for (int i = 0; i < numRails; i++) {
            rail[i] = new StringBuilder();
        }

        int railIndex = 0;
        boolean directionDown = true;

        for (char c : plaintext.toCharArray()) {
            rail[railIndex].append(c);
            if (railIndex == 0) {
                directionDown = true;
            } else if (railIndex == numRails - 1) {
                directionDown = false;
            }

            railIndex += directionDown ? 1 : -1;
        }

        StringBuilder ciphertext = new StringBuilder();
        for (StringBuilder sb : rail) {
            ciphertext.append(sb);
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int numRails) {
        if (numRails <= 1) {
            return ciphertext;
        }
        
        int x = ciphertext.length();

        int[] railLengths = new int[numRails];
        int railIndex = 0;
        boolean directionDown = true;

        for (char c : ciphertext.toCharArray()) {
            railLengths[railIndex]++;
            if (railIndex == 0) {
                directionDown = true;
            } else if (railIndex == numRails - 1) {
                directionDown = false;
            }

            railIndex += directionDown ? 1 : -1;
        }

        StringBuilder[] rail = new StringBuilder[numRails];
        for (int i = 0; i < numRails; i++) {
            rail[i] = new StringBuilder(ciphertext.substring(0, railLengths[i]));
            ciphertext = ciphertext.substring(railLengths[i]);
        }

        StringBuilder plaintext = new StringBuilder();
        railIndex = 0;
        directionDown = true;

        int[] railPos = new int[numRails];
        for (int i = 0; i < railLengths.length; i++) {
            railPos[i] = 0;
        }

        for (int i = 0; i <x; i++) {
            plaintext.append(rail[railIndex].charAt(railPos[railIndex]++));

            if (railIndex == 0) {
                directionDown = true;
            } else if (railIndex == numRails - 1) {
                directionDown = false;
            }

            railIndex += directionDown ? 1 : -1;
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.println("Enter the number of rails: ");
        int numRails = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String ciphertext = encrypt(plaintext, numRails);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, numRails);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}