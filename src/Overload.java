public class Overload {
    static String f(int x) {
        return f("?");
    }

    static String f(String x) {
        return x;
    }

    public static void main() {
        System.out.println(f(1));
        System.out.println(f("!"));
    }
}
