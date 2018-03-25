package service;

import entity.Summable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sum {

    private final List<? extends Summable> values;
    private int sum;
    private List<Integer> removed;
    private int state;

    Sum(List<? extends Summable> values, int sum) {
        this.values = values;
        this.removed = new ArrayList<>();
        this.sum = sum;
        this.state = values.size();
    }

    public static Sum of(Sum sum) {
        Sum newSum = new Sum(sum.values, sum.sum);
        newSum.removed = new ArrayList<>(sum.removed);
        newSum.state = sum.state;
        return newSum;
    }

    public Sum remove() {
        this.removed.add(this.state);
        return this;
    }

    public Sum decrease() {
        this.sum -= get(state).getValue();
        return this;
    }

    public List<? extends Summable> getRest() {
        return IntStream.range(state, values.size())
                .filter(i -> !removed.contains(i))
                .mapToObj(values::get)
                .collect(Collectors.toList());
    }

    public int getSum() {
        return sum;
    }

    public Summable get(int index) {
        return values.get(index);
    }

    public int getState() {
        return state;
    }

    public Sum stateDecrement(){
        this.state = this.state > 0 ? this.state - 1 : 0;
        return this;
    }
}
