import java.util.function.BiFunction;
import java.util.function.Supplier;

class Lazy<T> {
    final Supplier<T> func;
    private T value;
    Supplier<T> cache;

    private T force() {
        this.value = func.get();
        this.cache = () -> this.value;
        return this.value;
    }

    public Lazy(Supplier<T> func) {
        this.value = null;
        this.func = func;
        this.cache = () -> this.force();
    }
}

class List<T> {
    final T head;
    private final Lazy<List<T>> _tail;

    public List(T head, Lazy<List<T>> tail) {
        this.head = head;
        this._tail = tail;
    }

    public List<T> tail() {
        return this._tail.cache.get();
    }

    public List<T> drop(int n) {
        List<T> list = this;
        for (; 0 < n; --n) {
            list = list.tail();
        }
        return list;
    }
}

public class LazyFibs {
    static <T> List<T> zipWith(BiFunction<T, T, T> func, List<T> xs, List<T> ys) {
        // clang-format off
        return new List<T>(
            func.apply(xs.head, ys.head),
            new Lazy<List<T>>(() -> {
                return zipWith(func, xs.tail(), ys.tail());
            })
        );
        // clang-format on
    }

    static List<Long> fibs;

    public static void main(String[] args) {
        // clang-format off
        fibs = new List<Long>(0L, new Lazy<List<Long>>(() -> {
            return new List<Long>(1L, new Lazy<List<Long>>(() -> {
                return zipWith((Long a, Long b) -> {
                    return a + b;
                }, fibs, fibs.tail());
            }));
        }));
        // clang-format on
        final Long x = fibs.drop(50).head;
        System.out.println(x);
        assert x == 12586269025L;
    }
}
