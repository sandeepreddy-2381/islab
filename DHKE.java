import java.util.Scanner;

public class DHKE {
    public static void main(String[] args) {
        int q, alpha_picked, xa, xb, ya, yb, ka, kb, index = 0;
        int alpha[] = new int[100];
        System.out.println("Enter the prime: ");
        Scanner sc = new Scanner(System.in);
        q = sc.nextInt();

        for (int i = 2; i < q; i++) {
            int alpharnot[] = new int[q];
            for (int j = 1; j <= q; j++) {
                alpharnot[j - 1] = (int) ((Math.pow(i, j)) % q);
                int c = 0;
                for (int k = 0; k < q; k++) {
                    for (int p = k + 1; p < q; p++) {
                        if (alpharnot[k] == alpharnot[p]) {
                            c++;
                        }
                    }
                }
                if (c == 0) {
                    alpha[index] = i;
                    index++;
                }
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println("Primitive root is: " + alpha[i]);
        }

        System.out.println("Select one of the roots: ");
        alpha_picked = sc.nextInt();
        System.out.println("Select Xa: ");
        xa = sc.nextInt();
        System.out.println("Select Xb: ");
        xb = sc.nextInt();
        ya = (int) ((Math.pow(alpha_picked, xa)) % q);
        yb = (int) ((Math.pow(alpha_picked, xb)) % q);
        ka = (int) ((Math.pow(yb, xa)) % q);
        kb = (int) ((Math.pow(ya, xb)) % q);

        System.out.println("Ka: " + ka + " Kb: " + kb);
        if (ka == kb) {
            System.out.println("Keys are same");
        }
    }
}