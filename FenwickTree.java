public class FenwickTree {
    private int[] tree;

    public FenwickTree(int size) {
        tree = new int[size];
    }

    private int getParent(int index) {
        return index - (index & -index);
    }

    private int getNext(int index) {
        return index + (index & -index);
    }

    public void add(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index = getNext(index);
        }
    }

    public int sum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index = getParent(index);
        }
        return sum;
    }

    public void remove(int index, int value) {
        while (index < tree.length) {
            tree[index] -= value;
            index = getNext(index);
        }
    }
}
