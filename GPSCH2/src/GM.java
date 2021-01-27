import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GM extends Distribution {

    public GM(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    double gamma1(ArrayList<Double> x) {
        double y = 1;
        for (int i = 0; i < c; i++) {
            y *= 1 - U(x.get(i));
        }
        return a - b * Math.log(y);
    }

    double[] gamma2(ArrayList<Double> x, int k) {
        double[] y = new double[2];
        y[0] = 1; y[1] = 1;
        double[] z = norm(x.get(0), x.get(1), 0, 1);
        for (int i = 0; i < k; i++) {
            y[0] *= 1 - U(x.get(2 + i));
            y[1] *= 1 - U(x.get(k + 2 + i));
        }
        y[0] = a + b * (z[0] * z[0] / 2 - Math.log(y[0]));
        y[1] = a + b * (z[1] * z[1] / 2 - Math.log(y[1]));
        return y;
    }

    @Override
    void makeDis() throws IOException {
        String s = (new Double(c)).toString();
        s = s.substring(s.length() - 2);
        ArrayList<Double> X = new ArrayList<Double>();
        do {
            X.add(new Double(f.nextLine()));
        } while (f.hasNextLine());
        int n = X.size();
        ArrayList<Double> x;
        if (s.equals(".5")) {
            int k = (int) (c - 0.5);
            for (int i = 0; i < n / (2 * k + 2); i++) {
                double[] y = gamma2(new ArrayList<Double>(X.subList(i * (2 * k + 2), (i + 1) * (2 * k + 2))), k);
                o.printf(Locale.US, "%.3f", y[0]);
                o.println();
                o.printf(Locale.US, "%.3f", y[1]);
                o.println();
            }

        } else {
            int k = (int) c;
            for (int i = 0; i < n / k; i++) {
                double y = gamma1(new ArrayList<Double>(X.subList(i * k, (i + 1) * k)));
                o.printf(Locale.US, "%.3f", y);
                o.println();
            }
        }
    }
}