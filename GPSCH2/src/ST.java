import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

class ST extends Distribution {

    public ST(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        super(m, f, o, a, b, c);
    }

    @Override
    void makeDis() throws IOException {
        do {
            double x = new Double(f.nextLine());
            double y = a + U(x) * b;
            o.printf(Locale.US, "%.3f", y);
            o.println();
        } while (f.hasNextLine());
    }
}
