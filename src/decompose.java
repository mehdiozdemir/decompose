import java.util.Scanner;
import java.util.InputMismatchException;

public class decompose {

    public static void decompose(double[][] a) {
        int n = a.length;

        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = a[i][k] / a[k][k];
                a[i][k] = factor;
                for (int j = k + 1; j < n; j++) {
                    a[i][j] = a[i][j] - factor * a[k][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0, m = 0;
        double[][] a = null;

        while (true) {
            try {
                System.out.print("Matrisin satır sayısını girin (pozitif tam sayı): ");
                n = scanner.nextInt();
                if (n <= 0) {
                    throw new InputMismatchException("Satır sayısı pozitif olmalıdır.");
                }

                System.out.print("Matrisin sütun sayısını girin (pozitif tam sayı): ");
                m = scanner.nextInt();
                if (m <= 0) {
                    throw new InputMismatchException("Sütun sayısı pozitif olmalıdır.");
                }

                if (n != m) {
                    throw new InputMismatchException("LU ayrıştırması için kare matris girilmelidir.");
                }

                a = new double[n][m];
                System.out.println("Matrisin elemanlarını girin (ondalıklı sayılar):");

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print("a[" + i + "][" + j + "]: ");
                        a[i][j] = scanner.nextDouble();
                    }
                }

                break; // Eğer buraya kadar geldiyse, tüm girişler doğru demektir.
            } catch (InputMismatchException e) {
                System.out.println("Hata: " + e.getMessage() + " Lütfen tekrar deneyin.");
                scanner.nextLine(); // Hatalı girişi temizle
            }
        }

        decompose(a);

        System.out.println("Alt Üçgen Matris (L):");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j < i) {
                    System.out.print(a[i][j] + "\t");
                } else if (j == i) {
                    System.out.print("1.0\t"); // Köşegen elemanları 1
                } else {
                    System.out.print("0.0\t");
                }
            }
            System.out.println();
        }

        System.out.println("\nÜst Üçgen Matris (U):");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j >= i) {
                    System.out.print(a[i][j] + "\t");
                } else {
                    System.out.print("0.0\t");
                }
            }
            System.out.println();
        }
    }
}
