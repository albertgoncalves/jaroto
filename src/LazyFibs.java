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
    static List<Long> fibs;

    static List<Long> zipSum(List<Long> xs, List<Long> ys) {
        return new List<Long>(xs.head() + ys.head(), new F0(xs, ys));
    }

    static class F0 implements Func<List<Long>> {
        List<Long> xs;
        List<Long> ys;

        public F0(List<Long> l0, List<Long> l1) {
            xs = l0;
            ys = l1;
        }

        public List<Long> eval() {
            return zipSum(xs.tail(), ys.tail());
        }
    }

    static class F1 implements Func<List<Long>> {
        public List<Long> eval() {
            return zipSum(fibs, fibs.tail());
        }
    }

    static class F2 implements Func<List<Long>> {
        public List<Long> eval() {
            return new List<Long>(1L, new F1());
        }
    }

    public static void main(String[] args) {
        fibs = new List<Long>(0L, new F2());
        Long x = fibs.drop(50).head();
        System.out.println(x);
        assert x == 12586269025L;
    }
}
