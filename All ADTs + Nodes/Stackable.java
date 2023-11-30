public interface Stackable<E> {
    public boolean isEmpty();
    public void push(E newItem);
    public E pop() throws ListException;
    public void popAll();
    public E peek() throws ListException;
}