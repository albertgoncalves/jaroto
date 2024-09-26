public class AnonFunc {
    interface Procedure {
        void apply();
    }

    public static void main(String[] args) {
        int a[] = {0};
        int b = 1;
        int c = 2;

        Procedure f0 = () -> {
            ++a[0];
            Procedure f1 = () -> {
                System.out.println(a[0] + b + c);
            };
            f1.apply();
        };

        f0.apply();
        ++a[0];
        f0.apply();
    }
}
