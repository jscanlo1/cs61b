public class LinkedListDeque<Item>{
    private class itemNode {
        public Item item;
        public itemNode next;
        public itemNode prev;

        public itemNode(Item i, itemNode n, itemNode p){
            item = i;
            next = n;
            prev = p;
        }
    }

    //Using a circular linked list with a sentinel;
    private itemNode sentinel;
    private int size;

    public LinkedListDeque(){
        //Value of sentinel irrelevant. Set to arbitrary null value;
        sentinel = new itemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    public LinkedListDeque(Item x){

        sentinel = new itemNode(null, null, null);
        itemNode new_node = new itemNode(x, sentinel, sentinel);
        sentinel.next = new_node;
        sentinel.prev = new_node;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new itemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        itemNode curr_other = other.sentinel.next;

        itemNode curr = sentinel;

        while(curr_other != other.sentinel){
            curr.next = new itemNode(curr_other.item, sentinel,curr);
            curr = curr.next;
            curr_other = curr_other.next;
        }
        size = other.size();
    }

    public void addFirst(Item x){
        sentinel.next =  new itemNode(x,sentinel.next, sentinel);
        size += 1;
    }

    public void addLast(Item x){
        sentinel.prev = new itemNode(x, sentinel, sentinel.prev);
        size += 1;
    }

    public boolean isEmpty(){
        if(sentinel.next == sentinel){
            return true;
        } else {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        itemNode curr = sentinel.next;
        while(curr != sentinel){
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public Item removeFirst(){
        Item first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size --;
        return first;
    }

    public Item removeLast(){
        Item last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return last;
    }

    public Item get(int x){
        int counter = 0;
        itemNode curr = sentinel.next;
        while(curr != sentinel && counter != x){
            curr = curr.next;
        }

        //Technically could just return curr.item as sentinel has null value there
        //But for clarity's sake have included an if statement
        if(curr == sentinel){
            return null;
        } else{
            return curr.item;
        }

    }
}
