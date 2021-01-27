import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class TR extends Distribution{

    public TR(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    @Override
    void makeDis() throws IOException {
        do {
            double x1 = new Double(f.nextLine());
            if (f.hasNextLine()) {
                double x2 = new Double(f.nextLine());
                double y = (a + b * (U(x1) + U(x2) - 1));
                o.printf(Locale.US, "%.3f", y);
                o.println();
            }
            else
                break;
        } while (f.hasNextLine());
    }
}
