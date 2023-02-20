public class ArrayDeque <Item> {
    private Item[] array;
    private int size;

    public ArrayDeque(){
        array = (Item[]) new Object[100];
        size = 0;
    }

    public ArrayDeque(Item x){
        array = (Item[]) new Object[100];
        array[0] = x;
        size = 1;
    }

    public ArrayDeque(ArrayDeque other){
        array = (Item[]) new Object[other.array.length];
        System.arraycopy(other,0, array,0, other.size());

    }

    public int size(){
        return size;
    }
    private void resize(int new_capacity){
        Item[] new_array = (Item[]) new Object[new_capacity];
        System.arraycopy(array,0,new_array,0, size);
        array = new_array;
    }

    public void addFirst(Item x){
        if(size == array.length){
            resize(array.length * 2);
        }

        Item[] new_array = (Item[]) new Object[array.length];
        new_array[0] = x;
        System.arraycopy(array,0,new_array,1,size);
        size++;
    }
    public void addLast(Item x){
        if(size == array.length) {
            resize(array.length * 2);
        }

        array[size] = x;
        size++;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        } else {
            return false;
        }
    }

    public void printDeque(){
        for(int i = 0; i < size; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public Item removeFirst(){
        Item first = array[0];
        Item[] new_array = (Item[]) new Object[array.length];
        System.arraycopy(array,1,new_array,0, size - 1);
        array = new_array;
        size--;
        return first;
    }

    public Item removeLast(){
        Item last = array[size - 1];
        size --;
        return last;
    }

    public Item get(int i){
        if(i < 0 || i >= size){
            return null;
        } else {
            return array[i];
            
        }
    }


}
