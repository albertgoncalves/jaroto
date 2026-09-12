public class AnonFunc {
    public static void main(String[] args) {
        int a[] = {0};
        int b = 1;
        int c = 2;

        Runnable f0 = () -> {
            ++a[0];
            Runnable f1 = () -> {
                System.out.println(a[0] + b + c);
            };
            f1.run();
        };

        f0.run();
        ++a[0];
        f0.run();
    }
}
