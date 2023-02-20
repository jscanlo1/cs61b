import java.util.Iterator;


public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {

        for (T y: items) {
            if (x.equals(y)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {

        if (x == null) {
            //throw new IllegalArgumentException("Cannot add null to a Set");
            //Alternative change contains function to be null safe
            return;
        }

        if (contains(x)) {
            return;
        }
        items[size] = x;
        size++;
    }



    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** Returns an iterator into this set */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int position;

        private ArraySetIterator() {
            position = 0;
        }
        @Override
        public boolean hasNext() {
            return position < size();
        }

        @Override
        public T next() {
            T nextItem = items[position];
            position++;
            return nextItem;
        }
    }


    @Override
    public String toString(){
        StringBuilder outputSB = new StringBuilder("{");

        for(T x: this){
            outputSB.append(x.toString());
            outputSB.append(", ");

        }

        outputSB.append("}");

        return outputSB.toString();
    }

    @Override
    public boolean equals(Object other){

        if(this == other){
            return true;
        }

        if(other == null){
            return false;
        }

        if(other.getClass() != this.getClass()){
            return false;
        }

        ArraySet<T> o = (ArraySet<T>) other;

        if(o.size() != this.size()){
            return false;
        }

        for(T item: this){
            if(!o.contains(item)){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        //s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        Iterator<String> seer = s.iterator();

        while (seer.hasNext()) {
            System.out.println(seer.next());
        }

        for(String x: s) {
            System.out.println(x);
        }


        System.out.println(s);
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
