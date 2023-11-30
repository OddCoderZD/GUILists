public class ReferenceBasedList<E> extends ListADT<E> {
    private int numFriends;
    private Node<E> head;

    // for an empty list
    public ReferenceBasedList() {
        this.numFriends = 0;
        this.head = null;
    }

    // for a list with a starting item (friend)
    public ReferenceBasedList(E firstItem) {
        this.numFriends = 1;
        this.head = new Node<E>(firstItem);
    }

    @Override
    public int size() {
        return this.numFriends;
        //return this.getSize(this.head);
        /* 
        int num = 0;
        Node<Friend> current = this.head;
        while (current != null) {
            current = current.getNext(); // moves to the next 
            num++;
        }
        return num;
        */
    }

    public int getSize(Node<E> node) {
        if (node == null) 
            return 0;
        else 
            return 1 + this.getSize(node.getNext());
    }

    @Override
    public boolean isEmpty() {
        return (this.numFriends == 0);
    }

    @Override
    public void removeAll() {
        this.numFriends = 0;
        this.head = null;
    }

    private Node<E> getNodeAt(int index) {
        Node<E> n = this.head; // index 0
        // n.getNext() // index 1
        // n.getNext().getNext() // index 2

        // automate this
        for (int k = 1; k <= index; k++)
            n = n.getNext();

        return n;
    }

    @Override
    public E get(int index) throws ListException {
        if (index < 0 || index >= this.size())
            throw new ListException("Index " + index +
                    " is invalid for a list of size " + this.size());

        Node<E> node = this.getNodeAt(index);
        return node.getItem();
    }

    @Override
    public void add(int index, E item) throws ListException {
          if (index < 0 || index > this.size())
            throw new ListException("Index " + index +
                    " is invalid for a list of size " + this.size());


        Node<E> newFriend = new Node<E>(item);
        if (index == 0) { // start of the list
            newFriend.setNext(this.head);
            this.head = newFriend;
        } else {
            Node<E> previous = this.getNodeAt(index-1);
            Node<E> currentAtIndex = previous.getNext();

            previous.setNext(newFriend);
            newFriend.setNext(currentAtIndex);
        }
        this.numFriends++; // nice
    }

    @Override
    public void remove(int index) throws ListException {
        if (index < 0 || index >= this.size())
            throw new ListException("Index " + index +
                    " is invalid for a list of size " + this.size());

        if (index == 0) { // remove the first element
            this.head = this.head.getNext();
        } else { // removing an element from the middle or end of the list
            Node<E> previous = this.getNodeAt(index - 1);
            Node<E> nodeToRemove = previous.getNext();
            Node<E> successor = nodeToRemove.getNext();

            previous.setNext(successor);
            nodeToRemove.setNext(null);
        }

        this.numFriends--;
    }

    @Override
    public String toString() {
        String s = "[";
        if (this.size() > 0) {
            Node<E> current = this.head;
            for (int i = 0; i < this.size() - 1; i++) {
                s = s + current.getItem().toString() + ", ";
                current = current.getNext();
            }
            s = s + current.getItem().toString(); 
        }
        return s + "]";
    }
}