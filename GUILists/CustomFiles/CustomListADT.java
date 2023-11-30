package CustomFiles;

public abstract class CustomListADT<E> {
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract void removeAll();
    public abstract void add(int index, E item) throws CustomListException;
    public abstract E get(int index) throws CustomListException;
    public abstract void remove(int index) throws CustomListException;
}