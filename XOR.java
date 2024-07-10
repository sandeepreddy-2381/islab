public class XOR{

    public static void main(String[] args) {
        String input = "Hello World";
        char[] charArray = input.toCharArray();
        
        System.out.println("Original String: " + input);
        
        StringBuilder xorResult = new StringBuilder();
        for (char c : charArray) {
            char xorChar = (char) (c ^ 0);  // XOR with 0
            xorResult.append(xorChar);
        }
        
        System.out.println("Result after XOR with 0: " + xorResult.toString());
    }
}
