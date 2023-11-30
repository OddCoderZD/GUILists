public interface Queueable<E> {
    public boolean isEmpty();
    public E peek() throws ListException;
    public void enqueue(E item);
    public E dequeue() throws ListException;
    public void dequeueAll();
}