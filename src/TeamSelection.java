import java.util.Scanner;

// NOTE: See `https://codeforces.com/gym/102646/problem/D`.
// NOTE: See `https://codeforces.com/blog/entry/80150`.

public class TeamSelection {
    static long[] toLongs(String[] input) {
        long[] output = new long[input.length];
        for (int i = 0; i < input.length; ++i) {
            output[i] = Long.parseLong(input[i]);
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        long[] a = toLongs(scanner.nextLine().split(" "));
        long[] b = toLongs(scanner.nextLine().split(" "));
        scanner.close();
        {
            int n = a.length;
            int k = b.length;
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
                            Math.max(table[i + 1][j + 1],
                                     table[i][j] + (a[i] * b[j]));
                    }
                }
            }
            System.out.println(table[n][k]);
        }
    }
}
