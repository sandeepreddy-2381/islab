import java.util.Scanner;

public class HillCipher {

    private static final int MATRIX_SIZE = 3;
    private static final int[][] keyMatrix = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
    };

    private static final int[][] inverseKeyMatrix = {
            {8, 5, 10},
            {21, 8, 21},
            {21, 12, 8}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        while (plaintext.length() % MATRIX_SIZE != 0) {
            plaintext += 'X';
        }

        String ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    private static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += MATRIX_SIZE) {
            int[] messageVector = new int[MATRIX_SIZE];
            int[] cipherVector = new int[MATRIX_SIZE];

            for (int j = 0; j < MATRIX_SIZE; j++) {
                messageVector[j] = plaintext.charAt(i + j) - 'A';
            }

            for (int x = 0; x < MATRIX_SIZE; x++) {
                cipherVector[x] = 0;
                for (int y = 0; y < MATRIX_SIZE; y++) {
                    cipherVector[x] += keyMatrix[x][y] * messageVector[y];
                }
                cipherVector[x] %= 26;
            }

            for (int z = 0; z < MATRIX_SIZE; z++) {
                ciphertext.append((char) (cipherVector[z] + 'A'));
            }
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += MATRIX_SIZE) {
            int[] cipherVector = new int[MATRIX_SIZE];
            int[] messageVector = new int[MATRIX_SIZE];

            for (int j = 0; j < MATRIX_SIZE; j++) {
                cipherVector[j] = ciphertext.charAt(i + j) - 'A';
            }

            for (int x = 0; x < MATRIX_SIZE; x++) {
                messageVector[x] = 0;
                for (int y = 0; y < MATRIX_SIZE; y++) {
                    messageVector[x] += inverseKeyMatrix[x][y] * cipherVector[y];
                }
                messageVector[x] %= 26;
            }

            for (int z = 0; z < MATRIX_SIZE; z++) {
                plaintext.append((char) (messageVector[z] + 'A'));
            }
        }

        return plaintext.toString();
    }
}
