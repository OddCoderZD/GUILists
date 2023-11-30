public class QueueArray<E> implements Queueable<E> {
    private static final int MAX = 50; 
    private int size; 
    private Object[] queueArray;

    public QueueArray() {
        this.size = 0;
        this.queueArray = new Object[MAX];
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    
    public boolean isFull() {
        return (this.size == MAX);
    }

    @Override
    public E peek() throws ListException {
        if (this.isEmpty())
            throw new ListException("Queue is empty!");

        return (E) this.queueArray[0];
    }

    @Override
    public void enqueue(E item) throws ListException {
        if (this.isFull())    
            throw new ListException("Queue is full!");

        this.queueArray[this.size] = item;
        this.size++;        
    }

    @Override
    public E dequeue() throws ListException {
        if (this.isEmpty())
            throw new ListException("Queue is empty!");
        
        E firstItem = (E) this.queueArray[0];
        for (int i = 1; i < this.size; i++) {
            this.queueArray[i-1] = this.queueArray[i];
        }
        // optional 
        this.queueArray[this.size-1] = null; 


        this.size--;

        return firstItem;
    }

    @Override
    public void dequeueAll() {
        this.size = 0;
        // optional but recommended
        this.queueArray = new Object[MAX];
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < this.size; i++) {
            s = s + this.queueArray[i].toString();
            if (i != this.size-1) 
                s = s + ", ";
        }
        return s + "]";
    }
    
}