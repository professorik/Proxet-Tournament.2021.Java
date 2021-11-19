package proxet.tournament.generator.dto;

public class Pair<T, K> {
    private final T first;
    private final K last;

    public Pair(T first, K last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public K getLast() {
        return last;
    }
}
