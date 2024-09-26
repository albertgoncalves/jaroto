public class MinCostPath {
    static int minCost(int[][] costs, int m, int n) {
        int[][] table = new int[m + 1][n + 1];
        table[0][0] = costs[0][0];
        for (int i = 1; i <= m; ++i) {
            table[i][0] = table[i - 1][0] + costs[i][0];
        }
        for (int j = 1; j <= n; ++j) {
            table[0][j] = table[0][j - 1] + costs[0][j];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                table[i][j] = costs[i][j] + Math.min(table[i - 1][j - 1],
                                                     Math.min(table[i][j - 1], table[i - 1][j]));
            }
        }
        return table[m][n];
    }

    public static void main(String[] args) {
        int[][] costs = {{1, 2, 3}, {4, 8, 2}, {1, 5, 3}};
        assert  minCost(costs, 2, 2) == 8;
        System.out.println("Done!");
    }
}
