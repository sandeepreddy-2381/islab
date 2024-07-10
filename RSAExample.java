import  java.util.Scanner;

public class RSAExample {

    public static int modfun(int a, int n, int b) {
        if (b == 1) {
            return a % n;
        } else {
            return ((a % n) * modfun(a, n, b - 1)) % n;
        }
    }

    public static int gcd(int a, int b) {
        if (a%b == 0) 
            return b;
           else 
           return gcd(b,a%b);
          
    }

    public static int totient(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (gcd(n, i) == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, n, e, d, phi, m;
        int em, dm;

        System.out.print("Enter two prime numbers: ");
        a = scanner.nextInt();
        b = scanner.nextInt();

        n = a * b;
        phi = totient(n);
        System.out.println(phi);

        System.out.print("Enter e value: ");
        e = scanner.nextInt();

        if (0 < e && e < n && gcd(e, phi) == 1) {
            d = 0;
            for (int i = 2; i < n; i++) {
                if ((e * i) % phi == 1) {
                    System.out.println("d value is: " + i);
                    d = i;
                    break;
                }
            }

            System.out.println("KU {" + e + " " + n + "}");
            System.out.println("KR {" + d + " " + n + "}");

            System.out.print("Enter message: ");
            m = scanner.nextInt();

            em = modfun(m, n, e);
            dm = modfun(em, n, d);

            System.out.println("Encrypted message: " + em);
            System.out.println("Decrypted message: " + dm % n);
        } else {
            System.out.println("Invalid e value");
        }

        scanner.close();
    }
}