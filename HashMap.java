package HashMapJAVA;


/*
 * This is a simple implementation of a HashMap in Java.
 * It will use an array of LinkedLists to store the key-value pairs.
 */

// compute the hashcode of the key
// use the hashcode to find the index in the array
// add the key-value pair to the LinkedList at that index
// if it maps to the same index, we add it to the LinkedList at the end

// collision: when two keys map to the same index

public class myHashMap<K, V>{

    private class Entry<K, V>{
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public V getValue(){
            return this.value;
        }

        public K getKey(){
            return this.key;
        }

        public void setValue(V value){
            this.value = value;
        }

    }

    private final int SIZE = 5;

    private Entry<K, V>[] table;

    public myHashMap(){
        table = new Entry[SIZE];
    }

    public V get (K key){
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];

        if (e == null){
            return null;
        }
        
        while (e != null){
            if (e.getKey().equals(key)){
                return e.getValue();
            }
            e = e.next;
        }

        return null;
    }

    public void put(K key, V value){
        int hash = key.hashCode() % SIZE;

        Entry<K, V> e = table[hash];

        if (e == null){
            table[hash] = new Entry<K, V>(key, value);  
        } else {
            while (e.next != null){
                if (e.key.equals(key)){
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }

            if (e.getKey() == key){
                e.setValue(value);
                return;
            }

            e.next = new Entry<K, V>(key, value);

        }

        }

     public Entry<K , V> remove(K key){
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];

        if (e == null){
            return null;
        }

        if (e.getKey().equals(key)){
            table[hash] = e.next;
            e.next = null;
            return e;
        }

        Entry<K, V> prev = e;
        e = e.next;

        while (e != null){
            if (e.getKey().equals(key)){
                prev.next = e.next;
                e.next = null;
                return e;
            }
            prev = e;
            e = e.next;
        }

        return null;

    }
}

