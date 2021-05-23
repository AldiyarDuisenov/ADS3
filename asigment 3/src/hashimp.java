import java.util.ArrayList;
import java.util.Objects;

public class hashimp {


    static class HashNode<K, V> {
        private K key;
        private V value;
        final int hashCode;

        HashNode<K, V> next;

        public HashNode(K key, V value, int hashCode)
        {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    static class Map<K, V>{
        private ArrayList<HashNode<K, V> > bucketArray;

        private int numBuckets;

        private int size;

        public Map(){
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;

            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
        }


        private final int hash(K key) {
            return Objects.hashCode(key);
        }

        private int getBucketIndex(K key){
            int hashCode = hash(key);
            int index = hashCode % numBuckets;
            index = index < 0 ? index * -1 : index;
            return index;
        }

        public V remove(K key){
            int bucketIndex = getBucketIndex(key);
            int hashCode = hash(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

            HashNode<K, V> prev = null;
            while (head != null) {
                if (head.key.equals(key) && hashCode == head.hashCode)
                    break;

                prev = head;
                head = head.next;
            }

             if (head == null)
                return null;

             size--;

             if (prev != null)
                prev.next = head.next;
            else
                bucketArray.set(bucketIndex, head.next);

            return head.value;
        }

         public V get(K key){
             int bucketIndex = getBucketIndex(key);
            int hashCode = hash(key);

            HashNode<K, V> head = bucketArray.get(bucketIndex);

             while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode)
                    return head.value;
                head = head.next;
            }

             return null;
        }

         public void put(K key, V value){
             int bucketIndex = getBucketIndex(key);
            int hashCode = hash(key);
            HashNode<K, V> head = bucketArray.get(bucketIndex);

             while (head != null){
                if (head.key.equals(key) && head.hashCode == hashCode) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

             size++;
            head = bucketArray.get(bucketIndex);
            HashNode<K, V> newNode
                    = new HashNode<K, V>(key, value, hashCode);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);

              if ((1.0 * size) / numBuckets >= 0.7) {
                ArrayList<HashNode<K, V> > temp = bucketArray;
                bucketArray = new ArrayList<>();
                numBuckets = 2 * numBuckets;
                size = 0;
                for (int i = 0; i < numBuckets; i++)
                    bucketArray.add(null);

                for (HashNode<K, V> headNode : temp) {
                    while (headNode != null) {
                        put(headNode.key, headNode.value);
                        headNode = headNode.next;
                    }
                }
            }
        }

         public static void main(String[] args){
            Map<String, Integer> map = new Map<>();
            map.put("aaa", 1);
            map.put("bbb", 2);
            map.put("ccc", 4);
            map.put("ddd", 5);
            System.out.println(map.get("aaa"));
            System.out.println(map.remove("bbb"));
        }
    }

}
