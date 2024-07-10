import java.util.Scanner;

public class VigenereCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            if (Character.isLetter(plainChar)) {
                char keyChar = key.charAt(keyIndex);
                char encryptedChar = (char) ((plainChar + keyChar - 2 * 'A') % 26 + 'A');
                ciphertext.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                ciphertext.append(plainChar); // Non-alphabetic characters are not encrypted
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            if (Character.isLetter(cipherChar)) {
                char keyChar = key.charAt(keyIndex);
                char decryptedChar = (char) ((cipherChar - keyChar + 26) % 26 + 'A');
                plaintext.append(decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                plaintext.append(cipherChar); // Non-alphabetic characters are not decrypted
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        System.out.println("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}