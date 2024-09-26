import java.util.Scanner;

// NOTE: See `https://codeforces.com/gym/102646/problem/D`.
// NOTE: See `https://codeforces.com/blog/entry/80150`.

/* NOTE: $ 5 4
 *         5 9 10 3 2
 *         10 10 5 5
 *       > 215
 * NOTE: $ 7 4
 *         1 9 3 8 19 3 2
 *         50 1 9 3
 *       > 638
 */

public class TeamSelection {
    static long[] parse(Scanner scanner) {
        String[] input = scanner.nextLine().split(" ");
        long[]   output = new long[input.length];
        for (int i = 0; i < input.length; ++i) {
            output[i] = Long.parseLong(input[i]);
        }
        return output;
    }

    public static void main(String[] args) {
        long[] a, b;
        {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            a = parse(scanner);
            b = parse(scanner);
            scanner.close();
        }
        {
            int      n = a.length;
            int      k = b.length;
            long[][] table = new long[n + 1][k + 1];
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= k; ++j) {
                    table[i][j] = Long.MIN_VALUE;
                }
            }
            table[0][0] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j <= k; ++j) {
                    table[i + 1][j] = Math.max(table[i + 1][j], table[i][j]);
                    if (j < k) {
                        table[i + 1][j + 1] =
                            Math.max(table[i + 1][j + 1], table[i][j] + (a[i] * b[j]));
                    }
                }
            }
            System.out.println(table[n][k]);
        }
    }
}
