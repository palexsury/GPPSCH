import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class BI extends Distribution{

    public BI(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    static long Factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    static long C(int k, int b) {
        return Factorial(b) / (Factorial(k) * Factorial(b - k));
    }

    @Override
    void makeDis() throws IOException {
        do {
            double x = new Double(f.nextLine());
            double u = U(x);
            double s = 0;
            double y = 0;
            for (int k = 0; k < b; k++) {
                s += C(k, (int) b) * Math.pow(a, k) * Math.pow(1 - a, b - k);
                if (s > u) {
                    y = k;
                    break;
                }
                if (k > b - 1) {
                    k++;
                }
                else y = b;
            }
            o.printf(Locale.US, "%.3f", y);
            o.println();
        } while (f.hasNextLine());
    }
}
