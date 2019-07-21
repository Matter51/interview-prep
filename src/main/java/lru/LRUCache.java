package lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class LRUCache<T> {

    private int size;
    private Queue<T> queue;
    private HashMap<Integer, T> entries;

    public LRUCache(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
        this.entries = new HashMap<>();
    }

    public int getCacheSize() {
        return this.entries.size();
    }

    public T get(int hashcode) {
        if (entries.containsKey(hashcode)) {
            T entryToReturn = entries.get(hashcode);
            // Move entry to front of list, it is now most recently used
            Queue<T> newQueue = new LinkedList<>();

            for (T entry : this.queue) {
                if (entry != entryToReturn) {
                    newQueue.add(entry);
                }
            }
            newQueue.add(entryToReturn);

            this.queue = newQueue;
            return entryToReturn;
        } else {
            return null;
        }
    }

    public void put(T entry) {
        if (!entries.containsKey(entry.hashCode())) {
            if (entries.size() == this.size) {
                T removed = queue.poll();
                entries.remove(removed.hashCode());
            }
            entries.put(entry.hashCode(), entry);
            queue.add(entry);
        } else {
            // Already contains the entry, do nothing
        }
    }
}
