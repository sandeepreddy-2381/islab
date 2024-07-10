import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MonoalphabeticCipher {
    
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String KEY = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Substitute with your own key

    private static Map<Character, Character> createEncryptionMap() {
        Map<Character, Character> encryptionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            encryptionMap.put(ALPHABET.charAt(i), KEY.charAt(i));
        }
        return encryptionMap;
    }

    private static Map<Character, Character> createDecryptionMap() {
        Map<Character, Character> decryptionMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length(); i++) {
            decryptionMap.put(KEY.charAt(i), ALPHABET.charAt(i));
        }
        return decryptionMap;
    }

    private static String encrypt(String plaintext, Map<Character, Character> encryptionMap) {
        StringBuilder ciphertext = new StringBuilder();
        for (char character : plaintext.toUpperCase().toCharArray()) {
            if (encryptionMap.containsKey(character)) {
                ciphertext.append(encryptionMap.get(character));
            } else {
                ciphertext.append(character); // Non-alphabetic characters are not encrypted
            }
        }
        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, Map<Character, Character> decryptionMap) {
        StringBuilder plaintext = new StringBuilder();
        for (char character : ciphertext.toUpperCase().toCharArray()) {
            if (decryptionMap.containsKey(character)) {
                plaintext.append(decryptionMap.get(character));
            } else {
                plaintext.append(character); // Non-alphabetic characters are not decrypted
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        Map<Character, Character> encryptionMap = createEncryptionMap();
        String ciphertext = encrypt(plaintext, encryptionMap);

        System.out.println("Ciphertext: " + ciphertext);

        Map<Character, Character> decryptionMap = createDecryptionMap();
        String decryptedText = decrypt(ciphertext, decryptionMap);

        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}