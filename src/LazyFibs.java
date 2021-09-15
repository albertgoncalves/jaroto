interface Func<T> {
    T eval();
}

class Lazy<T> {
    T       result;
    Func<T> func;
    boolean cached;

    public Lazy(Func<T> f) {
        result = null;
        func = f;
        cached = false;
    }

    public T force() {
        if (cached) {
            return result;
        }
        result = func.eval();
        cached = true;
        return result;
    }
}

class List<T> {
    T             value;
    Lazy<List<T>> thunk;

    public List(T v, Func<List<T>> f) {
        value = v;
        thunk = new Lazy<List<T>>(f);
    }

    public T head() {
        return value;
    }

    public List<T> tail() {
        return thunk.force();
    }

    public List<T> drop(int n) {
        List<T> l = this;
        for (; 0 < n; --n) {
            l = l.tail();
        }
        return l;
    }
}

public class LazyFibs {
    public static List<Long> fibs;

    static List<Long> zipSum(List<Long> a, List<Long> b) {
        return new List<Long>(a.head() + b.head(),
                              () -> zipSum(a.tail(), b.tail()));
    }

    public static void main(String[] args) {
        fibs = new List<Long>(
            0L,
            () -> new List<Long>(1L, () -> zipSum(fibs, fibs.tail())));
        Long x = fibs.drop(50).head();
        System.out.println(x);
        assert x == 12586269025L;
    }
}
