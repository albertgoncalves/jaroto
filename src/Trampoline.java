import java.util.ArrayList;
import java.util.function.IntConsumer;

public class Trampoline {
    ArrayList<Runnable> thunks = new ArrayList<Runnable>();

    void runAll() {
        while (!thunks.isEmpty()) {
            thunks.removeLast().run();
        }
    }

    void ackermannPeterCpsTrampoline(IntConsumer k, int m, int n) {
        if (m == 0) {
            thunks.addLast(() -> { k.accept(n + 1); });
        } else if (n == 0) {
            thunks.addLast(() -> { ackermannPeterCpsTrampoline(k, m - 1, 1); });
        } else {
            thunks.addLast(() -> {
                ackermannPeterCpsTrampoline((x) -> {
                    thunks.addLast(() -> { ackermannPeterCpsTrampoline(k, m - 1, x); });
                }, m, n - 1);
            });
        }
    }

    public void main(String[] args) {
        final int m = 3;
        for (int i = 0; i < 11; ++i) {
            final int n = i;
            thunks.addLast(() -> {
                ackermannPeterCpsTrampoline((int x) -> { System.out.println(x); }, m, n);
            });
            runAll();
        }
    }
}
