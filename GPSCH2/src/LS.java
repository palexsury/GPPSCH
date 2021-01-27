import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class LS extends Distribution {

    public LS(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    @Override
    void makeDis() throws IOException {
        do {
            double x = new Double(f.nextLine());
            double u = U(x);
            double y = a + b * Math.log(u / (1 - u));
            o.printf(Locale.US, "%.3f", y);
            o.println();
        } while (f.hasNextLine());
    }
}
