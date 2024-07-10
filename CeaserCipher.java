import java.util.Scanner;

public class CeaserCipher {

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        shift = shift % 26 + 26;  // Ensure the shift is within 0-25

        for (char i : plaintext.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    ciphertext.append((char) ('A' + (i - 'A' + shift) % 26 ));
                } else {
                    ciphertext.append((char) ('a' + (i - 'a' + shift) % 26 ));
                }
            } else {
                ciphertext.append(i);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, -shift);  // Decrypting is just encrypting with a negative shift
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();

        String encrypted = encrypt(plaintext, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
