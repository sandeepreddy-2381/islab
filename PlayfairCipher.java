import java.util.Scanner;

class PlayfairCipher {

    private static char[][] keyTable = new char[5][5];
    private static final char SUBSTITUTE_CHAR = 'X'; // Character to substitute for repeated letters or padding

    private static void generateKeyTable(String key) {
        boolean[] alphabet = new boolean[26];
        int index = 0;

        // Fill the key table with the unique characters from the key
        for (char c : key.toUpperCase().toCharArray()) {
            if (c == 'J') c = 'I'; // Treat 'I' and 'J' as the same letter
            if (!alphabet[c - 'A']) {
                alphabet[c - 'A'] = true;
                keyTable[index / 5][index % 5] = c;
                index++;
            }
        }

        // Fill the remaining spaces with the rest of the alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue; // Skip 'J'
            if (!alphabet[c - 'A']) {
                alphabet[c - 'A'] = true;
                keyTable[index / 5][index % 5] = c;
                index++;
            }
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(keyTable[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static String formatPlaintext(String plaintext) {
        StringBuilder formattedText = new StringBuilder();
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I'); // Remove non-letters and replace 'J' with 'I'
        
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            formattedText.append(c);

            if (i < plaintext.length() - 1 && c == plaintext.charAt(i + 1)) {
                formattedText.append(SUBSTITUTE_CHAR); // Insert 'X' between repeated letters
            }
        }

        if (formattedText.length() % 2 != 0) {
            formattedText.append(SUBSTITUTE_CHAR); // Pad with 'X' if length is odd
        }

        return formattedText.toString();
    }

    private static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        String formattedText = formatPlaintext(plaintext);

        for (int i = 0; i < formattedText.length(); i += 2) {
            char a = formattedText.charAt(i);
            char b = formattedText.charAt(i + 1);
            int[] posA = getPosition(a);
            int[] posB = getPosition(b);

            if (posA[0] == posB[0]) {
                // Same row
                ciphertext.append(keyTable[posA[0]][(posA[1] + 1) % 5]);
                ciphertext.append(keyTable[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
                // Same column
                ciphertext.append(keyTable[(posA[0] + 1) % 5][posA[1]]);
                ciphertext.append(keyTable[(posB[0] + 1) % 5][posB[1]]);
            } else {
                // Rectangle swap
                ciphertext.append(keyTable[posA[0]][posB[1]]);
                ciphertext.append(keyTable[posB[0]][posA[1]]);
            }
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);
            int[] posA = getPosition(a);
            int[] posB = getPosition(b);

            if (posA[0] == posB[0]) {
                // Same row
                plaintext.append(keyTable[posA[0]][(posA[1] + 4) % 5]);
                plaintext.append(keyTable[posB[0]][(posB[1] + 4) % 5]);
            } else if (posA[1] == posB[1]) {
                // Same column
                plaintext.append(keyTable[(posA[0] + 4) % 5][posA[1]]);
                plaintext.append(keyTable[(posB[0] + 4) % 5][posB[1]]);
            } else {
                // Rectangle swap
                plaintext.append(keyTable[posA[0]][posB[1]]);
                plaintext.append(keyTable[posB[0]][posA[1]]);
            }
        }

        return plaintext.toString();
    }

    private static int[] getPosition(char c) {
        int[] pos = new int[2];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (keyTable[row][col] == c) {
                    pos[0] = row;
                    pos[1] = col;
                    return pos;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the key: ");
        String key = scanner.nextLine();

        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        generateKeyTable(key);

        String ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}