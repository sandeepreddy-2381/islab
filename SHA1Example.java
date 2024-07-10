import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA1Example {
    public static String calculateSHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = sha1Digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the text: ");
            String inputText = scanner.nextLine();

            String sha1Hash = calculateSHA1(inputText);
            System.out.println("SHA-1 Hash: " + sha1Hash);

            scanner.close();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
