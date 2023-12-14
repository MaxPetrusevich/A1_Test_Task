import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt(); // Замените на нужное значение n
        Double result = calculateUn(n);
        BigDecimal un = BigDecimal.valueOf(result).setScale(6, RoundingMode.HALF_UP);
        System.out.println("u_" + n + " = " + un);
    }

    // Функция для вычисления факториала
    private static Double factorial(int num) {
        double result = 1.0;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    // Функция для вычисления значения u_n
    private static Double calculateUn(int n) {
        Double sum = 0.0;
        for (int k = 1; k <= n; k++) {
            sum += factorial(k);
        }
        return sum/factorial(n);
    }
}
