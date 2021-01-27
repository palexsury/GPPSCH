import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class NR extends Distribution {

    public NR(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    @Override
    void makeDis() throws IOException {
        do {
            double x1 = new Double(f.nextLine());
            if (f.hasNextLine()) {
                double x2 = new Double(f.nextLine());
                double Y[] = norm(x1, x2);
                o.printf(Locale.US, "%.3f", Y[0]);
                o.println();
                o.printf(Locale.US, "%.3f", Y[1]);
                o.println();
            }
        } while (f.hasNextLine());
    }
}