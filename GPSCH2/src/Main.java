import java.io.*;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.*;

abstract class Distribution {

    double m;
    Scanner f;
    PrintWriter o;
    double a;
    double b;
    double c;

    Distribution(double m, Scanner f, PrintWriter o, double a, double b, double c) {
        this.m = m;
        this.f = f;
        this.o = o;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double U(double x) {
        return (1. * x / m);
    }

    double[] norm(double x1, double x2) {
        double[] Y = new double[2];
        Y[0] = a + b * Math.sqrt(-2 * Math.log(1 - U(x1))) * Math.cos(2 * Math.PI * U(x2));
        Y[1] = a + b * Math.sqrt(-2 * Math.log(1 - U(x1))) * Math.sin(2 * Math.PI * U(x2));
        return Y;
    }
    double[] norm(double x1, double x2, double a1, double b1) {
        double[] Y = new double[2];
        Y[0] = a1 + b1 * Math.sqrt(-2 * Math.log(1 - U(x1))) * Math.cos(2 * Math.PI * U(x2));
        Y[1] = a1 + b1 * Math.sqrt(-2 * Math.log(1 - U(x1))) * Math.sin(2 * Math.PI * U(x2));
        return Y;
    }

    abstract void makeDis() throws IOException;
}

public class Main {
    public static void main(String[] args) throws IOException {
        if (args[0].contains("/h")) {
            err.println("Программа: преобразование ПСЧ к нужному распределению\n");
            err.println("----------------------------------------------------------------");
            err.println("Для управления приложением используется следующий формат параметров комендной строки:");
            err.println("/f:<имя_файла> - имя файла с входной последовательностью. Ожидается, что в файле числа записаны построчно. Если не указано, то используется стандартный поток ввода");
            err.println("/m:<неотрицательное целое число (32 бит)> - на вход подаются числа от 0 до m-1");
            err.println("/d:<распределение> - код распределения для преобразования последовательности.");
            err.println("o:<имя файла> - имя файлы с выходной последовательностью. Если отсутствует, то вывод происходит в стандартный поток вывода");
            err.println("/a:<число> - обязательный параметр распределения");
            err.println("/b:<число> - обязательный параметр распределения");
            err.println("/c:<число> - параметр обязателен только для гамма распределения");
            err.println("/h - показать информацию о программе");
            err.println("----------------------------------------------------------------");
            err.println("Коды распределений:");
            err.println("st - стандартное равномерное с заданным интервалом;");
            err.println("tr - треугольное распределение;");
            err.println("ex - общее экспоненциальное распределение;");
            err.println("nr - нормальное распределение;");
            err.println("gm - гамма распределение;");
            err.println("ln - логнормальное распределение;");
            err.println("ls - логистическое распределение;");
            err.println("bi - биномиальное распределение.");
        }
        else {
            boolean file_f = false;
            boolean file_o = false;
            String name = "";
            Scanner f = null;
            PrintWriter o = null;
            double m = 0, a = 0, b = 0, c = 0;
            for (String r : args) {
                if (r.contains("/f")) {
                    file_f = true;
                    f = new Scanner(new FileReader(r.substring(3)));
                }
                else if (r.contains("/m")) {
                    m = Double.parseDouble(r.substring(3));
                }
                else if (r.contains("/d")) {
                    name = r.substring(3);
                }
                else if (r.contains("/o")) {
                    file_o = true;
                    o = new PrintWriter(r.substring(3));
                }
                else if (r.contains("/a")) {
                    a = Double.parseDouble(r.substring(3));
                }
                else if (r.contains("/b")) {
                    b = Double.parseDouble(r.substring(3));
                }
                else if (r.contains("/c")) {
                    c = Double.parseDouble(r.substring(3));
                }
            }
            if (!file_f) {
                f = new Scanner(in);
                err.println("Вводите числа через строку");
                err.println("Для завершения ввода на новой строке введите ^Z(или ^D)");
            }
            if (!file_o) {
                o = new PrintWriter(new OutputStreamWriter(System.err, "Cp866"), true);
            }
            Distribution distribution;
            switch (name) {
                case ("st") :
                    distribution = new ST(m, f, o, a, b, c);
                    break;
                case ("tr") :
                    distribution = new TR(m, f, o, a, b, c);
                    break;
                case ("ex") :
                    distribution = new EX(m, f, o, a, b, c);
                    break;
                case ("nr") :
                    distribution = new NR(m, f, o, a, b, c);
                    break;
                case ("ln") :
                    distribution = new LN(m, f, o, a, b, c);
                    break;
                case ("ls") :
                    distribution = new LS(m, f, o, a, b, c);
                    break;
                case ("bi") :
                    distribution = new BI(m, f, o, a, b, c);
                    break;
                case ("gm") :
                    distribution = new GM(m, f, o, a, b, c);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + name);
            }
            distribution.makeDis();
            if (!file_o) {
                o.println("Преобразование к выбранному распределению успешно завершено");
            }
            else {
                err.println("Преобразование к выбранному распределению успешно завершено");
            }
            o.flush();
            o.close();
            f.close();
        }
    }
}