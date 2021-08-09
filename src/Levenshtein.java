// NOTE: See `https://en.wikipedia.org/wiki/Levenshtein_distance`.

public class Levenshtein {
    static int distance(String a, String b) {
        int m = a.length();
        int n = b.length();
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        int table[][] = new int[m + 1][n + 1];
        table[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            table[i][0] = i;
        }
        for (int j = 1; j <= n; ++j) {
            table[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                table[i][j] = Math.min(
                    table[i - 1][j] + 1,
                    Math.min(
                        table[i][j - 1] + 1,
                        table[i - 1][j - 1] +
                            ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1)));
            }
        }
        return table[m][n];
    }

    public static void main(String[] args) {
        assert distance("foobar", "") == 6;
        assert distance("", "foobar") == 6;
        assert distance("sitting", "kitten") == 3;
        assert distance("flaw", "lawn") == 2;
        assert distance("saturday", "sunday") == 3;
        assert distance("gumbo", "gambol") == 2;
        assert distance("book", "back") == 2;
        assert distance("edward", "edwin") == 3;
        assert distance("what is a sentence", "this is another thing") == 13;
        assert distance("the quick brown fox jumps over the lazy dog",
                        "pack my box with five dozen liquor jugs") == 33;
        System.out.println("Done!");
    }
}
