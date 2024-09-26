public class AckermannPeter {
    // def ackermann_peter(m, n):
    //     if m == 0:
    //         return n + 1
    //     elif n == 0:
    //         return ackermann_peter(m - 1, 1)
    //     else:
    //         return ackermann_peter(m - 1, ackermann_peter(m, n - 1))

    static int ackermannPeter(int m, int n) {
        if (m == 0) {
            return n + 1;
        } else if (n == 0) {
            return ackermannPeter(m - 1, 1);
        } else {
            return ackermannPeter(m - 1, ackermannPeter(m, n - 1));
        }
    }

    public static void main(String[] args) {
        for (int n = 0; n < 11; ++n) {
            System.out.println(ackermannPeter(3, n));
        }
    }
}
