public class AndXor {
    public static void main(String[] args) {
        String input = "Hello World";
        char[] charArray = input.toCharArray();
        
        StringBuilder andResult = new StringBuilder();
        StringBuilder xorResult = new StringBuilder();
        
        for (char c : charArray) {
            char andChar = (char) (c & 127);  // AND with 127
            char xorChar = (char) (c ^ 127);  // XOR with 127
            andResult.append(andChar);
            xorResult.append(xorChar);
        }
        
        System.out.println("Original String: " + input);
        System.out.println("Result after AND with 127: " + andResult.toString());
        System.out.println("Result after XOR with 127: " + xorResult.toString());
    }
}
