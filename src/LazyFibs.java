class Lazy<T> {
    final Func<T> func;
    private T value;
    Func<T> cache;

    interface Func<T> {
        T call();
    }

    private T force() {
        this.value = func.call();
        this.cache = () -> this.value;
        return this.value;
    }

    public Lazy(Func<T> func) {
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
        return this._tail.cache.call();
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
    interface Func<T> {
        T call(T a, T b);
    }

    static <T> List<T> zipWith(Func<T> func, List<T> xs, List<T> ys) {
        return new List<T>(func.call(xs.head, ys.head), new Lazy<List<T>>(() -> {
                               return zipWith(func, xs.tail(), ys.tail());
                           }));
    }

    static List<Long> fibs;

    public static void main(String[] args) {
        fibs = new List<Long>(0L, new Lazy<List<Long>>(() -> {
                                  return new List<Long>(1L, new Lazy<List<Long>>(() -> {
                                                            return zipWith((Long a, Long b) -> {
                                                                return a + b;
                                                            }, fibs, fibs.tail());
                                                        }));
                              }));
        Long x = fibs.drop(50).head;
        System.out.println(x);
        assert x == 12586269025L;
    }
}
