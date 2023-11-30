package CustomFiles;

public class FavoriteList extends CustomListADT<Favorite> {

    private Favorite[] favorites;
    private int numFavorites;
    private static final int MAX_FAVORITES = 50;


    public FavoriteList() {
        this.numFavorites = 0;
        this.favorites = new Favorite[MAX_FAVORITES];
    }

    @Override
    public int size() {
        
        return this.numFavorites;
    }
    @Override
    public boolean isEmpty() {
       return (this.numFavorites == 0); 
    }
    @Override
    public void removeAll() {
        this.numFavorites = 0;
        this.favorites =  new Favorite[MAX_FAVORITES];
    }

    @Override
    public void add(int index, Favorite newFave) throws CustomListException {
        if (index < 0  || index > this.size())
            throw new CustomListException("Index " + index + " is invalid for a list of size: " + this.size());
        
        if (this.size()  >= MAX_FAVORITES)
            throw new CustomListException("Index exceeds the maximum allowed size of the list: " + MAX_FAVORITES);

        for (int i = this.size(); i > index;  i--) {
            this.favorites[i] = this.favorites[i-1];
        }
        this.favorites[index] = newFave;
        this.numFavorites++;
    }
    @Override
    public Favorite get(int index) throws CustomListException {
        if (index < 0 || index >= this.size())
            throw new CustomListException("Index " + index + " is invalid for a list of size: " + this.size());

        return this.favorites[index];
    }
    @Override
    public void remove(int index) throws CustomListException {
        if (index < 0 || index >= this.size())
            throw new CustomListException("Index " + index + " is invalid for a list of size: " + this.size());

        for (int i = index+1; i < this.size(); i++) {
            this.favorites[i-1] = this.favorites[i];
        }
        
        this.numFavorites--;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.size(); i++) 
            s = s + i + " : "  + this.get(i) + "\n";

        return s;
    }
    
    
}