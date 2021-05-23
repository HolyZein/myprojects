import java.math.BigInteger;
import java.util.LinkedList;

public class MyHashTable<K, V> {
    private int capacity = 5;
    private int size = 0;
    private LinkedList<Node<K, V>>[] values;

    MyHashTable() {
        values = new LinkedList[capacity];
    }


    private int hash(K key) {
        int id = key.hashCode() % capacity;
        id = id < 0 ? -id : id;
        return id;
    }


    public void put(K key, V value) {
        if ((double) size / capacity > 0.7)
            increaseBuffer();
        int id = hash(key);
        if (values[id] == null) {
            LinkedList<Node<K, V>> chain = new LinkedList<>();
            chain.add(new Node<>(key, value));
            values[id] = chain;
        } else {
            LinkedList<Node<K, V>> chain = values[id];
            chain.add(new Node<>(key, value));
        }
        size++;
    }


    private void increaseBuffer() {
        capacity *= 1.5;
        LinkedList<Node<K, V>>[] temp = values.clone();
        values = new LinkedList[capacity];
        System.arraycopy(temp, 0, values, 0, temp.length);
    }


    public V get(K key) {
        int id = hash(key);
        if (values[id] == null) return null;
        LinkedList<Node<K, V>> chain = values[id];
        for (Node<K, V> node : chain)
            if (node.key == key) return node.value;
        return null;
    }


    public V remove(K key) {
        int id = hash(key);
        Node<K, V> result = null;
        LinkedList<Node<K, V>> chain = values[id];
        for (Node<K, V> node : chain)
            if (node.key == key) result = node;
        if (result == null) return null;
        chain.remove(result);
        size--;
        return result.value;
    }


    public boolean contains(V value) {
        for (LinkedList<Node<K, V>> chain : values)
            if (chain != null)
                for (Node<K, V> node : chain)
                    if (node.value == value) return true;
        return false;
    }

    public K getKey(V value) {
        for (LinkedList<Node<K, V>> chain : values)

            if (chain != null)
                for (Node<K, V> node : chain)
                    if (node.value == value) return node.key;
        return null;
    }

    static class Node<K, V> {
        private final K key;
        private final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class Cryptocurrency {
        private final Double price;
        private final BigInteger volume;
        private final BigInteger marketCap;

        public Cryptocurrency(Double price, BigInteger marketCap, BigInteger volume) {
            this.price = price;
            this.volume = volume;
            this.marketCap = marketCap;
        }

        public String toString() {
            return "Cryptocurrency{" +
                    "price=" + price + "," +
                    "volume=" + volume + "," +
                    "marketCap=" + marketCap +
                    "}";
        }
    }

    public static void main(String[] args) {
        MyHashTable<String, Cryptocurrency> cryptocurrencyHashTable = new MyHashTable<>();
        Cryptocurrency bitcoin = new Cryptocurrency(37_982.90, new BigInteger("710800561660"), new BigInteger("86058489330"));
        Cryptocurrency etherium = new Cryptocurrency(2_407.99, new BigInteger("279812737842"), new BigInteger("59004780070"));
        Cryptocurrency tether = new Cryptocurrency(1.0, new BigInteger("58588192742"), new BigInteger("186767865500"));
        Cryptocurrency binanceCoin = new Cryptocurrency(320.58, new BigInteger("48824981282"), new BigInteger("6892513143"));
        cryptocurrencyHashTable.put("Bitcoin", bitcoin);
        cryptocurrencyHashTable.put("Etherium", etherium);
        cryptocurrencyHashTable.put("Tether", tether);
        System.out.println(cryptocurrencyHashTable.get("Bitcoin"));
        System.out.println(cryptocurrencyHashTable.remove("Tether"));
        System.out.println(cryptocurrencyHashTable.getKey(etherium));
        System.out.println(cryptocurrencyHashTable.getKey(tether));
        System.out.println(cryptocurrencyHashTable.contains(bitcoin));
        System.out.println(cryptocurrencyHashTable.contains(binanceCoin));
    }
}
