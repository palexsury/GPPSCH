import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class LN extends Distribution {

    public LN(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    @Override
    void makeDis() throws IOException {
        do {
            double x1 = new Double(f.nextLine());
            if (f.hasNextLine()) {
                Double x2 = new Double(f.nextLine());
                double[] Z = norm(x1, x2, 0,1);
                double y1 = a + Math.exp(b - Z[0]);
                double y2 = a + Math.exp(b - Z[1]);
                o.printf(Locale.US, "%.3f", y1);
                o.println();
                o.printf(Locale.US, "%.3f", y2);
                o.println();
            }
        } while (f.hasNextLine());
    }
}
