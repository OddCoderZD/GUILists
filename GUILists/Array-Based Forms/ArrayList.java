public class ArrayList<E> extends ListADT<E> {
    private Object[] items;
    private int numItems;
    private static final int MAX_ITEMS = 50;


    public ArrayList() {
        this.numItems = 0;
        this.items = new Object[MAX_ITEMS];
    }

    @Override
    public int size() {
        
        return this.numItems;
    }
    @Override
    public boolean isEmpty() {
       return (this.numItems == 0); 
    }
    @Override
    public void removeAll() {
        this.numItems = 0;
        this.items =  new Object[MAX_ITEMS];
    }

    @Override
    public void add(int index, E newItem) throws ListException {
        if (index < 0  || index > this.size())
            throw new ListException("Index " + index + " is invalid for a list of size: " + this.size());
        
        if (this.size()  >= MAX_ITEMS)
            throw new ListException("Index exceeds the maximum allowed size of the list: " + MAX_ITEMS);

        for (int i = this.size(); i > index;  i--) {
            this.items[i] = this.items[i-1];
        }
        this.items[index] = newItem;
        this.numItems++;
    }
    @Override
    public E get(int index) throws ListException {
        if (index < 0 || index >= this.size())
            throw new ListException("Index " + index + " is invalid for a list of size: " + this.size());

        return (E) this.items[index];
    }
    @Override
    public void remove(int index) throws ListException {
        if (index < 0 || index >= this.size())
            throw new ListException("Index " + index + " is invalid for a list of size: " + this.size());

        for (int i = index+1; i < this.size(); i++) {
            this.items[i-1] = this.items[i];
        }
        
        this.numItems--;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.size(); i++) 
            s = s + i + " : "  + this.get(i) + "\n";

        return s;
    }
}
